package com.engineer.result.service;

import com.engineer.result.service.base.BaseService;
import org.apache.ibatis.annotations.Param;

public interface ChargingService<T> extends BaseService<T> {

    void setVariate_id(Long variate_id);
}
