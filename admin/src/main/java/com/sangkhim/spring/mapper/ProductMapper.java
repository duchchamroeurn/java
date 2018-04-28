package com.sangkhim.spring.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.sangkhim.spring.domain.Product;
import com.sangkhim.spring.domain.ProductImage;

@Repository
public interface ProductMapper {
	
	public List<Product> getAll();
	
	public Product getById(int productId);
	
	public int insert(Product product);
	
	public int update(Product product);
	
	public int updateIsOnline(@Param("productId") int productId, @Param("isOnline") int isOnline);
	
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