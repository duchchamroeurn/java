package com.sangkhim.spring.base;

import java.util.ArrayList;
import java.util.Map;


/**
 * @author Kimleng
 * DataTable API Version 1.10.x
 * 
 * draw:1
 * columns[0][data]:0
 * columns[0][name]:
 * columns[0][searchable]:true
 * columns[0][orderable]:true
 * columns[0][search][value]:
 * columns[0][search][regex]:false
 * ...
 * order[0][column]:0
 * order[0][dir]:asc
 * ...
 * start:0
 * length:10
 * search[value]:
 * search[regex]:false
 */
public class DataTable {
	
	private 	int 				draw;
	
	// request data
	private 	int 				start;
	private 	int 				length;
	private 	String				searchValue;
	private		String 				searchRegex;
	
	private		ArrayList<String>	dataColumns;
	private		ArrayList<String> 	nameColumns;	
	
	private		ArrayList<Boolean> 	searchableColumns;
	private		ArrayList<Boolean>	orderableColumns;
	
	private		ArrayList<String> 	searchColumnsValue;
	
	private		ArrayList<Integer>	orderColumnsIndex;
	private		ArrayList<String>	orderColumnsName;
	private		ArrayList<String>	orderDirs;
	
	private		ArrayList<String>	searchRegexColumns;
	
	// response data
	private		long				recordsTotal;
	private		long				recordsFiltered;
	private		Object				data;
	private		String				error;
	
	public DataTable(Map<String, String> map){
		this.draw 					= Integer.parseInt(map.get("draw"));
		this.start 					= Integer.parseInt(map.get("start"));
		this.length 				= Integer.parseInt(map.get("length"));
		this.searchValue 			= map.get("search[value]");
		this.searchRegex 			= map.get("search[regex]");
		
		this.dataColumns 			= this.prepareDataColumns(map);
		this.nameColumns 			= this.prepareNameColumns(map);
		
		this.searchableColumns		= this.prepareSearchableColumns(map);		
		this.orderableColumns		= this.prepareOrderableColumns(map);
		
		this.searchColumnsValue		= this.prepareSearchColumnsValue(map);
		
		this.orderColumnsIndex 		= this.prepareOrderColumnsIndex(map);
		this.orderColumnsName		= this.prepareOrderColumnsName(map);
		this.orderDirs 				= this.prepareOrderDirs(map);
		
		this.searchRegexColumns		= this.prepareSearchRegexColumns(map);
	}
	
	private ArrayList<String> prepareDataColumns(Map<String, String> map) {
		ArrayList<String> dataColumns = new ArrayList<String>();
		int i = 0;
		while(map.get("columns[" + i + "][data]") != null){
			dataColumns.add(map.get("columns[" + i + "][data]"));
			i++;
		}
		return dataColumns;
	}	
	private ArrayList<String> prepareNameColumns(Map<String, String> map) {
		ArrayList<String> nameColumns = new ArrayList<String>();
		int i = 0;
		while(map.get("columns[" + i + "][name]") != null){
			nameColumns.add(map.get("columns[" + i + "][name]"));
			i++;
		}
		return nameColumns;
	}
	
	private ArrayList<Boolean> prepareSearchableColumns(Map<String, String> map) {
		ArrayList<Boolean> searchableColumns = new ArrayList<Boolean>();
		int i = 0;
		while(map.get("columns[" + i + "][searchable]") != null){
			searchableColumns.add(Boolean.parseBoolean(map.get("columns[" + i + "][searchable]")));
			i++;
		}
		return searchableColumns;
	}
	private ArrayList<Boolean> prepareOrderableColumns(Map<String, String> map) {
		ArrayList<Boolean> orderableColumns = new ArrayList<Boolean>();
		int i = 0;
		while(map.get("columns[" + i + "][orderable]") != null){
			orderableColumns.add(Boolean.parseBoolean(map.get("columns[" + i + "][orderable]")));
			i++;
		}
		return orderableColumns;
	}
	
	private ArrayList<String> prepareSearchColumnsValue(Map<String, String> map) {
		ArrayList<String> searchColumnsValue = new ArrayList<String>();
		int i = 0;
		while(map.get("columns[" + i + "][search][value]") != null){
			searchColumnsValue.add(map.get("columns[" + i + "][search][value]"));
			i++;
		}
		return searchColumnsValue;
	}
	
