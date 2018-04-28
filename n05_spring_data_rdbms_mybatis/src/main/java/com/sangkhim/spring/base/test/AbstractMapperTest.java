package com.sangkhim.spring.base.test;

import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test-context.xml"})
@Transactional
public abstract class AbstractMapperTest extends AbstractTransactionalJUnit4SpringContextTests {
	
	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	
}