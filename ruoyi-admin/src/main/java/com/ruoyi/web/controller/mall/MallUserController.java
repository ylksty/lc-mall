package com.ruoyi.web.controller.mall;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.mall.domain.MallUser;
import com.ruoyi.mall.service.IMallUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户Controller
 * 
 * @author yanglk
 * @date 2021-03-17
 */
@RestController
@RequestMapping("/mall/user")
public class MallUserController extends BaseController
{
    @Autowired
    private IMallUserService mallUserService;

    /**
     * 查询用户列表
     */
    @PreAuthorize("@ss.hasPermi('mall:user:list')")
    @GetMapping("/list")
    public TableDataInfo list(MallUser mallUser)
    {
        startPage();
        List<MallUser> list = mallUserService.selectMallUserList(mallUser);
        return getDataTable(list);
    }

    /**
     * 导出用户列表
     */
    @PreAuthorize("@ss.hasPermi('mall:user:export')")
    @Log(title = "用户", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(MallUser mallUser)
    {
        List<MallUser> list = mallUserService.selectMallUserList(mallUser);
        ExcelUtil<MallUser> util = new ExcelUtil<MallUser>(MallUser.class);
        return util.exportExcel(list, "user");
    }

    /**
     * 获取用户详细信息
     */
    @PreAuthorize("@ss.hasPermi('mall:user:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(mallUserService.selectMallUserById(id));
    }

    /**
     * 新增用户
     */
    @PreAuthorize("@ss.hasPermi('mall:user:add')")
    @Log(title = "用户", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MallUser mallUser)
    {
        return toAjax(mallUserService.insertMallUser(mallUser));
    }

    /**
     * 修改用户
     */
    @PreAuthorize("@ss.hasPermi('mall:user:edit')")
    @Log(title = "用户", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MallUser mallUser)
    {
        return toAjax(mallUserService.updateMallUser(mallUser));
    }

    /**
     * 删除用户
     */
    @PreAuthorize("@ss.hasPermi('mall:user:remove')")
    @Log(title = "用户", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(mallUserService.deleteMallUserByIds(ids));
    }
}
