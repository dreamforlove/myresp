package com.engineer.result.service;

import com.engineer.result.service.base.BaseService;
import org.apache.ibatis.annotations.Param;

public interface BillQuantityService<T>  extends BaseService<T> {

    void setUnit_engi_id(Long unit_engi_id);
}
