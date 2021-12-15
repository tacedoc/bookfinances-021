package com.sc.test;

import com.sc.dao.ReportDao;
import com.sc.dao.impl.ReportDaoImpl;
import com.sc.service.ReportService;
import com.sc.service.impl.ReportServiceImpl;
import org.junit.Test;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;

public class UtilTest {

    @Test
    public void test01(){
        //System.out.println(new Date().getTime());

        System.out.println(new Date(1636817903068L));
    }

    @Test
    public void test02(){
        System.out.println(doSome());
    }

    public boolean doSome(){
        boolean res = true;
        int a = 1;
        try{
            if (a > 2){
                System.out.println(a);
                return false;
            }
            ++a;
            System.out.println(a);
            throw new Exception();

        }catch (Exception e){
            System.out.println("catch");
        }finally {
            ++a;
            System.out.println(a);
        }
        System.out.println(a);
        return false;

    }

    @Test
    public void test03(){
        ReportService reportService = new ReportServiceImpl();
        Double money = reportService.sumByPayee(null,"2021","10");
        System.out.println(money);
    }

    @Test
    public void test04(){
        Calendar instance = Calendar.getInstance();
        int month = instance.get(Calendar.MONTH);
        int year = instance.get(Calendar.YEAR);
        System.out.println(month);
        System.out.println(year);
    }
}
