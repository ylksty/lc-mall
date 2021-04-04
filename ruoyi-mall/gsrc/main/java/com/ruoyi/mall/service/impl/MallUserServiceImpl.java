package com.ruoyi.mall.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.mall.mapper.MallUserMapper;
import com.ruoyi.mall.domain.MallUser;
import com.ruoyi.mall.service.IMallUserService;

/**
 * 用户Service业务层处理
 * 
 * @author yanglk
 * @date 2021-04-04
 */
@Service
public class MallUserServiceImpl implements IMallUserService 
{
    @Autowired
    private MallUserMapper mallUserMapper;

    /**
     * 查询用户
     * 
     * @param id 用户ID
     * @return 用户
     */
    @Override
    public MallUser selectMallUserById(Long id)
    {
        return mallUserMapper.selectMallUserById(id);
    }

    /**
     * 查询用户列表
     * 
     * @param mallUser 用户
     * @return 用户
     */
    @Override
    public List<MallUser> selectMallUserList(MallUser mallUser)
    {
        return mallUserMapper.selectMallUserList(mallUser);
    }

    /**
     * 新增用户
     * 
     * @param mallUser 用户
     * @return 结果
     */
    @Override
    public int insertMallUser(MallUser mallUser)
    {
        mallUser.setCreateTime(DateUtils.getNowDate());
        return mallUserMapper.insertMallUser(mallUser);
    }

    /**
     * 修改用户
     * 
     * @param mallUser 用户
     * @return 结果
     */
    @Override
    public int updateMallUser(MallUser mallUser)
    {
        mallUser.setUpdateTime(DateUtils.getNowDate());
        return mallUserMapper.updateMallUser(mallUser);
    }

    /**
     * 批量删除用户
     * 
     * @param ids 需要删除的用户ID
     * @return 结果
     */
    @Override
    public int deleteMallUserByIds(Long[] ids)
    {
        return mallUserMapper.deleteMallUserByIds(ids);
    }

    /**
     * 删除用户信息
     * 
     * @param id 用户ID
     * @return 结果
     */
    @Override
    public int deleteMallUserById(Long id)
    {
        return mallUserMapper.deleteMallUserById(id);
    }
}
