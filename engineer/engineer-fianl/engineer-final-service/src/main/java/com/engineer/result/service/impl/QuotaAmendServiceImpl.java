package com.engineer.result.service.impl;

import com.engineer.common.enums.impl.FinalResultEnum;
import com.engineer.common.exceptions.ServiceException;
import com.engineer.common.vo.PageResult;
import com.engineer.result.pojo.QuotaAmend;
import com.engineer.result.service.QuotaAmendService;
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
public class QuotaAmendServiceImpl extends BaseServiceImpl<QuotaAmend> implements QuotaAmendService<QuotaAmend> {

    @Override
    public void save(QuotaAmend quotaAmend) {
        int insert = quotaAmendMapper.insert(quotaAmend);
        if (insert==0){
            throw new ServiceException(FinalResultEnum.Save_Failed);
        }
    }

    @Override
    public void update(QuotaAmend quotaAmend) {
        int i = quotaAmendMapper.updateByPrimaryKey(quotaAmend);
        if (i==0){
            throw new ServiceException(FinalResultEnum.QuotaAmend_Update_Failed);
        }
    }

    @Override
    public void delete(Long id) {
        int i = quotaAmendMapper.deleteByPrimaryKey(id);
        if (i==0){
            throw new ServiceException(FinalResultEnum.Delete_Failed);
        }
    }

    @Override
    public QuotaAmend find(Long id) {
        QuotaAmend quotaAmend = quotaAmendMapper.selectByPrimaryKey(id);
        if (quotaAmend==null){
            throw new ServiceException(FinalResultEnum.Find_Failed);
        }

        return quotaAmend;
    }

    @Override
    public List<QuotaAmend> findAll() {
        List<QuotaAmend> quotaAmends = quotaAmendMapper.selectAll();
        if (CollectionUtils.isEmpty(quotaAmends)){
            throw new ServiceException(FinalResultEnum.List_Not_Found);
        }

        return null;
    }

    @Override
    public PageResult<QuotaAmend> pageQueryList(Integer pageNum, Integer pageSize, String keywords) {
        try {
            PageHelper.startPage(pageNum,pageSize);
            Example example =new Example(QuotaAmend.class);
            if (StringUtils.isNotBlank(keywords)){
                example.createCriteria().orLike("take_variable","%"+keywords+"%").orEqualTo("variable_id",keywords).orEqualTo("user_id",keywords).orEqualTo("productid",keywords);
                List<QuotaAmend> select = quotaAmendMapper.selectByExample(example);
                //5.解析分页结果   PageInfo
                PageInfo<QuotaAmend> info = new PageInfo<>(select);
                return new PageResult<QuotaAmend>(info.getTotal(),select);
            }else {
                List<QuotaAmend> select = quotaAmendMapper.selectAll();
                PageInfo<QuotaAmend> info = new PageInfo<>(select);
                return new PageResult<QuotaAmend>(info.getTotal(),select);
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
        int i = quotaAmendMapper.setProj_id(proj_id);

    }
}
