package com.engineer.result.controller;

import com.alibaba.excel.EasyExcel;
import com.engineer.common.enums.impl.FinalResultEnum;
import com.engineer.common.exceptions.ServiceException;
import com.engineer.common.vo.PageResult;
import com.engineer.result.pojo.Difference;
import com.engineer.result.pojo.Variable;
import com.engineer.result.service.VariableService;
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
@RequestMapping("/variable")
public class VariableController extends  BaseController {
 @GetMapping("/list")
    public ResponseEntity<PageResult<Variable>> queryDifferenceList(
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize", defaultValue = "100") Integer pageSize,
            @RequestParam(value = "keywords", required = false,defaultValue="") String keywords,
            @RequestParam(value = "userId", required = false) Long userId
    ){
        System.out.println("pageSize------>"+pageSize);
        PageResult<Variable> differenceServicePageResult = variableService.pageQueryList(pageNum, pageSize, keywords);
        differenceServicePageResult.setPageNum(pageNum);
        return ResponseEntity.ok(differenceServicePageResult);
    }

    @PostMapping("/add")
    public ResponseEntity<Void> saveDifference(@RequestBody Variable variable){
        if (variable.getVariable_id() ==null&&variable.getUser_id()==null&&variable.getProductid()==null&&variable.getTake_variable()==null&&variable.getTake_describe()==null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        variableService.save(variable);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @PutMapping("/update")
    public ResponseEntity<Void> updateDifference(@RequestBody Variable variable){
        if (variable.getVariable_id() ==null&&variable.getUser_id()==null&&variable.getProductid()==null&&variable.getTake_variable()==null&&variable.getTake_describe()==null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        variableService.update(variable);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteDifferenceById(@PathVariable("id")Long id){
        if (id==null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        /*
        * （1）取费表 charging 字段 variate_id
        * */
        try {
            variableService.delete(id);
            chargingService.setVariate_id(id);

        }catch (Exception e){
            throw new ServiceException(FinalResultEnum.Delete_Failed);
        }


        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/upload")
    public ResponseEntity<Void> uploadFileExcel(@RequestParam("file") MultipartFile multipartFile) throws IOException {
        System.out.println(multipartFile);

        variableService.upload(multipartFile);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @RequestMapping(value="/download.html",method = RequestMethod.GET)
    public ResponseEntity<Void> downloadFileExcel(HttpServletResponse httpServletResponse) throws Exception {
        try {
            OutputStream outputStream = ExcelUtil.getOutputStream("变量表", httpServletResponse);
            List<Variable> all = variableService.findAll();
            EasyExcel.write(outputStream, Variable.class).sheet("模板").doWrite(all);

        }catch (Exception e){
            throw new ServiceException(FinalResultEnum.Difference_Download_Failed);
        }

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/select_options")
    public ResponseEntity<List<Variable>> selectOptions(){
        return ResponseEntity.ok(variableService.findAll());
    }

}
