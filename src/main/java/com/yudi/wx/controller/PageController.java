package com.yudi.wx.controller;

import com.yudi.wx.Constant.Constants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Controller
@RequestMapping("/page")
public class PageController {


    @RequestMapping(value = "/goindex", method = RequestMethod.GET)
    public String goindex() {
        System.out.println("asfdf");
        return "fragments";
    }


}
