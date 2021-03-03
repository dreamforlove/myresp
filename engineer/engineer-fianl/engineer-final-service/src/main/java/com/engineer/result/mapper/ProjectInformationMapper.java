package com.engineer.result.mapper;

import com.engineer.result.pojo.ProjectInformation;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

public interface ProjectInformationMapper extends BaseMapper<ProjectInformation> {

    @Update("UPDATE `engineer-user`.`zy_engi_proj_information_sheet` SET `node_id`=NULL WHERE `node_id`=#{node_id}")
    int setNode_id(@Param("node_id")Long node_id);
}
