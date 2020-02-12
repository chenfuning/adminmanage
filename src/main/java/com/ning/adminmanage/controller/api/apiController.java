package com.ning.adminmanage.controller.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("${api-url}")
public class apiController {
    /**
     * 页面通过参数pageName跳转到不页面
     * @param modelAndView
     * @param pageName
     * @return
     */
    @RequestMapping("/getPage")
    public ModelAndView getPage(ModelAndView modelAndView,String pageName){
        modelAndView.setViewName(pageName);
        return  modelAndView;
    }

}
