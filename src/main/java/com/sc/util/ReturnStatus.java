package com.sc.util;

public enum ReturnStatus {

    /**
     * Borrowing and not overdue 借阅中且未逾期
     */
    BANO(0),

    /**
     * Borrowing and overdue 借阅中且已逾期
     */
    BAO(1),


    /**
     * 已归还且之前未逾期
     */
    RANO(2),

    /**
     * 已归还且之前逾期
     */
    RAO(3),

    /**
     * damaged 报损
     */
    DAMAGED(4);

    private int value;

    ReturnStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
