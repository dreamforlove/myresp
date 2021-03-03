package com.engineer.user.pojo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

public class Menu implements Serializable {
    /**
     * 菜单编号
     */
    @Id
    @Column(name = "menu_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT LAST_INSERT_ID()")
    private Long menuId;

    /**
     * 权限id
     */
    @Column(name = "permission_id")
    private Long permissionId;

    /**
     * 菜单名称
     */
    @Column(name = "menu_name")
    private String menuName;

    /**
     * 菜单url
     */
    @Column(name = "menu_url")
    private String menuUrl;

    /**
     * 父菜单id
     */
    @Column(name = "menu_pid")
    private Long menuPid;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 产品id
     */
    private Long productid;

    private static final long serialVersionUID = 1L;

    public Menu(Long menuId, Long permissionId, String menuName, String menuUrl, Long menuPid, Date createTime, Long productid) {
        this.menuId = menuId;
        this.permissionId = permissionId;
        this.menuName = menuName;
        this.menuUrl = menuUrl;
        this.menuPid = menuPid;
        this.createTime = createTime;
        this.productid = productid;
    }

    public Menu() {
        super();
    }

    /**
     * 获取菜单编号
     *
     * @return menu_id - 菜单编号
     */
    public Long getMenuId() {
        return menuId;
    }

    /**
     * 设置菜单编号
     *
     * @param menuId 菜单编号
     */
    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    /**
     * 获取权限id
     *
     * @return permission_id - 权限id
     */
    public Long getPermissionId() {
        return permissionId;
    }

    /**
     * 设置权限id
     *
     * @param permissionId 权限id
     */
    public void setPermissionId(Long permissionId) {
        this.permissionId = permissionId;
    }

    /**
     * 获取菜单名称
     *
     * @return menu_name - 菜单名称
     */
    public String getMenuName() {
        return menuName;
    }

    /**
     * 设置菜单名称
     *
     * @param menuName 菜单名称
     */
    public void setMenuName(String menuName) {
        this.menuName = menuName == null ? null : menuName.trim();
    }

    /**
     * 获取菜单url
     *
     * @return menu_url - 菜单url
     */
    public String getMenuUrl() {
        return menuUrl;
    }

    /**
     * 设置菜单url
     *
     * @param menuUrl 菜单url
     */
    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl == null ? null : menuUrl.trim();
    }

    /**
     * 获取父菜单id
     *
     * @return menu_pid - 父菜单id
     */
    public Long getMenuPid() {
        return menuPid;
    }

    /**
     * 设置父菜单id
     *
     * @param menuPid 父菜单id
     */
    public void setMenuPid(Long menuPid) {
        this.menuPid = menuPid;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取产品id
     *
     * @return productid - 产品id
     */
    public Long getProductid() {
        return productid;
    }

    /**
     * 设置产品id
     *
     * @param productid 产品id
     */
    public void setProductid(Long productid) {
        this.productid = productid;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", menuId=").append(menuId);
        sb.append(", permissionId=").append(permissionId);
        sb.append(", menuName=").append(menuName);
        sb.append(", menuUrl=").append(menuUrl);
        sb.append(", menuPid=").append(menuPid);
        sb.append(", createTime=").append(createTime);
        sb.append(", productid=").append(productid);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}