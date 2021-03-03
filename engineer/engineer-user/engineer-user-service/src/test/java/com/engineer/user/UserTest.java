package com.engineer.user;

import com.engineer.user.service.LocationService;
import com.engineer.user.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Lemon
 * @date 2020/4/10 19:25
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class UserTest {
    @Autowired
    private UserService userService;

    @Test
    public void testSendCode() {
        userService.sendCode("15890011378");
    }
}
