package com.engineer.result.controller;

import com.alibaba.excel.EasyExcel;
import com.engineer.common.enums.impl.FinalResultEnum;
import com.engineer.common.exceptions.ServiceException;
import com.engineer.common.vo.PageResult;
import com.engineer.result.pojo.ProjectInformation;
import com.engineer.result.pojo.SingleEngineer;
import com.engineer.result.service.SingleEngineerService;
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
@RequestMapping("prj_single")
public class SingleEngineerController extends  BaseController {
   @GetMapping("/list")
    public ResponseEntity<PageResult<SingleEngineerService>> queryChargingList(
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize", defaultValue = "100") Integer pageSize,
            @RequestParam(value = "keywords", required = false,defaultValue="") String keywords,
            @RequestParam(value = "userId", required = false) Long userId
    ){
        PageResult<SingleEngineerService> chargingPageResult = singleEngineerService.pageQueryList(pageNum, pageSize, keywords);
        chargingPageResult.setPageNum(pageNum);
        return ResponseEntity.ok(chargingPageResult);
    }

    @PostMapping("/add")
    public ResponseEntity<Void> saveDifference(@RequestBody SingleEngineer singleEngineer){
        singleEngineerService.save(singleEngineer);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @PutMapping("/update")
    public ResponseEntity<Void> updateCharging(@RequestBody SingleEngineer singleEngineer){
        singleEngineerService.update(singleEngineer);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteDifferenceById(@PathVariable("id")Long id){
        if (id==null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        try {
            singleEngineerService.delete(id);
            /*
             * 引用：（1）单位工程信息表：unit_engi_Information_sheet 字段： single_engi_id
             * */
            unitEngineerService.setSingle_engi_id(id);
        }catch (Exception e){
            throw new ServiceException(FinalResultEnum.Delete_Failed);
        }

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @PostMapping("/upload")
    public ResponseEntity<Void> uploadFileExcel(@RequestParam("file") MultipartFile multipartFile) throws IOException {

        if (multipartFile==null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        singleEngineerService.upload(multipartFile);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @RequestMapping(value="/download.html",method = RequestMethod.GET)
    public ResponseEntity<Void> downloadFileExcel(HttpServletResponse httpServletResponse) throws Exception {
        try {
            OutputStream outputStream = ExcelUtil.getOutputStream("单项工程", httpServletResponse);

            List<SingleEngineer> all = singleEngineerService.findAll();
            EasyExcel.write(outputStream, SingleEngineer.class).sheet("模板").doWrite(all);

        }catch (Exception e){
            throw new ServiceException(FinalResultEnum.Download_Failed);
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @GetMapping("/select_options")
    public ResponseEntity<List<SingleEngineerService>> selectOptions(){
        return ResponseEntity.ok(singleEngineerService.findAll());
    }


}
