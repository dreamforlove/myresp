package com.engineer.result.service.impl;

import com.engineer.common.enums.impl.FinalResultEnum;
import com.engineer.common.exceptions.ServiceException;
import com.engineer.common.vo.PageResult;

import com.engineer.result.pojo.ProjectInformation;
import com.engineer.result.service.ProjectInformationService;
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
public class ProjectInformationServiceImpl extends BaseServiceImpl<ProjectInformation> implements ProjectInformationService<ProjectInformation> {

    @Override
    public void save(ProjectInformation projectInformation) {
        int insert = projectInformationMapper.insert(projectInformation);
        if (insert==0){
            throw new ServiceException(FinalResultEnum.Save_Failed);
        }
    }

    @Override
    public void update(ProjectInformation projectInformation) {
        int i = projectInformationMapper.updateByPrimaryKey(projectInformation);
        if (i==0){
            throw new ServiceException(FinalResultEnum.Update_Failed);
        }
    }

    @Override
    public void delete(Long id) {
        int delete = projectInformationMapper.deleteByPrimaryKey(id);
        if (delete==0){
            throw new ServiceException(FinalResultEnum.Delete_Failed);
        }
    }

    @Override
    public ProjectInformation find(Long id) {
        ProjectInformation projectInformation = projectInformationMapper.selectByPrimaryKey(id);
        if (projectInformation==null){
            throw new ServiceException(FinalResultEnum.Find_Failed);
        }
        return projectInformation;
    }

    @Override
    public List<ProjectInformation> findAll() {
        List<ProjectInformation> projectInformations = projectInformationMapper.selectAll();
        if (CollectionUtils.isEmpty(projectInformations)){
            throw new ServiceException(FinalResultEnum.List_Not_Found);
        }

        return projectInformations;
    }

    @Override
    public PageResult<ProjectInformation> pageQueryList(Integer pageNum, Integer pageSize, String keywords) {

        try {

            PageHelper.startPage(pageNum, pageSize);
            Example example =new Example(ProjectInformation.class);
            if (StringUtils.isNotBlank(keywords)){
                example.createCriteria().orLike("engi_proj_name","%"+keywords+"%")
                        .orEqualTo("user_id",keywords).orEqualTo("cons_orga_name",keywords).orEqualTo("engi_site",keywords)
                         .orEqualTo("per_cost_index",keywords)   ;
                List<ProjectInformation> select = projectInformationMapper.selectByExample(example);
                //5.解析分页结果   PageInfo
                PageInfo<ProjectInformation> info = new PageInfo<>(select);
                return new PageResult<ProjectInformation>(info.getTotal(),select);
            }else {
                List<ProjectInformation> select = projectInformationMapper.selectAll();
                PageInfo<ProjectInformation> info = new PageInfo<>(select);
                return new PageResult<ProjectInformation>(info.getTotal(),select);
            }
        }catch (Exception e){
            throw new ServiceException(FinalResultEnum.List_Not_Found);
        }

    }

    @Override
    public void upload(MultipartFile multipartFile) throws IOException {
        List<ProjectInformation> upload = ExcelUtil.upload(multipartFile, new ProjectInformation(), new ExcelUtil.ModelListener<ProjectInformation>());
        int i = projectInformationMapper.insertList(upload);
        if (i==0){
            throw new ServiceException(FinalResultEnum.Save_Failed);
        }
        ExcelUtil.build.finish();
        upload.clear();

    }

    @Override
    public List<ProjectInformation> selectAllButDate() {
//        List<ProjectInformation> projectInformations = projectInformationMapper.selectAllButDate();
//        if (CollectionUtils.isEmpty(projectInformations) ){
//            throw new ServiceException(FinalResultEnum.List_Not_Found);
//
//        }
        return null;
    }

    @Override
    public void setNode_id(Long node_id) {
        int i = projectInformationMapper.setNode_id(node_id);

    }
}
