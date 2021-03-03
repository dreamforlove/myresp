package com.engineer.user.controller;

import com.engineer.common.enums.impl.EnabledEnum;
import com.engineer.user.interceptor.UserInfoInterceptor;
import com.engineer.common.vo.PageResult;
import com.engineer.common.vo.UserInfo;
import com.engineer.user.pojo.User;
import com.engineer.user.service.UserService;
import com.engineer.user.vo.RegisterVo;
import com.engineer.user.vo.UserDetail;
import com.engineer.user.vo.UserExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author Lemon
 * @date 2019/9/27 20:20
 */
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("userExt")
    public ResponseEntity<UserExt> getUserExt(@RequestParam("userAccount") String userAccount) {
        return ResponseEntity.ok(userService.getUserExt(userAccount));
    }

    @PreAuthorize("hasAuthority('/user/list')")
    @GetMapping("list")
    public ResponseEntity<PageResult<User>> userList(
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
            @RequestParam(value = "keywordsType", required = false) String keywordsType,
            @RequestParam(value = "keywords", required = false) String keywords
    ) {
        PageResult<User> userList = userService.getUserListByPage(pageNum, pageSize, keywordsType, keywords);
        return ResponseEntity.ok(userList);
    }

    @PreAuthorize("hasAuthority('/user/disabled')")
    @PostMapping("disabled")
    public ResponseEntity<Void> setUserDisabled(@RequestParam("userId") Long userId) {
        userService.setUserStatus(userId, EnabledEnum.DISABLED.getValue());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PreAuthorize("hasAuthority('/user/enabled')")
    @PostMapping("enabled")
    public ResponseEntity<Void> setUserEnabled(@RequestParam("userId") Long userId) {
        userService.setUserStatus(userId, EnabledEnum.ENABLED.getValue());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("detail")
    public ResponseEntity<UserDetail> getUserDetail(@RequestParam("userId") Long userId) {
        UserInfo userInfo = UserInfoInterceptor.getUserInfo();
        if (!userInfo.getAuthorities().contains("/user/detail") && !userInfo.getUserId().equals(userId)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        UserDetail userDetail = userService.getUserDetail(userId);
        return ResponseEntity.ok(userDetail);
    }

    @PutMapping("detail")
    public ResponseEntity<Void> updateUserDetail(@RequestBody UserDetail userDetail) {
        UserInfo userInfo = UserInfoInterceptor.getUserInfo();
        if (!userInfo.getAuthorities().contains("/user/detail") && !userInfo.getUserId().equals(userDetail.getUserId())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        userService.updateUserDetail(userDetail);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("check")
    public ResponseEntity<Boolean> checkUserData(
            @RequestParam("data") String data,
            @RequestParam("type") String type
    ) {
        return ResponseEntity.ok(userService.checkUserData(data, type));
    }

    @PostMapping("code")
    public ResponseEntity<Void> sendCode(@RequestParam("phone") String phone){
        userService.sendCode(phone);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("register")
    public ResponseEntity<Void> register(@RequestBody @Valid RegisterVo registerVo) {
        userService.register(registerVo);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("change/password")
    public ResponseEntity<Void> changePassword(
            @RequestParam("userAccount") String userAccount,
            @RequestParam("password") String password,
            @RequestParam("phone") String phone,
            @RequestParam("code") String code
    ) {
        userService.changePassword(userAccount, password, phone, code);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
