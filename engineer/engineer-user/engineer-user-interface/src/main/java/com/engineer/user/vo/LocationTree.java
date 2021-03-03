package com.engineer.user.vo;

import lombok.Data;

import java.util.List;

/**
 * @author Lemon
 * @date 2020/3/13 14:53
 */
@Data
public class LocationTree {
    private int value;
    private String label;
    private List<LocationTree> children;
}
