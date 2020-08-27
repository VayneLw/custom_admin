package com.upc.lw.moudules.system.rest;

import com.upc.lw.moudules.system.service.DeptService;
import com.upc.lw.request.dept.DeptRequest;
import com.upc.lw.system.dto.DeptDto;
import com.upc.lw.utills.PageUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @Description 部门——对外接口
 * @author: liwei
 * @date: 2020/8/13 17:08
 */
@Slf4j
@RestController
@RequestMapping("/api/dept")
public class DeptController {

    @Autowired
    private DeptService deptService;

    @GetMapping
    public ResponseEntity<Object> deptList(DeptRequest.QueryArg arg) {
        try {
            List<DeptDto> deptList = deptService.findDeptList(arg);
            Map<String, Object> trees = deptService.buildTrees(deptList);
            return new ResponseEntity<>(trees, HttpStatus.OK);
        } catch (Exception e) {
            log.info("deptList error:{}", e);
        }
        return new ResponseEntity<>(PageUtils.toPage(null, 0), HttpStatus.OK);
    }

}
