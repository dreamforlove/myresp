package com.engineer.user.pojo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

public class Resource implements Serializable {
    /**
     * 资源编号
     */
    @Id
    @Column(name = "src_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT LAST_INSERT_ID()")
    private Long srcId;

    /**
     * 权限编号
     */
    @Column(name = "permission_id")
    private Long permissionId;

    /**
     * 资源名称
     */
    @Column(name = "src_name")
    private String srcName;

    /**
     * 资源路径
     */
    private String url;

    /**
     * 排序编号
     */
    private Integer sequence;

    /**
     * 描述
     */
    private String description;

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

    public Resource(Long srcId, Long permissionId, String srcName, String url, Integer sequence, String description, Date createTime, Long productid) {
        this.srcId = srcId;
        this.permissionId = permissionId;
        this.srcName = srcName;
        this.url = url;
        this.sequence = sequence;
        this.description = description;
        this.createTime = createTime;
        this.productid = productid;
    }

    public Resource() {
        super();
    }

    /**
     * 获取资源编号
     *
     * @return src_id - 资源编号
     */
    public Long getSrcId() {
        return srcId;
    }

    /**
     * 设置资源编号
     *
     * @param srcId 资源编号
     */
    public void setSrcId(Long srcId) {
        this.srcId = srcId;
    }

    /**
     * 获取权限编号
     *
     * @return permission_id - 权限编号
     */
    public Long getPermissionId() {
        return permissionId;
    }

    /**
     * 设置权限编号
     *
     * @param permissionId 权限编号
     */
    public void setPermissionId(Long permissionId) {
        this.permissionId = permissionId;
    }

    /**
     * 获取资源名称
     *
     * @return src_name - 资源名称
     */
    public String getSrcName() {
        return srcName;
    }

    /**
     * 设置资源名称
     *
     * @param srcName 资源名称
     */
    public void setSrcName(String srcName) {
        this.srcName = srcName == null ? null : srcName.trim();
    }

    /**
     * 获取资源路径
     *
     * @return url - 资源路径
     */
    public String getUrl() {
        return url;
    }

    /**
     * 设置资源路径
     *
     * @param url 资源路径
     */
    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
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
     * 获取描述
     *
     * @return description - 描述
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置描述
     *
     * @param description 描述
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
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
        sb.append(", srcId=").append(srcId);
        sb.append(", permissionId=").append(permissionId);
        sb.append(", srcName=").append(srcName);
        sb.append(", url=").append(url);
        sb.append(", sequence=").append(sequence);
        sb.append(", description=").append(description);
        sb.append(", createTime=").append(createTime);
        sb.append(", productid=").append(productid);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}