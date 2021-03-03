package com.engineer.result.mapper;

import com.engineer.result.pojo.BillQuantity;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

public interface BillQuantityMapper extends BaseMapper<BillQuantity> {

    @Update("UPDATE `engineer-user`.`zy_bill_quantities` SET `unit_engi_id`=NULL WHERE `unit_engi_id`=#{unit_engi_id}")
    int setUnit_engi_id(@Param("unit_engi_id")Long unit_engi_id);

}
