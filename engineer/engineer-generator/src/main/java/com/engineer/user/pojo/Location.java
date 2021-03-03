package com.engineer.user.pojo;

import java.io.Serializable;
import javax.persistence.*;

public class Location implements Serializable {
    /**
     * 地区编号
     */
    @Id
    @Column(name = "loc_id")
    private Integer locId;

    /**
     * 地区名称
     */
    @Column(name = "loc_name")
    private String locName;

    /**
     * 区域上级标识
     */
    private Integer pid;

    /**
     * 区域等级
     */
    private Integer level;

    /**
     * 区域编码
     */
    @Column(name = "city_code")
    private String cityCode;

    /**
     * 邮政编码
     */
    @Column(name = "postal_code")
    private String postalCode;

    /**
     * 组合名称
     */
    private String mername;

    /**
     * 地区名称拼音
     */
    private String pinyin;

    private static final long serialVersionUID = 1L;

    public Location(Integer locId, String locName, Integer pid, Integer level, String cityCode, String postalCode, String mername, String pinyin) {
        this.locId = locId;
        this.locName = locName;
        this.pid = pid;
        this.level = level;
        this.cityCode = cityCode;
        this.postalCode = postalCode;
        this.mername = mername;
        this.pinyin = pinyin;
    }

    public Location() {
        super();
    }

    /**
     * 获取地区编号
     *
     * @return loc_id - 地区编号
     */
    public Integer getLocId() {
        return locId;
    }

    /**
     * 设置地区编号
     *
     * @param locId 地区编号
     */
    public void setLocId(Integer locId) {
        this.locId = locId;
    }

    /**
     * 获取地区名称
     *
     * @return loc_name - 地区名称
     */
    public String getLocName() {
        return locName;
    }

    /**
     * 设置地区名称
     *
     * @param locName 地区名称
     */
    public void setLocName(String locName) {
        this.locName = locName == null ? null : locName.trim();
    }

    /**
     * 获取区域上级标识
     *
     * @return pid - 区域上级标识
     */
    public Integer getPid() {
        return pid;
    }

    /**
     * 设置区域上级标识
     *
     * @param pid 区域上级标识
     */
    public void setPid(Integer pid) {
        this.pid = pid;
    }

    /**
     * 获取区域等级
     *
     * @return level - 区域等级
     */
    public Integer getLevel() {
        return level;
    }

    /**
     * 设置区域等级
     *
     * @param level 区域等级
     */
    public void setLevel(Integer level) {
        this.level = level;
    }

    /**
     * 获取区域编码
     *
     * @return city_code - 区域编码
     */
    public String getCityCode() {
        return cityCode;
    }

    /**
     * 设置区域编码
     *
     * @param cityCode 区域编码
     */
    public void setCityCode(String cityCode) {
        this.cityCode = cityCode == null ? null : cityCode.trim();
    }

    /**
     * 获取邮政编码
     *
     * @return postal_code - 邮政编码
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * 设置邮政编码
     *
     * @param postalCode 邮政编码
     */
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode == null ? null : postalCode.trim();
    }

    /**
     * 获取组合名称
     *
     * @return mername - 组合名称
     */
    public String getMername() {
        return mername;
    }

    /**
     * 设置组合名称
     *
     * @param mername 组合名称
     */
    public void setMername(String mername) {
        this.mername = mername == null ? null : mername.trim();
    }

    /**
     * 获取地区名称拼音
     *
     * @return pinyin - 地区名称拼音
     */
    public String getPinyin() {
        return pinyin;
    }

    /**
     * 设置地区名称拼音
     *
     * @param pinyin 地区名称拼音
     */
    public void setPinyin(String pinyin) {
        this.pinyin = pinyin == null ? null : pinyin.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", locId=").append(locId);
        sb.append(", locName=").append(locName);
        sb.append(", pid=").append(pid);
        sb.append(", level=").append(level);
        sb.append(", cityCode=").append(cityCode);
        sb.append(", postalCode=").append(postalCode);
        sb.append(", mername=").append(mername);
        sb.append(", pinyin=").append(pinyin);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}