package com.sangkhim.spring.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sangkhim.spring.base.DataTable;
import com.sangkhim.spring.base.FileUploadUtils;
import com.sangkhim.spring.base.message.ResponseMessage;
import com.sangkhim.spring.base.message.ResponseMessageUtils;
import com.sangkhim.spring.domain.Product;
import com.sangkhim.spring.domain.ProductImage;
import com.sangkhim.spring.service.ProductService;

@RestController
@RequestMapping("/api/admin")
public class ProductRestController {
	
	@Autowired 
	ProductService productService;
	
	@RequestMapping( value = "/products/dt", method = RequestMethod.GET )
	public DataTable dt(@RequestParam Map<String, String> param){		
		DataTable dataTable = new DataTable(param);
		
		Product product = new Product();
		product.setStart(dataTable.getStart());
		product.setLength(dataTable.getLength());
		
		ArrayList<String> searchBy = new ArrayList<String>();
		ArrayList<String> searchColumnsValue = new ArrayList<String>();
		int index = 0;
		for(String item : dataTable.getSearchColumnsValue()){
			if (!item.equalsIgnoreCase("")) {
				searchBy.add(dataTable.getNameColumns().get(index));
				searchColumnsValue.add(item);
			}
			index++;
		}
		product.setSearchBy(searchBy);
		product.setSearchKeyword(searchColumnsValue);
		
		product.setSortColumn(dataTable.getOrderColumnsName());
		product.setSortDir(dataTable.getOrderDirs());
		
		List<Product> list;
		Long listCount = productService.getListCount(product);
		Long searchListCount = 0L;
		
		if(product.getSearchBy().size() > 0){
			list = productService.getSearchList(product);
			searchListCount = productService.getSearchListCount(product);
			dataTable.setResponse(listCount, searchListCount, list, "");
		}else{
			list = productService.getList(product);
			dataTable.setResponse(listCount, listCount, list, "");
		}		
		
		return dataTable;
	}
	
	@RequestMapping(value = "/products", method = RequestMethod.GET)
	public ResponseMessage<List<Product>> products() {
		List<Product> productList = productService.getAll();
		return ResponseMessageUtils.makeSuccessResponse(productList);
	}

	@RequestMapping(value = "/products/{productId}", method = RequestMethod.GET)
	public ResponseMessage<Product> product(@PathVariable("productId") int productId) {
		Product product = productService.getById(productId);
		return ResponseMessageUtils.makeSuccessResponse(product);
	}

	@RequestMapping(value = "/public/products", method = RequestMethod.POST)
	public ResponseMessage<String> public_insert(@ModelAttribute Product product) {
		return this.insert(product);
	}
	
	@RequestMapping(value = "/products", method = RequestMethod.POST)
	public ResponseMessage<String> insert(@ModelAttribute Product product) {
		product.setCreatedBy(1);
		productService.insert(product);
		
		if(product.getProductImageList() != null) {
			for(int i=0; i < product.getProductImageList().size(); i++) {
				ProductImage productImage = product.getProductImageList().get(i);
				MultipartFile file = productImage.getFile();
				if(file != null && file.getSize() > 0) {
					String image = FileUploadUtils.saveFileUploaded(file);
					productImage.setSrc(image);
					
					productImage.setProductId(product.getProductId());
					productImage.setSortOrder(productImage.getSortOrder());
					productService.insertProductImage(productImage);
				}else if(productImage.getFileBase64() != null) {
					byte[] byteArray = Base64Utils.decodeFromString(productImage.getFileBase64()); 
					String image = FileUploadUtils.saveFileUploadedFromByteArray(byteArray);
					productImage.setSrc(image);
					
					productImage.setProductId(product.getProductId());
					productImage.setSortOrder(productImage.getSortOrder());
					productService.insertProductImage(productImage);
				}
			}
		}
		
		return ResponseMessageUtils.makeResponse(true, "Success");
	}
	
	@RequestMapping(value = "/public/update-products", method = RequestMethod.POST)
	public ResponseMessage<String> public_update(@ModelAttribute Product product) {
		return this.update(product);
	}

	@RequestMapping(value = "/update-products", method = RequestMethod.POST)
	public ResponseMessage<String> update(@ModelAttribute Product product) {
		product.setModifiedBy(1);
		productService.update(product);
		
		if(product.getProductImageList() != null) {
			for(int i=0; i < product.getProductImageList().size(); i++) {
				ProductImage productImage = product.getProductImageList().get(i);
				MultipartFile file = productImage.getFile();
				if(file != null && file.getSize() > 0) {
					String image = FileUploadUtils.saveFileUploaded(file);
					productImage.setSrc(image);
					
					productImage.setProductId(product.getProductId());
					productImage.setSortOrder(productImage.getSortOrder());
					productService.insertProductImage(productImage);
				}else if(productImage.getFileBase64() != null) {
					byte[] byteArray = Base64Utils.decodeFromString(productImage.getFileBase64());
					String image = FileUploadUtils.saveFileUploadedFromByteArray(byteArray);
					productImage.setSrc(image);
					
					productImage.setProductId(product.getProductId());
					productImage.setSortOrder(productImage.getSortOrder());
					productService.insertProductImage(productImage);
				}
			}
		}
		if(product.getDeleteProductImageList() != null) {
			for (int i = 0; i < product.getDeleteProductImageList().size(); i++) {
				ProductImage productImage = productService.getProductImageById(product.getDeleteProductImageList().get(i));
				FileUploadUtils.deleteFile(productImage.getSrc());
				productService.deleteProductImage(productImage.getProductImageId());
			}
		}
		
		return ResponseMessageUtils.makeResponse(true, "Success");
	}

	@RequestMapping(value = "/public/delete-products/{productId}", method = RequestMethod.POST)
	public ResponseMessage<String> public_delete(@PathVariable("productId") int productId) {
		return this.delete(productId);
	}
	
	@RequestMapping(value = "/delete-products/{productId}", method = RequestMethod.POST)
	public ResponseMessage<String> delete(@PathVariable("productId") int productId) {
		if(productService.deleteById(productId) == 1){
			return ResponseMessageUtils.makeResponse(true, "Success");
		}else{
			return ResponseMessageUtils.makeResponse(false, "Fail");
		}
	}
	
	@RequestMapping(value = "/products/{productId}/onlines/{isOnline}", method = RequestMethod.POST)
	public ResponseMessage<String> updateIsOnline(@PathVariable("productId") int productId, @PathVariable("isOnline") int isOnline) {
		if(productService.updateIsOnline(productId, isOnline) == 1){
			return ResponseMessageUtils.makeResponse(true, "Success");
		}else{
			return ResponseMessageUtils.makeResponse(false, "Fail");
		}		
	}

}