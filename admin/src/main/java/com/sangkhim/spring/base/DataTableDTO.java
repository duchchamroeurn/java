package com.sangkhim.spring.base;

import java.util.ArrayList;

public class DataTableDTO {
	
	private			Integer				start;
	private			Integer				length;
	private 		ArrayList<String> 	searchBy;
	private 		ArrayList<String>	searchKeyword;
	private			ArrayList<String>	sortColumn;
	private			ArrayList<String>	sortDir;
		
	public Integer getStart() {
		return start;
	}
	public void setStart(Integer start) {
		this.start = start;
	}
	public Integer getLength() {
		return length;
	}
	public void setLength(Integer length) {
		this.length = length;
	}
	public ArrayList<String> getSearchBy() {
		return searchBy;
	}
	public void setSearchBy(ArrayList<String> searchBy) {
		this.searchBy = searchBy;
	}
	public ArrayList<String> getSearchKeyword() {
		return searchKeyword;
	}
	public void setSearchKeyword(ArrayList<String> searchKeyword) {
		this.searchKeyword = searchKeyword;
	}
	public ArrayList<String> getSortColumn() {
		return sortColumn;
	}
	public void setSortColumn(ArrayList<String> sortColumn) {
		this.sortColumn = sortColumn;
	}
	public ArrayList<String> getSortDir() {
		return sortDir;
	}
	public void setSortDir(ArrayList<String> sortDir) {
		this.sortDir = sortDir;
	}

}