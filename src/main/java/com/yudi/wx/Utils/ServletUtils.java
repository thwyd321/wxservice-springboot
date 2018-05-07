/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.yudi.wx.Utils;

import com.alibaba.fastjson.JSON;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author jinlong.rhj
 * @version $Id: ServletUtils.java, v 0.1 2017-08-18 16:51 jinlong.rhj Exp $$
 */
public class ServletUtils {

    private static final Logger logger = LoggerFactory.getLogger(ServletUtils.class);

    public static String getRequestServerStr(HttpServletRequest request) {
        String serv = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
        return serv;
    }

    public static Map<String, String> getAllRequestParamsMap(HttpServletRequest request) {
        Map<String, String> params = new TreeMap<>();
        Map map = request.getParameterMap();
        int i = 0;
        for (Object key : map.keySet()) {
            params.put(key.toString(), request.getParameter(key.toString()));

        }
        return params;
    }

    /**
     * 拼接所有请求参数为url格式
     *
     * @param request
     * @param joinStr
     * @return
     */
    public static String getAllRequestParams(HttpServletRequest request, String joinStr) {
        if (joinStr == null || joinStr.length() == 0) {
            joinStr = "&";
        }
        StringBuilder sb = new StringBuilder(joinStr);
        Map map = request.getParameterMap();
        int i = 0;
        for (Object key : map.keySet()) {
            if (i != 0) {
                sb.append(joinStr);
            }
            sb.append(key).append("=");
            sb.append(request.getParameter(key.toString()));

            ++i;

        }
        String params = sb.toString();
        return params.substring(joinStr.length(), params.length());
    }

    /**
     * 返回json
     *
     * @param response
     * @param obj
     */
    public static void responseJson(HttpServletResponse response, Object obj) {
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-type", "application/json;charset=UTF-8");
        response.setStatus(200);
        try {
            response.getWriter().write(JSON.toJSONString(obj));
        } catch (IOException ex) {
            logger.error(ex.getMessage());
        }
    }

    /**
     * 返回写入纯文本
     *
     * @param response
     * @param text
     */
    public static void responseText(HttpServletResponse response, String text) {
        PrintWriter printWriter = null;
        try {
            response.setContentType("text/plain");//设置相应类型
            response.setCharacterEncoding("UTF-8");
            printWriter = response.getWriter();
            printWriter.write(text);
        } catch (IOException e) {
            logger.error("output error", e);
        } finally {
            if (printWriter != null) {
                printWriter.close();
            }
        }
    }

    public static String getIpAddress(javax.servlet.http.HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        // 如果是多级代理，那么取第一个ip为客户端ip
        if (ip != null && ip.indexOf(",") != -1) {
            ip = ip.substring(0, ip.indexOf(",")).trim();
        }

        return ip;
    }
}