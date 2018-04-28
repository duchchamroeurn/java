package com.sangkhim.spring.service;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.sangkhim.spring.base.test.AbstractTest;
import com.sangkhim.spring.domain.Product;

public class ProductServiceTest extends AbstractTest {
	
	private static Logger logger = LoggerFactory.getLogger(ProductServiceTest.class);

	@Autowired
	private ProductService productService;

	@Test
	public void getAll() {
		List<Product> list = productService.getAll();
		logger.debug("{}", list);
		assertNotNull(list);
	}

}