package com.sangkhim.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sangkhim.spring.domain.Product;
import com.sangkhim.spring.domain.ProductImage;
import com.sangkhim.spring.mapper.ProductMapper;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	ProductMapper productMapper;
	
	public List<Product> getAll() {
		return productMapper.getAll();
	}
	
	public Product getById(int productId) {
		return productMapper.getById(productId);
	}
	
	public int insert(Product product) {
		return productMapper.insert(product);
	}
	
	public int update(Product product) {
		return productMapper.update(product);
	}
	
	public int updateIsOnline(int productId, int isOnline) {
		return productMapper.updateIsOnline(productId, isOnline);
	}
	
	public int deleteById(int productId) {
		return productMapper.deleteById(productId);
	}
	
	public ProductImage getProductImageById(int productImageId) {
		return productMapper.getProductImageById(productImageId);
	}
	
	public int insertProductImage(ProductImage productImage) {
		return productMapper.insertProductImage(productImage);
	}
	
	public int deleteProductImage(int productImageId) {
		return productMapper.deleteProductImage(productImageId);
	}
	
	/**
	 *  DataTables
	 *  getList
	 *  getListCount
	 *  getSearchList
	 *  getSearchListCount
	 */
	public List<Product> getList(Product product) {
		return productMapper.getList(product);
	}
	
	public Long getListCount(Product product) {
		return productMapper.getListCount(product);
	}
	
	public List<Product> getSearchList(Product product) {
		return productMapper.getSearchList(product);
	}
	
	public Long getSearchListCount(Product product) {
		return productMapper.getSearchListCount(product);
	}
	
}