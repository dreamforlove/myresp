package com.engineer.result.service.impl;

import com.engineer.common.enums.impl.FinalResultEnum;
import com.engineer.common.exceptions.ServiceException;
import com.engineer.common.vo.PageResult;
import com.engineer.result.pojo.SingleEngineer;
import com.engineer.result.service.SingleEngineerService;
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
public class SingleEngineerServiceImpl extends BaseServiceImpl<SingleEngineer> implements SingleEngineerService<SingleEngineer> {


    @Override
    public void save(SingleEngineer singleEngineer) {
        int insert = singleEngineerMapper.insert(singleEngineer);
        if (insert==0){
            throw new ServiceException(FinalResultEnum.Save_Failed);
        }
    }

    @Override
    public void update(SingleEngineer singleEngineer) {
        int i = singleEngineerMapper.updateByPrimaryKey(singleEngineer);
        if (i==0){
            throw new ServiceException(FinalResultEnum.Update_Failed);
        }
    }

    @Override
    public void delete(Long id) {
        int i = singleEngineerMapper.deleteByPrimaryKey(id);
        if (i==0){
            throw new ServiceException(FinalResultEnum.Delete_Failed);
        }
    }

    @Override
    public SingleEngineer find(Long id) {
        SingleEngineer singleEngineer = singleEngineerMapper.selectByPrimaryKey(id);
        if (singleEngineer==null){
            throw new ServiceException(FinalResultEnum.Find_Failed);
        }
        return singleEngineer;
    }

    @Override
    public List<SingleEngineer> findAll() {
        List<SingleEngineer> singleEngineers = singleEngineerMapper.selectAll();
        if (CollectionUtils.isEmpty(singleEngineers)){
            throw new ServiceException(FinalResultEnum.List_Not_Found);
        }
        return singleEngineers;
    }

    @Override
    public PageResult<SingleEngineer> pageQueryList(Integer pageNum, Integer pageSize, String keywords) {
        try {


        Example example =new Example(SingleEngineer.class);
        PageHelper.startPage(pageNum,pageSize);
        if (StringUtils.isNotBlank(keywords)){
            example.createCriteria().orLike("single_engi_name","%"+keywords+"%")
             .orEqualTo("engi_proj_id",keywords).orEqualTo("node_id",keywords).orEqualTo("single_engi_area");
            List<SingleEngineer> select = singleEngineerMapper.selectByExample(example);
            //5.解析分页结果   PageInfo
            PageInfo<SingleEngineer> info = new PageInfo<>(select);
            return new PageResult<SingleEngineer>(info.getTotal(),select);
        }else {
            List<SingleEngineer> select = singleEngineerMapper.selectAll();
            PageInfo<SingleEngineer> info = new PageInfo<>(select);
            return new PageResult<SingleEngineer>(info.getTotal(),select);
        }
        }catch (Exception e){
        throw new ServiceException(FinalResultEnum.List_Not_Found);
        }


    }

    @Override
    public void upload(MultipartFile multipartFile) throws IOException {
        List<SingleEngineer> upload = ExcelUtil.upload(multipartFile, new SingleEngineer(), new ExcelUtil.ModelListener<SingleEngineer>());
        int i = singleEngineerMapper.insertList(upload);
        if (i==0){
            throw new ServiceException(FinalResultEnum.Save_Failed);
        }
        ExcelUtil.build.finish();
        upload.clear();


    }

    @Override
    public void setNode_id(Long node_id) {
        int i = singleEngineerMapper.setNode_id(node_id);

    }

    @Override
    public void setEngi_proj_id(Long engi_proj_id) {
        int i = singleEngineerMapper.setEngi_proj_id(engi_proj_id);

    }
}
