package com.wsheng.user.controller;

import com.wsheng.user.VO.ResultVO;
import com.wsheng.user.constant.CookieContant;
import com.wsheng.user.constant.RedisContant;
import com.wsheng.user.dataobject.UserInfo;
import com.wsheng.user.enums.ResultEnum;
import com.wsheng.user.enums.RoleEnum;
import com.wsheng.user.service.UserService;
import com.wsheng.user.utils.CookieUtil;
import com.wsheng.user.utils.ResultVOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @Auther: wsheng
 * @Date: 2018/11/6 21:58
 * @Description:
 */
@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private UserService userService;

    /**
     * 买家登录
     * @param openid
     * @param response
     * @return
     */
    @RequestMapping("/buyer")
    public ResultVO buyer(@RequestParam("openid") String openid,
                          HttpServletResponse response){

        //1.openid 和数据库中的数据是否匹配
        UserInfo userInfo = userService.findByOpenid(openid);
        if (userInfo == null){
            return ResultVOUtil.error(ResultEnum.LOGIN_FAIL);
        }
        //2.判断角色
        if (RoleEnum.BUYER.getCode() != userInfo.getRole() ){
            return ResultVOUtil.error(ResultEnum.ROLE_ERROR);
        }
        //3.cookies 里面设置openID=ABC
        CookieUtil.set(response, CookieContant.OPENID,openid,CookieContant.EXPIRE);

        return ResultVOUtil.success();
    }

    /**
     * 卖家登录
     * @param openid
     * @param response
     * @return
     */
    @RequestMapping("/seller")
    public ResultVO seller(@RequestParam("openid") String openid,
                          HttpServletRequest request,
                          HttpServletResponse response){
        //判断是否登录
        Cookie cookie = CookieUtil.get(request,CookieContant.TOKEN);
        if (cookie != null && !StringUtils.isEmpty(stringRedisTemplate.opsForValue().get(String.format(RedisContant.TOKEN_TEMPLATE,cookie.getValue())))){
            return ResultVOUtil.success();
        }
        //1.openid 和数据库中的数据是否匹配
        UserInfo userInfo = userService.findByOpenid(openid);
        if (userInfo == null){
            return ResultVOUtil.error(ResultEnum.LOGIN_FAIL);
        }
        //2.判断角色
        if (RoleEnum.SELLER.getCode() != userInfo.getRole() ){
            return ResultVOUtil.error(ResultEnum.ROLE_ERROR);
        }
        //3.设置redis,key=UUID ,value=cd
        String uuid = UUID.randomUUID().toString();
        stringRedisTemplate.opsForValue().set(String.format(RedisContant.TOKEN_TEMPLATE,uuid),
                openid,
                CookieContant.EXPIRE,
                TimeUnit.SECONDS
                );
        //4.cookies 里面设置token=UUID
        CookieUtil.set(response, CookieContant.TOKEN,uuid,CookieContant.EXPIRE);

        return ResultVOUtil.success();
    }
}
