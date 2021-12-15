package com.sc.listener;

import com.sc.model.Borrow;
import com.sc.service.BorrowService;
import com.sc.service.impl.BorrowServiceImpl;
import com.sc.vo.BorrowVo;
import com.sc.vo.Page;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

@WebListener()
public class BorrowerStatusListener implements ServletContextListener{

    public BorrowerStatusListener() {
    }

    public void contextInitialized(ServletContextEvent sce) {
        Timer qTimer = new Timer();
        qTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("每分钟任务已执行");
                BorrowService borrowService = new BorrowServiceImpl();
                BorrowVo borrowVo = new BorrowVo();
                borrowVo.setReturnStatus(0);
                List<BorrowVo> borrowVoList = borrowService.showBorrowList(borrowVo, new Page());
                for (BorrowVo bv : borrowVoList) {
                    boolean isOverdue = borrowService.overdueVerify(bv.getBorrowDate(), bv.getPromiseDay());
                    if (isOverdue){
                        System.out.println("逾期的借阅记录ID为："+bv.getId());
                        Borrow borrow = new Borrow();
                        borrow.setId(bv.getId());
                        borrow.setReturnStatus(1);
                        borrowService.update(borrow);
                    }
                }
            }
        }, 0L,  60 * 1000);// 0 ：立即执行，60 * 1000 ：每1分钟
        System.out.println("借阅状态监听--已启动！");
    }

    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("借阅状态监听--已关闭");
    }
}
