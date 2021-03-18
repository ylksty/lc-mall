package com.ruoyi.demo.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.demo.mapper.DemoOrderMapper;
import com.ruoyi.demo.domain.DemoOrder;
import com.ruoyi.demo.service.IDemoOrderService;

/**
 * 订单Service业务层处理
 * 
 * @author yanglk
 * @date 2021-03-17
 */
@Service
public class DemoOrderServiceImpl implements IDemoOrderService 
{
    @Autowired
    private DemoOrderMapper demoOrderMapper;

    /**
     * 查询订单
     * 
     * @param id 订单ID
     * @return 订单
     */
    @Override
    public DemoOrder selectDemoOrderById(Long id)
    {
        return demoOrderMapper.selectDemoOrderById(id);
    }

    /**
     * 查询订单列表
     * 
     * @param demoOrder 订单
     * @return 订单
     */
    @Override
    public List<DemoOrder> selectDemoOrderList(DemoOrder demoOrder)
    {
        return demoOrderMapper.selectDemoOrderList(demoOrder);
    }

    /**
     * 新增订单
     * 
     * @param demoOrder 订单
     * @return 结果
     */
    @Override
    public int insertDemoOrder(DemoOrder demoOrder)
    {
        return demoOrderMapper.insertDemoOrder(demoOrder);
    }

    /**
     * 修改订单
     * 
     * @param demoOrder 订单
     * @return 结果
     */
    @Override
    public int updateDemoOrder(DemoOrder demoOrder)
    {
        demoOrder.setUpdateTime(DateUtils.getNowDate());
        return demoOrderMapper.updateDemoOrder(demoOrder);
    }

    /**
     * 批量删除订单
     * 
     * @param ids 需要删除的订单ID
     * @return 结果
     */
    @Override
    public int deleteDemoOrderByIds(Long[] ids)
    {
        return demoOrderMapper.deleteDemoOrderByIds(ids);
    }

    /**
     * 删除订单信息
     * 
     * @param id 订单ID
     * @return 结果
     */
    @Override
    public int deleteDemoOrderById(Long id)
    {
        return demoOrderMapper.deleteDemoOrderById(id);
    }
}
