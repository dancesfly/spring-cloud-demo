package cn.demo.service0.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.demo.model.Person;
import cn.demo.service0.service.PersonService;

@RestController
@RequestMapping("/person/")
public class PersonController {
	
	@Autowired
	private PersonService personService;
	
	@RequestMapping(value="/insert",method=RequestMethod.POST)
	public Person insert(@RequestBody Person persion){//用post提交对象必须加 @RequestBody
		return personService.insert(persion);
	}
	
	@RequestMapping(value="/findById",method=RequestMethod.GET)
	public Person findById(String id){
		System.out.println(id);
		System.out.println(personService.findById(id));
		return personService.findById(id);
	}
}
