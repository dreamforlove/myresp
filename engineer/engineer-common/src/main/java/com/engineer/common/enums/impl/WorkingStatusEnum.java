package com.engineer.common.enums.impl;

import com.engineer.common.enums.ConstEnum;

/**
 * @author Lemon
 * @date 2020/4/5 18:41
 */
public enum WorkingStatusEnum implements ConstEnum {
    /**
     * DISABLED：状态值
     * ENABLED：描述
     */
    TO_BE_ACCEPTED(0, "待接受"),
    ACCEPTED(1, "已接受"),
    REFUSED(2, "已拒绝"),
    RESIGNED(3, "已离职")
    ;
    private int value;
    private String desc;

    @Override
    public int getValue() {
        return value;
    }

    @Override
    public String getDesc() {
        return desc;
    }

    WorkingStatusEnum(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }
}
