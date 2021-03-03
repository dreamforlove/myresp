package com.engineer.result.util.base;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
public class BaseListener<T> extends AnalysisEventListener<T> {

    List<T> datas = new ArrayList<T>();
    @Override
    public void invoke(T t, AnalysisContext context) {
        datas.add(t);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
    }
    public List<T> getDatas() {
        return datas;
    }

    public void setDatas(List<T> datas) {
        this.datas = datas;
    }
}