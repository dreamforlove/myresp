package com.engineer.result.service;

import com.engineer.common.vo.PageResult;
import com.engineer.result.pojo.Difference;
import com.engineer.result.service.base.BaseService;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface DifferenceService<T> extends BaseService<T> {
    PageResult<Difference> queryDifferenceList(Integer pageNum, Integer pageSize, String keywords);



}
