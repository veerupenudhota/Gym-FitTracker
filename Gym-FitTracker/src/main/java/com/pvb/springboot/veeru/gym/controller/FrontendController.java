package com.pvb.springboot.veeru.gym.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FrontendController {

    @RequestMapping({ "/", "/{path:^(?!api|static|images1|music|css|js|media|.*\\..*).*$}" })
    public String forward() {
        return "forward:/index.html";
    }
}

