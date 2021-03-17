package com.ruoyi.demo.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import com.ruoyi.common.utils.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import com.ruoyi.demo.domain.DemoOrder;
import com.ruoyi.demo.mapper.DemoUserMapper;
import com.ruoyi.demo.domain.DemoUser;
import com.ruoyi.demo.service.IDemoUserService;

/**
 * 用户信息Service业务层处理
 * 
 * @author yanglk
 * @date 2021-03-17
 */
@Service
public class DemoUserServiceImpl implements IDemoUserService 
{
    @Autowired
    private DemoUserMapper demoUserMapper;

    /**
     * 查询用户信息
     * 
     * @param userId 用户信息ID
     * @return 用户信息
     */
    @Override
    public DemoUser selectDemoUserById(Long userId)
    {
        return demoUserMapper.selectDemoUserById(userId);
    }

    /**
     * 查询用户信息列表
     * 
     * @param demoUser 用户信息
     * @return 用户信息
     */
    @Override
    public List<DemoUser> selectDemoUserList(DemoUser demoUser)
    {
        return demoUserMapper.selectDemoUserList(demoUser);
    }

    /**
     * 新增用户信息
     * 
     * @param demoUser 用户信息
     * @return 结果
     */
    @Transactional
    @Override
    public int insertDemoUser(DemoUser demoUser)
    {
        demoUser.setCreateTime(DateUtils.getNowDate());
        int rows = demoUserMapper.insertDemoUser(demoUser);
        insertDemoOrder(demoUser);
        return rows;
    }

    /**
     * 修改用户信息
     * 
     * @param demoUser 用户信息
     * @return 结果
     */
    @Transactional
    @Override
    public int updateDemoUser(DemoUser demoUser)
    {
        demoUser.setUpdateTime(DateUtils.getNowDate());
        demoUserMapper.deleteDemoOrderByUserId(demoUser.getUserId());
        insertDemoOrder(demoUser);
        return demoUserMapper.updateDemoUser(demoUser);
    }

    /**
     * 批量删除用户信息
     * 
     * @param userIds 需要删除的用户信息ID
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteDemoUserByIds(Long[] userIds)
    {
        demoUserMapper.deleteDemoOrderByUserIds(userIds);
        return demoUserMapper.deleteDemoUserByIds(userIds);
    }

    /**
     * 删除用户信息信息
     * 
     * @param userId 用户信息ID
     * @return 结果
     */
    @Override
    public int deleteDemoUserById(Long userId)
    {
        demoUserMapper.deleteDemoOrderByUserId(userId);
        return demoUserMapper.deleteDemoUserById(userId);
    }

    /**
     * 新增订单信息
     * 
     * @param demoUser 用户信息对象
     */
    public void insertDemoOrder(DemoUser demoUser)
    {
        List<DemoOrder> demoOrderList = demoUser.getDemoOrderList();
        Long userId = demoUser.getUserId();
        if (StringUtils.isNotNull(demoOrderList))
        {
            List<DemoOrder> list = new ArrayList<DemoOrder>();
            for (DemoOrder demoOrder : demoOrderList)
            {
                demoOrder.setUserId(userId);
                list.add(demoOrder);
            }
            if (list.size() > 0)
            {
                demoUserMapper.batchDemoOrder(list);
            }
        }
    }
}
