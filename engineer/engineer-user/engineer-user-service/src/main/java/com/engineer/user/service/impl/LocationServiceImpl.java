package com.engineer.user.service.impl;

import com.engineer.common.enums.impl.LocationExceptionEnum;
import com.engineer.common.exceptions.ServiceException;
import com.engineer.common.utils.IdWorker;
import com.engineer.common.utils.JsonUtils;
import com.engineer.common.vo.PageResult;
import com.engineer.user.mapper.LocationMapper;
import com.engineer.user.pojo.Location;
import com.engineer.user.service.LocationService;
import com.engineer.user.vo.LocationTree;
import com.engineer.user.vo.LocationVo;
import com.github.pagehelper.PageHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * @author Lemon
 * @date 2020/3/13 15:00
 */
@Service
public class LocationServiceImpl implements LocationService {

    private static final String LOCATION_TREE_KEY = "location_tree";
    private static final String LOCATION_LIST_PREFIX_KEY = "location_list";
    private static final String LOCATION_LIST_PAGE_KEY = ":page_num=";

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private LocationMapper locationMapper;

    @Override
    public List<LocationTree> getLocationTree() {
        List<LocationTree> locationTree;
        locationTree = getLocationTreeFromRedis();
        if (CollectionUtils.isEmpty(locationTree)) {
            int highLevel = locationMapper.getHighLevelCount();
            locationTree = locationMapper.getHighLocation(highLevel);
            for (LocationTree tree : locationTree) {
                List<LocationTree> children = getLocationTreeChildren(tree.getValue());
                tree.setChildren(children);
            }
            saveLocationTreeToRedis(locationTree);
        }
        return locationTree;
    }

    @Override
    public PageResult<LocationVo> getLocationList(Integer pageNum, Integer pageSize) {
        List<LocationVo> list;
        Long total = locationMapper.getProvinceTotal();
        int pages = (int) ((total + pageSize -1) / pageSize);
        list = getLocationListFromRedis(pageNum);
        if (CollectionUtils.isEmpty(list)) {
            // 地区最高等级 0 中国
            int highLevel = locationMapper.getHighLevelCount();
            PageHelper.startPage(pageNum, pageSize);
            // highLevel + 1 即省级行政区，获取省级行政区及以下的行政区信息
            list = locationMapper.getHighLocationDetail(highLevel + 1);
            for (LocationVo location : list) {
                List<LocationVo> children = getLocationListChildren(location.getLocId());
                location.setChildren(children);
            }
            saveLocationListToRedis(list, pageNum);
        }
        PageResult<LocationVo> result = new PageResult<>();
        result.setRows(list);
        result.setPageNum(pageNum);
        result.setTotal(total);
        result.setPages(pages);
        return result;
    }

    @Override
    public List<Integer> getLocIdArrayList(Integer locId) {
        Location location = locationMapper.selectByPrimaryKey(locId);
        Integer level = location.getLevel();
        List<Integer> locationIds = new ArrayList<>();
        locationIds.add(location.getLocId());
        locationIds.add(location.getPid());
        Integer pid = location.getPid();
        for (int i = level - 1; level > 0; level--) {
            Location locationParent = locationMapper.selectByPrimaryKey(pid);
            pid = locationParent.getPid();
            locationIds.add(pid);
        }
        Collections.sort(locationIds);
        return locationIds;
    }

    @Override
    @Transactional(rollbackFor = ServiceException.class)
    public void deleteLocationById(Integer locId) {
        if (locId == null) {
            throw new ServiceException(LocationExceptionEnum.PARAM_ERROR);
        }
        Location location = new Location();
        location.setPid(locId);
        locationMapper.delete(location);
        int count = locationMapper.deleteByPrimaryKey(locId);
        if (count != 1) {
            throw new ServiceException(LocationExceptionEnum.DELETE_LOCATION_FAILED);
        }
    }


    @Override
    @Async
    public void updateRedisLocationCache() {
        deleteRedisLocationCache();
        System.out.println("开始异步更新Redis地区缓存");
        Long total = locationMapper.getProvinceTotal();
        System.out.println("更新级联地区信息成功");
        getLocationTree();
        int pages = (int) ((total + 5 -1) / 5);
        for (int pageNum = 1; pageNum <= pages; pageNum++) {
            System.out.println("更新分页地区信息，页码：" + pageNum);
            getLocationList(pageNum, 5);
        }
    }

