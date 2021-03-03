package com.engineer.user.mapper;

import com.engineer.common.mapper.BaseMapper;
import com.engineer.user.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper extends BaseMapper<User> {

    /**
     * 通过用户名查询用户基本信息
     *
     * @param userAccount
     * @return
     */
    User findUserByUserAccount(@Param("userAccount") String userAccount);

    /**
     * 根据条件查询用户信息列表
     *
     * @param keywordsType
     * @param keywords
     * @return
     */
    List<User> selectUserList(@Param("keywordsType") String keywordsType, @Param("keywords") String keywords);
}