package com.ruoyi.demo.service;

import java.util.List;
import com.ruoyi.demo.domain.DemoUser;

/**
 * 用户信息Service接口
 * 
 * @author yanglk
 * @date 2021-03-17
 */
public interface IDemoUserService 
{
    /**
     * 查询用户信息
     * 
     * @param id 用户信息ID
     * @return 用户信息
     */
    public DemoUser selectDemoUserById(Long id);

    /**
     * 查询用户信息列表
     * 
     * @param demoUser 用户信息
     * @return 用户信息集合
     */
    public List<DemoUser> selectDemoUserList(DemoUser demoUser);

    /**
     * 新增用户信息
     * 
     * @param demoUser 用户信息
     * @return 结果
     */
    public int insertDemoUser(DemoUser demoUser);

    /**
     * 修改用户信息
     * 
     * @param demoUser 用户信息
     * @return 结果
     */
    public int updateDemoUser(DemoUser demoUser);

    /**
     * 批量删除用户信息
     * 
     * @param ids 需要删除的用户信息ID
     * @return 结果
     */
    public int deleteDemoUserByIds(Long[] ids);

    /**
     * 删除用户信息信息
     * 
     * @param id 用户信息ID
     * @return 结果
     */
    public int deleteDemoUserById(Long id);
}
