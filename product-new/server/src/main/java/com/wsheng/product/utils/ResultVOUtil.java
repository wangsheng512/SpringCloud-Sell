package com.wsheng.product.utils;

import com.wsheng.product.VO.ResultVO;

/**
 * @Auther: wsheng
 * @Date: 2018/10/19 15:39
 * @Description:
 */
public class ResultVOUtil {

    public static ResultVO success(Object object) {
        ResultVO resultVO = new ResultVO();
        resultVO.setData(object);
        resultVO.setCode(0);
        resultVO.setMsg("成功");
        return resultVO;
    }
}