package com.engineer.user.pojo;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

public class Organization implements Serializable {
    /**
     * 组织机构编号
     */
    @Id
    @Column(name = "orga_id")
    private Long orgaId;

    /**
     * 组织机构名称
     */
    @Column(name = "orga_name")
    private String orgaName;

    /**
     * 组织机构地址
     */
    private String address;

    /**
     * 所在地区编号
     */
    @Column(name = "loc_id")
    private Integer locId;

    /**
     * 组织机构邮编
     */
    @Column(name = "postal_code")
    private String postalCode;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 电话
     */
    private String phone;

    /**
     * 行业类别
     */
    @Column(name = "orga_type")
    private String orgaType;

    /**
     * 组织机构代码
     */
    @Column(name = "orga_code")
    private String orgaCode;

    /**
     * 营业执照
     */
    @Column(name = "business_license")
    private String businessLicense;

    /**
     * 经营范围
     */
    @Column(name = "business_scope")
    private String businessScope;

    /**
     * 组织机构领导
     */
    private String leader;

    /**
     * 领导电话
     */
    @Column(name = "leader_phone")
    private String leaderPhone;

    /**
     * 法人代表
     */
    @Column(name = "legal_person")
    private String legalPerson;

    /**
     * 上一级部门编号
     */
    private Long pid;

    /**
     * 排序编号 默认 0
     */
    private Integer sequence;

    /**
     * 备注
     */
    private String memo;

    /**
     * 是否通过验证 1 通过 -1 未通过 0 待审核 默认 0
     */
    private Integer verify;

    /**
     * 是否禁用 1 启用 0 禁用
     */
    private Integer enabled;

    /**
     * 创建人编号
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

    public Organization(Long orgaId, String orgaName, String address, Integer locId, String postalCode, String email, String phone, String orgaType, String orgaCode, String businessLicense, String businessScope, String leader, String leaderPhone, String legalPerson, Long pid, Integer sequence, String memo, Integer verify, Integer enabled, Long createrId, Date createTime, Long productid) {
        this.orgaId = orgaId;
        this.orgaName = orgaName;
        this.address = address;
        this.locId = locId;
        this.postalCode = postalCode;
        this.email = email;
        this.phone = phone;
        this.orgaType = orgaType;
        this.orgaCode = orgaCode;
        this.businessLicense = businessLicense;
        this.businessScope = businessScope;
        this.leader = leader;
        this.leaderPhone = leaderPhone;
        this.legalPerson = legalPerson;
        this.pid = pid;
        this.sequence = sequence;
        this.memo = memo;
        this.verify = verify;
        this.enabled = enabled;
        this.createrId = createrId;
        this.createTime = createTime;
        this.productid = productid;
    }

    public Organization() {
        super();
    }

    /**
     * 获取组织机构编号
     *
     * @return orga_id - 组织机构编号
     */
    public Long getOrgaId() {
        return orgaId;
    }

    /**
     * 设置组织机构编号
     *
     * @param orgaId 组织机构编号
     */
    public void setOrgaId(Long orgaId) {
        this.orgaId = orgaId;
    }

    /**
     * 获取组织机构名称
     *
     * @return orga_name - 组织机构名称
     */
    public String getOrgaName() {
        return orgaName;
    }

    /**
     * 设置组织机构名称
     *
     * @param orgaName 组织机构名称
     */
    public void setOrgaName(String orgaName) {
        this.orgaName = orgaName == null ? null : orgaName.trim();
    }

    /**
     * 获取组织机构地址
     *
     * @return address - 组织机构地址
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置组织机构地址
     *
     * @param address 组织机构地址
     */
    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
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
     * 获取组织机构邮编
     *
     * @return postal_code - 组织机构邮编
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * 设置组织机构邮编
     *
     * @param postalCode 组织机构邮编
     */
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode == null ? null : postalCode.trim();
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
     * 获取行业类别
     *
     * @return orga_type - 行业类别
     */
    public String getOrgaType() {
        return orgaType;
    }

    /**
     * 设置行业类别
     *
     * @param orgaType 行业类别
     */
    public void setOrgaType(String orgaType) {
        this.orgaType = orgaType == null ? null : orgaType.trim();
    }

    /**
     * 获取组织机构代码
     *
     * @return orga_code - 组织机构代码
     */
    public String getOrgaCode() {
        return orgaCode;
    }

    /**
     * 设置组织机构代码
     *
     * @param orgaCode 组织机构代码
     */
    public void setOrgaCode(String orgaCode) {
        this.orgaCode = orgaCode == null ? null : orgaCode.trim();
    }

    /**
     * 获取营业执照
     *
     * @return business_license - 营业执照
     */
    public String getBusinessLicense() {
        return businessLicense;
    }

