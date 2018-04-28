package com.sangkhim.spring.domain;

import java.util.Date;
import java.util.List;

import com.sangkhim.spring.base.DataTableDTO;

public class Product extends DataTableDTO {
	int productId;
	String name;
	String price;
	String description;
	String metaTagTitle;
	String metaTagDescription;
	String metaTagKeywords;
	String location;
	private Date created;
	private int createdBy;
	private Date modified;
	private int modifiedBy;
	private int isOnline;
	String isActive;
	
	private List<ProductImage> productImageList;
	private List<Integer> deleteProductImageList;
	
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getMetaTagTitle() {
		return metaTagTitle;
	}
	public void setMetaTagTitle(String metaTagTitle) {
		this.metaTagTitle = metaTagTitle;
	}
	public String getMetaTagDescription() {
		return metaTagDescription;
	}
	public void setMetaTagDescription(String metaTagDescription) {
		this.metaTagDescription = metaTagDescription;
	}
	public String getMetaTagKeywords() {
		return metaTagKeywords;
	}
	public void setMetaTagKeywords(String metaTagKeywords) {
		this.metaTagKeywords = metaTagKeywords;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public int getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}
	public Date getModified() {
		return modified;
	}
	public void setModified(Date modified) {
		this.modified = modified;
	}
	public int getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(int modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	public int getIsOnline() {
		return isOnline;
	}
	public void setIsOnline(int isOnline) {
		this.isOnline = isOnline;
	}
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	
	public List<ProductImage> getProductImageList() {
		return productImageList;
	}
	public void setProductImageList(List<ProductImage> productImageList) {
		this.productImageList = productImageList;
	}
	public List<Integer> getDeleteProductImageList() {
		return deleteProductImageList;
	}
	public void setDeleteProductImageList(List<Integer> deleteProductImageList) {
		this.deleteProductImageList = deleteProductImageList;
	}
	
	@Override
	public String toString() {
		return "Product [productId=" + productId + ", name=" + name + ", price=" + price + ", description="
				+ description + ", metaTagTitle=" + metaTagTitle + ", metaTagDescription=" + metaTagDescription
				+ ", metaTagKeywords=" + metaTagKeywords + ", location=" + location + ", created=" + created
				+ ", createdBy=" + createdBy + ", modified=" + modified + ", modifiedBy=" + modifiedBy + ", isOnline="
				+ isOnline + ", isActive=" + isActive + ", productImageList=" + productImageList
				+ ", deleteProductImageList=" + deleteProductImageList + "]";
	}
		
}
