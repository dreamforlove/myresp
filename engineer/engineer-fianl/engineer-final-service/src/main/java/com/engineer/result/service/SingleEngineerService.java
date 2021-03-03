package com.engineer.result.service;

import com.engineer.result.service.base.BaseService;
import org.apache.ibatis.annotations.Param;

public interface SingleEngineerService<T> extends BaseService<T> {

    void setNode_id(Long node_id);
    void setEngi_proj_id(Long engi_proj_id);
}
