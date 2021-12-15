package com.sc.listener;

import com.sc.model.Report;
import com.sc.service.ReportService;
import com.sc.service.impl.ReportServiceImpl;
import com.sc.vo.Page;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.*;

@WebListener()
public class ReportTaskListener implements ServletContextListener{

    public ReportTaskListener() {
    }

    public void contextInitialized(ServletContextEvent sce) {
/*        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        calendar.set(year, month, day, 8, 00, 00);
        // 当天8点（默认执行时间）
        Date defaultdate = calendar.getTime();
        Date sendDate = null;
        if (defaultdate.before(new Date())) {
            // 若当前时间超过了defaultdate时间，当天再也不执行，则将执行时间sendDate改成明天8点
            calendar.add(Calendar.DATE, 1);
            sendDate = calendar.getTime();
        }else {
            // 若当前时间没有超过defaultdate时间，则将执行时间sendDate改成defaultdate
            sendDate = defaultdate;
        }*/

        /**
         * ----------------每分钟任务 ----------------
         * 启动服务器后，若此时时间没过8点，等待。到了8点自动执行一次，1分钟后再执行一次，周而复始
         * 启动服务器后，若此时时间超过8点，会马上执行一次，等到1分钟后再次执行一次，周而复始 到了次日，不会再判断是不是8点，这个开始时间，只会判断一次而已
         */
/*        Timer qTimer = new Timer();
        qTimer.schedule(new TimerTask() {

            @Override
            public void run() {
                System.out.println("每分钟任务已执行");
                // TODO 写你的逻辑
            }
        }, 0L,  60 * 1000);// 定时每1分钟
        System.out.println("每分钟定时发送Xml信息监听--已启动！");*/

        /**
         * ----------------每日任务 ----------------
         * 启动服务器后，若此时时间没过8点，等待。到了8点自动执行一次，24小时后（次日8点）再执行一次，周而复始
         * 启动服务器后，若此时时间已经超过8点，则等到24小时后（次日8点）才执行一次，周而复始
         */
        /*Timer dTimer = new Timer();
        dTimer.schedule(new TimerTask() {

            @Override
            public void run() {
                System.out.println("每日任务已经执行");
                // TODO 写你的逻辑
                //select sum(trade_amount) from trade_flow where year(trade_date)='2021' and month(trade_date)='11'
            }
        }, sendDate, 24 * 60 * 60 * 1000);// 定时24小时：24 * 60 * 60 * 1000
        System.out.println("每日定时发送Xml信息监听--已启动！");*/

        /**
         * ----------------每个月任务 ----------------
         * 启动服务器后，若此时时间没过8点，等待。到了8点自动执行判断是不是当前月份的1号，如果是则执行一次，
         * 24小时后（次日8点）再执行一次判断（每个月1号之后后的29天或30天后才会是下月1号，再执行一次），周而复始 启动服务器后，若此时时间已经超过8点，会马上执行一次，等到下个月1号再次执行一次，周而复始
         */
        Timer mTimer = new Timer();
        mTimer.schedule(new TimerTask() {

            @Override
            public void run() {
                Calendar c = Calendar.getInstance();
                int day = c.get(Calendar.DAY_OF_MONTH);
                System.out.println("月任务 判断中");
                if (day == 1) {
                    // 天天执行，若为每个月1号才执行
                    System.out.println(new Date() + "月任务执行已开始-------------");
                    // TODO 写你的逻辑
                    int month = c.get(Calendar.MONTH);//Calender的月从0开始
                    int year = c.get(Calendar.YEAR);
                    ReportService reportService = new ReportServiceImpl();

                    if (month == 0){
                        --year;
                        month = 12;
                    }
                    List<Report> reportExist = reportService.showReportList(String.valueOf(year), String.valueOf(month), new Page());

                    if (reportExist != null && reportExist.size()>0){
                        System.out.println("已生成过月度报表！");
                        return;
                    }
                    Double month_get = reportService.sumByPayee("图书馆", String.valueOf(year), String.valueOf(month));
                    Double totalAmount = reportService.sumByPayee(null,String.valueOf(year), String.valueOf(month));
                    Double month_out = totalAmount - month_get;
                    //Date reportDate = new Date(year,month,1);
                    Calendar reportCalender = new GregorianCalendar(year,month,1);

                    Report report =new Report();
                    report.setMonthGet(month_get);
                    report.setMonthOut(month_out);
                    report.setReportDate(reportCalender.getTime());

                    reportService.insert(report);
                    System.out.println("月任务已执行完毕------------");
                }

            }
        }, 0L, 24 * 60 * 60 * 1000);// 24 * 60 * 60 * 1000每天执行一次检查

        System.out.println("月度报表定时任务监听--已启动！");
    }

    public void contextDestroyed(ServletContextEvent sce) {
      System.out.println("月度报表定时任务监听--已关闭！");
    }
}
