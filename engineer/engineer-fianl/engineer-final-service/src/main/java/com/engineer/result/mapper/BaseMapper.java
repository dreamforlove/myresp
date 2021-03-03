package com.engineer.result.mapper;

import tk.mybatis.mapper.additional.insert.InsertListMapper;
import tk.mybatis.mapper.annotation.RegisterMapper;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.special.InsertUseGeneratedKeysMapper;
@RegisterMapper
public interface BaseMapper<T> extends Mapper<T>, InsertListMapper<T>, InsertUseGeneratedKeysMapper<T> {
}
