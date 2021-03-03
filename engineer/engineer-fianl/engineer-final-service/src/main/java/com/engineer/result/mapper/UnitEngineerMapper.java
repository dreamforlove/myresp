package com.engineer.result.mapper;

import com.engineer.result.pojo.UnitEngineer;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.common.Mapper;

public interface UnitEngineerMapper extends BaseMapper<UnitEngineer> {
    @Update("UPDATE `engineer-user`.`zy_unit_engi_information_sheet` SET `difference_id`=NULL WHERE `difference_id`=#{difference_id}")
    int setDifference_id(@Param("difference_id")Long difference_id);

    @Update("UPDATE `engineer-user`.`zy_unit_engi_information_sheet` SET `charging_id`=NULL WHERE `charging_id`=#{charging_id}")
    int setCharging_id(@Param("charging_id")Long charging_id);

    @Update("UPDATE `engineer-user`.`zy_unit_engi_information_sheet` SET `node_id`=NULL WHERE `node_id`=#{node_id}")
    int setNode_id(@Param("node_id")Long node_id);

    @Update("UPDATE `engineer-user`.`zy_unit_engi_information_sheet` SET `single_engi_id`=NULL WHERE `single_engi_id`=#{single_engi_id}")
    int setSingle_engi_id(@Param("single_engi_id")Long single_engi_id);

}
