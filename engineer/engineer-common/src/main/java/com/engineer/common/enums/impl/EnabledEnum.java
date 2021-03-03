package com.engineer.common.enums.impl;

import com.engineer.common.enums.ConstEnum;

/**
 * 禁用启用状态值枚举类
 * @author Lemon
 * @date 2020/3/5 19:07
 */
public enum  EnabledEnum implements ConstEnum {

    /**
     * DISABLED：状态值
     * ENABLED：描述
     */
    DISABLED(0, "禁用"),
    ENABLED(1, "启用")
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

    EnabledEnum(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }
}
