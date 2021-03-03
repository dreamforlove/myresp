package com.engineer.user.service;

import com.engineer.common.vo.PageResult;
import com.engineer.user.pojo.Location;
import com.engineer.user.vo.LocationTree;
import com.engineer.user.vo.LocationVo;

import java.util.List;

/**
 * @author Lemon
 * @date 2020/3/13 15:00
 */
public interface LocationService {

    /**
     * 获取地区列表树(用于级联选择地区)
     * @return
     */
    List<LocationTree> getLocationTree();

    /**
     * 获取地区列表（用于地区管理）
     * @return
     * @param pageNum
     * @param pageSize
     */
    PageResult<LocationVo> getLocationList(Integer pageNum, Integer pageSize);

    /**
     * 根据地区编号获取其上所有父地区的编号
     * @param locId
     * @return
     */
    List<Integer> getLocIdArrayList(Integer locId);

    /**
     * 根据地区编号删除地区及下属地区
     * @param locId
     */
    void deleteLocationById(Integer locId);

    /**
     * 异步更新Redis中的地区信息缓存
     */
    void updateRedisLocationCache();

    /**
     * 新增地区
     * @param location
     */
    void addLocation(Location location);

    /**
     * 修改地区基本信息
     * @param location
     */
    void updateLocation(Location location);
}
