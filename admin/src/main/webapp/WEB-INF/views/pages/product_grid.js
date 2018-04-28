$(function ($) {
	$('#btn-list').click(function(event){
		event.preventDefault();
		window.location.href = ctx + 'admin/product_list.html';
	});
	
	$('#btn-add-new-product').click(function(event){
		window.location.href = ctx + 'admin/product_add.html';
	});
});