    /**
     * 设置营业执照
     *
     * @param businessLicense 营业执照
     */
    public void setBusinessLicense(String businessLicense) {
        this.businessLicense = businessLicense == null ? null : businessLicense.trim();
    }

    /**
     * 获取经营范围
     *
     * @return business_scope - 经营范围
     */
    public String getBusinessScope() {
        return businessScope;
    }

    /**
     * 设置经营范围
     *
     * @param businessScope 经营范围
     */
    public void setBusinessScope(String businessScope) {
        this.businessScope = businessScope == null ? null : businessScope.trim();
    }

    /**
     * 获取组织机构领导
     *
     * @return leader - 组织机构领导
     */
    public String getLeader() {
        return leader;
    }

    /**
     * 设置组织机构领导
     *
     * @param leader 组织机构领导
     */
    public void setLeader(String leader) {
        this.leader = leader == null ? null : leader.trim();
    }

    /**
     * 获取领导电话
     *
     * @return leader_phone - 领导电话
     */
    public String getLeaderPhone() {
        return leaderPhone;
    }

    /**
     * 设置领导电话
     *
     * @param leaderPhone 领导电话
     */
    public void setLeaderPhone(String leaderPhone) {
        this.leaderPhone = leaderPhone == null ? null : leaderPhone.trim();
    }

    /**
     * 获取法人代表
     *
     * @return legal_person - 法人代表
     */
    public String getLegalPerson() {
        return legalPerson;
    }

    /**
     * 设置法人代表
     *
     * @param legalPerson 法人代表
     */
    public void setLegalPerson(String legalPerson) {
        this.legalPerson = legalPerson == null ? null : legalPerson.trim();
    }

    /**
     * 获取上一级部门编号
     *
     * @return pid - 上一级部门编号
     */
    public Long getPid() {
        return pid;
    }

    /**
     * 设置上一级部门编号
     *
     * @param pid 上一级部门编号
     */
    public void setPid(Long pid) {
        this.pid = pid;
    }

    /**
     * 获取排序编号 默认 0
     *
     * @return sequence - 排序编号 默认 0
     */
    public Integer getSequence() {
        return sequence;
    }

    /**
     * 设置排序编号 默认 0
     *
     * @param sequence 排序编号 默认 0
     */
    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    /**
     * 获取备注
     *
     * @return memo - 备注
     */
    public String getMemo() {
        return memo;
    }

    /**
     * 设置备注
     *
     * @param memo 备注
     */
    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
    }

    /**
     * 获取是否通过验证 1 通过 -1 未通过 0 待审核 默认 0
     *
     * @return verify - 是否通过验证 1 通过 -1 未通过 0 待审核 默认 0
     */
    public Integer getVerify() {
        return verify;
    }

    /**
     * 设置是否通过验证 1 通过 -1 未通过 0 待审核 默认 0
     *
     * @param verify 是否通过验证 1 通过 -1 未通过 0 待审核 默认 0
     */
    public void setVerify(Integer verify) {
        this.verify = verify;
    }

    /**
     * 获取是否禁用 1 启用 0 禁用
     *
     * @return enabled - 是否禁用 1 启用 0 禁用
     */
    public Integer getEnabled() {
        return enabled;
    }

    /**
     * 设置是否禁用 1 启用 0 禁用
     *
     * @param enabled 是否禁用 1 启用 0 禁用
     */
    public void setEnabled(Integer enabled) {
        this.enabled = enabled;
    }

    /**
     * 获取创建人编号
     *
     * @return creater_id - 创建人编号
     */
    public Long getCreaterId() {
        return createrId;
    }

    /**
     * 设置创建人编号
     *
     * @param createrId 创建人编号
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
        sb.append(", orgaId=").append(orgaId);
        sb.append(", orgaName=").append(orgaName);
        sb.append(", address=").append(address);
        sb.append(", locId=").append(locId);
        sb.append(", postalCode=").append(postalCode);
        sb.append(", email=").append(email);
        sb.append(", phone=").append(phone);
        sb.append(", orgaType=").append(orgaType);
        sb.append(", orgaCode=").append(orgaCode);
        sb.append(", businessLicense=").append(businessLicense);
        sb.append(", businessScope=").append(businessScope);
        sb.append(", leader=").append(leader);
        sb.append(", leaderPhone=").append(leaderPhone);
        sb.append(", legalPerson=").append(legalPerson);
        sb.append(", pid=").append(pid);
        sb.append(", sequence=").append(sequence);
        sb.append(", memo=").append(memo);
        sb.append(", verify=").append(verify);
        sb.append(", enabled=").append(enabled);
        sb.append(", createrId=").append(createrId);
        sb.append(", createTime=").append(createTime);
        sb.append(", productid=").append(productid);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}