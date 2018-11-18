package com.wsheng.user.repository;

import com.wsheng.user.dataobject.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Auther: wsheng
 * @Date: 2018/11/6 21:54
 * @Description:
 */
public interface UserRepostory extends JpaRepository<UserInfo,String>{

    UserInfo findByOpenid(String openid);
}
