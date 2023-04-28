package com.pix.forum.controller;

import java.util.*;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.http.HttpRequest;
import java.util.Enumeration;
import java.util.Objects;

/**
 * @Author pix
 * @Date 2023/4/25 21:26
 */
@Controller
@RequestMapping("/alpha")
public class AlphaController {

    @RequestMapping("/hello")
    @ResponseBody
    public String hello(){
        return "Hello,SpringBoot!";
    }

    @RequestMapping("/http")
    public void http(HttpServletRequest request, HttpServletResponse response){
        System.out.println(request.getMethod());
        System.out.println(request.getServletPath());
        Enumeration<String> headerNames = request.getHeaderNames();
        while(headerNames.hasMoreElements()){
            String name = headerNames.nextElement();
            String value = request.getHeader(name);
            System.out.println(name + ":" + value);
        }
        System.out.println(request.getParameter("code"));

        //
        response.setContentType("text/html;charset = utf-8");
        try(PrintWriter writer = response.getWriter();){


            writer.write("<h1>牛客网</h1>");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @RequestMapping(path= "/students", method = RequestMethod.GET)
    @ResponseBody
    public String getSutdent(
            @RequestParam(name = "current", required = false,defaultValue = "1") int current,
            @RequestParam(name = "limit", required = false,defaultValue = "10")int limit) {
        System.out.println(current);
        System.out.println(limit);
        return "some student";
    }


    @RequestMapping(path = "/student/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String getStudent(@PathVariable("id") int id) {
        System.out.println(id);
        return "a student";
    }

    // POST
    @RequestMapping(path = "/student", method = RequestMethod.POST)
    @ResponseBody
    public String saveStudent(String name, int age) {
        System.out.println(name);
        System.out.println(age);
        return "success";
    }

    //modelAndView
    @RequestMapping(path = "/teacher", method = RequestMethod.GET)
    public ModelAndView getTeacher() {
        ModelAndView mav = new ModelAndView();
        mav.addObject("name", "pix");
        mav.addObject("age", 22);
        mav.setViewName("/demo/view");
        return mav;
    }


    //model
    @RequestMapping(path = "/school",method = RequestMethod.GET)
    public String getSchool(Model model){
        model.addAttribute("name","PKU");
        model.addAttribute("age","125");
        return "/demo/view";
    }

    //响应json(异步请求)

    @RequestMapping(path = "/emp",method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getEMpeg(){
        Map<String,Object> emp = new HashMap<>();
        emp.put("name","张三");
        emp.put("age",23);
        emp.put("salary",8000.00);
        return emp;
    }

}
