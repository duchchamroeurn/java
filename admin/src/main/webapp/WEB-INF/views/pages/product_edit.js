$(function ($) {
	var ProductEditController = {
		
		dbService: DbService,
		allFiles: [],
		deleteFiles: [],
		photoThumbnailRel: 1,
		
		init : function(){
			ProductEditController.initForm();
			ProductEditController.renderData();
			ProductEditController.bindEvent();
		},
		
		initForm : function(){
			$('.input-group.date').datepicker({
				todayBtn : "linked",
				keyboardNavigation : false,
				forceParse : false,
				calendarWeeks : true,
				autoclose : true
			});
			
			ProductEditController.initCropper();
		},
		
		initCropper : function(){
			var $image = $(".image-crop > img")
            $($image).cropper({
                aspectRatio: "auto",
                preview: ".img-preview",
                done: function(data) {
                    // Output the result data for cropping image.
                }
            });

            var $inputImage = $("#inputImage");
            if (window.FileReader) {
                $inputImage.change(function() {
                    var fileReader = new FileReader(),
                            files = this.files,
                            file;

                    if (!files.length) {
                        return;
                    }

                    file = files[0];

                    if (/^image\/\w+$/.test(file.type)) {
                        fileReader.readAsDataURL(file);
                        fileReader.onload = function () {
                            $inputImage.val("");
                            $image.cropper("reset", true).cropper("replace", this.result);
                        };
                    } else {
                        showMessage("Please choose an image file.");
                    }
                });
            } else {
                $inputImage.addClass("hide");
            }

            $("#zoomIn").click(function() {
                $image.cropper("zoom", 0.1);
            });

            $("#zoomOut").click(function() {
                $image.cropper("zoom", -0.1);
            });

            $("#rotateLeft").click(function() {
                $image.cropper("rotate", 45);
            });

            $("#rotateRight").click(function() {
                $image.cropper("rotate", -45);
            });
            
            $("#setDragModeMove").click(function() {
            	$image.cropper("setDragMode", "move");
            });
            
            $("#setDragModeCrop").click(function() {
                $image.cropper("setDragMode", "crop");
            });

            $("#download").click(function() {
            	var template = $("#template-tr-img").html();
            	
            	var obj = {};
            	obj.imgSrc = $image.cropper("getDataURL", "image/jpeg");
            	obj.rel = ProductEditController.photoThumbnailRel++;
            	obj.imgUrl = "";
            	obj.imgSortOrder = 1;
            	
				$("#tbl-product-images tbody").append(template.supplant(obj));
				
				obj.imgSrc = window.dataURLtoBlob && window.dataURLtoBlob(obj.imgSrc)
				ProductEditController.allFiles.push(obj);
            });
		},
		
		renderData : function(){
			var productId = $("#product-id").val();
			var url = ctx + "/api/admin/products/" + productId;
			ProductEditController.dbService.get(url, null, function(data){				    
		    	if(data.header.result){	
		    		$("#form #name").val(data.body.name);
		    		$("#form #price").val(data.body.price);
		    		$("#form #description").html(data.body.description).summernote();
		    		$("#form #meta_tag_title").val(data.body.metaTagTitle);
		    		$("#form #meta_tag_description").val(data.body.metaTagDescription);
		    		$("#form #meta_tag_keywords").val(data.body.metaTagKeywords);
		    		$("#form #location").val(data.body.location);
		    		
		    		$.each(data.body.productImageList, function(key, value) {
		    			if(value.src != null) {
							var template = $("#template-tr-img").html();
			            	
			            	var obj = {};
			            	obj.imgSrc = ctx + value.src;
			            	obj.rel = "";
			            	obj.imgUrl = ctx + value.src;
			            	obj.imgSortOrder = value.sortOrder;
			            	
			            	obj.productImageId = value.productImageId;
			            	
							$("#tbl-product-images tbody").append(template.supplant(obj));
		    			}
					});
		    	}
			});
		},
		
		bindEvent : function(){
			$(document).on('click', '.btn-img-delete', function (event) {
				event.preventDefault();
				
				var rel = $(this).closest("tr").find(".photo-thumbnail").attr("rel");
				ProductEditController.allFiles = $.grep(ProductEditController.allFiles, function(e){
				    return e.rel != rel; 
				});
				
				if($(this).closest("tr").find(".photo-thumbnail").attr("product-image-id") != "{productImageId}") {
					ProductEditController.deleteFiles.push($(this).closest("tr").find(".photo-thumbnail").attr("product-image-id"));					
				}
				
				$(this).closest("tr").remove();
			});
			
			$("#btn-save").click(function(){
				if($("#name").val().trim() == "") {
					$("#name").closest("div.form-group").addClass("has-error");
					$("#name").focus();
				}else if($("#price").val().trim() == "" || isNaN($("#price").val())) {
					$("#price").closest("div.form-group").addClass("has-error");
					$("#price").focus();
				}else{
					var formData = new FormData();
					
					var otherData = $('#form').serializeArray();
				    $.each(otherData,function(key,input){
				    	if(input.value != ""){
				    		formData.append(input.name, input.value);
				    	}
				    });
				    
			    	formData.append("description", $("#description").code());
			    	
			    	for(var i = 0; i < ProductEditController.allFiles.length; i++){
				    	formData.append("productImageList["+ ( i ) +"].file", ProductEditController.allFiles[i].imgSrc);
				    	formData.append("productImageList["+ ( i ) +"].sortOrder", ProductEditController.allFiles[i].imgSortOrder);
					}
			    	
			    	for(var i = 0; i < ProductEditController.deleteFiles.length; i++){
						formData.append("deleteProductImageList", ProductEditController.deleteFiles[i]);
					}
					
				    var url = ctx + "/api/admin/update-products/";
				    url += "?" + $("#_csrf").attr("name") + "=" + $("#_csrf").val();
				    ProductEditController.dbService.postWithFormData(url, formData, function(data){				    
				    	if(data.header.result){	
				    		swal({
				                title: "Good job!",
				                text: "Your form has been saved.",
				                type: "success",
				                timer: 3000
				    		}, function () {
				            	window.location.href = ctx + 'admin/product_list.html';
				            });
				    	}
					});
				}
			});
		}
	}
	ProductEditController.init();
});