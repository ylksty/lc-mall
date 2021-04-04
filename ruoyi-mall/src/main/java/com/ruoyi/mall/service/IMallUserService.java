package com.ruoyi.mall.service;

import java.util.List;
import com.ruoyi.mall.domain.MallUser;

/**
 * 用户Service接口
 * 
 * @author yanglk
 * @date 2021-04-04
 */
public interface IMallUserService 
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
     * 批量删除用户
     * 
     * @param ids 需要删除的用户ID
     * @return 结果
     */
    public int deleteMallUserByIds(Long[] ids);

    /**
     * 删除用户信息
     * 
     * @param id 用户ID
     * @return 结果
     */
    public int deleteMallUserById(Long id);

    /**
     * @author joe 2021/4/4 11:03
     * 根据openid查询用户
     * @param oprnid: oprnid
     * @return {@link MallUser }
     **/
    public MallUser selectMallUserByOpenid(String oprnid);
}
