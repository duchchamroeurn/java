/**
 * @author Kimleng
 * v0.3
 */
/*************** DOM Position of DataTables ***********************
l - Length changing
f - Filtering input
t - The Table!
i - Information
p - Pagination
r - pRocessing
< and > - div elements
<"#id" and > - div with an id
<"class" and > - div with a class
<"#id.class" and > - div with an id and class
*****************************************************/

$.fn.dt = function(options){
	var that = this;
	/** if you change it, it will be affect css style *****
	 ******************************************************/
	var pagination = "dataTables_pagination";
	var sub_pagination = "dataTables_sub_pagination";
	/********************************************************
	 * ******************************************************/
	
	var searchInput = null;
	
	options = $.extend(true, {
		url : "",
		reqParam : {},
		selectRow : true,
		showResult : true
	}, options || {});
	
	var dt = {
		_tb : null,
		_oTb : null,
		_selectedCheckboxData : [],
		_dtInit : function(options){
			// config datatable before init datatable
			var opt = dt._beforeInitDataTable(options);
			// init datatable after configuration
			dt._oTb = dt._initDataTable(opt);
			// provide datatable v1.10 api
			dt._tb = dt._oTb.api();
			// init datatable interface
			dt._initInterface(options);
			
			return dt._tb;
		},
		
		_beforeInitDataTable : function(options){	
			var dom = "";
			var hideResult = '<"col-lg-6"l><"pull-right"r>t<"'+ pagination +'"<"'+ sub_pagination +'"p>>';
			var showResult = '<"col-lg-6"l><"pull-right"r>t<"'+ pagination +'"<"'+ sub_pagination +'"p>><"result"i>';
			if( options.showResult == true ) dom = showResult;
			else dom = hideResult;
			
			var opt = $.extend(true, {
				"dom": dom,
				"language": {
				    "decimal":        "",
				    "emptyTable":     "No data available in table",
				    "info":           "Showing _START_ to _END_ of _TOTAL_ entries",
				    "infoEmpty":      "Showing 0 to 0 of 0 entries",
				    "infoFiltered":   "(filtered from _MAX_ total entries)",
				    "infoPostFix":    "",
				    "thousands":      ",",
				    "lengthMenu":     "Show _MENU_ entries",
				    "loadingRecords": "Loading...",
				    "processing":     "Processing...",
				    "search":         "Search:",
				    "zeroRecords":    "No matching records found",
				    "paginate": {
				        "first":      "First",
				        "last":       "Last",
				        "next":       "Next",
				        "previous":   "Previous"
				    },
				    "aria": {
				        "sortAscending":  ": activate to sort column ascending",
				        "sortDescending": ": activate to sort column descending"
				    }
				},
				"processing": true,
				"serverSide": true,
				"stateSave" : true,
		        "searching" : true,
		        "lengthChange" : true,
		        "lengthMenu" : [ [10, 25, 50, 1000000000], [10, 25, 50, "All"] ],
		        "pageLength" : 10,
		        "order": [],
		        "ajax" : {
		        	"url" : options.url,
		        	"type" : "GET",
		        	"data" : function( p ){
		        		$.extend( p, options.reqParam );
		        		var dt_params = $( that ).data( 'dt_params' );
		        		if( dt_params ) $.extend( p, dt_params );
		        	}
		        },
		        "columns" : [],
		        "columnDefs" : [],
		        "initComplete" : function(settings, json){
		        	dt._afterInitDataTable(json);
		        	
		        	dt._bindDataTableEvent();
		        	
		        	if('onComplete' in options){
		        		options.onComplete(settings, json);
		        	}
		        }
			}, options || {});
			
			// config show row number in datatable
			if( 'showNo' in options && options.showNo == true){
				// unshift/push - add an element to the beginning/end of an array
				// shift/pop - remove and return the first/last element of and array
				opt.columns.unshift({"data" : null, "name" : "no", "searchable" : false, "orderable" : false});
				$(that).find("thead tr:first-child").prepend("<th>" + "No" + "</th>");
			}
			
			if( 'multiSelect' in options && options.multiSelect == true){
				$(that).find("colgroup").prepend('<col width="10%">');
				$(that).find("thead tr:first-child").prepend("<th><input type='checkbox' class='dataTable_parent_checkbox'><label></label></th>");
				opt.columns.unshift({"data" : null, "name" : "checkbox", "searchable" : false, "orderable" : false, "render" : function(data, type, row){
					return "<input type='checkbox' class='dataTable_child_checkbox'><label></label>";
				}});
			}
			
			// config datatable column for searchable
			if( 'search' in options && options.search != null){
				if( 'options' in options.search && options.search.options != null){
					var index = [];
					$.each(options.search.options, function(k,v){
						if( 'inTable' in v && v.inTable == false){
							var column = {};
							column.data = null;
							column.name = v.name;
							opt.columns.push(column);
							index.push(opt.columns.length - 1);
						}
					});
					if(index.length > 0){
						var columnDef = {};
						columnDef.searchable = true;
						columnDef.orderable = false;
						columnDef.visible = false;
						columnDef.targets = index;
						opt.columnDefs.push(columnDef);
					}
				}
			}
			
			return opt;
		},
		
		_initDataTable : function(options){
			return $(that).dataTable(options);
		},
		
		_afterInitDataTable : function(json){
			if( json.data.length <= 0 ){
				
			}else{
				
			}
			
			if( 'search' in options && options.search != null){
				if( 'options' in options.search && options.search.options != null){
					if(dt._tb.state() != null){
						var columns = dt._tb.state().columns;
						$.each(columns, function( k, v ){
							var search = v.search.search;
							if( search != "" ){
								
							}
						});
					}
				}else{
					var search = dt._tb.state().search.search;
					searchInput.val(search);
				}
			}
		},
		
		_initInterface : function(options){
			// display search (select , input and button at the top of table)
			if( 'search' in options && options.search != null ) {
				if( 'options' in options.search && options.search.options != null){
					this._showSearchingWithOption(options.search);
				}else{
					this._showSearching(options.search);
				}
			}
			
			// display row number
			if( 'showNo' in options && options.showNo == true) this._showNo();
			
			// table footer
			$("#dt_info").append($(".dataTables_info"));
			if( 'inputPage' in options && options.inputPage == true ) this._showInputPage();
			$("#dt_paginate").append($(".dataTables_paginate").addClass("pull-right").find(".pagination").css({ 'margin' : '0px' }));
		},
		
		_checkboxTriggerEvent : function(){			
			$(that).find("thead tr th input[class='dataTable_parent_checkbox']").off().on("click", function(e){
				$(that).find('td .dataTable_child_checkbox').prop('checked', this.checked);
				$(that).find("tbody tr").toggleClass('selected', this.checked);
				dt._setSelectedData();
			});
			
			$(that).find("tbody tr td input[class='dataTable_child_checkbox']").off().on("click", function(e){
				e.stopPropagation();
				$(this).closest("tr").toggleClass('selected', this.checked);
				if(!this.checked) $(that).find("thead tr th input[class='dataTable_parent_checkbox']").prop('checked', false);
				dt._setSelectedData();
			});
		},
		
		_bindDataTableEvent : function(){
			if('selectRow' in options && options.selectRow == true){
				$(that).find("tbody tr").off().on( "click", function(e){
					$(that).find("tbody tr").toggleClass('selected', false);
					$(that).find('td .dataTable_child_checkbox').prop('checked', false);
					$(this).find('td .dataTable_child_checkbox').prop('checked', true);
					$(this).toggleClass('selected', true);
					dt._setSelectedData();
				});
			}
			
			if( 'multiSelect' in options && options.multiSelect == true){
				dt._checkboxTriggerEvent();
				$(that).find("thead tr th input[class='dataTable_parent_checkbox']").prop('checked', false);
			}
			
			dt._tb.one( 'draw', function (e, settings) {
				dt._afterInitDataTable(settings.json);
				dt._bindDataTableEvent();
			});
		},
		
		_setSelectedData : function(){
			dt._selectedCheckboxData = [];
			$.each(dt._tb.rows( '.selected' ).data(), function(k,v){
				dt._selectedCheckboxData.push(v);
			});
		},
		
		_resetColumnSearch : function(){
			dt._tb.columns().every( function () {
				this.search( "" );
			} );
		},
		
		_showSearching : function(search){
			$("#tbl-header div:first").addClass("col-md-9");
			$(".dataTables_wrapper").removeClass("form-inline");
			
			var tblHeaderSearchInput = $('<div/>', { 'class' : 'col-md-3'});			
			var searchInput = $('<input/>', {'type' : 'text', 'placeholder' : search.inputPlaceholder, 'class' : 'form-control'});
			tblHeaderSearchInput.append(searchInput);
			
			$("#tbl-header").append(tblHeaderSearchInput);
			
			dt._searchButtonTriggerEvent(searchInput);
		},
		
		_searchButtonTriggerEvent : function(input){
			input.on('keypress', function(e){
				var code = (e.keyCode ? e.keyCode : e.which);
				if(code == 13) { //Enter keycode
					var value = $(input).val();
					dt.dtSearch(value);
				}
			});
		},
		
		_showSearchingWithOption : function(search){
			$.each(search.options, function(k, v){
				dt._searchWithOptionButtonTriggerEvent(v.name, $("#" + v.id), v.inputType);
			});
		},
		
		_searchWithOptionButtonTriggerEvent : function(fieldName, input, inputType){
			if(inputType == "text") {
				input.on('keypress', function(e){
					var code = (e.keyCode ? e.keyCode : e.which);
					if(code == 13) { //Enter keycode
						var column = fieldName;
						var value = $(input).val();
						dt.dtColumnSearch(column, value);
					}
				});
			}else{
				input.on('change', function(e){
					var column = fieldName;
					var value = $(input).val();
					dt.dtColumnSearch(column, value);
				});
			}
		},
		
		_showInputPage : function(){
			var div = $('<div/>', { 'class' : 'dataTables_btn_move input-group input-group-sm' });
			var input = $('<input/>', { 'type' : 'text', 'class' : 'form-control' });
			var span = $('<span/>', { 'class' : 'input-group-btn' });
			var button = $('<button/>', { 'type' : 'button', 'class' : 'btn btn-secondary', 'text' : 'Move' });
			span.append(button);
			div.append(input);
			div.append(span);
			$("#dt_move").append(div);
			
			dt._inputPageButtonTriggerEvent(input, button);
		},
		
		_inputPageButtonTriggerEvent : function(input, button){
			button.on('click', function(e){
				var page = input.val();
				if (page !== "" && page !=='0' && page !=='00'){
					dt.dtInputPage( page );
				}
			});
			
			input.on('keypress', function(e){
				var code = (e.keyCode ? e.keyCode : e.which);
				 if(code == 13) { //Enter keycode
					 button.click();
				 }
				 if (code != 8 && code != 0 && (code < 48 || code > 57)) {
					return false;
				}
			});
		},
		
		_showNo : function(){
			dt._tb.on( 'draw.dt order.dt search.dt', function () {
				var index = 0;
				if('multiSelect' in options && options.multiSelect == true) index = 1;
		        dt._tb.column(index, {search:'applied', order:'applied'}).nodes().each( function (cell, i) {
		        	var page = dt._tb.page.info().page;
		        	var pageLength = dt._tb.page.len();
		            cell.innerHTML = (i+1) + (page*pageLength);
		        } );
		    } );
		},
		
		dtSearch : function(search){
			dt._tb.search(search).draw();
		},
		
		dtColumnSearch : function(column, search){
			dt._tb.column(column + ":name").search(search).draw();
		},
		
		dtInputPage : function(page, force){			
			if( page > dt._tb.page.info().pages ) return;
			
			var start = (page * dt._tb.page.len()) - dt._tb.page.len();
			
			dt._oTb.setDisplayStart(start);
		},
		
		dtGetSelectedData : function(){
			return dt._selectedCheckboxData;
		}
	};
	
	var table = dt._dtInit(options);
	$.extend(true, dt, table);
	return dt;
};

