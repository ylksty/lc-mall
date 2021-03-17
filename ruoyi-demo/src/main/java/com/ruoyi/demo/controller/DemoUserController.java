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
import com.ruoyi.demo.domain.DemoUser;
import com.ruoyi.demo.service.IDemoUserService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 用户信息Controller
 * 
 * @author yanglk
 * @date 2021-03-17
 */
@RestController
@RequestMapping("/demo/user")
public class DemoUserController extends BaseController
{
    @Autowired
    private IDemoUserService demoUserService;

    /**
     * 查询用户信息列表
     */
    @PreAuthorize("@ss.hasPermi('demo:user:list')")
    @GetMapping("/list")
    public TableDataInfo list(DemoUser demoUser)
    {
        startPage();
        List<DemoUser> list = demoUserService.selectDemoUserList(demoUser);
        return getDataTable(list);
    }

    /**
     * 导出用户信息列表
     */
    @PreAuthorize("@ss.hasPermi('demo:user:export')")
    @Log(title = "用户信息", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(DemoUser demoUser)
    {
        List<DemoUser> list = demoUserService.selectDemoUserList(demoUser);
        ExcelUtil<DemoUser> util = new ExcelUtil<DemoUser>(DemoUser.class);
        return util.exportExcel(list, "user");
    }

    /**
     * 获取用户信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('demo:user:query')")
    @GetMapping(value = "/{userId}")
    public AjaxResult getInfo(@PathVariable("userId") Long userId)
    {
        return AjaxResult.success(demoUserService.selectDemoUserById(userId));
    }

    /**
     * 新增用户信息
     */
    @PreAuthorize("@ss.hasPermi('demo:user:add')")
    @Log(title = "用户信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody DemoUser demoUser)
    {
        return toAjax(demoUserService.insertDemoUser(demoUser));
    }

    /**
     * 修改用户信息
     */
    @PreAuthorize("@ss.hasPermi('demo:user:edit')")
    @Log(title = "用户信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody DemoUser demoUser)
    {
        return toAjax(demoUserService.updateDemoUser(demoUser));
    }

    /**
     * 删除用户信息
     */
    @PreAuthorize("@ss.hasPermi('demo:user:remove')")
    @Log(title = "用户信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{userIds}")
    public AjaxResult remove(@PathVariable Long[] userIds)
    {
        return toAjax(demoUserService.deleteDemoUserByIds(userIds));
    }
}
