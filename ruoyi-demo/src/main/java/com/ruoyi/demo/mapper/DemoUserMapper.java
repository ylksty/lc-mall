package com.ruoyi.demo.mapper;

import java.util.List;
import com.ruoyi.demo.domain.DemoUser;
import com.ruoyi.demo.domain.DemoOrder;

/**
 * 用户信息Mapper接口
 * 
 * @author yanglk
 * @date 2021-03-17
 */
public interface DemoUserMapper 
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
     * 删除用户信息
     * 
     * @param id 用户信息ID
     * @return 结果
     */
    public int deleteDemoUserById(Long id);

    /**
     * 批量删除用户信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteDemoUserByIds(Long[] ids);

    /**
     * 批量删除订单
     * 
     * @param customerIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteDemoOrderByUserIds(Long[] ids);
    
    /**
     * 批量新增订单
     * 
     * @param demoOrderList 订单列表
     * @return 结果
     */
    public int batchDemoOrder(List<DemoOrder> demoOrderList);
    

    /**
     * 通过用户信息ID删除订单信息
     * 
     * @param roleId 角色ID
     * @return 结果
     */
    public int deleteDemoOrderByUserId(Long id);
}
