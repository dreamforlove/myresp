package com.engineer.result.pojo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "zy_project_catalog_management_table")
public class CatalogManagement {
    @Id
    @ExcelProperty(value = "结点编码", index = 0)
    private Long node_id;
    @ExcelProperty(value = "结点名称", index = 1)
    private String node_name;
    @ExcelProperty(value = "工程项目类型", index = 2)
    private String engi_proj_type;
    @ExcelProperty(value = "产品ID", index = 3)
    private Long productid;
}