	private ArrayList<Integer> prepareOrderColumnsIndex(Map<String, String> map){
		ArrayList<Integer> orderColumnsIndex = new ArrayList<Integer>();
		int i = 0;
		while(map.get("order[" + i + "][column]") != null){
			orderColumnsIndex.add(Integer.parseInt(map.get("order[" + i + "][column]")));
			i++;
		}
		return orderColumnsIndex;
	}
	private ArrayList<String> prepareOrderColumnsName(Map<String, String> map) {
		ArrayList<String> orderColumnsName = new ArrayList<String>();
		for(int index : this.getOrderColumnsIndex()){
			String columnName = this.getNameColumns().get(index);
			orderColumnsName.add(columnName);
		}
		return orderColumnsName;
	}	
	private ArrayList<String> prepareOrderDirs(Map<String, String> map) {
		ArrayList<String> orderDirs = new ArrayList<String>();
		int i = 0;
		while(map.get("order[" + i + "][dir]") != null){
			orderDirs.add(map.get("order[" + i + "][dir]"));
			i++;
		}
		return orderDirs;
	}
	
	private ArrayList<String> prepareSearchRegexColumns(Map<String, String> map) {
		ArrayList<String> searchRegexColumns = new ArrayList<String>();
		int i = 0;
		while(map.get("columns[" + i + "][search][regex]") != null){
			searchRegexColumns.add(map.get("columns[" + i + "][search][regex]"));
			i++;
		}
		return searchRegexColumns;
	}
	
	public void setResponse(long recordsTotal, long recordsFiltered, Object data, String error){
		this.recordsTotal = recordsTotal;
		this.recordsFiltered = recordsFiltered;
		this.data = data;
		this.error = error;
	}	

	public int getDraw() {
		return draw;
	}
	public void setDraw(int draw) {
		this.draw = draw;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public String getSearchValue() {
		return searchValue;
	}
	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}
	public String getSearchRegex() {
		return searchRegex;
	}
	public void setSearchRegex(String searchRegex) {
		this.searchRegex = searchRegex;
	}
	public ArrayList<String> getDataColumns() {
		return dataColumns;
	}
	public void setDataColumns(ArrayList<String> dataColumns) {
		this.dataColumns = dataColumns;
	}
	public ArrayList<String> getNameColumns() {
		return nameColumns;
	}
	public void setNameColumns(ArrayList<String> nameColumns) {
		this.nameColumns = nameColumns;
	}
	public ArrayList<Boolean> getSearchableColumns() {
		return searchableColumns;
	}
	public void setSearchableColumns(ArrayList<Boolean> searchableColumns) {
		this.searchableColumns = searchableColumns;
	}
	public ArrayList<Boolean> getOrderableColumns() {
		return orderableColumns;
	}
	public void setOrderableColumns(ArrayList<Boolean> orderableColumns) {
		this.orderableColumns = orderableColumns;
	}
	public ArrayList<String> getSearchColumnsValue() {
		return searchColumnsValue;
	}
	public void setSearchColumnsValue(ArrayList<String> searchColumnsValue) {
		this.searchColumnsValue = searchColumnsValue;
	}
	public ArrayList<Integer> getOrderColumnsIndex() {
		return orderColumnsIndex;
	}
	public void setOrderColumnsIndex(ArrayList<Integer> orderColumnsIndex) {
		this.orderColumnsIndex = orderColumnsIndex;
	}
	public ArrayList<String> getOrderColumnsName() {
		return orderColumnsName;
	}
	public void setOrderColumnsName(ArrayList<String> orderColumnsName) {
		this.orderColumnsName = orderColumnsName;
	}
	public ArrayList<String> getOrderDirs() {
		return orderDirs;
	}
	public void setOrderDirs(ArrayList<String> orderDirs) {
		this.orderDirs = orderDirs;
	}
	public ArrayList<String> getSearchRegexColumns() {
		return searchRegexColumns;
	}
	public void setSearchRegexColumns(ArrayList<String> searchRegexColumns) {
		this.searchRegexColumns = searchRegexColumns;
	}
	public long getRecordsTotal() {
		return recordsTotal;
	}
	public void setRecordsTotal(long recordsTotal) {
		this.recordsTotal = recordsTotal;
	}
	public long getRecordsFiltered() {
		return recordsFiltered;
	}
	public void setRecordsFiltered(long recordsFiltered) {
		this.recordsFiltered = recordsFiltered;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}

	@Override
	public String toString() {
		return "DataTable [draw=" + draw + ", start=" + start + ", length=" + length + ", searchValue=" + searchValue
				+ ", searchRegex=" + searchRegex + ", dataColumns=" + dataColumns + ", nameColumns=" + nameColumns
				+ ", searchableColumns=" + searchableColumns + ", orderableColumns=" + orderableColumns
				+ ", searchColumnsValue=" + searchColumnsValue + ", orderColumnsIndex=" + orderColumnsIndex
				+ ", orderColumnsName=" + orderColumnsName + ", orderDirs=" + orderDirs + ", searchRegexColumns="
				+ searchRegexColumns + ", recordsTotal=" + recordsTotal + ", recordsFiltered=" + recordsFiltered
				+ ", data=" + data + ", error=" + error + "]";
	}
	
}