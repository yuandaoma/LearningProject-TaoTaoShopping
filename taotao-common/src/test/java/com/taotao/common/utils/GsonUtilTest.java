package com.taotao.common.utils;

import java.util.Date;

import org.junit.Test;

/**
 * @author ZhuTao
 * @version 1.0
 * @Date 2017年10月7日
 * @description
 */
public class GsonUtilTest {

	@Test
	public void testObjectToGson() {
		Person person = new Person(150736071237663L, "taotao", new Date());
		String retVal = GsonUtil.getGson().toJson(person);
		System.out.println("retVal = " + retVal);
	}

	@Test
	public void testJsonToObjectByGson() {
		String personJson = "{\"id\":150736071237663,\"name\":\"taotao\",\"Birthday\":1507385089935}";
		Person person = GsonUtil.getGson().fromJson(personJson, Person.class);
		System.out.println("person = " + person);
	}

}

class Person {
	private Long id;

	private String name;

	private Date Birthday;

	public Person() {
	}

	public Person(Long id, String name, Date birthday) {
		this.id = id;
		this.name = name;
		Birthday = birthday;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBirthday() {
		return Birthday;
	}

	public void setBirthday(Date birthday) {
		Birthday = birthday;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + ", Birthday=" + Birthday + "]";
	}

}
