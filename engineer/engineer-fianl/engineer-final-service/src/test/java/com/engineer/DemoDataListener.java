package com.engineer;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class DemoDataListener extends AnalysisEventListener<DemoData> {
    @Autowired
    private DemoMapper demoMapper;
    List<DemoData> list = new ArrayList<DemoData>();
    @Override
    public void invoke(DemoData data, AnalysisContext context) {
        System.out.println(data);
        demoMapper.insert(data);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {

        for (DemoData demoData:list){

        }
    }
}