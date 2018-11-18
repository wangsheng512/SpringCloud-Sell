package com.wsheng.order.utils;

import com.wsheng.order.VO.ResultVO;

/**
 * @Auther: wsheng
 * @Date: 2018/10/22 16:22
 * @Description:
 */
public class ResultVOUtil {

    public static ResultVO success(Object object) {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(0);
        resultVO.setMsg("成功");
        resultVO.setData(object);
        return resultVO;
    }
}