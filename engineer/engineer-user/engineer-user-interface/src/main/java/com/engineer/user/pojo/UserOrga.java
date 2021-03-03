package com.engineer.user.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Table(name = "user_orga")
public class UserOrga implements Serializable {
    /**
     * 编号
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT LAST_INSERT_ID()")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long id;

    /**
     * 用户id
     */
    @Column(name = "user_id")
    private Long userId;

    /**
     * 组织机构id
     */
    @Column(name = "orga_id")
    private Long orgaId;

    /**
     * 状态（待接受 0 已接受 1 已拒绝 2 已离职 3）
     */
    private Integer status;

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

    public UserOrga(Long id, Long userId, Long orgaId, Integer status, Date createTime, Long productid) {
        this.id = id;
        this.userId = userId;
        this.orgaId = orgaId;
        this.status = status;
        this.createTime = createTime;
        this.productid = productid;
    }

    public UserOrga() {
        super();
    }

    /**
     * 获取编号
     *
     * @return id - 编号
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置编号
     *
     * @param id 编号
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取用户id
     *
     * @return user_id - 用户id
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * 设置用户id
     *
     * @param userId 用户id
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * 获取组织机构id
     *
     * @return orga_id - 组织机构id
     */
    public Long getOrgaId() {
        return orgaId;
    }

    /**
     * 设置组织机构id
     *
     * @param orgaId 组织机构id
     */
    public void setOrgaId(Long orgaId) {
        this.orgaId = orgaId;
    }

    /**
     * 获取状态（待接受 0 已接受 1 已拒绝 2 已离职 3）
     *
     * @return status - 状态（待接受 0 已接受 1 已拒绝 2 已离职 3）
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置状态（待接受 0 已接受 1 已拒绝 2 已离职 3）
     *
     * @param status 状态（待接受 0 已接受 1 已拒绝 2 已离职 3）
     */
    public void setStatus(Integer status) {
        this.status = status;
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
        sb.append(", id=").append(id);
        sb.append(", userId=").append(userId);
        sb.append(", orgaId=").append(orgaId);
        sb.append(", status=").append(status);
        sb.append(", createTime=").append(createTime);
        sb.append(", productid=").append(productid);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}