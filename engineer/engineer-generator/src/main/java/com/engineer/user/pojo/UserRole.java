package com.engineer.user.pojo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "user_role")
public class UserRole implements Serializable {
    /**
     * 编号
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT LAST_INSERT_ID()")
    private Long id;

    /**
     * 用户id
     */
    @Column(name = "user_id")
    private Long userId;

    /**
     * 角色id
     */
    @Column(name = "role_id")
    private Long roleId;

    /**
     * 授权人id
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

    public UserRole(Long id, Long userId, Long roleId, Long createrId, Date createTime, Long productid) {
        this.id = id;
        this.userId = userId;
        this.roleId = roleId;
        this.createrId = createrId;
        this.createTime = createTime;
        this.productid = productid;
    }

    public UserRole() {
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
     * 获取授权人id
     *
     * @return creater_id - 授权人id
     */
    public Long getCreaterId() {
        return createrId;
    }

    /**
     * 设置授权人id
     *
     * @param createrId 授权人id
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
        sb.append(", id=").append(id);
        sb.append(", userId=").append(userId);
        sb.append(", roleId=").append(roleId);
        sb.append(", createrId=").append(createrId);
        sb.append(", createTime=").append(createTime);
        sb.append(", productid=").append(productid);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}