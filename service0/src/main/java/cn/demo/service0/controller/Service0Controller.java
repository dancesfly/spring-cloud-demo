package cn.demo.service0.controller;

import java.util.Date;

import org.apache.commons.lang.time.FastDateFormat;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/service0")
public class Service0Controller {

    @RequestMapping(value="/test",method=RequestMethod.GET)
    String test() {
        return FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss.sss").format(new Date());
    }
}
