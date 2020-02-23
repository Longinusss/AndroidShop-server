package com.lon.qingshe.controller;

import com.lon.qingshe.mapper.GoodsMapper;
import com.lon.qingshe.util.JsonR;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/goods")
public class GoodsController {
    @Autowired
    GoodsMapper goodsMapper;

    @RequestMapping("/showAll")
    public JsonR showAllGooods() {
        try {
            return JsonR.createSuccess(goodsMapper.findAllGoods());

        } catch (Exception e) {
            return JsonR.createFail(e.getMessage());
        }
    }

    @RequestMapping("/search")
    public JsonR search(String name) {
        try {
            if (goodsMapper.search(name) != null &&goodsMapper.search(name).size()!=0)
                return JsonR.createSuccess(goodsMapper.search(name));
            else
                throw new Exception("找不到您要搜索的结果");
        } catch (Exception e) {
            return JsonR.createFail(e.getMessage());
        }

    }

    //目前只支持收藏商品，服务不支持
    @RequestMapping("/collect")
    public JsonR collect(String user_id, int goods_id, HttpServletRequest request) {
        try {
            String header = request.getHeader("receipt");
            if (header == null || header.length() == 0)
                return JsonR.createFail("未登录!");
            if (goodsMapper.ifCollect(user_id, goods_id).size() != 0)
                throw new Exception("已收藏过！");
            goodsMapper.collect(user_id, goods_id);
            return JsonR.createSuccess("收藏成功！");
        } catch (Exception e) {
            return JsonR.createFail(e.getMessage());
        }
    }

}
