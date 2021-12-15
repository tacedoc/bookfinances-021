package com.sc.model;

import java.util.Date;

public class Report {
    private Integer id;

    private Date reportDate;

    private Double monthOut;

    private Double monthGet;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getReportDate() {
        return reportDate;
    }

    public void setReportDate(Date reportDate) {
        this.reportDate = reportDate;
    }

    public Double getMonthOut() {
        return monthOut;
    }

    public void setMonthOut(Double monthOut) {
        this.monthOut = monthOut;
    }

    public Double getMonthGet() {
        return monthGet;
    }

    public void setMonthGet(Double monthGet) {
        this.monthGet = monthGet;
    }
}