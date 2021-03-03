package com.engineer.result.service.impl;

import com.engineer.common.enums.impl.FinalResultEnum;
import com.engineer.common.exceptions.ServiceException;
import com.engineer.common.vo.PageResult;
import com.engineer.result.pojo.Difference;
import com.engineer.result.service.DifferenceService;
import com.engineer.result.service.base.Impl.BaseServiceImpl;
import com.engineer.result.util.base.ExcelUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import tk.mybatis.mapper.entity.Example;

import java.io.IOException;
import java.util.List;

@Service
@Transactional
@Slf4j
public class DifferenceServiceImpl extends BaseServiceImpl<Difference> implements DifferenceService<Difference> {

    @Override
    public PageResult<Difference> queryDifferenceList(Integer pageNum, Integer pageSize, String keywords) {
        /**
        * @Description 分页查询Difference
        * @Author  xingming
        * @Date   2020/4/23 0023 下午 15:41
        * @Param  [pageNum, pageSize, keywords]
        * @Return      com.engineer.common.vo.PageResult<com.engineer.result.pojo.Difference>
        * @Exception
        *
        */
        try {
        PageHelper.startPage(pageNum, pageSize);
        Example example =new Example(Difference.class);
        if (StringUtils.isNotBlank(keywords)){
            example.createCriteria().orLike("lmm_name","%"+keywords+"%").orEqualTo("unit",keywords).orEqualTo("price",keywords).orEqualTo("diff_id",keywords).orEqualTo("user_id",keywords).orEqualTo("lmm_id",keywords);
            List<Difference> select = differenceMapper.selectByExample(example);
            //5.解析分页结果   PageInfo
            PageInfo<Difference> info = new PageInfo<>(select);
            return new PageResult<Difference>(info.getTotal(),select);
        }else {
            List<Difference> select = differenceMapper.selectAll();
            PageInfo<Difference> info = new PageInfo<>(select);
            return new PageResult<Difference>(info.getTotal(),select);
        }
        }catch (Exception e){
            log.error("未查询到调差",e);
            throw new ServiceException(FinalResultEnum.DifferenceList_Not_found);
        }
    }

    @Override
    public void upload(MultipartFile multipartFile) throws IOException {
        /**
        * @Description 文件上传Excel表格
        * @Author  xingming
        * @Date   2020/4/24 0024 下午 14:44
        * @Param  [multipartFile]
        * @Return      void
        * @Exception
        *
        */
        try {
            List<Difference> datas = ExcelUtil.upload(multipartFile, new Difference(), new ExcelUtil.ModelListener<Difference>());
            int i = differenceMapper.insertList(datas);
            if (i==0){
                throw new ServiceException(FinalResultEnum.Save_Failed);
            }
            ExcelUtil.build.finish();
            datas.clear();
        }catch (Exception e)
        {
            throw new ServiceException(FinalResultEnum.Difference_Upload_Failed);
        }

    }

    @Override
    public void save(Difference difference) {
        /**
        * @Description 保存Difference
        * @Author  xingming
        * @Date   2020/4/23 0023 下午 15:41
        * @Param  [difference]
        * @Return      void
        * @Exception
        *
        */

        int insert = differenceMapper.insert(difference);
        if (insert==0){
            throw new ServiceException(FinalResultEnum.Difference_Save_Failed);
        }
    }

    @Override
    public void update(Difference difference) {
        /**
        * @Description 更新Difference
        * @Author  xingming
        * @Date   2020/4/23 0023 下午 16:54
        * @Param  [difference]
        * @Return      void
        * @Exception
        *
        */

        int i = differenceMapper.updateByPrimaryKey(difference);
        if (i==0){
            throw new ServiceException(FinalResultEnum.Difference_Update_Failed);
        }
    }

    @Override
    public void delete(Long id) {
        int i = differenceMapper.deleteByPrimaryKey(id);
        if (i==0){
            throw new ServiceException(FinalResultEnum.Difference_Delete_Failed);
        }

    }

    @Override
    public Difference find(Long id) {
        return null;
    }

    @Override
    public List<Difference> findAll() {
        List<Difference> differences = differenceMapper.selectAll();

        return differences;
    }

    @Override
    public PageResult<Difference> pageQueryList(Integer pageNum, Integer pageSize, String keywords) {
        return null;
    }
}
