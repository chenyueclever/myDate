package com.cy.text;

import java.util.List;

/**
 * Created by gsb on 2017/3/20.
 */

public class DateListBean {
    public static final int ITEM = 0;
    public static final int SECTION = 1;
    private int type;
    private int year;
    private int month;
    private List<DateListDayBean> list;

    public DateListBean(int year, int month) {
        this.year = year;
        this.month = month;

    }

    public DateListBean(int year, int month,int type,List<DateListDayBean> list) {
        this.year = year;
        this.month = month;
        this.type=type;
        this.list=list;
    }

    public List<DateListDayBean> getList() {
        return list;
    }

    public void setList(List<DateListDayBean> list) {
        this.list = list;
    }

    public static int getITEM() {
        return ITEM;
    }

    public static int getSECTION() {
        return SECTION;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }
}
