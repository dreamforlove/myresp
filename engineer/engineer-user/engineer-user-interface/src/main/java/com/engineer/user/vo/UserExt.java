package com.engineer.user.vo;

import com.engineer.user.pojo.Menu;
import com.engineer.user.pojo.Permission;
import com.engineer.user.pojo.Role;
import com.engineer.user.pojo.User;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author Lemon
 * @date 2019/10/19 18:20
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserExt extends User {
    private List<String> resource;
    private List<String> menu;
}
