package com.sc.service.impl;

import com.sc.dao.BorrowDao;
import com.sc.dao.impl.BorrowDaoImpl;
import com.sc.model.*;
import com.sc.service.*;
import com.sc.util.DBUtil;
import com.sc.vo.BorrowVo;
import com.sc.vo.Page;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class BorrowServiceImpl implements BorrowService {

    private BorrowDao borrowDao = new BorrowDaoImpl();
    private BookService bookService = new BookServiceImpl();
    private LoginService loginService = new LoginServiceImpl();
    private UserService userService = new UserServiceImpl();
    private TradeFlowService tradeFlowService = new TradeFlowServiceImpl();

    @Override
    public Boolean insert(Borrow borrow) {
        Connection connection = null;
        try {
            connection = DBUtil.getThreadCon();
            DBUtil.startTransaction();

            //将原来的图书信息查询出来后，更改剩余库存
            Book book = bookService.queryById(borrow.getBookId());
            book.setRemainCount(book.getRemainCount() - 1);
            bookService.update(book);
            borrowDao.insert(borrow);

            DBUtil.commitTransaction();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                //将连接返回给连接池,同时从ThreadLocal中移除
                DBUtil.closeConnection();
            }
        }
        return true;
    }

    @Override
    public Boolean update(Borrow borrow) {
        try {
            borrowDao.update(borrow);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.closeConnection();
        }
        return true;
    }

    @Override
    public Boolean bookReturn(Integer borrowId) {
        Connection connection = null;
        try {
            connection = DBUtil.getThreadCon();
            DBUtil.startTransaction();

            Borrow borrow = queryById(borrowId);
            Book book = bookService.queryById(borrow.getBookId());
            boolean overdueVerify = overdueVerify(borrow.getBeginDate(), borrow.getPromiseDay());

            //计算总的借阅费用
            long oneDaySec = 24 * 60 * 60 * 1000; //一天的毫秒数
            Integer borrowDays = null; //实际借阅天数
            if (overdueVerify) {
                //根据借阅记录判断是否逾期，逾期部分借阅费用翻倍
                long borrowSec = new Date().getTime() - borrow.getBeginDate().getTime();
                if (borrowSec % oneDaySec == 0) {
                    borrowDays = (int) (borrowSec / oneDaySec);
                } else {
                    borrowDays = (int) (borrowSec / oneDaySec) + 1;
                }
                borrow.setCost(book.getBorrowPrice() * borrow.getPromiseDay() +
                        2 * book.getBorrowPrice() * (borrowDays - borrow.getPromiseDay())
                );
                borrow.setReturnStatus(3);
            } else {
                //如果没有逾期，就计算从借阅开始到现在的天数
                long borrowSec = new Date().getTime() - borrow.getBeginDate().getTime();
                if (borrowSec % oneDaySec == 0) {
                    borrowDays = (int) (borrowSec / oneDaySec);
                } else {
                    borrowDays = (int) (borrowSec / oneDaySec) + 1;
                }
                borrow.setCost(book.getBorrowPrice() * borrowDays);
                borrow.setReturnStatus(2);
            }
            borrow.setEndDate(new Date());

            //计算出总的借阅费用后，修改用户余额
            Login login = loginService.queryById(borrow.getLoginId());
            User user = userService.queryById(login.getUserId());
            if (user.getBalance() < borrow.getCost()){
                return false;
            }
            user.setBalance(user.getBalance() - borrow.getCost());
            userService.update(user);

            //修改图书库存信息
            book.setRemainCount(book.getRemainCount() + 1);
            bookService.update(book);

            //修改借阅记录
            update(borrow);

            //最后增添一条流水记录
            TradeFlow tradeFlow = new TradeFlow();
            tradeFlow.setPayer(borrow.getLoginId());
            tradeFlow.setPayee("图书馆");
            tradeFlow.setTradeType("图书借阅");
            tradeFlow.setTradeAmount(borrow.getCost());
            tradeFlow.setTradeDate(new Date());
            tradeFlowService.insert(tradeFlow);

            DBUtil.commitTransaction();
        }catch (SQLException e) {
            e.printStackTrace();
            try {
                DBUtil.rollBackTransaction();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            if (connection != null) {
                //将连接返回给连接池,同时从ThreadLocal中移除
                DBUtil.closeConnection();
            }
        }
        return true;
    }

    @Override
    public Boolean bookPostpone(Integer borrowId, Integer postponeDays) {
        Borrow borrow = queryById(borrowId);
        //判断是否逾期，若已逾期，则延期失败，需先归还图书
        if (overdueVerify(borrow.getBeginDate(),borrow.getPromiseDay())){
            return false;
        }else {
            //这里借助之前的借阅验证，先产生一个借阅对象，借阅天数为延期天数，但并不插入借阅记录
            Borrow tmpBorrow = new Borrow();
            tmpBorrow.setLoginId(borrow.getLoginId());
            tmpBorrow.setBookId(borrow.getBookId());
            tmpBorrow.setPromiseDay(postponeDays);
            Boolean verify = borrowVerify(tmpBorrow);
            //验证通过则延期
            if (verify){
                borrow.setPromiseDay(borrow.getPromiseDay() + postponeDays);
                update(borrow);
                return true;
            }else {
                return false;
            }
        }
    }

    @Override
    public Boolean bookDamage(Integer borrowId) {
        Connection connection = null;
        try {
            connection = DBUtil.getThreadCon();
            DBUtil.startTransaction();

            Borrow borrow = queryById(borrowId);
            Book book = bookService.queryById(borrow.getBookId());
            boolean overdueVerify = overdueVerify(borrow.getBeginDate(), borrow.getPromiseDay());

            //计算总的费用,这里会多加图书的单价
            long oneDaySec = 24 * 60 * 60 * 1000; //一天的毫秒数
            Integer borrowDays = null; //实际借阅天数
            if (overdueVerify) {
                //根据借阅记录判断是否逾期，逾期部分借阅费用翻倍
                long borrowSec = new Date().getTime() - borrow.getBeginDate().getTime();
                if (borrowSec % oneDaySec == 0) {
                    borrowDays = (int) (borrowSec / oneDaySec);
                } else {
                    borrowDays = (int) (borrowSec / oneDaySec) + 1;
                }
                borrow.setCost(book.getBorrowPrice() * borrow.getPromiseDay() +
                        2 * book.getBorrowPrice() * (borrowDays - borrow.getPromiseDay()) +
                        book.getUnitPrice()
                );
            } else {
                //如果没有逾期，就计算从借阅开始到现在的天数
                long borrowSec = new Date().getTime() - borrow.getBeginDate().getTime();
                if (borrowSec % oneDaySec == 0) {
                    borrowDays = (int) (borrowSec / oneDaySec);
                } else {
                    borrowDays = (int) (borrowSec / oneDaySec) + 1;
                }
                borrow.setCost(book.getBorrowPrice() * borrowDays +
                        book.getUnitPrice()
                        );
            }
            borrow.setReturnStatus(4);
            borrow.setEndDate(new Date());

            //计算出总的借阅费用后，修改用户余额
            Login login = loginService.queryById(borrow.getLoginId());
            User user = userService.queryById(login.getUserId());
            if (user.getBalance() < borrow.getCost()){
                return false;
            }
            user.setBalance(user.getBalance() - borrow.getCost());
            userService.update(user);

            //修改图书总库存信息
            book.setTotalCount(book.getTotalCount() - 1);
            bookService.update(book);

            //修改借阅记录
            update(borrow);

            //最后增添一条流水记录
            TradeFlow tradeFlow = new TradeFlow();
            tradeFlow.setPayer(borrow.getLoginId());
            tradeFlow.setPayee("图书馆");
            tradeFlow.setTradeType("图书报损");
            tradeFlow.setTradeAmount(borrow.getCost());
            tradeFlow.setTradeDate(new Date());
            tradeFlowService.insert(tradeFlow);

            DBUtil.commitTransaction();
        }catch (SQLException e) {
            e.printStackTrace();
            try {
                DBUtil.rollBackTransaction();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            if (connection != null) {
                //将连接返回给连接池,同时从ThreadLocal中移除
                DBUtil.closeConnection();
            }
        }
        return true;
    }

    @Override
    public Borrow queryById(Integer id) {
        Borrow borrow = null;
        try {
            borrow = borrowDao.queryById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.closeConnection();
        }
        return borrow;
    }

    @Override
    public Boolean borrowVerify(Borrow borrow) {

        try {
            Book nowBook = bookService.queryById(borrow.getBookId());
            if (nowBook.getRemainCount() < 1) {
                return false;
            }
            //取出用户还未归还的借阅记录
            List<Borrow> borrowList = borrowDao.queryByLoginId(borrow.getLoginId());
            double totalCost = 0;
            for (Borrow userBorrow : borrowList) {
                //如果有未处理的逾期借阅记录，则不予通过借阅验证
                if (overdueVerify(userBorrow.getBeginDate(), userBorrow.getPromiseDay())) {
                    return false;
                }
                Book book = bookService.queryById(userBorrow.getBookId());
                double cost = book.getBorrowPrice() * userBorrow.getPromiseDay();
                //计算正在借阅图书的总花销
                totalCost += cost;
            }
            //最后再加上本次借阅所需的费用
            totalCost += nowBook.getBorrowPrice() * borrow.getPromiseDay();
            Login login = loginService.queryById(borrow.getLoginId());
            User user = userService.queryById(login.getUserId());

            return user.getBalance() >= totalCost;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.closeConnection();
        }
        return false;
    }

    @Override
    public List<BorrowVo> showBorrowList(BorrowVo borrowVo, Page page) {
        List<BorrowVo> list = null;
        try {
            list = borrowDao.queryByConditionAndPage(borrowVo, page);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Page createPageInfo(BorrowVo borrowVo) {
        Page page = new Page();
        try {
            page.setTotalCount(borrowDao.totalByCondition(borrowVo));
            if (page.getTotalCount() % Page.PAGESIZE == 0) {
                page.setTotalPageNum(page.getTotalCount() / Page.PAGESIZE);
            } else {
                page.setTotalPageNum(page.getTotalCount() / Page.PAGESIZE + 1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.closeConnection();
        }
        return page;
    }

    /**
     * 逾期判断
     * @return 逾期返回true，否则返回false
     */
    public boolean overdueVerify(Date beginDate, Integer promiseDay) {
        // 1970年1月1日至约定归还日期的毫秒数
        long promiseDate = beginDate.getTime() + promiseDay * 24 * 60 * 60 * 1000;
        // 1970年1月1日至现在的毫秒数
        long nowDate = new Date().getTime();

        return nowDate > promiseDate;
    }
}
