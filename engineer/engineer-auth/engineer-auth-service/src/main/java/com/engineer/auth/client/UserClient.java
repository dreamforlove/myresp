package com.engineer.auth.client;

import com.engineer.cilent.EngineerServiceList;
import com.engineer.user.api.UserApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author Lemon
 * @date 2019/10/20 17:07
 */
@FeignClient(value = EngineerServiceList.ENGINEER_SERVICE_USER)
public interface UserClient extends UserApi {
}
