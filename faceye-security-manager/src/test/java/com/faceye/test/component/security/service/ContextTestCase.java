package com.faceye.test.component.security.service;

import org.eclipse.core.runtime.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.faceye.feature.util.bean.ContextLoader;

@RunWith(JUnit4.class)
public class ContextTestCase {

	private String beanName="";
	@Before
	public void set() throws Exception{
		beanName="userServiceImpl";
	}
	@After
	public void after() throws Exception{}
	
	@Test
	public void getBean() throws Exception{
		Object bean=ContextLoader.getInstance().getBean(beanName);
		Assert.isTrue(bean!=null);
	}
	
}
