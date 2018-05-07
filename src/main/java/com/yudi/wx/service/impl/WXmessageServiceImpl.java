package com.yudi.wx.service.impl;

import com.yudi.wx.Utils.WXUtils;
import com.yudi.wx.controller.WxController;
import com.yudi.wx.service.IWXmessageService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
@Service
public class WXmessageServiceImpl implements IWXmessageService {
    private static Logger logger = Logger.getLogger(WxController.class);
    /**
     * 微信聊天服务
     * @param request
     * @return
     */
    @Override
    public String wxchat(HttpServletRequest request) {
        String resxml="";
        String xml = WXUtils.requstToXml(request);
        try {
            Map<String,String> map  = WXUtils.xmlToMap(xml);
            String ToUserName = map.get("ToUserName");
            String FromUserName = map.get("FromUserName");
            String CreateTime = map.get("CreateTime");
            String MsgType = map.get("MsgType");
            String Content = map.get("Content");
            String MsgId = map.get("MsgId");
            logger.info(ToUserName);
            logger.info(FromUserName);
            logger.info(CreateTime);
            logger.info(MsgType);
            logger.info(Content);
            logger.info(MsgId);
            Map<String,String > responseMap = new HashMap<>();
            responseMap.put("ToUserName",FromUserName);
            responseMap.put("FromUserName",ToUserName);
            responseMap.put("CreateTime",String.valueOf(System.currentTimeMillis()));
            responseMap.put("MsgType","text");
            responseMap.put("Content",Content+"测试成功");
            request.setAttribute("responseMap",responseMap);
            resxml = WXUtils.mapToXml(responseMap);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return resxml;
    }
}
