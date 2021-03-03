package com.engineer.user.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

public class Permission implements Serializable {
    /**
     * 权限id
     */
    @Id
    @Column(name = "permission_id")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long permissionId;

    /**
     * 权限类型
     */
    @Column(name = "permission_type")
    private String permissionType;

    /**
     * 是否禁用 1 启用 0 禁用 默认为1
     */
    private Integer enabled;

    /**
     * 创建者id
     */
    @Column(name = "creater_id")
    private Long createrId;

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

    public Permission(Long permissionId, String permissionType, Integer enabled, Long createrId, Date createTime, Long productid) {
        this.permissionId = permissionId;
        this.permissionType = permissionType;
        this.enabled = enabled;
        this.createrId = createrId;
        this.createTime = createTime;
        this.productid = productid;
    }

    public Permission() {
        super();
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
     * 获取权限类型
     *
     * @return permission_type - 权限类型
     */
    public String getPermissionType() {
        return permissionType;
    }

    /**
     * 设置权限类型
     *
     * @param permissionType 权限类型
     */
    public void setPermissionType(String permissionType) {
        this.permissionType = permissionType == null ? null : permissionType.trim();
    }

    /**
     * 获取是否禁用 1 启用 0 禁用 默认为1
     *
     * @return enabled - 是否禁用 1 启用 0 禁用 默认为1
     */
    public Integer getEnabled() {
        return enabled;
    }

    /**
     * 设置是否禁用 1 启用 0 禁用 默认为1
     *
     * @param enabled 是否禁用 1 启用 0 禁用 默认为1
     */
    public void setEnabled(Integer enabled) {
        this.enabled = enabled;
    }

    /**
     * 获取创建者id
     *
     * @return creater_id - 创建者id
     */
    public Long getCreaterId() {
        return createrId;
    }

    /**
     * 设置创建者id
     *
     * @param createrId 创建者id
     */
    public void setCreaterId(Long createrId) {
        this.createrId = createrId;
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
        sb.append(", permissionId=").append(permissionId);
        sb.append(", permissionType=").append(permissionType);
        sb.append(", enabled=").append(enabled);
        sb.append(", createrId=").append(createrId);
        sb.append(", createTime=").append(createTime);
        sb.append(", productid=").append(productid);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}