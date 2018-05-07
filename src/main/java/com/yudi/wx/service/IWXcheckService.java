package com.yudi.wx.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public interface IWXcheckService {

    public String checkUsers(HttpServletRequest request,HttpServletResponse response);
}
