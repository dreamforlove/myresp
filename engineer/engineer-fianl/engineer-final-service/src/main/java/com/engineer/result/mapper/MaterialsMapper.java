package com.engineer.result.mapper;

import com.engineer.result.pojo.Materials;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.common.Mapper;

public interface MaterialsMapper extends BaseMapper<Materials> {

    @Update("UPDATE `engineer-user`.`zy_lmm_summary_sheet` SET `diff_id`=NULL WHERE `diff_id`=#{diff_id}")
    int setDifference_id(@Param("diff_id")Long diff_id);
    @Update("UPDATE `engineer-user`.`zy_lmm_summary_sheet` SET `unit_engi_id`=NULL WHERE `unit_engi_id`=#{unit_engi_id}")
    int setUnit_engi_id(@Param("unit_engi_id")Long unit_engi_id);

}
