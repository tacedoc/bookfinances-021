package com.sc.dao.impl;

import com.sc.dao.BorrowDao;
import com.sc.model.Book;
import com.sc.model.Borrow;
import com.sc.util.DBUtil;
import com.sc.vo.BorrowVo;
import com.sc.vo.Page;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BorrowDaoImpl implements BorrowDao {

    @Override
    public int insert(Borrow borrow) throws SQLException {
        QueryRunner queryRunner = new QueryRunner();
        String sql = "insert into borrow(login_id,book_id,begin_date,promise_day,end_date,cost,return_status) value(?,?,?,?,?,?,?)";
        Object[] params = {borrow.getLoginId(), borrow.getBookId(), borrow.getBeginDate(), borrow.getPromiseDay(),
                borrow.getEndDate(), borrow.getCost(), borrow.getReturnStatus()};
        int count = 0;
        count = queryRunner.update(DBUtil.getThreadCon(), sql, params);
        return count;
    }

    @Override
    public int update(Borrow borrow) throws SQLException {
        QueryRunner queryRunner = new QueryRunner();
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList<>();
        sql.append("update borrow set id=?");
        paramList.add(borrow.getId());
        if (borrow.getLoginId() != null) {
            sql.append(",login_id=?");
            paramList.add(borrow.getLoginId());
        }
        if (borrow.getBookId() != null) {
            sql.append(",book_id=?");
            paramList.add(borrow.getBookId());
        }
        if (borrow.getBeginDate() != null) {
            sql.append(",begin_date=?");
            paramList.add(borrow.getBeginDate());
        }
        if (borrow.getPromiseDay() != null) {
            sql.append(",promise_day=?");
            paramList.add(borrow.getPromiseDay());
        }
        if (borrow.getEndDate() != null) {
            sql.append(",end_date=?");
            paramList.add(borrow.getEndDate());
        }
        if (borrow.getCost() != null) {
            sql.append(",cost=?");
            paramList.add(borrow.getCost());
        }
        if (borrow.getReturnStatus() != null) {
            sql.append(",return_status=?");
            paramList.add(borrow.getReturnStatus());
        }
        sql.append(" where id=?");
        paramList.add(borrow.getId());
        Object[] params = paramList.toArray();
        int count = 0;
        count = queryRunner.update(DBUtil.getThreadCon(), sql.toString(), params);
        return count;
    }

    @Override
    public Borrow queryById(Integer id) throws SQLException {
        QueryRunner queryRunner = new QueryRunner();
        String sql = "select id,login_id as loginId,book_id as bookId,begin_date as beginDate,promise_day as promiseDay,end_date as endDate,cost,return_status as returnStatus from borrow where id=?";
        Object[] params = {id};
        Borrow borrow = null;
        borrow = queryRunner.query(DBUtil.getThreadCon(), sql, new BeanHandler<>(Borrow.class), params);
        return borrow;
    }

    @Override
    public List<Borrow> queryByLoginId(Integer loginId) throws SQLException {
        QueryRunner queryRunner = new QueryRunner();
        String sql = "select id,login_id as loginId,book_id as bookId,begin_date as beginDate,promise_day as promiseDay,end_date as endDate,cost,return_status as returnStatus from borrow where login_id=? and end_date is null";
        Object[] params = {loginId};
        List<Borrow> list = null;
        list = queryRunner.query(DBUtil.getThreadCon(), sql, new BeanListHandler<>(Borrow.class), params);
        return list;
    }

    @Override
    public List<BorrowVo> queryByConditionAndPage(BorrowVo borrowVo, Page page) throws SQLException {
        QueryRunner queryRunner = new QueryRunner();
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList<>();
        sql.append("select a.id,d.username,e.name as bookName,a.begin_date as borrowDate,a.promise_day as promiseDay,a.end_date as returnDate,a.cost,a.return_status as returnStatus");
        sql.append(" from borrow a");
        sql.append(" left join (select b.id,c.username from login b inner join user c on b.user_id=c.id) d");
        sql.append(" on a.login_id=d.id");
        sql.append(" left join book e");
        sql.append(" on a.book_id=e.id where 1=1");
        if (borrowVo.getLoginId() != null){
            sql.append(" and a.login_id = ?");
            paramList.add(borrowVo.getLoginId());
        }
        if (borrowVo.getReturnStatus() != null){
            sql.append(" and a.return_status = ?");
            paramList.add(borrowVo.getReturnStatus());
        }
        if (borrowVo.getStartDate() != null){
            sql.append(" and a.begin_date >= ?");
            paramList.add(borrowVo.getStartDate());
        }
        if (borrowVo.getEndDate() != null){
            sql.append(" and a.begin_date <= ?");
            paramList.add(borrowVo.getEndDate());
        }
        sql.append(" limit ?,?");
        paramList.add((page.getCurrentPage() - 1) * Page.PAGESIZE);
        paramList.add(Page.PAGESIZE);
        Object[] params = paramList.toArray();
        List<BorrowVo> list;
        list = queryRunner.query(DBUtil.getThreadCon(), sql.toString(), new BeanListHandler<>(BorrowVo.class), params);
        return list;
    }

    @Override
    public int totalByCondition(BorrowVo borrowVo) throws SQLException {
        QueryRunner queryRunner = new QueryRunner();
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList<>();
        sql.append("select count(1)");
        sql.append(" from borrow where 1=1");
        if (borrowVo.getLoginId() != null){
            sql.append(" and login_id = ?");
            paramList.add(borrowVo.getLoginId());
        }
        if (borrowVo.getReturnStatus() != null){
            sql.append(" and return_status = ?");
            paramList.add(borrowVo.getReturnStatus());
        }
        if (borrowVo.getStartDate() != null){
            sql.append(" and begin_date >= ?");
            paramList.add(borrowVo.getStartDate());
        }
        if (borrowVo.getEndDate() != null){
            sql.append(" and begin_date <= ?");
            paramList.add(borrowVo.getEndDate());
        }
        Object[] params = paramList.toArray();
        Long count = null;
        count = queryRunner.query(DBUtil.getThreadCon(), sql.toString(), new ScalarHandler<>(), params);
        if (count != null) {
            return count.intValue();
        }
        return 0;
    }
}
