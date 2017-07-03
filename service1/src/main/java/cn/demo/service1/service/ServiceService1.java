package cn.demo.service1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import cn.demo.model.Person;
import cn.demo.service1.client.Service0Client;

@Service
public class ServiceService1 {
	
	@Autowired
    Service0Client service0Client;
	
	@HystrixCommand(fallbackMethod="testfallback")
	public String test(){
		return service0Client.test();
	}
	
	private String testfallback(){
		System.out.println("dddddddddddddd");
		return "断路器生效";
	}
	
	@HystrixCommand(fallbackMethod="insertfallback")
	public Person insert(Person persion){
		return service0Client.insert(persion);
	}
	
	
	private Person insertfallback(Person persion,Throwable e){
		System.out.println("断路器生效");
		e.printStackTrace();
		return persion;
	}
	
	@HystrixCommand(fallbackMethod="findByIdfallback")
	public Person findById(String id){
		return service0Client.findById(id);
	}
	
	private Person findByIdfallback(String id,Throwable e){
		e.printStackTrace();
		return new Person();
	}
}
