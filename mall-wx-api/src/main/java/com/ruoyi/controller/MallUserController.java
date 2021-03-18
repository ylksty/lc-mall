package com.ruoyi.controller;

import org.springframework.web.bind.annotation.*;

/**
 * 用户Controller
 * 
 * @author yanglk
 * @date 2021-03-17
 */
@RestController
@RequestMapping("/mall/user")
public class MallUserController
{

    /**
     * 查询用户列表
     */
//    @PreAuthorize("@ss.hasPermi('mall:user:list')")
    @GetMapping("/list")
    public String list()
    {
        System.out.println("abc");
        return "abc";
    }
}
