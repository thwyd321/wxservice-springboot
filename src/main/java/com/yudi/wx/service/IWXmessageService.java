package com.yudi.wx.service;

import javax.servlet.http.HttpServletRequest;

public interface IWXmessageService {
    /**
     * 微信聊天服务
     * @param request
     * @return
     */
    public String wxchat(HttpServletRequest request);

}
