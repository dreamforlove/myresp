package com.engineer.common.vo;

import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * @author Lemon
 * @date 2019/9/10 18:38
 */
@Data
@ToString
public class UserInfo {
    private Long userId;
    private String userAccount;
    private String userName;
    private List<String> menu;
    private List<String> authorities;
}
