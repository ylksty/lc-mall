package com.ruoyi.demo.controller;

import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.demo.domain.DemoOrder;
import com.ruoyi.demo.service.IDemoOrderService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 订单Controller
 * 
 * @author yanglk
 * @date 2021-03-17
 */
@RestController
@RequestMapping("/demo/order")
public class DemoOrderController extends BaseController
{
    @Autowired
    private IDemoOrderService demoOrderService;

    /**
     * 查询订单列表
     */
    @PreAuthorize("@ss.hasPermi('demo:order:list')")
    @GetMapping("/list")
    public TableDataInfo list(DemoOrder demoOrder)
    {
        startPage();
        List<DemoOrder> list = demoOrderService.selectDemoOrderList(demoOrder);
        return getDataTable(list);
    }

    /**
     * 导出订单列表
     */
    @PreAuthorize("@ss.hasPermi('demo:order:export')")
    @Log(title = "订单", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(DemoOrder demoOrder)
    {
        List<DemoOrder> list = demoOrderService.selectDemoOrderList(demoOrder);
        ExcelUtil<DemoOrder> util = new ExcelUtil<DemoOrder>(DemoOrder.class);
        return util.exportExcel(list, "order");
    }

    /**
     * 获取订单详细信息
     */
    @PreAuthorize("@ss.hasPermi('demo:order:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(demoOrderService.selectDemoOrderById(id));
    }

    /**
     * 新增订单
     */
    @PreAuthorize("@ss.hasPermi('demo:order:add')")
    @Log(title = "订单", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody DemoOrder demoOrder)
    {
        return toAjax(demoOrderService.insertDemoOrder(demoOrder));
    }

    /**
     * 修改订单
     */
    @PreAuthorize("@ss.hasPermi('demo:order:edit')")
    @Log(title = "订单", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody DemoOrder demoOrder)
    {
        return toAjax(demoOrderService.updateDemoOrder(demoOrder));
    }

    /**
     * 删除订单
     */
    @PreAuthorize("@ss.hasPermi('demo:order:remove')")
    @Log(title = "订单", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(demoOrderService.deleteDemoOrderByIds(ids));
    }
}
