package com.engineer.user.controller;

import com.engineer.common.vo.PageResult;
import com.engineer.user.pojo.Location;
import com.engineer.user.service.LocationService;
import com.engineer.user.vo.LocationTree;
import com.engineer.user.vo.LocationVo;
import com.engineer.user.vo.MenuVo;
import com.engineer.user.vo.ResourceVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Lemon
 * @date 2020/3/13 14:52
 */
@RestController
@RequestMapping("location")
public class LocationController {

    @Autowired
    private LocationService locationService;

    @GetMapping("tree")
    public ResponseEntity<List<LocationTree>> getLocationTree() {
        List<LocationTree> locationTree = locationService.getLocationTree();
        return ResponseEntity.ok(locationTree);
    }

    @GetMapping("list")
    public ResponseEntity<PageResult<LocationVo>> getLocationList(
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize
    ) {
        PageResult<LocationVo> list = locationService.getLocationList(pageNum, pageSize);
        return ResponseEntity.ok(list);
    }

    @GetMapping("locIds/{locId}")
    public ResponseEntity<List<Integer>> getLocIdArrayList(@PathVariable Integer locId) {
        return ResponseEntity.ok(locationService.getLocIdArrayList(locId));
    }

    @PreAuthorize("hasAuthority('/location/delete')")
    @DeleteMapping("{locId}")
    public ResponseEntity<Void> deleteRoleById(@PathVariable Integer locId) {
        locationService.deleteLocationById(locId);
        locationService.updateRedisLocationCache();
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAuthority('/location/add')")
    @PostMapping("add")
    public ResponseEntity<Void> addLocation(@RequestBody Location location) {
        locationService.addLocation(location);
        locationService.updateRedisLocationCache();
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PreAuthorize("hasAuthority('/location/update')")
    @PutMapping("update")
    public ResponseEntity<Void> updateLocation(@RequestBody Location location) {
        locationService.updateLocation(location);
        locationService.updateRedisLocationCache();
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
