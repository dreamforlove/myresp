package com.engineer.result.service.impl;

import com.engineer.common.enums.impl.FinalResultEnum;
import com.engineer.common.exceptions.ServiceException;
import com.engineer.common.vo.PageResult;
import com.engineer.result.pojo.Charging;
import com.engineer.result.service.ChargingService;
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
public class ChargingServiceImpl extends BaseServiceImpl<Charging> implements ChargingService<Charging> {

    @Override
    public void save(Charging charging) {
        int insert = chargingMapper.insert(charging);
        if (insert==0){
            throw new ServiceException(FinalResultEnum.Save_Failed);
        }
    }

    @Override
    public void update(Charging charging) {
        int i = chargingMapper.updateByPrimaryKey(charging);
        if (i==0){
            throw new ServiceException(FinalResultEnum.Update_Failed);
        }
    }

    @Override
    public void delete(Long id) {
        int i = chargingMapper.deleteByPrimaryKey(id);
        if (i==0){
            throw new ServiceException(FinalResultEnum.Delete_Failed);
        }
    }

    @Override
    public Charging find(Long id) {
        Charging charging = chargingMapper.selectByPrimaryKey(id);
        if (charging==null){
            throw new ServiceException(FinalResultEnum.Find_Failed);
        }
        return charging;
    }

    @Override
    public List<Charging> findAll() {
        List<Charging> chargings = chargingMapper.selectAll();
        if (CollectionUtils.isEmpty(chargings)){
            throw new ServiceException(FinalResultEnum.List_Not_Found);
        }
        return chargings;
    }



    @Override
    public void upload(MultipartFile multipartFile) throws IOException {
        List<Charging> upload = ExcelUtil.upload(multipartFile, new Charging(), new ExcelUtil.ModelListener<Charging>());
        int i = chargingMapper.insertList(upload);
        if (i==0){
            throw new ServiceException(FinalResultEnum.Save_Failed);
        }
        ExcelUtil.build.finish();
        upload.clear();


    }

    @Override
    public PageResult<Charging> pageQueryList(Integer pageNum, Integer pageSize, String keywords) {
        try {
            PageHelper.startPage(pageNum, pageSize);
            Example example =new Example(Charging.class);
            if (StringUtils.isNotBlank(keywords)){
                example.createCriteria().orLike("fee_proj_name","%"+keywords+"%").orEqualTo("charging_id",keywords).orEqualTo("variate_id",keywords).orEqualTo("rate",keywords).orEqualTo("user_id",keywords);
                List<Charging> select = chargingMapper.selectByExample(example);
                //5.解析分页结果   PageInfo
                PageInfo<Charging> info = new PageInfo<>(select);
                return new PageResult<Charging>(info.getTotal(),select);
            }else {
                List<Charging> select = chargingMapper.selectAll();
                PageInfo<Charging> info = new PageInfo<>(select);
                return new PageResult<Charging>(info.getTotal(),select);
            }
        }catch (Exception e){
            throw new ServiceException(FinalResultEnum.List_Not_Found);
        }

    }

    @Override
    public void setVariate_id(Long variate_id) {
        int i = chargingMapper.setVariate_id(variate_id);

    }
}
