package com.engineer.result.service.impl;

import com.engineer.common.enums.impl.FinalResultEnum;
import com.engineer.common.exceptions.ServiceException;
import com.engineer.common.vo.PageResult;
import com.engineer.result.pojo.Materials;
import com.engineer.result.service.MaterialsService;
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
public class MaterialsServiceImpl extends BaseServiceImpl<Materials> implements MaterialsService<Materials> {

    @Override
    public void save(Materials materials) {
        int insert = materialsMapper.insert(materials);
        if (insert==0){
            throw new ServiceException(FinalResultEnum.Save_Failed);
        }
    }

    @Override
    public void update(Materials materials) {
        int i = materialsMapper.updateByPrimaryKey(materials);
        if (i==0){
            throw new ServiceException(FinalResultEnum.Update_Failed);
        }


    }

    @Override
    public void delete(Long id) {
        int i = materialsMapper.deleteByPrimaryKey(id);
        if (i==0){
            throw new ServiceException(FinalResultEnum.Delete_Failed);
        }
    }

    @Override
    public Materials find(Long id) {
        Materials materials = materialsMapper.selectByPrimaryKey(id);
        if (materials==null){
            throw new ServiceException(FinalResultEnum.Find_Failed);
        }

        return materials;
    }

    @Override
    public List<Materials> findAll() {

        List<Materials> materials = materialsMapper.selectAll();
        if (CollectionUtils.isEmpty(materials)){
            throw new ServiceException(FinalResultEnum.List_Not_Found);
        }
        return materials;
    }

    @Override
    public PageResult<Materials> pageQueryList(Integer pageNum, Integer pageSize, String keywords) {

        try {
            PageHelper.startPage(pageNum,pageSize);
            Example example =new Example(Materials.class);
            if (StringUtils.isNotBlank(keywords)){
                example.createCriteria().orLike("take_variable","%"+keywords+"%")
                        .orEqualTo("variable_id",keywords).orEqualTo("user_id",keywords)
                        .orEqualTo("productid",keywords);
                List<Materials> select = materialsMapper.selectByExample(example);
                //5.解析分页结果   PageInfo
                PageInfo<Materials> info = new PageInfo<>(select);
                return new PageResult<Materials>(info.getTotal(),select);
            }else {
                List<Materials> select = materialsMapper.selectAll();
                PageInfo<Materials> info = new PageInfo<>(select);
                return new PageResult<Materials>(info.getTotal(),select);
            }
        }catch (Exception e){

            throw new ServiceException(FinalResultEnum.List_Not_Found);
        }



    }

    @Override
    public void upload(MultipartFile multipartFile) throws IOException {

        List<Materials> upload = ExcelUtil.upload(multipartFile, new Materials(), new ExcelUtil.ModelListener<Materials>());

        int i = materialsMapper.insertList(upload);
        if (i==0){
            throw new ServiceException(FinalResultEnum.Save_Failed);
        }
        ExcelUtil.build.finish();
        upload.clear();

//        for (Materials materials:upload){
//            int insert = materialsMapper.insert(materials);
//            if (insert==0){
//                throw new ServiceException(FinalResultEnum.Save_Failed);
//            }
//        }

    }

    @Override
    public void setDifference_id(Long diff_id) {
        int i = materialsMapper.setDifference_id(diff_id);

    }

    @Override
    public void setUnit_engi_id(Long unit_engi_id) {
        int i = materialsMapper.setUnit_engi_id(unit_engi_id);

    }
}
