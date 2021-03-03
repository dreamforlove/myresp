package com.engineer.result.pojo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "zy_bill_quantities")
public class BillQuantity {
    @Id
    @ExcelProperty(value = "项目编码", index = 0)
    private Long proj_id;
    @ExcelProperty(value = "项目名称", index = 2)
    private String proj_name;
    @ExcelProperty(value = "单位工程编码", index = 3)
    private Long unit_engi_id;
    @ExcelProperty(value = "清单编码", index = 4)
    private Long bill_id;
    @ExcelProperty(value = "定额编码", index = 5)
    private Long quota_id;
    @ExcelProperty(value = "序号", index = 1)
    private Long sequence;
    @ExcelProperty(value = "清单结点", index = 6)
    private String bill_node;
    @ExcelProperty(value = "清单特征描述", index = 7)
    private String bill_description;
    @ExcelProperty(value = "所属分部", index = 8)
    private String segment_id;
    @ExcelProperty(value = "单位", index = 9)
    private String unit;
    @ExcelProperty(value = "工程量", index = 10)
    private Double quantity;
    @ExcelProperty(value = "综合单价（元）", index = 11)
    private Double synt_price;
    @ExcelProperty(value = "合价（元)", index = 12)
    private Double comb_price;
    @ExcelProperty(value = "暂估价（元）", index = 13)
    private Double evaluate;
    @ExcelProperty(value = "产品ID", index = 14)
    private Long productid;

}
