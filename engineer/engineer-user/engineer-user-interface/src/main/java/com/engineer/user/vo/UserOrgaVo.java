package com.engineer.user.vo;

import com.engineer.user.pojo.Organization;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Lemon
 * @date 2020/4/5 16:18
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserOrgaVo extends Organization {
    private Long id;
    private Long userId;
    private Integer status;
}
