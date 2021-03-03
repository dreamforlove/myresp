package com.engineer.result.service.impl;

import com.engineer.common.enums.impl.FinalResultEnum;
import com.engineer.common.exceptions.ServiceException;
import com.engineer.common.vo.PageResult;
import com.engineer.result.pojo.UnitEngineer;
import com.engineer.result.service.UnitEngineerService;
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
public class UnitEngineerServiceImpl extends BaseServiceImpl<UnitEngineer> implements UnitEngineerService<UnitEngineer> {

    @Override
    public void save(UnitEngineer unitEngineer) {
        int insert = unitEngineerMapper.insert(unitEngineer);
        if (insert==0){
            throw new ServiceException(FinalResultEnum.Save_Failed);
        }
    }

    @Override
    public void update(UnitEngineer unitEngineer) {
        int i = unitEngineerMapper.updateByPrimaryKey(unitEngineer);
        if (i==0){
            throw new ServiceException(FinalResultEnum.Update_Failed);
        }
    }

    @Override
    public void delete(Long id) {
        int i = unitEngineerMapper.deleteByPrimaryKey(id);
        if (i==0){
            throw new ServiceException(FinalResultEnum.Delete_Failed);
        }
    }

    @Override
    public UnitEngineer find(Long id) {
        UnitEngineer unitEngineer = unitEngineerMapper.selectByPrimaryKey(id);
        if (unitEngineer==null){
            throw new ServiceException(FinalResultEnum.Find_Failed);
        }


        return unitEngineer;
    }

    @Override
    public List<UnitEngineer> findAll() {
        List<UnitEngineer> unitEngineers = unitEngineerMapper.selectAll();
        if (CollectionUtils.isEmpty(unitEngineers)){
            throw new ServiceException(FinalResultEnum.List_Not_Found);
        }

        return unitEngineers;
    }

    @Override
    public PageResult<UnitEngineer> pageQueryList(Integer pageNum, Integer pageSize, String keywords) {
        try {
            PageHelper.startPage(pageNum,pageSize);
            Example example =new Example(UnitEngineer.class);
            if (StringUtils.isNotBlank(keywords)){
                example.createCriteria().orLike("unit_engi_name","%"+keywords+"%")
                        .orEqualTo("unit_engi_id",keywords).orEqualTo("single_engi_id",keywords)
                        .orEqualTo("node_id",keywords).orEqualTo("difference_id",keywords).orEqualTo("charging_id",keywords);
                List<UnitEngineer> select = unitEngineerMapper.selectByExample(example);
                //5.UnitEngineer   PageInfo
                PageInfo<UnitEngineer> info = new PageInfo<>(select);
                return new PageResult<UnitEngineer>(info.getTotal(),select);
            }else {
                List<UnitEngineer> select = unitEngineerMapper.selectAll();
                PageInfo<UnitEngineer> info = new PageInfo<>(select);
                return new PageResult<UnitEngineer>(info.getTotal(),select);
            }
        }catch (Exception e){
            throw new ServiceException(FinalResultEnum.List_Not_Found);
        }
    }

    @Override
    public void upload(MultipartFile multipartFile) throws IOException {
        List<UnitEngineer> upload = ExcelUtil.upload(multipartFile, new UnitEngineer(), new ExcelUtil.ModelListener<UnitEngineer>());
        int i = unitEngineerMapper.insertList(upload);
        if (i==0){
            throw new ServiceException(FinalResultEnum.Save_Failed);
        }
        ExcelUtil.build.finish();
        upload.clear();

    }

    @Override
    public void setDifference_id(Long diff_id) {
        int i = unitEngineerMapper.setDifference_id(diff_id);



    }

    @Override
    public void setCharging_id(Long charging_id) {
        int i = unitEngineerMapper.setCharging_id(charging_id);

    }

    @Override
    public void setNode_id(Long node_id) {
        int i = unitEngineerMapper.setNode_id(node_id);

    }

    @Override
    public void setSingle_engi_id(Long single_engi_id) {
        int i = unitEngineerMapper.setSingle_engi_id(single_engi_id);

    }
}
