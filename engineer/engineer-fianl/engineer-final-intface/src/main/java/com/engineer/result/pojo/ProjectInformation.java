package com.engineer.result.pojo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.alibaba.excel.annotation.format.NumberFormat;
import com.engineer.result.util.DateUtil;
import com.engineer.result.util.DatetoLongSerializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;
import java.sql.Timestamp;


@Data
@Table(name = "zy_engi_proj_information_sheet")
public class ProjectInformation {

    @Id
    @ExcelProperty(value = "工程项目编码", index = 0)
    private Long engi_proj_id;
    @ExcelProperty(value = "工程项目名称", index = 1)
    private String engi_proj_name;
    @ExcelProperty(value = "结点编码", index = 2)
    private Long node_id;
    @ExcelProperty(value = "用户编码", index = 3)
    private Long user_id;
    @ExcelProperty(value = "建设单位名称", index = 4)
    private String cons_orga_name;
    @ExcelProperty(value = "法定代表人", index = 5)
    private String legal_person;
    @ExcelProperty(value = "建设规模 ", index = 6)
    private String cons_scale;
    @ExcelProperty(value = "合同编号 ", index = 7)
    private Long contract_id;
    @ExcelProperty(value = "签订日期 ", index = 8)
    private String date = DateUtil.timeStamp2Date();
    @ExcelProperty(value = "工程地址", index = 9)
    private String engi_site;
    @ExcelProperty(value = "单项工程总价", index = 10)
    private Double single_engi_price;
    @ExcelProperty(value = "单位造价指标", index = 11)
    private Double unit_cost_index;
    @ExcelProperty(value = "单方造价指标", index = 12)
    private Double per_cost_index;
    @ExcelProperty(value = "产品ID", index = 13)
    private Long productid;


}
