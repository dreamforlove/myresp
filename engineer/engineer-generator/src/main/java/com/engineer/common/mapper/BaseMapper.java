package com.engineer.common.mapper;

import tk.mybatis.mapper.additional.insert.InsertListMapper;
import tk.mybatis.mapper.annotation.RegisterMapper;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author Lemon
 * @date 2019/9/8 16:25
 */
@RegisterMapper
public interface BaseMapper<T> extends Mapper<T>, InsertListMapper<T> {
}
