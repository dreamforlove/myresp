package com.engineer;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.engineer.common.enums.impl.FinalResultEnum;
import com.engineer.common.exceptions.ServiceException;
import com.engineer.result.mapper.*;
import com.engineer.result.service.BillQuantityService;
import com.engineer.result.service.QuotaAmendService;
import com.engineer.result.service.QuotaListService;
import com.engineer.result.util.DateUtil;
import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.*;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FileTest {
    @Autowired
    private UnitEngineerMapper unitEngineerMapper;
    @Test
    public void test() {


        unitEngineerMapper.setDifference_id(10L);
        /*
        * （1）定额组成表：quota_list字段：proj_id
           （2）定额修正表：quota_amend table字段：proj_id
        * */
//            quotaListService.setProj_id(id);
//            quotaAmendService.setProj_id(id);
    }


}
