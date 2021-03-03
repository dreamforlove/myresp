package com.engineer.result.pojo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "zy_charging")
public class Charging {
    @Id
    @ExcelProperty(value = "取费表编码", index = 0)
    private Long charging_id;
    @ExcelProperty(value = "变量ID编码", index = 1)
    private Long variate_id;
    @ExcelProperty(value = "用户编码", index = 2)
    private Long user_id;
    @ExcelProperty(value = "序号", index = 3)
    private Long sequence;
    @ExcelProperty(value = "取费项目名", index = 4)
    private String fee_proj_name;
    @ExcelProperty(value = "取费项目计算公式", index = 5)
    private String fee_proj_formula;
    @ExcelProperty(value = "费率", index = 6)
    private Double rate;
    @ExcelProperty(value = "费用金额", index = 7)
    private Double cost;
    @ExcelProperty(value = "产品ID", index = 8)
    private Long productid;
}
