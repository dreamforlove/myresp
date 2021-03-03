package com.engineer.result.service.impl;

import com.engineer.common.enums.impl.FinalResultEnum;
import com.engineer.common.exceptions.ServiceException;
import com.engineer.common.vo.PageResult;
import com.engineer.result.pojo.CatalogManagement;
import com.engineer.result.service.CatalogManagementService;
import com.engineer.result.service.base.Impl.BaseServiceImpl;
import com.engineer.result.util.base.ExcelUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;
import tk.mybatis.mapper.entity.Example;

import java.io.IOException;
import java.util.List;

@Service
@Transactional
public class CatalogManagementServiceImpl extends BaseServiceImpl<CatalogManagement> implements CatalogManagementService<CatalogManagement> {

    @Override
    public void save(CatalogManagement catalogManagement) {
        int insert = catalogManagementMapper.insert(catalogManagement);
        if (insert==0){
            throw new ServiceException(FinalResultEnum.Save_Failed);
        }
    }

    @Override
    public void update(CatalogManagement catalogManagement) {
        int i = catalogManagementMapper.updateByPrimaryKey(catalogManagement);
        if (i==0){
            throw new ServiceException(FinalResultEnum.Update_Failed);
        }
    }

    @Override
    public void delete(Long id) {
        int i = catalogManagementMapper.deleteByPrimaryKey(id);
        if (i==0){
            throw new ServiceException(FinalResultEnum.Delete_Failed);
        }
    }

    @Override
    public CatalogManagement find(Long id) {
        CatalogManagement catalogManagement = catalogManagementMapper.selectByPrimaryKey(id);
        if (catalogManagement==null){
            throw new ServiceException(FinalResultEnum.Find_Failed);
        }
        return catalogManagement;
    }

    @Override
    public List<CatalogManagement> findAll() {
        List<CatalogManagement> catalogManagements = catalogManagementMapper.selectAll();
        if (CollectionUtils.isEmpty(catalogManagements)){
            throw new ServiceException(FinalResultEnum.List_Not_Found);
        }
        return catalogManagements;
    }

    @Override
    public PageResult<CatalogManagement> pageQueryList(Integer pageNum, Integer pageSize, String keywords) {
        try {
            PageHelper.startPage(pageNum, pageSize);
            Example example =new Example(CatalogManagement.class);
            if (StringUtils.isNotBlank(keywords)){
                example.createCriteria().orLike("node_name","%"+keywords+"%").orEqualTo("node_id",keywords);
                List<CatalogManagement> select = catalogManagementMapper.selectByExample(example);
                //5.解析分页结果   PageInfo
                PageInfo<CatalogManagement> info = new PageInfo<>(select);
                return new PageResult<CatalogManagement>(info.getTotal(),select);
            }else {
                List<CatalogManagement> select = catalogManagementMapper.selectAll();
                PageInfo<CatalogManagement> info = new PageInfo<>(select);
                return new PageResult<CatalogManagement>(info.getTotal(),select);
            }
        }catch (Exception e){
            throw new ServiceException(FinalResultEnum.List_Not_Found);
        }
    }

    @Override
    public void upload(MultipartFile multipartFile) throws IOException {

        List<CatalogManagement> upload = ExcelUtil.upload(multipartFile, new CatalogManagement(), new ExcelUtil.ModelListener<CatalogManagement>());
        int i = catalogManagementMapper.insertList(upload);
        if (i==0){
            throw new ServiceException(FinalResultEnum.Save_Failed);
        }
        ExcelUtil.build.finish();
        upload.clear();

    }
}
