package com.engineer.result.controller;

import com.alibaba.excel.EasyExcel;
import com.engineer.common.enums.impl.FinalResultEnum;
import com.engineer.common.exceptions.ServiceException;
import com.engineer.common.vo.PageResult;
import com.engineer.result.pojo.QuotaList;
import com.engineer.result.pojo.Variable;
import com.engineer.result.service.QuotaListService;
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
@RequestMapping("/quotaMake")
public class QuotaListController extends  BaseController {
   @GetMapping("/list")
    public ResponseEntity<PageResult<QuotaList>> queryDifferenceList(
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize", defaultValue = "100") Integer pageSize,
            @RequestParam(value = "keywords", required = false,defaultValue="") String keywords,
            @RequestParam(value = "userId", required = false) Long userId
    ){
        PageResult<QuotaList> differenceServicePageResult = quotaListService.pageQueryList(pageNum, pageSize, keywords);
        differenceServicePageResult.setPageNum(pageNum);
        return ResponseEntity.ok(differenceServicePageResult);
    }

    @PostMapping("/add")
    public ResponseEntity<Void> saveDifference(@RequestBody QuotaList quotaList){

        quotaListService.save(quotaList);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @PutMapping("/update")
    public ResponseEntity<Void> updateDifference(@RequestBody QuotaList quotaList){
        quotaListService.update(quotaList);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteDifferenceById(@PathVariable("id")Long id){
        if (id==null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        quotaListService.delete(id);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/upload")
    public ResponseEntity<Void> uploadFileExcel(@RequestParam("file") MultipartFile multipartFile) throws IOException {


        quotaListService.upload(multipartFile);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @RequestMapping(value="/download.html",method = RequestMethod.GET)
    public ResponseEntity<Void> downloadFileExcel(HttpServletResponse httpServletResponse) throws Exception {
        /*try {
            OutputStream outputStream = ExcelUtil.getOutputStream("变量表", httpServletResponse);
            List<Variable> all = variableService.findAll();
            EasyExcel.write(outputStream, Variable.class).sheet("模板").doWrite(all);

        }catch (Exception e){
            throw new ServiceException(FinalResultEnum.Difference_Download_Failed);
        }*/

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/select_options")
    public ResponseEntity<List<QuotaList>> selectOptions(){
        return ResponseEntity.ok(quotaListService.findAll());
    }

}
