package com.sc.dao.impl;

import com.sc.dao.PurchaseDao;
import com.sc.model.Book;
import com.sc.model.Purchase;
import com.sc.util.DBUtil;
import com.sc.vo.Page;
import com.sc.vo.PurchaseVo;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PurchaseDaoImpl implements PurchaseDao {

    @Override
    public int insert(Purchase purchase) throws SQLException {
        QueryRunner queryRunner = new QueryRunner();
        String sql = "insert into purchase(login_id,book_id,count,unit_price,purchase_date) values(?,?,?,?,?)";
        Object[] params = {purchase.getLoginId(),purchase.getBookId(),purchase.getCount(),purchase.getUnitPrice(),purchase.getPurchaseDate()};
        int count = 0;
        count = queryRunner.update(DBUtil.getThreadCon(),sql,params);
        return count;
    }

    @Override
    public List<PurchaseVo> queryByConditionAndPage(PurchaseVo purchaseVo, Page page) throws SQLException {
        QueryRunner queryRunner = new QueryRunner();
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList<>();
        sql.append("select a.id,d.username as purchaser,e.vendor,e.name as bookName,a.count,a.unit_price as price,a.purchase_date as purchaseDate");
        sql.append(" from purchase a");
        sql.append(" left join (select b.id,c.username from login b inner join user c on b.user_id=c.id) d");
        sql.append(" on a.login_id=d.id");
        sql.append(" left join book e");
        sql.append(" on a.book_id=e.id where 1=1");
        if (purchaseVo.getPurchaser() != null && !"".equals(purchaseVo.getPurchaser())){
            sql.append(" and d.username like '%' ? '%'");
            paramList.add(purchaseVo.getPurchaser());
        }
        if (purchaseVo.getStartDate() != null){
            sql.append(" and a.purchase_date >= ?");
            paramList.add(purchaseVo.getStartDate());
        }
        if (purchaseVo.getEndDate() != null){
            sql.append(" and a.purchase_date <= ?");
            paramList.add(purchaseVo.getEndDate());
        }
        sql.append(" limit ?,?");
        paramList.add((page.getCurrentPage() - 1) * Page.PAGESIZE);
        paramList.add(Page.PAGESIZE);
        Object[] params = paramList.toArray();
        List<PurchaseVo> list;
        list = queryRunner.query(DBUtil.getThreadCon(), sql.toString(), new BeanListHandler<>(PurchaseVo.class), params);
        return list;
    }

    @Override
    public int totalByCondition(PurchaseVo purchaseVo) throws SQLException {
        QueryRunner queryRunner = new QueryRunner();
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList<>();
        sql.append("select count(1)");
        sql.append(" from purchase a");
        sql.append(" left join (select b.id,c.username from login b inner join user c on b.user_id=c.id) d");
        sql.append(" on a.login_id=d.id");
        sql.append(" left join book e");
        sql.append(" on a.book_id=e.id where 1=1");
        if (purchaseVo.getPurchaser() != null && !"".equals(purchaseVo.getPurchaser())){
            sql.append(" and d.username like '%' ? '%'");
            paramList.add(purchaseVo.getPurchaser());
        }
        if (purchaseVo.getStartDate() != null){
            sql.append(" and a.purchase_date >= ?");
            paramList.add(purchaseVo.getStartDate());
        }
        if (purchaseVo.getEndDate() != null){
            sql.append(" and a.purchase_date <= ?");
            paramList.add(purchaseVo.getEndDate());
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
