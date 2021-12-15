package com.sc.dao.impl;

import com.sc.dao.FactoryBookDao;
import com.sc.model.FactoryBook;
import com.sc.util.DBUtil;
import com.sc.vo.Page;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FactoryBookDaoImpl implements FactoryBookDao {

    @Override
    public List<FactoryBook> queryByConditionAndPage(FactoryBook factoryBook, Page page) throws SQLException {
        QueryRunner queryRunner = new QueryRunner();
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList<>();
        sql.append("select id,name,author,unit_price as unitPrice,factory_name as factoryName from factory_book where 1=1");
        if (factoryBook.getName() != null && !"".equals(factoryBook.getName())) {
            sql.append(" and name like '%' ? '%'");
            paramList.add(factoryBook.getName());
        }
        if (factoryBook.getAuthor() != null && !"".equals(factoryBook.getAuthor())) {
            sql.append(" and author like '%' ? '%'");
            paramList.add(factoryBook.getAuthor());
        }
        if (factoryBook.getFactoryName() != null && !"".equals(factoryBook.getFactoryName())) {
            sql.append(" and factory_name like '%' ? '%'");
            paramList.add(factoryBook.getFactoryName());
        }
        sql.append(" limit ?,?");
        paramList.add((page.getCurrentPage() - 1) * Page.PAGESIZE);
        paramList.add(Page.PAGESIZE);
        Object[] params = paramList.toArray();
        List<FactoryBook> list;
        list = queryRunner.query(DBUtil.getThreadCon(), sql.toString(), new BeanListHandler<>(FactoryBook.class), params);
        return list;
    }

    @Override
    public int totalByCondition(FactoryBook factoryBook) throws SQLException {
        QueryRunner queryRunner = new QueryRunner();
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList<>();
        sql.append("select count(1) from factory_book where 1=1");
        if (factoryBook.getName() != null && !"".equals(factoryBook.getName())) {
            sql.append(" and name like '%' ? '%'");
            paramList.add(factoryBook.getName());
        }
        if (factoryBook.getAuthor() != null && !"".equals(factoryBook.getAuthor())) {
            sql.append(" and author like '%' ? '%'");
            paramList.add(factoryBook.getAuthor());
        }
        if (factoryBook.getFactoryName() != null && !"".equals(factoryBook.getFactoryName())) {
            sql.append(" and factory_name like '%' ? '%'");
            paramList.add(factoryBook.getFactoryName());
        }
        Object[] params = paramList.toArray();
        Long count = null;
        count = queryRunner.query(DBUtil.getThreadCon(), sql.toString(), new ScalarHandler<>(), params);
        if (count != null){
            return count.intValue();
        }
        return 0;
    }
}
