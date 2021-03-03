package com.engineer.result.controller;


import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson.JSON;
import com.engineer.common.enums.impl.FinalResultEnum;
import com.engineer.common.exceptions.ServiceException;
import com.engineer.common.vo.PageResult;
import com.engineer.result.pojo.Difference;
import com.engineer.result.service.DifferenceService;
import com.engineer.result.util.base.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/difference")
public class DifferenceController extends  BaseController {


    @GetMapping("/list")
    public ResponseEntity<PageResult<Difference>> queryDifferenceList(
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize", defaultValue = "100") Integer pageSize,
            @RequestParam(value = "keywords", required = false,defaultValue="") String keywords,
            @RequestParam(value = "userId", required = false) Long userId
    ){

        System.out.println("-----------1111111");
        PageResult<Difference> differenceServicePageResult = differenceService.queryDifferenceList(pageNum, pageSize, keywords);
        differenceServicePageResult.setPageNum(pageNum);
        return ResponseEntity.ok(differenceServicePageResult);
    }

    @PostMapping("/add")
    public ResponseEntity<Void> saveDifference(@RequestBody Difference difference){
        if (difference.getDiff_id() ==null&&difference.getLmm_id()==null&&difference.getLmm_name()==null&&difference.getUnit()==null&&difference.getPrice()==null&&difference.getUser_id()==null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        differenceService.save(difference);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @PutMapping("/update")
    public ResponseEntity<Void> updateDifference(@RequestBody Difference difference){
        if (difference.getDiff_id() ==null&&difference.getLmm_id()==null&&difference.getLmm_name()==null&&difference.getUnit()==null&&difference.getPrice()==null&&difference.getUser_id()==null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        differenceService.update(difference);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteDifferenceById(@PathVariable("id")Long id){
        if (id==null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }





        /*
        *调差表ID： diff_id
        * 调用方1：单位工程表：unit -engi-information   字段：difference_id
        *调用方2：工料机汇总表：lmm_summary_sheet  字段：diff_id
        * */
        try { differenceService.delete(id);
            unitEngineerService.setDifference_id(id);
            materialsService.setDifference_id(id);
        }catch (Exception e){
            throw new ServiceException(FinalResultEnum.Delete_Failed);
        }



        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @PostMapping("/upload")
    public ResponseEntity<Void> uploadFileExcel(@RequestParam("file") MultipartFile multipartFile) throws IOException {
//        System.out.println("--------》开始执行upload");
//        System.out.println("------------->"+multipartFile);
         differenceService.upload(multipartFile);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @RequestMapping(value="/download.html",method = RequestMethod.GET)
    public ResponseEntity<Void> downloadFileExcel(HttpServletResponse httpServletResponse) throws Exception {
        try {
            OutputStream outputStream = ExcelUtil.getOutputStream("调差表", httpServletResponse);
            List<Difference> all = differenceService.findAll();
            EasyExcel.write(outputStream, Difference.class).sheet("模板").doWrite(all);

        }catch (Exception e){
            throw new ServiceException(FinalResultEnum.Difference_Download_Failed);
        }

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @GetMapping("/select_options")
    public ResponseEntity<List<Difference>> selectOptions(){
        return ResponseEntity.ok(differenceService.findAll());
    }
//

}
