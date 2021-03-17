package com.ruoyi.mall.mapper;

import java.util.List;
import com.ruoyi.mall.domain.MallUser;

/**
 * 用户Mapper接口
 * 
 * @author yanglk
 * @date 2021-03-17
 */
public interface MallUserMapper 
{
    /**
     * 查询用户
     * 
     * @param id 用户ID
     * @return 用户
     */
    public MallUser selectMallUserById(Long id);

    /**
     * 查询用户列表
     * 
     * @param mallUser 用户
     * @return 用户集合
     */
    public List<MallUser> selectMallUserList(MallUser mallUser);

    /**
     * 新增用户
     * 
     * @param mallUser 用户
     * @return 结果
     */
    public int insertMallUser(MallUser mallUser);

    /**
     * 修改用户
     * 
     * @param mallUser 用户
     * @return 结果
     */
    public int updateMallUser(MallUser mallUser);

    /**
     * 删除用户
     * 
     * @param id 用户ID
     * @return 结果
     */
    public int deleteMallUserById(Long id);

    /**
     * 批量删除用户
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteMallUserByIds(Long[] ids);
}
