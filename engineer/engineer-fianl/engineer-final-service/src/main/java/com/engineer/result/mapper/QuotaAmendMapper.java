package com.engineer.result.mapper;

import com.engineer.result.pojo.QuotaAmend;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

public interface QuotaAmendMapper extends BaseMapper<QuotaAmend>{
    @Update("UPDATE `engineer-user`.`zy_quota_amend_table` SET `proj_id`=NULL WHERE `proj_id`=#{proj_id}")
    int setProj_id(@Param("proj_id")Long proj_id);
}
