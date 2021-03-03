package com.engineer.user.vo;

import com.engineer.user.pojo.MProfile;
import com.engineer.user.pojo.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * @author Lemon
 * @date 2020/3/11 19:01
 */
public class UserDetail extends User {
    private MProfile mProfile;
    private List<Integer> location;

    public List<Integer> getLocation() {
        return location;
    }

    @JsonProperty("mProfile")
    public MProfile getMProfile() {
        return mProfile;
    }

    @JsonProperty("mProfile")
    public void setMProfile(MProfile mProfile) {
        this.mProfile = mProfile;
    }

    public void setLocation(List<Integer> location) {
        this.location = location;
    }
}
