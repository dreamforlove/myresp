package com.engineer.user.vo;

import com.engineer.user.pojo.User;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Lemon
 * @date 2020/4/5 22:12
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class InvitedStaff extends User {
    private Long id;
    private Integer status;
}
