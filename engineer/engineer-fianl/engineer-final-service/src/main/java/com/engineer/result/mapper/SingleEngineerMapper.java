package com.engineer.result.mapper;

import com.engineer.result.pojo.SingleEngineer;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.common.Mapper;

public interface SingleEngineerMapper extends BaseMapper<SingleEngineer> {
    @Update("UPDATE `engineer-user`.`zy_single_engi_information_sheet` SET `node_id`=NULL WHERE `node_id`=#{node_id}")
    int setNode_id(@Param("node_id")Long node_id);

    @Update("UPDATE `engineer-user`.`zy_single_engi_information_sheet` SET `engi_proj_id`=NULL WHERE `engi_proj_id`=#{engi_proj_id}")
    int setEngi_proj_id(@Param("engi_proj_id")Long engi_proj_id);

}
