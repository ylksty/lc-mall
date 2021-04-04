package com.ruoyi.controller;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.binarywang.wx.miniapp.bean.WxMaUserInfo;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.domain.model.LoginBody;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.utils.ServletUtils;
import com.ruoyi.dto.WxLoginInfo;
import com.ruoyi.framework.web.service.SysLoginService;
import com.ruoyi.framework.web.service.SysPermissionService;
import com.ruoyi.framework.web.service.TokenService;
import com.ruoyi.mall.domain.MallUser;
import com.ruoyi.mall.service.impl.MallUserServiceImpl;
import com.ruoyi.util.IpUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * 登录验证
 * 
 * @author ruoyi
 */
@Api("用户登录")
@RestController
public class WxAuthController
{
    @Autowired
    private SysLoginService loginService;

    @Autowired
    private SysPermissionService permissionService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private WxMaService wxService;

    @Autowired
    private MallUserServiceImpl mallUserService;
    /**
     * 登录方法
     * 
     * @param wxLoginInfo 登录信息
     * @return 结果
     */
    @ApiOperation("微信登录")
    @PostMapping("/login_by_weixin")
    public AjaxResult login(@RequestBody WxLoginInfo wxLoginInfo, HttpServletRequest request)
    {
        AjaxResult ajax = AjaxResult.success();

        String code = wxLoginInfo.getCode();
        String encryptedData = wxLoginInfo.getEncryptedData();
        String ivStr = wxLoginInfo.getIv();
        String rawData = wxLoginInfo.getRawData();
        String signature = wxLoginInfo.getSignature();
        if (code == null || encryptedData == null || ivStr == null || rawData == null || signature == null) {
            return AjaxResult.error("参数错误");
        }

        String sessionKey = null;
        String openId = null;
        WxMaJscode2SessionResult result = null;
        try {
            result = wxService.getUserService().getSessionInfo(code);
            sessionKey = result.getSessionKey();
            openId = result.getOpenid();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.error("意外请重试");
        }
        System.out.println(result);

        if (sessionKey == null || openId == null) {
            return AjaxResult.error("错误code");
        }

        boolean checkUserInfo = wxService.getUserService().checkUserInfo(sessionKey, rawData, signature);
        if (!checkUserInfo) {
            return AjaxResult.error("数据错误或过期，请重试");
        }

        WxMaUserInfo userInfo = wxService.getUserService().getUserInfo(sessionKey, encryptedData, ivStr);

        MallUser user = mallUserService.selectMallUserByOpenid(openId);
        if (user == null) {
            user = new MallUser();
            user.setUsername(userInfo.getNickName());
            user.setPassword(openId);
            user.setOpenid(openId);
            user.setAvatar(userInfo.getAvatarUrl());
            user.setNickname(userInfo.getNickName());
            user.setGender(Convert.toInt(userInfo.getGender()));
            user.setUserLevel(0);
            user.setStatus(0);
            user.setLastLoginIp(IpUtil.getIpAddr(request));
            user.setSessionKey(sessionKey);
            mallUserService.insertMallUser(user);
        } else {
            user.setLastLoginIp(IpUtil.getIpAddr(request));
            user.setSessionKey(sessionKey);
            mallUserService.updateMallUser(user);
            if (mallUserService.updateMallUser(user) == 0) {
                return AjaxResult.error("刷新失败，请重试");
            }
        }

        // 生成令牌
//        String token = loginService.login(loginBody.getUsername(), loginBody.getPassword(), loginBody.getCode(),
//                loginBody.getUuid());
//        ajax.put(Constants.TOKEN, token);
        return ajax;
    }
    /**
     * 登录方法
     *
     * @param loginBody 登录信息
     * @return 结果
     */
    @PostMapping("/loginb")
    public AjaxResult loginb(@RequestBody LoginBody loginBody)
    {
        AjaxResult ajax = AjaxResult.success();
        // 生成令牌
        String token = loginService.login(loginBody.getUsername(), loginBody.getPassword(), loginBody.getCode(),
                loginBody.getUuid());
        ajax.put(Constants.TOKEN, token);
        return ajax;
    }

    /**
     * 获取用户信息
     * 
     * @return 用户信息
     */
    @GetMapping("getInfo")
    public AjaxResult getInfo()
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        SysUser user = loginUser.getUser();
        // 角色集合
        Set<String> roles = permissionService.getRolePermission(user);
        // 权限集合
        Set<String> permissions = permissionService.getMenuPermission(user);
        AjaxResult ajax = AjaxResult.success();
        ajax.put("user", user);
        ajax.put("roles", roles);
        ajax.put("permissions", permissions);
        return ajax;
    }
}
