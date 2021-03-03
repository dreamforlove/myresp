package com.engineer.result.service.base;

import com.engineer.common.vo.PageResult;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface BaseService<T> {
    void save(T t);
    void update(T t);
    void delete(Long id);
    T find(Long id);
    List<T> findAll();
    PageResult<T> pageQueryList(Integer pageNum, Integer pageSize, String keywords);
    void upload(MultipartFile multipartFile) throws IOException;

}