    @Override
    public void addLocation(Location location) {
        if (
                StringUtils.isBlank(location.getLocName()) || StringUtils.isBlank(location.getMername())
                        || StringUtils.isBlank(location.getPinyin()) || location.getPid() == null
                        || location.getLevel() == null || location.getLocId() == null
        ) {
            throw new ServiceException(LocationExceptionEnum.PARAM_ERROR);
        }
        Location parentLocation = locationMapper.selectByPrimaryKey(location.getPid());
        if (parentLocation == null) {
            throw new ServiceException(LocationExceptionEnum.PID_IS_ERROR);
        }
        int insert = locationMapper.insert(location);
        if (insert != 1) {
            throw new ServiceException(LocationExceptionEnum.ADD_LOCATION_FAILED);
        }
    }

    @Override
    public void updateLocation(Location location) {
        if (
                StringUtils.isBlank(location.getLocName()) || StringUtils.isBlank(location.getMername())
                        || StringUtils.isBlank(location.getPinyin()) || location.getPid() == null
                        || location.getLevel() == null || location.getLocId() == null
        ) {
            throw new ServiceException(LocationExceptionEnum.PARAM_ERROR);
        }
        int count = locationMapper.updateByPrimaryKeySelective(location);
        if (count != 1) {
            throw new ServiceException(LocationExceptionEnum.UPDATE_LOCATION_FAILED);
        }
    }

    private void deleteRedisLocationCache() {
        redisTemplate.delete(LOCATION_TREE_KEY);
        Set<String> keys = redisTemplate.keys(LOCATION_LIST_PREFIX_KEY + "*");
        System.out.println("获取到的keys" + keys.toString());
        if (!CollectionUtils.isEmpty(keys)) {
            Long delete = redisTemplate.delete(keys);
            System.out.println(delete);
        }
    }


    private List<LocationTree> getLocationTreeFromRedis() {
        String locationTreeJson = redisTemplate.opsForValue().get(LOCATION_TREE_KEY);
        List<LocationTree> locationTree = JsonUtils.jsonToList(locationTreeJson, LocationTree.class);
        return locationTree;
    }

    private List<LocationVo> getLocationListFromRedis(Integer pageNum) {
        String locationListJson = redisTemplate.opsForValue().get(LOCATION_LIST_PREFIX_KEY + LOCATION_LIST_PAGE_KEY + pageNum);
        if (!StringUtils.isEmpty(locationListJson)) {
            List<LocationVo> list = JsonUtils.jsonToList(locationListJson, LocationVo.class);
            return list;
        }
        return null;
    }

    private List<LocationTree> getLocationTreeChildren(int pid) {
        List<LocationTree> children = locationMapper.getLocationChildrenByPid(pid);
        for (LocationTree child : children) {
            int childPid = child.getValue();
            List<LocationTree> childrenTree = getLocationTreeChildren(childPid);
            child.setChildren(childrenTree);
        }
        return children;
    }

    private List<LocationVo> getLocationListChildren(Integer pid) {
        List<LocationVo> children = locationMapper.getLocationChildrenListByPid(pid);
        for (LocationVo child : children) {
            int childPid = child.getLocId();
            List<LocationVo> childrenList = getLocationListChildren(childPid);
            child.setChildren(childrenList);
        }
        return children;
    }

    private void saveLocationTreeToRedis(List<LocationTree> locationTree) {
        if (!CollectionUtils.isEmpty(locationTree)) {
            String locationTreeJson = JsonUtils.objectToJson(locationTree);
            redisTemplate.boundValueOps(LOCATION_TREE_KEY).set(locationTreeJson);
        }
    }

    private void saveLocationListToRedis(List<LocationVo> list, Integer pageNum) {
        if (!CollectionUtils.isEmpty(list)) {
            String locationTreeJson = JsonUtils.objectToJson(list);
            redisTemplate.boundValueOps(LOCATION_LIST_PREFIX_KEY + LOCATION_LIST_PAGE_KEY + pageNum).set(locationTreeJson);
        }
    }

}
