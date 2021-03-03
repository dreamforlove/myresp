package com.engineer.user.vo;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * @author Lemon
 * @date 2020/4/11 14:24
 */
@Data
public class RegisterVo {
    @NotBlank(message = "用户名不能为空")
    @Pattern(regexp = "^[_a-zA-Z0-9]{5,18}$", message = "用户名格式错误")
    private String userAccount;
    @Length(min = 6, max = 18, message = "密码长度必须在6-18位之间")
    private String password;
    @NotBlank(message = "真实姓名不能为空")
    @Length(min = 1, max = 50, message = "输入内容长度过大")
    private String userName;
    @NotBlank(message = "手机号码不能为空")
    @Pattern(regexp = "^1(3|4|5|7|8)\\d{9}$", message = "手机号码格式错误")
    private String phone;
    @NotBlank(message = "验证码不能为空")
    private String code;
    @NotBlank(message = "邮箱不能为空")
    @Email
    private String email;
}
