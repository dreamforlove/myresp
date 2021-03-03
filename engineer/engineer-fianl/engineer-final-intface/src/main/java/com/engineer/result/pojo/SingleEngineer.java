package com.engineer.result.pojo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "zy_single_engi_information_sheet")
public class SingleEngineer {
    @Id
    @ExcelProperty(value = "单项工程编码", index = 0)
    private Long single_engi_id;
    @ExcelProperty(value = "单项工程名称", index = 1)
    private String single_engi_name;
    @ExcelProperty(value = "工程项目编码", index = 2)
    private Long engi_proj_id;
    @ExcelProperty(value = "结点编码", index = 3)
    private Long node_id;
    @ExcelProperty(value = "投标单位名称", index = 4)
    private String tender_orga_name;
    @ExcelProperty(value = "项目特征描述", index = 5)
    private String proj_feature;
    @ExcelProperty(value = "单项工程面积", index = 6)
    private Double single_engi_area;
    @ExcelProperty(value = "法定代表人", index = 7)
    private String legal_person;
    @ExcelProperty(value = "任务完成日期", index = 8)
    private String comp_date;
    @ExcelProperty(value = "单项工程总价", index = 9)
    private Double single_engi_price;
    @ExcelProperty(value = "单位造价指标", index = 10)
    private Double unit_cost_index;
    @ExcelProperty(value = "单方造价指标", index = 11)
    private Double per_cost_index;
    @ExcelProperty(value = "产品ID", index = 12)
    private Long productid;

}
