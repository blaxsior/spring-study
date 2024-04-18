package com.example.mvc.basic.response;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ResponseViewController {
    @RequestMapping("/response-view-v1")
    public ModelAndView responseView1() {
        ModelAndView modelAndView = new ModelAndView("response/hello")
                .addObject("data","testdata");

        return modelAndView;
    }

    @RequestMapping("/response-view-v2")
    public String responseView2(Model model) {
        model.addAttribute("data","testdata");

        return "response/hello";
    }

    @RequestMapping("/response/hello")
    public void responseView3(Model model) {
        model.addAttribute("data","testdata");
    }
}
