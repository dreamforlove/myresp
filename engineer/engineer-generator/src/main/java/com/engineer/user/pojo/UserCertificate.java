package com.engineer.user.pojo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "user_certificate")
public class UserCertificate implements Serializable {
    /**
     * 编号
     */
    @Id
    private Long id;

    /**
     * 用户编号
     */
    @Column(name = "user_id")
    private Long userId;

    /**
     * 证书名称
     */
    @Column(name = "cert_name")
    private String certName;

    /**
     * 证书类型
     */
    @Column(name = "cert_type")
    private String certType;

    /**
     * 证书编号
     */
    @Column(name = "cert_code")
    private String certCode;

    /**
     * 证书发放单位
     */
    @Column(name = "cert_orga")
    private String certOrga;

    /**
     * 是否通审核
     */
    private Integer verify;

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

    private static final long serialVersionUID = 1L;

    public UserCertificate(Long id, Long userId, String certName, String certType, String certCode, String certOrga, Integer verify, Integer enabled, Date createTime, Long productid) {
        this.id = id;
        this.userId = userId;
        this.certName = certName;
        this.certType = certType;
        this.certCode = certCode;
        this.certOrga = certOrga;
        this.verify = verify;
        this.enabled = enabled;
        this.createTime = createTime;
        this.productid = productid;
    }

    public UserCertificate() {
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
     * 获取证书名称
     *
     * @return cert_name - 证书名称
     */
    public String getCertName() {
        return certName;
    }

    /**
     * 设置证书名称
     *
     * @param certName 证书名称
     */
    public void setCertName(String certName) {
        this.certName = certName == null ? null : certName.trim();
    }

    /**
     * 获取证书类型
     *
     * @return cert_type - 证书类型
     */
    public String getCertType() {
        return certType;
    }

    /**
     * 设置证书类型
     *
     * @param certType 证书类型
     */
    public void setCertType(String certType) {
        this.certType = certType == null ? null : certType.trim();
    }

    /**
     * 获取证书编号
     *
     * @return cert_code - 证书编号
     */
    public String getCertCode() {
        return certCode;
    }

    /**
     * 设置证书编号
     *
     * @param certCode 证书编号
     */
    public void setCertCode(String certCode) {
        this.certCode = certCode == null ? null : certCode.trim();
    }

    /**
     * 获取证书发放单位
     *
     * @return cert_orga - 证书发放单位
     */
    public String getCertOrga() {
        return certOrga;
    }

    /**
     * 设置证书发放单位
     *
     * @param certOrga 证书发放单位
     */
    public void setCertOrga(String certOrga) {
        this.certOrga = certOrga == null ? null : certOrga.trim();
    }

    /**
     * 获取是否通审核
     *
     * @return verify - 是否通审核
     */
    public Integer getVerify() {
        return verify;
    }

    /**
     * 设置是否通审核
     *
     * @param verify 是否通审核
     */
    public void setVerify(Integer verify) {
        this.verify = verify;
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", userId=").append(userId);
        sb.append(", certName=").append(certName);
        sb.append(", certType=").append(certType);
        sb.append(", certCode=").append(certCode);
        sb.append(", certOrga=").append(certOrga);
        sb.append(", verify=").append(verify);
        sb.append(", enabled=").append(enabled);
        sb.append(", createTime=").append(createTime);
        sb.append(", productid=").append(productid);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}