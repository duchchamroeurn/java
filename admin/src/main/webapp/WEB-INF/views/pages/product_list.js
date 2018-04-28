$(function ($) {
	var ProductListController = {
		dbService: DbService,
		dataTable : null,
		init : function(){
			ProductListController.initTable();
			ProductListController.bindEvent();
		},	
		initTable: function(){		
			ProductListController.dataTable = $('#tbl-product').dt({
				"showNo" : true,
				"multiSelect" : false,
				"inputPage" : true,
		        "url": ctx + "/api/admin/products/dt/",
		        "order" : [[1, 'asc']],
		        "pageLength" : 10,
		        "search" : {
			        	"options" : [ 
			        		{
			        			"id" : "product_name", 
			        			"name" : "name", 
			        			"inputType" : "text", 
			        			"inTable" : true
			        		},{
			        			"id" : "price", 
			        			"name" : "price", 
			        			"inputType" : "text", 
			        			"inTable" : true
			        		},{
			        			"id" : "qty", 
			        			"name" : "qty", 
			        			"inputType" : "text", 
			        			"inTable" : false
			        		},{
			        			"id" : "is_online", 
			        			"name" : "is_online", 
			        			"inputType" : "select", 
			        			"inTable" : false
			        		}
		              	],
		              	"inputPlaceholder" : "Search"
		        },
		        "columns": [
					{  
		            	"data": null, 
		            	"name":"name",
		            	"orderable" : true,
		            	"render" : function( data, type, row ){
		            		var str = row.name != null ? row.name : "";
		            		var numShowChars = 20; 
		            		if(str.length > numShowChars) {
		            			return str.substring(0, numShowChars) + "...";
		            		}else{
		            			return str;
		            		}
		            	},
		            	"className" : "text-left"
		            },{  
		            	"data": null, 
		            	"name":"price",
		            	"orderable" : true,
		            	"render" : function( data, type, row ){
		            		return row.price;
		            	},
		            	"className" : "text-left"
		            },{  
		            	"data": null, 
		            	"name":"description",
		            	"orderable" : true,
		            	"render" : function( data, type, row ){
		            		var str = row.description != null ? row.description.trim() : "";
		            		var numShowChars = 20; 
		            		if(str.length > numShowChars) {
		            			return str.substring(0, numShowChars) + "...";
		            		}else{
		            			return str;
		            		}
		            	},
		            	"className" : "text-left"
		            },{  
		            	"data": null, 
		            	"name":"is_online",
		            	"orderable" : true,
		            	"render" : function( data, type, row ){
		            		var template = $("#templateToggleButton").html();
		            		row.productId = row.productId;
		            		row.isOnlineDif = row.isOnline == 1 ? 0 : 1;
		            		row.isOnlineChecked = row.isOnline == 1 ? 'checked="checked"' : '';
		            		return template.supplant(row);
		            	},
		            	"className" : "text-left"
		            },{  
		            	"data": null, 
		            	"name":"is_active",
		            	"orderable" : false,
		            	"render" : function( data, type, row ){
		            		var template = $("#templateActionButton").html();
		            		row.productId = row.productId;
		            		return template.supplant(row);
		            	},
		            	"className" : "text-right"
		            }
		        ],
		        "drawCallback": function( settings ) {
		        	
		        }
			});
		},		
		bindEvent : function(){
			$('#btn-grid').click(function(event){
				event.preventDefault();
				
				window.location.href = ctx + 'admin/product_grid.html';
			});
			
			$('#btn-add-new-product').click(function(event){
				window.location.href = ctx + 'admin/product_add.html';
			});
			
			$(document).on('change', '.btn-update-is-online', function (event) {
				var obj = $(this);
				var productId = obj.attr("product-id");
				var isOnline = obj.attr("is-online");
				
				var url = ctx + "/api/admin/products/" + productId + "/onlines/" + isOnline;
				url += "?" + $("#_csrf").attr("name") + "=" + $("#_csrf").val();
				ProductListController.dbService.post(url, null, function(data){				    
			    	if(data.header.result){	
			    		obj.attr("is-online", obj.attr("is-online") == 1 ? 0 : 1);
			    	}
				});
			});
			
			$(document).on('click', '.btn-view', function (event) {
				event.preventDefault();
				
				var obj = $(this);
				var productId = obj.attr("product-id");
				
				window.location.href = ctx + 'admin/product_detail/' + productId + '.html';
			});
			
			$(document).on('click', '.btn-edit', function (event) {
				event.preventDefault();
				
				var obj = $(this);
				var productId = obj.attr("product-id");
				
				window.location.href = ctx + 'admin/product_edit/' + productId + '.html';
			});
			
			$(document).on('click', '.btn-delete', function (event) {
				event.preventDefault();
				
				var obj = $(this);
				var productId = obj.attr("product-id");
				
	            swal({
	                title: "Are you sure?",
	                text: "You will not be able to recover this record!",
	                type: "warning",
	                showCancelButton: true,
	                confirmButtonColor: "#DD6B55",
	                confirmButtonText: "Yes, delete it!",
	                closeOnConfirm: false
	            }, function () {
	            	var url = ctx + "/api/admin/delete-products/" + productId;
					url += "?" + $("#_csrf").attr("name") + "=" + $("#_csrf").val();
					ProductListController.dbService.post(url, null, function(data){
						if(data.header.result){
							ProductListController.dataTable.ajax.reload(null, false);
							
							swal({
				                title: "Deleted!",
				                text: "Your record has been deleted.",
				                type: "success",
				                timer: 3000
				    		}, function () {
				    			swal.close();
				            });
						}
					});
	            });
			});
		}		
	}
	ProductListController.init();
});