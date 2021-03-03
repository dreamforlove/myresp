package com.engineer.result.controller;

import com.alibaba.excel.EasyExcel;
import com.engineer.common.enums.impl.FinalResultEnum;
import com.engineer.common.exceptions.ServiceException;
import com.engineer.common.vo.PageResult;
import com.engineer.result.pojo.CatalogManagement;
import com.engineer.result.pojo.Variable;
import com.engineer.result.service.CatalogManagementService;
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
@RequestMapping("prj_management")
public class CatalogManagementController extends  BaseController {

    @GetMapping("/list")
    public ResponseEntity<PageResult<CatalogManagement>> queryDifferenceList(
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize", defaultValue = "100") Integer pageSize,
            @RequestParam(value = "keywords", required = false,defaultValue="") String keywords,
            @RequestParam(value = "userId", required = false) Long userId
    ){
//        System.out.println("pageSize------>"+pageSize);
        PageResult<CatalogManagement> differenceServicePageResult = catalogManagementService.pageQueryList(pageNum, pageSize, keywords);
        differenceServicePageResult.setPageNum(pageNum);
        return ResponseEntity.ok(differenceServicePageResult);
    }

    @PostMapping("/add")
    public ResponseEntity<Void> saveDifference(@RequestBody CatalogManagement catalogManagement){
      if (catalogManagement.getNode_id()==null
            &&catalogManagement.getProductid()==null
              && !StringUtils.isNotBlank(catalogManagement.getNode_name())
              &&!StringUtils.isNotBlank(catalogManagement.getEngi_proj_type())
      ){
          return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
      }

        catalogManagementService.save(catalogManagement);


        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @PutMapping("/update")
    public ResponseEntity<Void> updateDifference(@RequestBody CatalogManagement catalogManagement){
        if (catalogManagement.getNode_id()==null
                &&catalogManagement.getProductid()==null
                && !StringUtils.isNotBlank(catalogManagement.getNode_name())
                &&!StringUtils.isNotBlank(catalogManagement.getEngi_proj_type())
        ){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        catalogManagementService.update(catalogManagement);


        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteDifferenceById(@PathVariable("id")Long id){
        if (id==null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        /*
        *引用：（1）工程项目信息表：engi_proj_Information_sheet  字段：node_ id
（2）单项工程信息表：single_engi_information_sheet 字段：node_ id
（3）单位工程信息表：unit_engi_Information_sheet 字段：node_ id
        *
        * */
        try {
            catalogManagementService.delete(id);
            projectInformationService.setNode_id(id);
            singleEngineerService.setNode_id(id);
            unitEngineerService.setNode_id(id);
        }catch (Exception e){
            throw new ServiceException(FinalResultEnum.Delete_Failed);
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/upload")
    public ResponseEntity<Void> uploadFileExcel(@RequestParam("file") MultipartFile multipartFile) throws IOException {
        catalogManagementService.upload(multipartFile);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @RequestMapping(value="/download.html",method = RequestMethod.GET)
    public ResponseEntity<Void> downloadFileExcel(HttpServletResponse httpServletResponse) throws Exception {
        try {
            OutputStream outputStream = ExcelUtil.getOutputStream("工程目录表", httpServletResponse);
            List<CatalogManagement> all = catalogManagementService.findAll();
            EasyExcel.write(outputStream, CatalogManagement.class).sheet("模板").doWrite(all);

        }catch (Exception e){
            throw new ServiceException(FinalResultEnum.Difference_Download_Failed);
        }

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/select_options")
    public ResponseEntity<List<CatalogManagement>> selectOptions(){
        return ResponseEntity.ok(catalogManagementService.findAll());
    }
}
