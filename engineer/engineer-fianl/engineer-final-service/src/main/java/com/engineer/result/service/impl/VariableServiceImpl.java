package com.engineer.result.service.impl;

import com.engineer.common.enums.impl.FinalResultEnum;
import com.engineer.common.exceptions.ServiceException;
import com.engineer.common.vo.PageResult;
import com.engineer.result.pojo.Variable;
import com.engineer.result.service.VariableService;
import com.engineer.result.service.base.Impl.BaseServiceImpl;
import com.engineer.result.util.base.ExcelUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class VariableServiceImpl extends BaseServiceImpl<Variable> implements VariableService<Variable> {

    @Override
    public void save(Variable variable) {
        /**
        * @Description 保存
        * @Author Mr.li
        * @Date   2020/4/24 0024 下午 23:27
        * @Param  [variable]
        */
        int insert = variableMapper.insert(variable);
        if(insert==0){
            throw new ServiceException(FinalResultEnum.Variable_Save_Failed);
        }
    }

    @Override
    public void update(Variable variable) {
    /**
    * @Description 更新
    * @Author Mr.li
    * @Date   2020/4/24 0024 下午 23:29
    * @Param  [variable]
    */
        int i = variableMapper.updateByPrimaryKey(variable);
        if (i==0){
            throw new ServiceException(FinalResultEnum.Variable_Update_Failed);
        }
    }

    @Override
    public void delete(Long id) {
        int i = variableMapper.deleteByPrimaryKey(id);
        if (i==0){
            throw new ServiceException(FinalResultEnum.Variable_Delete_Failed);
        }


    }

    @Override
    public Variable find(Long id) {
        Variable variable = variableMapper.selectByPrimaryKey(id);
        if (variable==null){
            throw new ServiceException(FinalResultEnum.Find_Failed);
        }
        return variable;
    }

    @Override
    public List<Variable> findAll() {
        /**
        * @Description 查询所有Variable
        * @Author Mr.li
        * @Date   2020/4/24 0024 下午 23:31
        * @Param  []
        */
        List<Variable> variables = variableMapper.selectAll();
        if (CollectionUtils.isEmpty(variables)){
            throw new ServiceException(FinalResultEnum.VariableList_Not_found);
        }

        return variables;
    }

    @Override
    public PageResult<Variable> pageQueryList(Integer pageNum, Integer pageSize, String keywords) {

        /**
         * @Description 分页查询Variable
         * @Author  xingming
         * @Date   2020/4/23 0023 下午 15:41
         * @Param  [pageNum, pageSize, keywords]
         * @Return      com.engineer.common.vo.PageResult<com.engineer.result.pojo.Difference>
         * @Exception
         *
         */
        try {
            PageHelper.startPage(pageNum,pageSize);
            Example example =new Example(Variable.class);
            if (StringUtils.isNotBlank(keywords)){
                example.createCriteria().orLike("take_variable","%"+keywords+"%").orEqualTo("variable_id",keywords).orEqualTo("user_id",keywords).orEqualTo("productid",keywords);
                List<Variable> select = variableMapper.selectByExample(example);
                //5.解析分页结果   PageInfo
                PageInfo<Variable> info = new PageInfo<>(select);
                return new PageResult<Variable>(info.getTotal(),select);
            }else {
                List<Variable> select = variableMapper.selectAll();
                PageInfo<Variable> info = new PageInfo<>(select);
                return new PageResult<Variable>(info.getTotal(),select);
            }
        }catch (Exception e){
            log.error("未查询到调差",e);
            throw new ServiceException(FinalResultEnum.VariableList_Not_found);
        }
    }

    @Override
    public void upload(MultipartFile multipartFile) throws IOException {
        /**
        * @Description 文件上传
        * @Author Mr.li
        * @Date   2020/4/24 0024 下午 23:33
        * @Param  [multipartFile]
        */
        try {
//            BaseListener<Variable> differenceListener = new BaseListener<Variable>();
//            ExcelReader build = EasyExcel.read(multipartFile.getInputStream(), Variable.class, differenceListener).build();
//            ReadSheet build1 = EasyExcel.readSheet(0).build();
//            ExcelReader read = build.read(build1);
//            List<Variable> datas = differenceListener.getDatas();
            //调用工具类BaseUpload
            List<Variable> upload = ExcelUtil.upload(multipartFile,new  Variable(),new ExcelUtil.ModelListener<Variable>());
            int i = variableMapper.insertList(upload);
            if (i==0){
                throw new ServiceException(FinalResultEnum.Save_Failed);
            }
            ExcelUtil.build.finish();
            upload.clear();
        }catch (Exception e)
        {
            throw new ServiceException(FinalResultEnum.Difference_Upload_Failed);
        }

    }
}
