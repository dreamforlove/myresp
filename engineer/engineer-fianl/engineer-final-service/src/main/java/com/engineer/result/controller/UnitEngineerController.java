package com.engineer.result.controller;

import com.alibaba.excel.EasyExcel;
import com.engineer.common.enums.impl.FinalResultEnum;
import com.engineer.common.exceptions.ServiceException;
import com.engineer.common.vo.PageResult;
import com.engineer.result.pojo.UnitEngineer;
import com.engineer.result.pojo.Variable;
import com.engineer.result.service.UnitEngineerService;
import com.engineer.result.util.base.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

@RestController
@RequestMapping("unitEngineer")
public class UnitEngineerController extends  BaseController {


    @GetMapping("/list")
    public ResponseEntity<PageResult<UnitEngineer>> queryDifferenceList(
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize", defaultValue = "100") Integer pageSize,
            @RequestParam(value = "keywords", required = false,defaultValue="") String keywords,
            @RequestParam(value = "userId", required = false) Long userId
    ){

        PageResult<UnitEngineer> differenceServicePageResult = unitEngineerService.pageQueryList(pageNum, pageSize, keywords);
        differenceServicePageResult.setPageNum(pageNum);
        return ResponseEntity.ok(differenceServicePageResult);
    }

    @PostMapping("/add")
    public ResponseEntity<Void> saveDifference(@RequestBody UnitEngineer unitEngineer){

        unitEngineerService.save(unitEngineer);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @PutMapping("/update")
    public ResponseEntity<Void> updateDifference(@RequestBody UnitEngineer unitEngineer){


        unitEngineerService.update(unitEngineer);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteDifferenceById(@PathVariable("id")Long id){
        if (id==null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        try {
            unitEngineerService.delete(id);
        /*
        * 引用：（1）工料机汇总表：lmm_summary_sheet  字段：unit_engi_id
                （2）清单定额工程量：bill quantities 字段：unit_engi_id
        *
        * */
            materialsService.setUnit_engi_id(id);
            billQuantityService.setUnit_engi_id(id);
        }catch (Exception e){
            throw new ServiceException(FinalResultEnum.Delete_Failed);
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/upload")
    public ResponseEntity<Void> uploadFileExcel(@RequestParam("file") MultipartFile multipartFile) throws IOException {


        unitEngineerService.upload(multipartFile);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @RequestMapping(value="/download.html",method = RequestMethod.GET)
    public ResponseEntity<Void> downloadFileExcel(HttpServletResponse httpServletResponse) throws Exception {
        try {
            OutputStream outputStream = ExcelUtil.getOutputStream("单位工程", httpServletResponse);
            List<UnitEngineer> all = unitEngineerService.findAll();
            EasyExcel.write(outputStream, UnitEngineer.class).sheet("模板").doWrite(all);

        }catch (Exception e){
            throw new ServiceException(FinalResultEnum.Difference_Download_Failed);
        }

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/select_options")
    public ResponseEntity<List<UnitEngineer>> selectOptions(){
        return ResponseEntity.ok(unitEngineerService.findAll());
    }

}
