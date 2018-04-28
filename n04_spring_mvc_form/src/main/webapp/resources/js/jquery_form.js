jQuery(function($){
	var IndexController = {
		
		files: [],
			
		init: function() {
			
			IndexController.bindingEvent();
			
		},
		
		bindingEvent: function() {
			
			$("#file-upload").on("change", function() {
				// read image
				var getFile = $("#file-upload").val().split('\\');				
				var fileName = getFile[getFile.length - 1];				
				var imageFile = this;
				// display image
				if(imageFile != null){
					if(imageFile.files && imageFile.files[0]) {
						var reader = new FileReader();
						reader.onload = function(e) {
							$("#file-upload-img").attr('src', e.target.result);
						}
						reader.readAsDataURL(imageFile.files[0]);
					}					
				}
			});	
			
			$("#files-upload").on("change", function() {
				// read image
				var getFile = $("#files-upload").val().split('\\');				
				var fileName = getFile[getFile.length - 1];				
				var imageFile = this;
				// display image
				if(imageFile != null){
					if(imageFile.files && imageFile.files[0]) {
						var reader = new FileReader();
						reader.onload = function(e) {
							var img = $("<img/>", {"src": e.target.result, "style": "width: 100px; height: 100px;"});
							$("#files-upload-img").append(img);
						}
						reader.readAsDataURL(imageFile.files[0]);
						// add to files
						IndexController.files.push(imageFile.files[0]);
					}					
				}
			});	
			
			$("#btn-submit").click(function(){
				
				// contentType: application/x-www-form-urlencoded; charset=UTF-8
				IndexController.insertFormSerialize(); // ARC: name="Sangkhim"&sex="Male"

				// contentType: multipart/form-data (ARC: application/x-www-form-urlencoded; charset=UTF-8)
				IndexController.insertFormData1(); // ARC: name="Sangkhim"&sex="Male"
				IndexController.insertFormData2();// ARC: name="Sangkhim"&sex="Male"
				
				// contentType: application/json
				IndexController.insertJsonData(); // ARC: {"name":"Sangkhim","sex":"Male"}
				
			});
			
		},
		
		insertFormSerialize: function() {
			
			var url = ctx + "/formSerialize/";
			$.ajax({
				type: "POST",
				url: url,
				contentType: 'application/x-www-form-urlencoded; charset=UTF-8', // default
			    data: $('#form').serialize(),
			    dataType: "json", // The type of data that you're expecting back from the server. If none is specified, jQuery will try to infer it based on the MIME type of the response.
			    async: false,
				cache: false,
				success: function(data){
					$("#formSerializeResult").text(JSON.stringify(data));
				},
				errorCallBack: function (data) {
					
				}
			});
			
		},
		
		insertFormData1: function() {
			
			var formData = new FormData();
//			formData.append("name", "Sangkhim");
//			formData.append("sex", "Male");
			var otherData = $('#form').serializeArray();
		    $.each(otherData,function(key,input){
		    	if(input.value != ""){
		    		formData.append(input.name, input.value);
		    	}
		    });
		    
		    var imageData = $('#file-upload')[0].files;
			for (var i = 0; i < imageData.length; i++) {
				formData.append("file", imageData[i]);
			}	
			
			for (var i = 0; i < IndexController.files.length; i++) {
				formData.append("address.files["+ ( i ) +"]", IndexController.files[i]);
			}	
			
			var url = ctx + "/formData1/";
			$.ajax({
				type: "POST",
				url: url,
				contentType	: false, // Setting the contentType to false is imperative, since otherwise jQuery will set it incorrectly.
				processData	: false, // Setting processData to false lets you prevent jQuery from automatically transforming the data into a query string.
			    data: formData == null ? "" : formData,
			    dataType: "json", // The type of data that you're expecting back from the server. If none is specified, jQuery will try to infer it based on the MIME type of the response.
			    async: false,
				cache: false,
				success: function(data){
					$("#formData1Result").html(JSON.stringify(data));
				},
				errorCallBack: function (data) {
					
				}
			});
			
		},
		
		insertFormData2: function() {
			
			var formData = new FormData();
//			formData.append("name", "Sangkhim");
//			formData.append("sex", "Male");
			var otherData = $('#form').serializeArray();
		    $.each(otherData,function(key,input){
		    	if(input.value != ""){
		    		formData.append(input.name, input.value);
		    	}
		    });
		    
		    var imageData = $('#file-upload')[0].files;
			for (var i = 0; i < imageData.length; i++) {
				formData.append("file", imageData[i]);
			}	
			
			for (var i = 0; i < IndexController.files.length; i++) {
				formData.append("address.files["+ ( i ) +"]", IndexController.files[i]);
			}
			
			var url = ctx + "/formData2/";
			$.ajax({
				type: "POST",
				url: url,
				contentType	: false, // Setting the contentType to false is imperative, since otherwise jQuery will set it incorrectly.
				processData	: false, // Setting processData to false lets you prevent jQuery from automatically transforming the data into a query string.
			    data: formData == null ? "" : formData,
//			    dataType: "json", // The type of data that you're expecting back from the server. If none is specified, jQuery will try to infer it based on the MIME type of the response.
			    async: false,
				cache: false,
				success: function(data){
					$("#formData2Result").html(data);
				},
				errorCallBack: function (data) {

				}
			});
			
		},
		
		insertJsonData: function() {
			
			var arr = {"name":"Sangkhim", "sex":"Male", "address":{"houseNo":"44", "streetNo":"677", "city":"Kampot", "country":"Cambodia"}};
			
			var url = ctx + "/jsonData/";
			$.ajax({
				type: "POST",
				url: url,
				contentType: 'application/json',
			    data: JSON.stringify(arr),
			    dataType: "json", // The type of data that you're expecting back from the server. If none is specified, jQuery will try to infer it based on the MIME type of the response.
			    async: false,
				cache: false,
				success: function(data){
					$("#jsonDataResult").html(JSON.stringify(data));
				},
				errorCallBack: function (data) {
					
				}
			});
			
		}
	}
	IndexController.init();
});