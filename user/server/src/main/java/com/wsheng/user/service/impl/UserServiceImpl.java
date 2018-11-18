package com.wsheng.user.service.impl;

import com.wsheng.user.dataobject.UserInfo;
import com.wsheng.user.repository.UserRepostory;
import com.wsheng.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Auther: wsheng
 * @Date: 2018/11/6 21:56
 * @Description:
 */
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepostory userRepostory;


    @Override
    public UserInfo findByOpenid(String openid) {
        return userRepostory.findByOpenid(openid);
    }
}
