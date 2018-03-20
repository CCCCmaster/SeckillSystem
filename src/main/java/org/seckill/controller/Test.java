package org.seckill.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import java.util.HashMap;
import java.util.Map;

@Controller
public class Test {
    @RequestMapping(value = "/json")
    @ResponseBody
    public Map tessf(){
        Map<String, String> hp = new HashMap<String, String>();
        hp.put("msg","succ");
        return hp;

    }
    @RequestMapping(value = "/")
    public String kk(){
        return "home";
    }

}
