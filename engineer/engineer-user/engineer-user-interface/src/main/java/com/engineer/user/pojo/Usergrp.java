package com.engineer.user.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

public class Usergrp implements Serializable {
    /**
     * 用户组编号
     */
    @Id
    @Column(name = "usergrp_id")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long usergrpId;

    /**
     * 用户组名称
     */
    @Column(name = "usergrp_name")
    private String usergrpName;

    /**
     * 描述
     */
    @Column(name = "grp_desc")
    private String grpDesc;

    /**
     * 用户组类型  ？
     */
    @Column(name = "usergrp_type")
    private Integer usergrpType;

    /**
     * 所属单位
     */
    @Column(name = "orga_id")
    private Long orgaId;

    /**
     * 联系人（负责人）
     */
    private String principal;

    /**
     * 联系人电话
     */
    private String phone;

    /**
     * 创建者id
     */
    @Column(name = "creater_id")
    private Long createrId;

    /**
     * 是否通过审核 审核通过 1 不通过 -1 未审核 0 默认为0 
     */
    private Integer verify;

    /**
     * 是否禁用 1 启用 0 禁用 默认为1
     */
    private Integer enabled;

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

    public Usergrp(Long usergrpId, String usergrpName, String grpDesc, Integer usergrpType, Long orgaId, String principal, String phone, Long createrId, Integer verify, Integer enabled, Date createTime, Long productid) {
        this.usergrpId = usergrpId;
        this.usergrpName = usergrpName;
        this.grpDesc = grpDesc;
        this.usergrpType = usergrpType;
        this.orgaId = orgaId;
        this.principal = principal;
        this.phone = phone;
        this.createrId = createrId;
        this.verify = verify;
        this.enabled = enabled;
        this.createTime = createTime;
        this.productid = productid;
    }

    public Usergrp() {
        super();
    }

    /**
     * 获取用户组编号
     *
     * @return usergrp_id - 用户组编号
     */
    public Long getUsergrpId() {
        return usergrpId;
    }

    /**
     * 设置用户组编号
     *
     * @param usergrpId 用户组编号
     */
    public void setUsergrpId(Long usergrpId) {
        this.usergrpId = usergrpId;
    }

    /**
     * 获取用户组名称
     *
     * @return usergrp_name - 用户组名称
     */
    public String getUsergrpName() {
        return usergrpName;
    }

    /**
     * 设置用户组名称
     *
     * @param usergrpName 用户组名称
     */
    public void setUsergrpName(String usergrpName) {
        this.usergrpName = usergrpName == null ? null : usergrpName.trim();
    }

    /**
     * 获取描述
     *
     * @return grp_desc - 描述
     */
    public String getGrpDesc() {
        return grpDesc;
    }

    /**
     * 设置描述
     *
     * @param grpDesc 描述
     */
    public void setGrpDesc(String grpDesc) {
        this.grpDesc = grpDesc == null ? null : grpDesc.trim();
    }

    /**
     * 获取用户组类型  ？
     *
     * @return usergrp_type - 用户组类型  ？
     */
    public Integer getUsergrpType() {
        return usergrpType;
    }

    /**
     * 设置用户组类型  ？
     *
     * @param usergrpType 用户组类型  ？
     */
    public void setUsergrpType(Integer usergrpType) {
        this.usergrpType = usergrpType;
    }

    /**
     * 获取所属单位
     *
     * @return orga_id - 所属单位
     */
    public Long getOrgaId() {
        return orgaId;
    }

    /**
     * 设置所属单位
     *
     * @param orgaId 所属单位
     */
    public void setOrgaId(Long orgaId) {
        this.orgaId = orgaId;
    }

    /**
     * 获取联系人（负责人）
     *
     * @return principal - 联系人（负责人）
     */
    public String getPrincipal() {
        return principal;
    }

    /**
     * 设置联系人（负责人）
     *
     * @param principal 联系人（负责人）
     */
    public void setPrincipal(String principal) {
        this.principal = principal == null ? null : principal.trim();
    }

    /**
     * 获取联系人电话
     *
     * @return phone - 联系人电话
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 设置联系人电话
     *
     * @param phone 联系人电话
     */
    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
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
     * 获取是否通过审核 审核通过 1 不通过 -1 未审核 0 默认为0 
     *
     * @return verify - 是否通过审核 审核通过 1 不通过 -1 未审核 0 默认为0 
     */
    public Integer getVerify() {
        return verify;
    }

    /**
     * 设置是否通过审核 审核通过 1 不通过 -1 未审核 0 默认为0 
     *
     * @param verify 是否通过审核 审核通过 1 不通过 -1 未审核 0 默认为0 
     */
    public void setVerify(Integer verify) {
        this.verify = verify;
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
        sb.append(", usergrpId=").append(usergrpId);
        sb.append(", usergrpName=").append(usergrpName);
        sb.append(", grpDesc=").append(grpDesc);
        sb.append(", usergrpType=").append(usergrpType);
        sb.append(", orgaId=").append(orgaId);
        sb.append(", principal=").append(principal);
        sb.append(", phone=").append(phone);
        sb.append(", createrId=").append(createrId);
        sb.append(", verify=").append(verify);
        sb.append(", enabled=").append(enabled);
        sb.append(", createTime=").append(createTime);
        sb.append(", productid=").append(productid);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}