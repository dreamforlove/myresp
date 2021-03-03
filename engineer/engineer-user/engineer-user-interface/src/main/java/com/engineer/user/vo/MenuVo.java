package com.engineer.user.vo;

import com.engineer.user.pojo.Menu;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Lemon
 * @date 2020/3/23 20:16
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class MenuVo extends Menu {
    private Long createrId;
    private Integer enabled;
}
