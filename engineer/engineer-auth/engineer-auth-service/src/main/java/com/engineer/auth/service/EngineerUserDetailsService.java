package com.engineer.auth.service;

import com.engineer.auth.client.UserClient;
import com.engineer.common.enums.impl.AuthExceptionEnum;
import com.engineer.common.enums.impl.EnabledEnum;
import com.engineer.common.exceptions.ServiceException;
import com.engineer.user.vo.UserExt;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author Lemon
 * @date 2019/9/7 11:24
 */
@Service
public class EngineerUserDetailsService implements UserDetailsService {

    @Autowired
    private ClientDetailsService clientDetailsService;
    @Autowired
    private UserClient userClient;

    @Override
    public UserDetails loadUserByUsername(String userAccount) throws UsernameNotFoundException {
        // 取出身份，如果身份为空说明没有认证
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // 没有认证统一采用httpbasic认证，httpbasic中存储了client_id和client_secret，开始认证client_id和client_secret
        if (authentication == null) {
            ClientDetails clientDetails = clientDetailsService.loadClientByClientId(userAccount);
            if (clientDetails != null) {
                // 密码
                String clientSecret = clientDetails.getClientSecret();
                return new User(userAccount, clientSecret, AuthorityUtils.commaSeparatedStringToAuthorityList(""));
            }
        }
        if (StringUtils.isEmpty(userAccount)) {
            return null;
        }
        // 请求用户服务查询用户信息
        UserExt userExt = userClient.getUserExt(userAccount);
        if (userExt == null) {
            return null;
        }
        if (EnabledEnum.DISABLED.getValue() == userExt.getEnabled()) {
            throw new ServiceException(AuthExceptionEnum.AUTH_USER_DISABLED);
        }
        String password = userExt.getPassword();
        String userPermissionString = "";
        if (!CollectionUtils.isEmpty(userExt.getResource())) {
            userPermissionString = StringUtils.join(userExt.getResource().toArray(), ",");
        }
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList(userPermissionString);
        UserJwt userDetails = new UserJwt(userAccount, password, grantedAuthorities);
        userDetails.setUserId(userExt.getUserId());
        userDetails.setUserName(userExt.getUserName());
        userDetails.setAvatarPath(userExt.getAvatarPath());
        userDetails.setMenu(userExt.getMenu());
        return userDetails;
    }
}
