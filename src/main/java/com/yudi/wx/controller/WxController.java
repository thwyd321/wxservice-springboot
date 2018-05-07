package com.yudi.wx.controller;
import com.yudi.wx.Constant.Constants;
import com.yudi.wx.Utils.WXUtils;
import com.yudi.wx.service.IWXcheckService;
import com.yudi.wx.service.IWXmessageService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@Controller
@RequestMapping("/wyd/wx")
public class WxController {
    private static Logger logger = Logger.getLogger(WxController.class);
    @Autowired
    private IWXcheckService wxcheckService;
    @Autowired
    private IWXmessageService wxMessageService;
    /**
     * 验证
     * @param request
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET)
    public void doGet(HttpServletRequest request, HttpServletResponse response)  {
        //验证用户签名
        String result = wxcheckService.checkUsers(request,response) ;
        if (Constants.MSG_SUCCESS.equals(result)){
            try {
               PrintWriter writer = response.getWriter();
               //将echostr 返还给微信服务器
               writer.print(request.getParameter("echostr"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * 接受微信发过来的Post请求
     * **/
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @ResponseBody
    @RequestMapping(method = RequestMethod.POST)
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String result = wxMessageService.wxchat(request);
        PrintWriter out = response.getWriter();
        result=new String(result.getBytes(), "iso8859-1");
        out.print(result);
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @ResponseBody
    @RequestMapping(value = "ddd",method = RequestMethod.GET)
    public void ddd(HttpServletRequest request, HttpServletResponse response)  {
        System.out.println("999999999999999999999");
    }
}
