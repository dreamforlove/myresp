package com.engineer.result.mapper;

import com.engineer.result.pojo.Charging;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.common.Mapper;

public interface ChargingMapper extends BaseMapper<Charging> {
    @Update("UPDATE `engineer-user`.`zy_charging` SET `variate_id`=NULL WHERE `variate_id`=#{variate_id}")
    int setVariate_id(@Param("variate_id")Long variate_id);

}
