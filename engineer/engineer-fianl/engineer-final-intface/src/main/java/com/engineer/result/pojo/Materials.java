package com.engineer.result.pojo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "zy_lmm_summary_sheet")
public class Materials {
    @Id
    @ExcelProperty(value = "工料机汇总编码号", index = 0)
    private Long lmm_summ_id;
    @ExcelProperty(value = "单位工程编码", index = 1)
    private Long unit_engi_id;
    @ExcelProperty(value = "调差表编号", index = 2)
    private Long diff_id;
    @ExcelProperty(value = "工料机编码", index =3)
    private Long lmm_id;
    @ExcelProperty(value = "工料机名称", index = 4)
    private String lmm_name;
    @ExcelProperty(value = "单位", index = 5)
    private String unit;
    @ExcelProperty(value = "定额价", index = 6)
    private Double quota_price;
    @ExcelProperty(value = "市场价", index = 7)
    private Double price;
    @ExcelProperty(value = "价差", index = 8)
    private Double differences;
    @ExcelProperty(value = "汇总量1", index = 9)
    private Double summ_one;
    @ExcelProperty(value = "汇总量2", index = 10)
    private Double summ_two;
    @ExcelProperty(value = "价差合计", index = 11)
    private Double differences_summ;
    @ExcelProperty(value = "产品ID", index = 12)
    private Long productid;
}
