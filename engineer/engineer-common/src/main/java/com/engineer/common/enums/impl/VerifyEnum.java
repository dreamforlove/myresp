package com.engineer.common.enums.impl;

import com.engineer.common.enums.ConstEnum;

/**
 * @author Lemon
 * @date 2020/3/17 16:27
 */
public enum VerifyEnum implements ConstEnum {

    /**
     * DISABLED：状态值
     * ENABLED：描述
     */
    CHECK_PENDING(0, "待审核"),
    ADOPTED(1, "审核通过"),
    REFUSED(-1, "启用未通过")
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

    VerifyEnum(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }
}
