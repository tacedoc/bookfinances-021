package com.sc.service;

import com.sc.model.Book;
import com.sc.model.Purchase;
import com.sc.vo.Page;
import com.sc.vo.PurchaseVo;

import java.util.List;

public interface PurchaseService {

    /**
     * 添加一条外购记录的同时，添加购买的图书、交易流水记录
     * @param book
     * @param purchase
     * @return
     */
    Boolean addPurchaseRecord(Book book, Purchase purchase);

    /**
     * 根据条件查询外购信息
     * @param purchaseVo
     * @param page
     * @return
     */
    List<PurchaseVo> showPurchaseVoList(PurchaseVo purchaseVo, Page page);

    /**
     * 根据条件设置页码等信息
     * @param purchaseVo
     * @return
     */
    Page createPageInfo(PurchaseVo purchaseVo);
}
