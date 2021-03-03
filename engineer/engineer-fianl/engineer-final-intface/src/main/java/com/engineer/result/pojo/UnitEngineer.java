package com.engineer.result.pojo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "zy_unit_engi_information_sheet")
public class UnitEngineer {
    @Id
    @ExcelProperty(value = "单位工程编码", index = 0)
    private Long unit_engi_id;
    @ExcelProperty(value = "单位工程名称", index = 1)
    private String unit_engi_name;
    @ExcelProperty(value = "单项工程编码", index = 2)
    private Long single_engi_id;
    @ExcelProperty(value = "结点编码", index = 3)
    private Long node_id;
    @ExcelProperty(value = "调差表编码", index = 4)
    private Long difference_id;
    @ExcelProperty(value = "取费表编码", index = 5)
    private Long charging_id;
    @ExcelProperty(value = "选定调差文件", index = 6)
    private String difference_file;
    @ExcelProperty(value = "选定取费文件", index = 7)
    private String charging_file;
    @ExcelProperty(value = "本地定额名称", index = 8)
    private String local_quota_name;
    @ExcelProperty(value = "建筑面积", index = 9)
    private Double floor_area;
    @ExcelProperty(value = "层数", index = 10)
    private Long layer;
    @ExcelProperty(value = "檐高", index = 11)
    private Double eaves_height;
    @ExcelProperty(value = "结构类型", index = 12)
    private String structure_type;
    @ExcelProperty(value = "工程类别", index = 13)
    private String project_type;
    @ExcelProperty(value = "造价类别", index = 14)
    private String cost_type;
    @ExcelProperty(value = "预算编制单位", index = 15)
    private String budget_compile_orga;
    @ExcelProperty(value = "编制人员姓名", index = 16)
    private String comp_pers_name;
    @ExcelProperty(value = "预算编制日期", index = 17)
    private String budg_compile_date;
    @ExcelProperty(value = "单项工程总价", index = 18)
    private Double single_engi_price;
    @ExcelProperty(value = "单位造价指标", index = 19)
    private String unit_cost_index;
    @ExcelProperty(value = "单方造价指标", index = 20)
    private String per_cost_index;
    @ExcelProperty(value = "产品ID", index = 21)
    private Long productid;
}
