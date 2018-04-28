package com.sangkhim.spring.mapper;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.sangkhim.spring.base.test.AbstractTest;
import com.sangkhim.spring.domain.Product;

public class ProductMapperTest extends AbstractTest {
	
	private static Logger logger = LoggerFactory.getLogger(ProductMapperTest.class);

	@Autowired
	private ProductMapper productMapper;

	@Test
	public void getAll() {
		List<Product> list = productMapper.getAll();
		logger.debug("{}", list);
		assertNotNull(list);
	}

}