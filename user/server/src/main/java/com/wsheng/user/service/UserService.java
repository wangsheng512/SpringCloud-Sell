package com.wsheng.user.service;

import com.wsheng.user.dataobject.UserInfo;

/**
 * @Auther: wsheng
 * @Date: 2018/11/6 21:55
 * @Description:
 */
public interface UserService {

    /**
     * 通过openID来查询
     * @param openid
     * @return
     */
    UserInfo findByOpenid(String openid);
}
