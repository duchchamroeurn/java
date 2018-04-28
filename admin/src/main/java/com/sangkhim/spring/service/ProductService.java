package com.sangkhim.spring.service;

import java.util.List;

import com.sangkhim.spring.domain.Product;
import com.sangkhim.spring.domain.ProductImage;

public interface ProductService {
	
	public List<Product> getAll();
	
	public Product getById(int productId);
	
	public int insert(Product product);
	
	public int update(Product product);

	public int updateIsOnline(int productId, int isOnline);
	
	public int deleteById(int productId);
	
	public ProductImage getProductImageById(int productImageId);
	
	public int insertProductImage(ProductImage productImage);
	
	public int deleteProductImage(int productImageId);
	
	/**
	 *  DataTables
	 *  getList
	 *  getListCount
	 *  getSearchList
	 *  getSearchListCount
	 */
	public List<Product> getList(Product product);
	
	public Long getListCount(Product product);
	
	public List<Product> getSearchList(Product product);
	
	public Long getSearchListCount(Product product);
	
}