package cn.demo.service1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.demo.model.Person;
import cn.demo.service1.service.ServiceService1;

@RestController
public class Service1Controller {
    @Autowired
    private ServiceService1 serviceService1;

    @RequestMapping("/test")
    public String test() {
        return serviceService1.test();
    }
    
    @RequestMapping(value="/person/insert",method=RequestMethod.GET)
    public Person  insert(Person person){
    	return serviceService1.insert(person);
    }
    
    @RequestMapping(value="/person/findById",method=RequestMethod.GET)
    public Person findById(String id){
    	return serviceService1.findById(id);
    }
}
