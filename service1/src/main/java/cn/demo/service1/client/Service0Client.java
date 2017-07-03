package cn.demo.service1.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cn.demo.model.Person;

@FeignClient("service0")
public interface Service0Client {

    @RequestMapping(method = RequestMethod.GET, value = "/service0/test")
    String test();
    
    @RequestMapping(value="/person/insert",method=RequestMethod.POST)
    Person insert(@RequestBody Person person);//用post提交对象必须加 @RequestBody
    
    @RequestMapping(value="/person/findById",method=RequestMethod.GET)
    Person findById(@RequestParam("id")String id);//@FeignClient默认以post提交 如果用get提交参数比加@RequestParam
}
