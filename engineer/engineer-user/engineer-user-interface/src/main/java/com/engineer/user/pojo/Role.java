package com.engineer.user.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

public class Role implements Serializable {
    /**
     * 角色id
     */
    @Id
    @Column(name = "role_id")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long roleId;

    /**
     * 角色名称
     */
    @Column(name = "role_name")
    private String roleName;

    /**
     * 角色描述
     */
    @Column(name = "role_desc")
    private String roleDesc;

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
     * 排序编号
     */
    private Integer sequence;

    /**
     * 产品id
     */
    private Long productid;

    private static final long serialVersionUID = 1L;

    public Role(Long roleId, String roleName, String roleDesc, Integer enabled, Long createrId, Date createTime, Integer sequence, Long productid) {
        this.roleId = roleId;
        this.roleName = roleName;
        this.roleDesc = roleDesc;
        this.enabled = enabled;
        this.createrId = createrId;
        this.createTime = createTime;
        this.sequence = sequence;
        this.productid = productid;
    }

    public Role() {
        super();
    }

    /**
     * 获取角色id
     *
     * @return role_id - 角色id
     */
    public Long getRoleId() {
        return roleId;
    }

    /**
     * 设置角色id
     *
     * @param roleId 角色id
     */
    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    /**
     * 获取角色名称
     *
     * @return role_name - 角色名称
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * 设置角色名称
     *
     * @param roleName 角色名称
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    /**
     * 获取角色描述
     *
     * @return role_desc - 角色描述
     */
    public String getRoleDesc() {
        return roleDesc;
    }

    /**
     * 设置角色描述
     *
     * @param roleDesc 角色描述
     */
    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc == null ? null : roleDesc.trim();
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
     * 获取排序编号
     *
     * @return sequence - 排序编号
     */
    public Integer getSequence() {
        return sequence;
    }

    /**
     * 设置排序编号
     *
     * @param sequence 排序编号
     */
    public void setSequence(Integer sequence) {
        this.sequence = sequence;
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
        sb.append(", roleId=").append(roleId);
        sb.append(", roleName=").append(roleName);
        sb.append(", roleDesc=").append(roleDesc);
        sb.append(", enabled=").append(enabled);
        sb.append(", createrId=").append(createrId);
        sb.append(", createTime=").append(createTime);
        sb.append(", sequence=").append(sequence);
        sb.append(", productid=").append(productid);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}