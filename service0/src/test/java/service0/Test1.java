package service0;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.demo.service0.config.Mongo3Properties;

public class Test1 {

	@Autowired
	private static Mongo3Properties properties;
	
	@Test
	public void test() {
		System.out.println(properties);
	}
}
