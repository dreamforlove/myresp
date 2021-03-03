package com.engineer.user.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {
    /**
     * 用户id
     */
    @Id
    @Column(name = "user_id")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long userId;

    /**
     * 用户名
     */
    @Column(name = "user_account")
    private String userAccount;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 用户真实姓名
     */
    @Column(name = "user_name")
    private String userName;

    /**
     * 真实姓名拼写
     */
    @Column(name = "name_spell")
    private String nameSpell;

    /**
     * 头像路径
     */
    @Column(name = "avatar_path")
    private String avatarPath;

    /**
     * 头像名称
     */
    @Column(name = "avatar_name")
    private String avatarName;

    /**
     * 头像类型
     */
    @Column(name = "avatar_type")
    private String avatarType;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 电话
     */
    private String phone;

    /**
     * 电话审核 1 审核通过  0 未审核  -1 审核不通过  默认为0
     */
    @Column(name = "phone_verified")
    private Integer phoneVerified;

    /**
     * 邮箱审核 1 审核通过  0 未审核  -1 审核不通过  默认为0
     */
    @Column(name = "email_verified")
    private Integer emailVerified;

    /**
     * 最近登录ip
     */
    @Column(name = "last_login_ip")
    private String lastLoginIp;

    /**
     * 最近登录时间
     */
    @Column(name = "last_login_time")
    private Date lastLoginTime;

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

    public User(Long userId, String userAccount, String password, String userName, String nameSpell, String avatarPath, String avatarName, String avatarType, String email, String phone, Integer phoneVerified, Integer emailVerified, String lastLoginIp, Date lastLoginTime, Integer enabled, Date createTime, Long productid) {
        this.userId = userId;
        this.userAccount = userAccount;
        this.password = password;
        this.userName = userName;
        this.nameSpell = nameSpell;
        this.avatarPath = avatarPath;
        this.avatarName = avatarName;
        this.avatarType = avatarType;
        this.email = email;
        this.phone = phone;
        this.phoneVerified = phoneVerified;
        this.emailVerified = emailVerified;
        this.lastLoginIp = lastLoginIp;
        this.lastLoginTime = lastLoginTime;
        this.enabled = enabled;
        this.createTime = createTime;
        this.productid = productid;
    }

    public User() {
        super();
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
     * 获取用户名
     *
     * @return user_account - 用户名
     */
    public String getUserAccount() {
        return userAccount;
    }

    /**
     * 设置用户名
     *
     * @param userAccount 用户名
     */
    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount == null ? null : userAccount.trim();
    }

    /**
     * 获取用户密码
     *
     * @return password - 用户密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置用户密码
     *
     * @param password 用户密码
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * 获取用户真实姓名
     *
     * @return user_name - 用户真实姓名
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 设置用户真实姓名
     *
     * @param userName 用户真实姓名
     */
    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    /**
     * 获取真实姓名拼写
     *
     * @return name_spell - 真实姓名拼写
     */
    public String getNameSpell() {
        return nameSpell;
    }

    /**
     * 设置真实姓名拼写
     *
     * @param nameSpell 真实姓名拼写
     */
    public void setNameSpell(String nameSpell) {
        this.nameSpell = nameSpell == null ? null : nameSpell.trim();
    }

    /**
     * 获取头像路径
     *
     * @return avatar_path - 头像路径
     */
    public String getAvatarPath() {
        return avatarPath;
    }

    /**
     * 设置头像路径
     *
     * @param avatarPath 头像路径
     */
    public void setAvatarPath(String avatarPath) {
        this.avatarPath = avatarPath == null ? null : avatarPath.trim();
    }

    /**
     * 获取头像名称
     *
     * @return avatar_name - 头像名称
     */
    public String getAvatarName() {
        return avatarName;
    }

    /**
     * 设置头像名称
     *
     * @param avatarName 头像名称
     */
    public void setAvatarName(String avatarName) {
        this.avatarName = avatarName == null ? null : avatarName.trim();
    }

    /**
     * 获取头像类型
     *
     * @return avatar_type - 头像类型
     */
    public String getAvatarType() {
        return avatarType;
    }

    /**
     * 设置头像类型
     *
     * @param avatarType 头像类型
     */
    public void setAvatarType(String avatarType) {
        this.avatarType = avatarType == null ? null : avatarType.trim();
    }

    /**
     * 获取邮箱
     *
     * @return email - 邮箱
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置邮箱
     *
     * @param email 邮箱
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    /**
     * 获取电话
     *
     * @return phone - 电话
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 设置电话
     *
     * @param phone 电话
     */
    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    /**
     * 获取电话审核 1 审核通过  0 未审核  -1 审核不通过  默认为0
     *
     * @return phone_verified - 电话审核 1 审核通过  0 未审核  -1 审核不通过  默认为0
     */
    public Integer getPhoneVerified() {
        return phoneVerified;
    }

    /**
     * 设置电话审核 1 审核通过  0 未审核  -1 审核不通过  默认为0
     *
     * @param phoneVerified 电话审核 1 审核通过  0 未审核  -1 审核不通过  默认为0
     */
    public void setPhoneVerified(Integer phoneVerified) {
        this.phoneVerified = phoneVerified;
    }

    /**
     * 获取邮箱审核 1 审核通过  0 未审核  -1 审核不通过  默认为0
     *
     * @return email_verified - 邮箱审核 1 审核通过  0 未审核  -1 审核不通过  默认为0
     */
    public Integer getEmailVerified() {
        return emailVerified;
    }

    /**
     * 设置邮箱审核 1 审核通过  0 未审核  -1 审核不通过  默认为0
     *
     * @param emailVerified 邮箱审核 1 审核通过  0 未审核  -1 审核不通过  默认为0
     */
    public void setEmailVerified(Integer emailVerified) {
        this.emailVerified = emailVerified;
    }

    /**
     * 获取最近登录ip
     *
     * @return last_login_ip - 最近登录ip
     */
    public String getLastLoginIp() {
        return lastLoginIp;
    }

    /**
     * 设置最近登录ip
     *
     * @param lastLoginIp 最近登录ip
     */
    public void setLastLoginIp(String lastLoginIp) {
        this.lastLoginIp = lastLoginIp == null ? null : lastLoginIp.trim();
    }

    /**
     * 获取最近登录时间
     *
     * @return last_login_time - 最近登录时间
     */
    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    /**
     * 设置最近登录时间
     *
     * @param lastLoginTime 最近登录时间
     */
    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
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
        sb.append(", userId=").append(userId);
        sb.append(", userAccount=").append(userAccount);
        sb.append(", password=").append(password);
        sb.append(", userName=").append(userName);
        sb.append(", nameSpell=").append(nameSpell);
        sb.append(", avatarPath=").append(avatarPath);
        sb.append(", avatarName=").append(avatarName);
        sb.append(", avatarType=").append(avatarType);
        sb.append(", email=").append(email);
        sb.append(", phone=").append(phone);
        sb.append(", phoneVerified=").append(phoneVerified);
        sb.append(", emailVerified=").append(emailVerified);
        sb.append(", lastLoginIp=").append(lastLoginIp);
        sb.append(", lastLoginTime=").append(lastLoginTime);
        sb.append(", enabled=").append(enabled);
        sb.append(", createTime=").append(createTime);
        sb.append(", productid=").append(productid);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}