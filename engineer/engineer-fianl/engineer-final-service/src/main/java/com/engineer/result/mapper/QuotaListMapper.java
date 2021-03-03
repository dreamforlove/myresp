package com.engineer.result.mapper;

import com.engineer.result.pojo.QuotaList;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

public interface QuotaListMapper extends BaseMapper<QuotaList> {

    @Update("UPDATE `engineer-user`.`zy_quota_list` SET `proj_id`=NULL WHERE `proj_id`=#{proj_id}")
    int setProj_id(@Param("proj_id")Long proj_id);
}
