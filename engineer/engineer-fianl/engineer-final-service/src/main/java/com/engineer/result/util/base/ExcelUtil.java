package com.engineer.result.util.base;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.read.metadata.ReadSheet;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class ExcelUtil {
    public ExcelUtil() {
    }
    //抽取出来一个监听器
    public static class ModelListener<T> extends AnalysisEventListener<T> {
        List<T> datas = new ArrayList<T>();
        @Override
        public void invoke(T data, AnalysisContext context) {
            datas.add(data);
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
    public static ExcelReader build ;
    static {
        build=null;
    }

    public static <T>List<T> upload(MultipartFile multipartFile, T t, ModelListener<T> modelListener) throws IOException {
        /**
         * @Description  提供上传文件所需要的参数 返回一个数组
         * @Author Mr.li
         * @Date   2020/4/25 0025 上午 2:26
         * @Param  [multipartFile 文件, t 实体, modelListener 用静态公共类]
         */

        build= EasyExcel.read(multipartFile.getInputStream(),t.getClass(), modelListener).build();
        ReadSheet build1 = EasyExcel.readSheet(0).build();
        ExcelReader read = build.read(build1);
        List<T> datas = modelListener.getDatas();
        return datas;
//            build.finish();
//            datas.clear();

    }
    /**
     * 导出文件时为Writer生成OutputStream
     * @param fileName
     * @param response
     * @return
     * @throws Exception
     */
    public static OutputStream getOutputStream(String fileName, HttpServletResponse response)
            throws Exception{
        try{
            fileName = URLEncoder.encode(fileName,"UTF-8");
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("UTF-8");
            //此处指定了文件类型为xls，如果是xlsx的，请自行替换修改
            response.setHeader("Content-Disposition", "attachment; filename=" + fileName + ".xlsx");
            response.setHeader("Pragma", "public");
            response.setHeader("Cache-Control", "no-store");
            response.addHeader("Cache-Control", "max-age=0");
            return response.getOutputStream();
        } catch (IOException e){
            throw new Exception("导出文件失败！");
        }
    }
}
