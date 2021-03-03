package com.engineer.result.pojo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "zy_difference")
public class Difference {
    @Id
    @ExcelProperty(value = "调差表编号", index = 0)
    private Long diff_id; //调差表编号
    @ExcelProperty(value = "用户编码", index = 1)
    private Long user_id; //用户编码
    @ExcelProperty(value = "工料机编号", index = 2)
    private Long lmm_id; //工料机编号
    @ExcelProperty(value = "工料机名称", index = 3)
    private String lmm_name; //工料机名称
    @ExcelProperty(value = "单位", index = 4)
    private String unit; //单位
    @ExcelProperty(value = "市场价", index = 5)
    private Double price; //市场价
    @ExcelProperty(value = "产品ID", index = 6)
    private Long productid; //产品ID
}
