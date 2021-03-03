package com.engineer.result.service;

import com.engineer.result.service.base.BaseService;
import org.apache.ibatis.annotations.Param;

public interface UnitEngineerService<T> extends BaseService<T> {
    /*
    * 更新字段diff_id为空
    * */

    void setDifference_id(Long diff_id);

    void setCharging_id(Long charging_id);
    void setNode_id(Long node_id);

    void setSingle_engi_id(Long single_engi_id);
}
