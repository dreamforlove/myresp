package com.engineer.user.mapper;

import com.engineer.common.mapper.BaseMapper;
import com.engineer.user.pojo.Location;
import com.engineer.user.vo.LocationTree;
import com.engineer.user.vo.LocationVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LocationMapper extends BaseMapper<Location> {

    /**
     * 获取最高等级地区列表
     *
     * @param highLevel
     * @return
     */
    List<LocationTree> getHighLocation(@Param("highLevel") int highLevel);

    /**
     * 获取地区表最高等级
     *
     * @return
     */
    int getHighLevelCount();

    /**
     * 获取地区最低等级
     *
     * @return
     */
    int getLowLevelCount();

    /**
     * 根据地区父id查询子地区信息
     *
     * @param pid
     * @return
     */
    List<LocationTree> getLocationChildrenByPid(@Param("pid") int pid);

    /**
     * 获取最高等级地区列表详情
     *
     * @param highLevel
     * @return
     */
    List<LocationVo> getHighLocationDetail(@Param("highLevel") int highLevel);

    /**
     * 根据地区父id查询子地区详细信息
     *
     * @param pid
     * @return
     */
    List<LocationVo> getLocationChildrenListByPid(@Param("pid") Integer pid);

    /**
     * 获取省级行政区数量
     * @return
     */
    Long getProvinceTotal();
}