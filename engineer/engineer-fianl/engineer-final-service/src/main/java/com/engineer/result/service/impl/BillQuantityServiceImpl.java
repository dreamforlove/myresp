package com.engineer.result.service.impl;

import com.engineer.common.enums.impl.FinalResultEnum;
import com.engineer.common.exceptions.ServiceException;
import com.engineer.common.vo.PageResult;
import com.engineer.result.pojo.BillQuantity;
import com.engineer.result.service.BillQuantityService;
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
public class BillQuantityServiceImpl extends BaseServiceImpl<BillQuantity> implements BillQuantityService<BillQuantity> {


    @Override
    public void save(BillQuantity billQuantity) {
        int insert = billQuantityMapper.insert(billQuantity);
        if (insert==0){
            throw new ServiceException(FinalResultEnum.Save_Failed);
        }
    }

    @Override
    public void update(BillQuantity billQuantity) {
        int i = billQuantityMapper.updateByPrimaryKey(billQuantity);
        if (i==0){
            throw new ServiceException(FinalResultEnum.Update_Failed);
        }

    }

    @Override
    public void delete(Long id) {

        int i = billQuantityMapper.deleteByPrimaryKey(id);
        if (i==0){
            throw new ServiceException(FinalResultEnum.Delete_Failed);
        }

    }

    @Override
    public BillQuantity find(Long id) {
        BillQuantity billQuantity = billQuantityMapper.selectByPrimaryKey(id);
        if (billQuantity==null){
            throw new ServiceException(FinalResultEnum.Find_Failed);
        }
        return billQuantity;

    }

    @Override
    public List<BillQuantity> findAll() {
        List<BillQuantity> billQuantities = billQuantityMapper.selectAll();
        if (CollectionUtils.isEmpty(billQuantities)){
            throw new ServiceException(FinalResultEnum.List_Not_Found);
        }
        return billQuantities;
    }

    @Override
    public PageResult<BillQuantity> pageQueryList(Integer pageNum, Integer pageSize, String keywords) {
        try {
            PageHelper.startPage(pageNum,pageSize);
            Example example =new Example(BillQuantity.class);
            if (StringUtils.isNotBlank(keywords)){
                example.createCriteria().orLike("take_variable","%"+keywords+"%")
                        .orEqualTo("variable_id",keywords).
                        orEqualTo("user_id",keywords).
                        orEqualTo("productid",keywords);
                List<BillQuantity> select = billQuantityMapper.selectByExample(example);
                //5.解析分页结果   PageInfo
                PageInfo<BillQuantity> info = new PageInfo<>(select);
                return new PageResult<BillQuantity>(info.getTotal(),select);
            }else {
                List<BillQuantity> select = billQuantityMapper.selectAll();
                PageInfo<BillQuantity> info = new PageInfo<>(select);
                return new PageResult<BillQuantity>(info.getTotal(),select);
            }
        }catch (Exception e){
            throw new ServiceException(FinalResultEnum.List_Not_Found);
        }


    }

    @Override
    public void upload(MultipartFile multipartFile) throws IOException {
        List<BillQuantity> upload = ExcelUtil.upload(multipartFile, new BillQuantity(), new ExcelUtil.ModelListener<BillQuantity>());
        int i = billQuantityMapper.insertList(upload);
        if (i==0){
            throw new ServiceException(FinalResultEnum.Upload_Failed);

        }

    }

    @Override
    public void setUnit_engi_id(Long unit_engi_id) {
        int i = billQuantityMapper.setUnit_engi_id(unit_engi_id);
    }
}
