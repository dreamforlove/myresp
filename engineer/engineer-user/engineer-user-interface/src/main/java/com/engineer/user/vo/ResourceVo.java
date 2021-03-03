package com.engineer.user.vo;

import com.engineer.user.pojo.Resource;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Lemon
 * @date 2020/3/24 19:42
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ResourceVo extends Resource {
    private Integer enabled;
    private Long createrId;
}
