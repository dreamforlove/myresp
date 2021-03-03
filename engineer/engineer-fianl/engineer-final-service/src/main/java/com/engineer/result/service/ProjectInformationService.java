package com.engineer.result.service;

import com.engineer.result.service.base.BaseService;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

public interface ProjectInformationService<T> extends BaseService<T> {
    List<T> selectAllButDate();

    void setNode_id(Long node_id);
}
