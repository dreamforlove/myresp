package com.engineer.user.vo;

import com.engineer.user.pojo.Role;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Lemon
 * @date 2020/4/8 16:14
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserRoleVo extends Role {
    private Integer status;
}
