package com.sangkhim.spring.domain;

import org.springframework.web.multipart.MultipartFile;

public class ProductImage {
	private int productImageId;
	private int productId;
	private String src;	
	private String sortOrder;
	
	private MultipartFile file;
	private String fileBase64;
	
	public int getProductImageId() {
		return productImageId;
	}
	public void setProductImageId(int productImageId) {
		this.productImageId = productImageId;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getSrc() {
		return src;
	}
	public void setSrc(String src) {
		this.src = src;
	}
	public String getSortOrder() {
		return sortOrder;
	}
	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}
	
	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
	}
	public String getFileBase64() {
		return fileBase64;
	}
	public void setFileBase64(String fileBase64) {
		this.fileBase64 = fileBase64;
	}
	
	@Override
	public String toString() {
		return "ProductImage [productImageId=" + productImageId + ", productId=" + productId + ", src=" + src
				+ ", sortOrder=" + sortOrder + ", file=" + file + ", fileBase64=" + fileBase64 + "]";
	}
	
}
