package com.engineer.result.service.impl;

import com.engineer.common.enums.impl.FinalResultEnum;
import com.engineer.common.exceptions.ServiceException;
import com.engineer.common.vo.PageResult;
import com.engineer.result.pojo.QuotaList;
import com.engineer.result.service.QuotaListService;
import com.engineer.result.service.base.Impl.BaseServiceImpl;
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
public class QuotaListServiceImpl extends BaseServiceImpl<QuotaList> implements QuotaListService<QuotaList> {

    @Override
    public void save(QuotaList quotaList) {
        int insert = quotaListMapper.insert(quotaList);
        if (insert==0){
            throw new ServiceException(FinalResultEnum.Save_Failed);
        }
    }

    @Override
    public void update(QuotaList quotaList) {
        int i = quotaListMapper.updateByPrimaryKey(quotaList);
        if (i==0){
            throw new ServiceException(FinalResultEnum.QuotaList_Update_Failed);
        }


    }

    @Override
    public void delete(Long id) {
        int i = quotaListMapper.deleteByPrimaryKey(id);
        if (i==0){
            throw new ServiceException(FinalResultEnum.Delete_Failed);
        }
    }

    @Override
    public QuotaList find(Long id) {
        QuotaList quotaList = quotaListMapper.selectByPrimaryKey(id);
        if (quotaList==null){
            throw new ServiceException(FinalResultEnum.Find_Failed);
        }

        return quotaList;
    }

    @Override
    public List<QuotaList> findAll() {
        List<QuotaList> quotaLists = quotaListMapper.selectAll();
        if (CollectionUtils.isEmpty(quotaLists)){
            throw new ServiceException(FinalResultEnum.List_Not_Found);
        }

        return quotaLists;
    }

    @Override
    public PageResult<QuotaList> pageQueryList(Integer pageNum, Integer pageSize, String keywords) {
        try {
            PageHelper.startPage(pageNum,pageSize);
            Example example =new Example(QuotaList.class);
            if (StringUtils.isNotBlank(keywords)){
                example.createCriteria().orLike("take_variable","%"+keywords+"%").orEqualTo("variable_id",keywords).orEqualTo("user_id",keywords).orEqualTo("productid",keywords);
                List<QuotaList> select = quotaListMapper.selectByExample(example);
                //5.解析分页结果   PageInfo
                PageInfo<QuotaList> info = new PageInfo<>(select);
                return new PageResult<QuotaList>(info.getTotal(),select);
            }else {
                List<QuotaList> select = quotaListMapper.selectAll();
                PageInfo<QuotaList> info = new PageInfo<>(select);
                return new PageResult<QuotaList>(info.getTotal(),select);
            }
        }catch (Exception e){
            throw new ServiceException(FinalResultEnum.List_Not_Found);
        }

    }

    @Override
    public void upload(MultipartFile multipartFile) throws IOException {
    }

    @Override
    public void setProj_id(Long proj_id) {
        int i = quotaListMapper.setProj_id(proj_id);
    }
}
