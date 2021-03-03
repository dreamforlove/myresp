package com.engineer.result.pojo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "zy_variable")
@AllArgsConstructor
@NoArgsConstructor
public class Variable {
    @Id
    @ExcelProperty(value = "变量ID", index = 0)
    private Long variable_id; //变量ID
    @ExcelProperty(value = "用户ID", index = 1)
    private Long user_id; //用户ID
    @ExcelProperty(value = "变量公式", index = 2)
    private String take_variable; //变量公式
    @ExcelProperty(value = "变量描述", index = 3)
    private String take_describe; //变量描述
    @ExcelProperty(value = "产品ID", index = 4)
    private Long  productid;  //产品ID

}
