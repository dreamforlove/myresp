package com.engineer.result.controller;

import com.alibaba.excel.EasyExcel;
import com.engineer.common.enums.impl.FinalResultEnum;
import com.engineer.common.exceptions.ServiceException;
import com.engineer.common.vo.PageResult;
import com.engineer.result.pojo.Charging;
import com.engineer.result.pojo.Difference;
import com.engineer.result.pojo.Variable;
import com.engineer.result.service.ChargingService;
import com.engineer.result.service.SingleEngineerService;
import com.engineer.result.service.VariableService;
import com.engineer.result.util.base.ExcelUtil;
import org.apache.commons.lang.StringUtils;
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
@RequestMapping("/charging")
public class ChargingController extends  BaseController {
     @GetMapping("/list")
    public ResponseEntity<PageResult<Charging>> queryChargingList(
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize", defaultValue = "100") Integer pageSize,
            @RequestParam(value = "keywords", required = false,defaultValue="") String keywords,
            @RequestParam(value = "userId", required = false) Long userId
    ){
        PageResult<Charging> chargingPageResult = chargingService.pageQueryList(pageNum, pageSize, keywords);
        chargingPageResult.setPageNum(pageNum);
        return ResponseEntity.ok(chargingPageResult);
    }

    @PostMapping("/add")
    public ResponseEntity<Void> saveDifference(@RequestBody Charging charging){
        if (  charging.getCharging_id()==null
                &&charging.getVariate_id()==null
                &&charging.getUser_id()==null
                &&charging.getSequence()==null
                &&!StringUtils.isNotBlank(charging.getFee_proj_name())
                &&!StringUtils.isNotBlank(charging.getFee_proj_formula())
                &&charging.getRate()==null
                &&charging.getCost()==null
                &&charging.getProductid()==null
        ){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        chargingService.save(charging);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @PutMapping("/update")
    public ResponseEntity<Void> updateCharging(@RequestBody Charging charging){
        if (  charging.getCharging_id()==null
                &&charging.getVariate_id()==null
                &&charging.getUser_id()==null
                &&charging.getSequence()==null
                &&!StringUtils.isNotBlank(charging.getFee_proj_name())
                &&!StringUtils.isNotBlank(charging.getFee_proj_formula())
                &&charging.getRate()==null
                &&charging.getCost()==null
                &&charging.getProductid()==null
        ){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        chargingService.update(charging);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteDifferenceById(@PathVariable("id")Long id){
        if (id==null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        try {
            chargingService.delete(id);
            /*
             * （1）单位工程信息表：unit_engi_Information_sheet 字段：charging_id
             *
             * */
            unitEngineerService.setCharging_id(id);
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
        chargingService.upload(multipartFile);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @RequestMapping(value="/download.html",method = RequestMethod.GET)
    public ResponseEntity<Void> downloadFileExcel(HttpServletResponse httpServletResponse) throws Exception {
        try {
            OutputStream outputStream = ExcelUtil.getOutputStream("取费管理", httpServletResponse);
            List<Charging> all = chargingService.findAll();
            EasyExcel.write(outputStream, Charging.class).sheet("模板").doWrite(all);
        }catch (Exception e){
            throw new ServiceException(FinalResultEnum.Download_Failed);
        }

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @GetMapping("/select_options")
    public ResponseEntity<List<Charging>> selectOptions(){
        return ResponseEntity.ok(chargingService.findAll());
    }


}
