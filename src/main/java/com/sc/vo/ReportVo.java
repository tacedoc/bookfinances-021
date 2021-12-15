package com.sc.vo;

import java.util.Date;

public class ReportVo {

    private Integer id;
    private Double monthGet;
    private Double monthOut;
    private Date reportDate;
    private String profitAndLoss;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getMonthGet() {
        return monthGet;
    }

    public void setMonthGet(Double monthGet) {
        this.monthGet = monthGet;
    }

    public Double getMonthOut() {
        return monthOut;
    }

    public void setMonthOut(Double monthOut) {
        this.monthOut = monthOut;
    }

    public Date getReportDate() {
        return reportDate;
    }

    public void setReportDate(Date reportDate) {
        this.reportDate = reportDate;
    }

    public String getProfitAndLoss() {
        return profitAndLoss;
    }

    public void setProfitAndLoss(String profitAndLoss) {
        this.profitAndLoss = profitAndLoss;
    }
}