/**
 * If you are willing to accept the error 
 * (for example if you cannot alter the backend system to fix the error), 
 * but don't want your end users to see the alert() message, 
 * you can change DataTables' error reporting mechanism to throw a Javascript error to the browser's console, 
 * rather than alerting it. This can be done using:
 */
$.fn.dataTable.ext.errMode = 'throw';

/**
 * Set the point at which DataTables will start it's display of data in the
 * table.
 *
 *  @name setDisplayStart
 *  @summary Change the table's paging display start.
 *  @author [Allan Jardine](http://sprymedia.co.uk)
 *  @deprecated
 *
 *  @param {integer} iStart Display start index.
 *  @param {boolean} [bRedraw=false] Indicate if the table should do a redraw or not.
 *
 *  @example
 *    var table = $('#example').dataTable();
 *    table.setDisplayStart( 21 );
 *    
 *  it works only with DataTables v1.9.x
 */
$.fn.dataTableExt.oApi.setDisplayStart = function ( oSettings, iStart, bRedraw ){
    if ( typeof bRedraw == 'undefined' ) {
        bRedraw = true;
    }

    oSettings._iDisplayStart = iStart;
    if ( oSettings.oApi._fnCalculateEnd ) {
        oSettings.oApi._fnCalculateEnd( oSettings );
    }

    if ( bRedraw ) {
        oSettings.oApi._fnDraw( oSettings );
    }
};