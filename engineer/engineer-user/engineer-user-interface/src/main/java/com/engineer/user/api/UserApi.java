package com.engineer.user.api;

import com.engineer.user.vo.UserExt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Lemon
 * @date 2019/10/20 17:01
 */
public interface UserApi {
    /**
     * 根据用户账号获取用户基本信息和权限信息
     * @param userAccount
     * @return
     */
    @GetMapping("/user/userExt")
    UserExt getUserExt(@RequestParam("userAccount") String userAccount);
}
