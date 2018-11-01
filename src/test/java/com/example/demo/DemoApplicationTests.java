package com.example.demo;

import com.alibaba.fastjson.JSONObject;
import com.example.common.redis.service.RedisService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {
	private JSONObject json = new JSONObject();
	private static final Logger logger = LoggerFactory.getLogger(DemoApplicationTests.class);

	@Autowired
	private RedisService redisService;

	@Test
	public void contextLoads() {

	}

	/**
	 * 插入字符串
	 */
	@Test
	public void setString() {
		redisService.set("redis_string_test", "springboot redis test");
	}

	/**
	 * 获取字符串
	 */
	@Test
	public void getString() {
		String result = redisService.get("redis_string_test");
		logger.info(result);
	}

	/**
	 * 插入对象
	 */
	@Test
	public void setObject() {
		Person person = new Person("person", "male");
		redisService.set("redis_obj_test", json.toJSONString(person));
	}

	/**
	 * 获取对象
	 */
	@Test
	public void getObject() {
		String result = redisService.get("redis_obj_test");
		Person person = json.parseObject(result, Person.class);
		logger.info(json.toJSONString(person));
	}

	/**
	 * 插入对象List
	 */
	@Test
	public void setList() {
		Person person1 = new Person("person1", "male");
		Person person2 = new Person("person2", "female");
		Person person3 = new Person("person3", "male");
		List<Person> list = new ArrayList<>();
		list.add(person1);
		list.add(person2);
		list.add(person3);
		redisService.set("redis_list_test", json.toJSONString(list));
	}

	/**
	 * 获取list
	 */
	@Test
	public void getList() {
		String result = redisService.get("redis_list_test");
		List<String> list = json.parseArray(result, String.class);
		System.out.println(list);
	}

	@Test
	public void remove() {
		redisService.remove("redis_string_test");
	}

}

class Person {
	private String name;
	private String sex;

	public Person() {

	}

	public Person(String name, String sex) {
		this.name = name;
		this.sex = sex;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
}
