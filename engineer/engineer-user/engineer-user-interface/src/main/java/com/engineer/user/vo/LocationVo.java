package com.engineer.user.vo;

import com.engineer.user.pojo.Location;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author Lemon
 * @date 2020/3/29 14:28
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class LocationVo extends Location {
    List<LocationVo> children;
}
