package com.ruoyi.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 * 用户Controller
 * 
 * @author yanglk
 * @date 2021-03-17
 */
@Api("用户列表")
@RestController
@RequestMapping("/testmall/user")
public class TestMallUserController
{

    /**
     * 查询用户列表
     * http://localhost:1024/dev-api/swagger-ui.html
     * http://localhost:8080/swagger-ui.html
     * http://localhost:8090/swagger-ui.html
     * http://localhost:8090/mall/user/list
     */
//    @PreAuthorize("@ss.hasPermi('mall:user:list')")
    @ApiOperation("测试接口")
    @GetMapping("/list")
    public String list()
    {
        System.out.println("abc");
        return "abc";
    }
}
