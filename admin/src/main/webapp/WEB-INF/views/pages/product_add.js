$(function ($) {
	var ProductAddController = {
		
		dbService: DbService,
		allFiles: [],
		photoThumbnailRel: 1,
		
		init : function(){
			ProductAddController.initForm();
			ProductAddController.bindEvent();
		},
		
		initForm : function(){
			$('.summernote').summernote();

			$('.input-group.date').datepicker({
				todayBtn : "linked",
				keyboardNavigation : false,
				forceParse : false,
				calendarWeeks : true,
				autoclose : true
			});
			
			ProductAddController.initCropper();
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
            	obj.rel = ProductAddController.photoThumbnailRel++;
            	obj.imgUrl = "";
            	obj.imgSortOrder = 1;
            	
				$("#tbl-product-images tbody").append(template.supplant(obj));
				
				obj.imgSrc = window.dataURLtoBlob && window.dataURLtoBlob(obj.imgSrc)
				ProductAddController.allFiles.push(obj);
            });
		},
		
		bindEvent : function(){
			$(document).on('click', '.btn-img-delete', function (event) {
				event.preventDefault();
				
				var rel = $(this).closest("tr").find(".photo-thumbnail").attr("rel");
				ProductAddController.allFiles = $.grep(ProductAddController.allFiles, function(e){
				    return e.rel != rel; 
				});
				
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
				    
				    for(var i = 0; i < ProductAddController.allFiles.length; i++){
				    	formData.append("productImageList["+ ( i ) +"].file", ProductAddController.allFiles[i].imgSrc);
				    	formData.append("productImageList["+ ( i ) +"].sortOrder", ProductAddController.allFiles[i].imgSortOrder);
					}
				    
				    var url = ctx + "/api/admin/products/";
				    url += "?" + $("#_csrf").attr("name") + "=" + $("#_csrf").val();
				    ProductAddController.dbService.postWithFormData(url, formData, function(data){				    
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
	ProductAddController.init();
});