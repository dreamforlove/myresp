package com.engineer.result.service;

import com.engineer.result.service.base.BaseService;
import org.apache.ibatis.annotations.Param;

public interface QuotaListService<T> extends BaseService<T> {

    void setProj_id(Long proj_id);
}
