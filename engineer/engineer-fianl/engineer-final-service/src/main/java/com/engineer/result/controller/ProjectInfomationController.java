package com.engineer.result.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.write.builder.ExcelWriterSheetBuilder;
import com.engineer.common.enums.impl.FinalResultEnum;
import com.engineer.common.exceptions.ServiceException;
import com.engineer.common.vo.PageResult;
import com.engineer.result.pojo.CatalogManagement;
import com.engineer.result.pojo.Charging;
import com.engineer.result.pojo.ProjectInformation;
import com.engineer.result.service.ProjectInformationService;
import com.engineer.result.util.base.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/prj_information")
public class ProjectInfomationController extends BaseController {

    @GetMapping("/list")
    public ResponseEntity<PageResult<ProjectInformation>> queryChargingList(
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize", defaultValue = "100") Integer pageSize,
            @RequestParam(value = "keywords", required = false, defaultValue = "") String keywords,
            @RequestParam(value = "userId", required = false) Long userId
    ) {
        PageResult<ProjectInformation> chargingPageResult = projectInformationService.pageQueryList(pageNum, pageSize, keywords);
        chargingPageResult.setPageNum(pageNum);
        return ResponseEntity.ok(chargingPageResult);
    }

    @PostMapping("/add")
    public ResponseEntity<Void> saveDifference(@RequestBody ProjectInformation projectInfomation) {
        projectInformationService.save(projectInfomation);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/update")
    public ResponseEntity<Void> updateCharging(@RequestBody ProjectInformation projectInfomation) {
        projectInformationService.update(projectInfomation);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteDifferenceById(@PathVariable("id") Long id) {
        if (id == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        try {
            projectInformationService.delete(id);

            /*
             * 引用：（1）单项工程信息表：single_engi_information_sheet 字段：engi_proj_id
             * */
            singleEngineerService.setEngi_proj_id(id);
        } catch (Exception e) {
            throw new ServiceException(FinalResultEnum.Delete_Failed);
        }

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/upload")
    public ResponseEntity<Void> uploadFileExcel(@RequestParam("file") MultipartFile multipartFile) throws IOException {

        if (multipartFile == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        projectInformationService.upload(multipartFile);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @RequestMapping(value = "/download.html", method = RequestMethod.GET)
    public ResponseEntity<Void> downloadFileExcel(HttpServletResponse httpServletResponse) throws Exception {
        try {
            OutputStream outputStream = ExcelUtil.getOutputStream("工程项目", httpServletResponse);

            List<ProjectInformation> all = projectInformationService.findAll();
            EasyExcel.write(outputStream, ProjectInformation.class).sheet("模板").doWrite(all);

        } catch (Exception e) {
            throw new ServiceException(FinalResultEnum.Download_Failed);
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/select_options")
    public ResponseEntity<List<ProjectInformation>> selectOptions() {
        return ResponseEntity.ok(projectInformationService.findAll());
    }


}
