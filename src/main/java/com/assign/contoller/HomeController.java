package com.assign.contoller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * This is page controller that will gives view pages.
 * @author kimilb
 *
 */
@Controller
public class HomeController {

    @RequestMapping(value = "/")
    public String index() {
        return "index";
    }
    
    
    @RequestMapping(value = "/box")
    public String index22() {
        return "index2";
    }
}
