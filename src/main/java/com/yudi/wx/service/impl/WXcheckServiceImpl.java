package com.yudi.wx.service.impl;

import com.yudi.wx.Constant.Constants;
import com.yudi.wx.Utils.WXUtils;
import com.yudi.wx.service.IWXcheckService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@Service
public class WXcheckServiceImpl implements IWXcheckService {
    @Value("${config.weixin.token}")
    private String token;
    private static Logger logger = Logger.getLogger(WXcheckServiceImpl.class);
    @Override
    public String checkUsers(HttpServletRequest request, HttpServletResponse response) {

        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");
        logger.info("signature"+signature);
        logger.info("timestamp"+timestamp);
        logger.info("nonce"+nonce);
        logger.info("echostr"+echostr);
        Boolean flag = WXUtils.checkSignature(signature,timestamp,nonce,token);
        logger.info(flag);
        if (flag){
            return Constants.MSG_SUCCESS;
        }
        return Constants.MSG_ERROR;
    }
}
