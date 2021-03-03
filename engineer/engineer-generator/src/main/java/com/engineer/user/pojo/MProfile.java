package com.engineer.user.pojo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "m_profile")
public class MProfile implements Serializable {
    /**
     * 用户编号
     */
    @Id
    @Column(name = "user_id")
    private Long userId;

    /**
     * 身份证
     */
    private String idcard;

    /**
     * 出生日期
     */
    private Date birthday;

    /**
     * 性别
     */
    private String sex;

    /**
     * 所在单位ID
     */
    @Column(name = "orga_id")
    private Long orgaId;

    /**
     * 职位
     */
    private String position;

    /**
     * 职称
     */
    @Column(name = "prof_title")
    private String profTitle;

    /**
     * 所在地区编号
     */
    @Column(name = "loc_id")
    private Integer locId;

    /**
     * 住址
     */
    private String address;

    /**
     * 学历
     */
    private String education;

    /**
     * 学位
     */
    private String degree;

    /**
     * 拒绝理由
     */
    @Column(name = "refuse_reason")
    private String refuseReason;

    /**
     * 是否禁用
     */
    private Integer enabled;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 产品ID
     */
    private Long productid;

    /**
     * 自我描述
     */
    @Column(name = "self_describe")
    private String selfDescribe;

    private static final long serialVersionUID = 1L;

    public MProfile(Long userId, String idcard, Date birthday, String sex, Long orgaId, String position, String profTitle, Integer locId, String address, String education, String degree, String refuseReason, Integer enabled, Date createTime, Long productid, String selfDescribe) {
        this.userId = userId;
        this.idcard = idcard;
        this.birthday = birthday;
        this.sex = sex;
        this.orgaId = orgaId;
        this.position = position;
        this.profTitle = profTitle;
        this.locId = locId;
        this.address = address;
        this.education = education;
        this.degree = degree;
        this.refuseReason = refuseReason;
        this.enabled = enabled;
        this.createTime = createTime;
        this.productid = productid;
        this.selfDescribe = selfDescribe;
    }

    public MProfile() {
        super();
    }

    /**
     * 获取用户编号
     *
     * @return user_id - 用户编号
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * 设置用户编号
     *
     * @param userId 用户编号
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * 获取身份证
     *
     * @return idcard - 身份证
     */
    public String getIdcard() {
        return idcard;
    }

    /**
     * 设置身份证
     *
     * @param idcard 身份证
     */
    public void setIdcard(String idcard) {
        this.idcard = idcard == null ? null : idcard.trim();
    }

    /**
     * 获取出生日期
     *
     * @return birthday - 出生日期
     */
    public Date getBirthday() {
        return birthday;
    }

    /**
     * 设置出生日期
     *
     * @param birthday 出生日期
     */
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    /**
     * 获取性别
     *
     * @return sex - 性别
     */
    public String getSex() {
        return sex;
    }

    /**
     * 设置性别
     *
     * @param sex 性别
     */
    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    /**
     * 获取所在单位ID
     *
     * @return orga_id - 所在单位ID
     */
    public Long getOrgaId() {
        return orgaId;
    }

    /**
     * 设置所在单位ID
     *
     * @param orgaId 所在单位ID
     */
    public void setOrgaId(Long orgaId) {
        this.orgaId = orgaId;
    }

    /**
     * 获取职位
     *
     * @return position - 职位
     */
    public String getPosition() {
        return position;
    }

    /**
     * 设置职位
     *
     * @param position 职位
     */
    public void setPosition(String position) {
        this.position = position == null ? null : position.trim();
    }

    /**
     * 获取职称
     *
     * @return prof_title - 职称
     */
    public String getProfTitle() {
        return profTitle;
    }

    /**
     * 设置职称
     *
     * @param profTitle 职称
     */
    public void setProfTitle(String profTitle) {
        this.profTitle = profTitle == null ? null : profTitle.trim();
    }

    /**
     * 获取所在地区编号
     *
     * @return loc_id - 所在地区编号
     */
    public Integer getLocId() {
        return locId;
    }

    /**
     * 设置所在地区编号
     *
     * @param locId 所在地区编号
     */
    public void setLocId(Integer locId) {
        this.locId = locId;
    }

    /**
     * 获取住址
     *
     * @return address - 住址
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置住址
     *
     * @param address 住址
     */
    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    /**
     * 获取学历
     *
     * @return education - 学历
     */
    public String getEducation() {
        return education;
    }

    /**
     * 设置学历
     *
     * @param education 学历
     */
    public void setEducation(String education) {
        this.education = education == null ? null : education.trim();
    }

    /**
     * 获取学位
     *
     * @return degree - 学位
     */
    public String getDegree() {
        return degree;
    }

    /**
     * 设置学位
     *
     * @param degree 学位
     */
    public void setDegree(String degree) {
        this.degree = degree == null ? null : degree.trim();
    }

    /**
     * 获取拒绝理由
     *
     * @return refuse_reason - 拒绝理由
     */
    public String getRefuseReason() {
        return refuseReason;
    }

    /**
     * 设置拒绝理由
     *
     * @param refuseReason 拒绝理由
     */
    public void setRefuseReason(String refuseReason) {
        this.refuseReason = refuseReason == null ? null : refuseReason.trim();
    }

    /**
     * 获取是否禁用
     *
     * @return enabled - 是否禁用
     */
    public Integer getEnabled() {
        return enabled;
    }

    /**
     * 设置是否禁用
     *
     * @param enabled 是否禁用
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
     * 获取产品ID
     *
     * @return productid - 产品ID
     */
    public Long getProductid() {
        return productid;
    }

    /**
     * 设置产品ID
     *
     * @param productid 产品ID
     */
    public void setProductid(Long productid) {
        this.productid = productid;
    }

    /**
     * 获取自我描述
     *
     * @return self_describe - 自我描述
     */
    public String getSelfDescribe() {
        return selfDescribe;
    }

    /**
     * 设置自我描述
     *
     * @param selfDescribe 自我描述
     */
    public void setSelfDescribe(String selfDescribe) {
        this.selfDescribe = selfDescribe == null ? null : selfDescribe.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", userId=").append(userId);
        sb.append(", idcard=").append(idcard);
        sb.append(", birthday=").append(birthday);
        sb.append(", sex=").append(sex);
        sb.append(", orgaId=").append(orgaId);
        sb.append(", position=").append(position);
        sb.append(", profTitle=").append(profTitle);
        sb.append(", locId=").append(locId);
        sb.append(", address=").append(address);
        sb.append(", education=").append(education);
        sb.append(", degree=").append(degree);
        sb.append(", refuseReason=").append(refuseReason);
        sb.append(", enabled=").append(enabled);
        sb.append(", createTime=").append(createTime);
        sb.append(", productid=").append(productid);
        sb.append(", selfDescribe=").append(selfDescribe);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}