package com.engineer.user.location;

import com.engineer.common.utils.JsonUtils;
import com.engineer.user.service.LocationService;
import com.engineer.user.vo.LocationTree;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author Lemon
 * @date 2020/3/13 16:31
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class LocationTest {

    @Autowired
    private LocationService locationService;

    @Test
    public void testGetLocationTree() {
        List<LocationTree> locationTree = locationService.getLocationTree();
        String json = JsonUtils.objectToJson(locationTree);
        System.out.println(json);
    }
}
