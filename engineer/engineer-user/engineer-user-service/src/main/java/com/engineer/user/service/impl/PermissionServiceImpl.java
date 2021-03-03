package com.engineer.user.service.impl;

import com.engineer.common.enums.impl.EnabledEnum;
import com.engineer.common.enums.impl.PermissionExceptionEnum;
import com.engineer.common.exceptions.ServiceException;
import com.engineer.common.utils.IdWorker;
import com.engineer.common.vo.PageResult;
import com.engineer.user.mapper.MenuMapper;
import com.engineer.user.mapper.PermissionMapper;
import com.engineer.user.mapper.ResourceMapper;
import com.engineer.user.mapper.RolePermissionMapper;
import com.engineer.user.pojo.Menu;
import com.engineer.user.pojo.Permission;
import com.engineer.user.pojo.Resource;
import com.engineer.user.pojo.RolePermission;
import com.engineer.user.service.PermissionService;
import com.engineer.user.vo.MenuVo;
import com.engineer.user.vo.ResourceVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author Lemon
 * @date 2020/3/23 20:00
 */
@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    IdWorker idWorker;

    @Autowired
    MenuMapper menuMapper;
    @Autowired
    RolePermissionMapper rolePermissionMapper;
    @Autowired
    ResourceMapper resourceMapper;
    @Autowired
    PermissionMapper permissionMapper;

    @Override
    public PageResult<MenuVo> getPagedAuthorizedMenuListByRoleId(Integer pageNum, Integer pageSize, Long roleId) {
        PageHelper.startPage(pageNum, pageSize);
        List<MenuVo> list = menuMapper.getAuthorizedMenuListByRoleId(roleId);
        return getMenuVoPageResult(list);
    }

    @Override
    public PageResult<MenuVo> getPagedUnauthorizedMenuListByRoleId(Integer pageNum, Integer pageSize, Long roleId) {
        PageHelper.startPage(pageNum, pageSize);
        List<MenuVo> list = menuMapper.getUnAuthorizedMenuListByRoleId(roleId);
        return getMenuVoPageResult(list);
    }

    @Override
    public void cancelAuthorization(Long roleId, Long permissionId) {
        if (roleId == null || permissionId == null) {
            throw new ServiceException(PermissionExceptionEnum.PARAM_ERROR);
        }
        RolePermission rolePermission = new RolePermission();
        rolePermission.setRoleId(roleId);
        rolePermission.setPermissionId(permissionId);
        int count = rolePermissionMapper.delete(rolePermission);
        if (count != 1) {
            throw new ServiceException(PermissionExceptionEnum.DELETE_ROLE_PERMISSION_FAILED);
        }
    }

    @Override
    public void authorization(RolePermission rolePermission) {
        if (rolePermission.getRoleId() == null || rolePermission.getPermissionId() == null
                || rolePermission.getCreaterId() == null) {
            throw new ServiceException(PermissionExceptionEnum.PARAM_ERROR);
        }
        rolePermission.setCreateTime(new Date());
        int id = rolePermissionMapper.insert(rolePermission);
        if (id == 0) {
            throw new ServiceException(PermissionExceptionEnum.INSERT_ROLE_PERMISSION_FAILED);
        }
    }

    @Override
    public PageResult<MenuVo> getPagedMenuList(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<MenuVo> list = menuMapper.getMenuList();
        return getMenuVoPageResult(list);
    }

    @Override
    public PageResult<ResourceVo> getPagedResourceList(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<ResourceVo> list = resourceMapper.getResourceList();
        PageInfo<ResourceVo> info = new PageInfo<>(list);
        PageResult<ResourceVo> result = new PageResult<>();
        result.setTotal(info.getTotal());
        result.setPages(info.getPages());
        result.setPageNum(info.getPageNum());
        result.setRows(info.getList());
        return result;
    }

    @Override
    public void setPermissionStatus(Long permissionId, int value) {
        Permission permission = new Permission();
        permission.setPermissionId(permissionId);
        permission.setEnabled(value);
        int count = permissionMapper.updateByPrimaryKeySelective(permission);
        if (count != 1) {
            throw new ServiceException(PermissionExceptionEnum.UPDATE_PERMISSION_STATUS_FAILED);
        }
    }

    @Override
    @Transactional(rollbackFor = ServiceException.class)
    public void deletePermissionById(Long permissionId) {
        Permission permission = permissionMapper.selectByPrimaryKey(permissionId);
        if (permission == null) {
            throw new ServiceException(PermissionExceptionEnum.PERMISSION_NOT_FOUND);
        }
        switch (permission.getPermissionType()) {
            case "menu":
                Menu menu = new Menu();
                menu.setPermissionId(permissionId);
                int menuDelete = menuMapper.delete(menu);
                if (menuDelete != 1) {
                    throw new ServiceException(PermissionExceptionEnum.DELETE_MENU_PERMISSION_FAILED);
                }
                break;
            case "resource":
                Resource resource = new Resource();
                resource.setPermissionId(permissionId);
                int resourceDelete = resourceMapper.delete(resource);
                if (resourceDelete != 1) {
                    throw new ServiceException(PermissionExceptionEnum.DELETE_RESOURCE_PERMISSION_FAILED);
                }
                break;
            default:
                throw new ServiceException(PermissionExceptionEnum.PERMISSION_NOT_FOUND);
        }
        int count = permissionMapper.deleteByPrimaryKey(permissionId);
        if (count != 1) {
            throw new ServiceException(PermissionExceptionEnum.DELETE_PERMISSION_FAILED);
        }
    }

    @Override
    @Transactional(rollbackFor = ServiceException.class)
    public void addMenuPermission(MenuVo menuVo) {
        Permission permission = new Permission();
        permission.setPermissionId(idWorker.nextId());
        permission.setPermissionType("menu");
        permission.setCreaterId(menuVo.getCreaterId());
        permission.setCreateTime(new Date());
        permission.setEnabled(EnabledEnum.ENABLED.getValue());
        Menu menu = new Menu();
        BeanUtils.copyProperties(menuVo, menu);
        menu.setPermissionId(permission.getPermissionId());
        menu.setCreateTime(permission.getCreateTime());
        int count = permissionMapper.insert(permission);
        if (count != 1) {
            throw new ServiceException(PermissionExceptionEnum.ADD_PERMISSION_FAILED);
        }
        int id = menuMapper.insert(menu);
        if (id == 0) {
            throw new ServiceException(PermissionExceptionEnum.ADD_MENU_PERMISSION_FAILED);
        }
    }

    @Override
    public void addResourcePermission(ResourceVo resourceVo) {
        Permission permission = new Permission();
        permission.setPermissionId(idWorker.nextId());
        permission.setPermissionType("resource");
        permission.setCreaterId(resourceVo.getCreaterId());
        permission.setCreateTime(new Date());
        permission.setEnabled(EnabledEnum.ENABLED.getValue());
        Resource resource = new Resource();
        BeanUtils.copyProperties(resourceVo, resource);
        resource.setPermissionId(permission.getPermissionId());
        resource.setCreateTime(permission.getCreateTime());
        int count = permissionMapper.insert(permission);
        if (count != 1) {
            throw new ServiceException(PermissionExceptionEnum.ADD_PERMISSION_FAILED);
        }
        int id = resourceMapper.insert(resource);
        if (id == 0) {
            throw new ServiceException(PermissionExceptionEnum.ADD_RESOURCE_PERMISSION_FAILED);
        }
    }

    @Override
    public void updateMenuPermission(Menu menu) {
        if (menu.getMenuId() == null) {
            throw new ServiceException(PermissionExceptionEnum.PARAM_ERROR);
        }
        int count = menuMapper.updateByPrimaryKeySelective(menu);
        if (count != 1) {
            throw new ServiceException(PermissionExceptionEnum.UPDATE_MENU_PERMISSION_FAILED);
        }
    }

    @Override
    public void updateResourcePermission(Resource resource) {
        if (resource.getSrcId() == null) {
            throw new ServiceException(PermissionExceptionEnum.PARAM_ERROR);
        }
        int count = resourceMapper.updateByPrimaryKeySelective(resource);
        if (count != 1) {
            throw new ServiceException(PermissionExceptionEnum.UPDATE_RESOURCE_PERMISSION_FAILED);
        }
    }

    @Override
    public PageResult<ResourceVo> getPagedAuthorizedResourceListByRoleId(Integer pageNum, Integer pageSize, Long roleId) {
        PageHelper.startPage(pageNum, pageSize);
        List<ResourceVo> list = resourceMapper.getAuthorizedResourceListByRoleId(roleId);
        return getResourceVoPageResult(list);
    }

    @Override
    public PageResult<ResourceVo> getPagedUnauthorizedResourceListByRoleId(Integer pageNum, Integer pageSize, Long roleId) {
        PageHelper.startPage(pageNum, pageSize);
        List<ResourceVo> list = resourceMapper.getUnAuthorizedResourceListByRoleId(roleId);
        return getResourceVoPageResult(list);
    }

    private PageResult<MenuVo> getMenuVoPageResult(List<MenuVo> list) {
        PageInfo<MenuVo> info = new PageInfo<>(list);
        PageResult<MenuVo> result = new PageResult<>();
        result.setTotal(info.getTotal());
        result.setPages(info.getPages());
        result.setPageNum(info.getPageNum());
        result.setRows(info.getList());
        return result;
    }

    private PageResult<ResourceVo> getResourceVoPageResult(List<ResourceVo> list) {
        PageInfo<ResourceVo> info = new PageInfo<>(list);
        PageResult<ResourceVo> result = new PageResult<>();
        result.setTotal(info.getTotal());
        result.setPages(info.getPages());
        result.setPageNum(info.getPageNum());
        result.setRows(info.getList());
        return result;
    }
}
