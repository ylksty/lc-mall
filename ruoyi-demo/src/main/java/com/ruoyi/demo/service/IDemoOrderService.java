package com.ruoyi.demo.service;

import java.util.List;
import com.ruoyi.demo.domain.DemoOrder;

/**
 * 订单Service接口
 * 
 * @author yanglk
 * @date 2021-03-17
 */
public interface IDemoOrderService 
{
    /**
     * 查询订单
     * 
     * @param id 订单ID
     * @return 订单
     */
    public DemoOrder selectDemoOrderById(Long id);

    /**
     * 查询订单列表
     * 
     * @param demoOrder 订单
     * @return 订单集合
     */
    public List<DemoOrder> selectDemoOrderList(DemoOrder demoOrder);

    /**
     * 新增订单
     * 
     * @param demoOrder 订单
     * @return 结果
     */
    public int insertDemoOrder(DemoOrder demoOrder);

    /**
     * 修改订单
     * 
     * @param demoOrder 订单
     * @return 结果
     */
    public int updateDemoOrder(DemoOrder demoOrder);

    /**
     * 批量删除订单
     * 
     * @param ids 需要删除的订单ID
     * @return 结果
     */
    public int deleteDemoOrderByIds(Long[] ids);

    /**
     * 删除订单信息
     * 
     * @param id 订单ID
     * @return 结果
     */
    public int deleteDemoOrderById(Long id);
}
