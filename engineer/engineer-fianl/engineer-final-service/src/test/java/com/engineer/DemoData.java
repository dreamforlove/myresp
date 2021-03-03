package com.engineer;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;

import javax.persistence.Table;
import java.util.Date;


@Data
@Table(name = "zy_demo")
public class DemoData extends BaseRowModel {
    @ExcelProperty(value = "字符串", index = 0)
    private String string;
    @ExcelProperty(value = "树期", index = 1)
    private Double date;
    @ExcelProperty(value = "数字", index = 2)
    private Double doubleDate;
}
