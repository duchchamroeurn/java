String.prototype.supplant = function (o) {
	return this.replace(
		/{([^{}]*)}/g, 
		function (a, b) {
			var r = o[b];
			return typeof r === 'string' || typeof r === 'number' ? r : a;
		}
	);
};

var DbService = {
	get: function(url, jsonData, successCallBack, errorCallBack) {
		$.ajax({
			type: "GET",
			url: url,
			contentType: "application/json",
		    data: jsonData == null ? "" : JSON.stringify(jsonData),
			success: function(data){
				if(typeof successCallBack === "function"){
					successCallBack(data);
				}
			},
			errorCallBack: function (data) {
				if(typeof errorCallBack === "function"){
					errorCallBack(data);
				}
			}
		});
	},
	post: function(url, jsonData, successCallBack, errorCallBack) {
		$.ajax({
			type: "POST",
			url: url,
			contentType: "application/json",
		    data: jsonData == null ? "" : JSON.stringify(jsonData),
			success: function(data){
				if(typeof successCallBack === "function"){
					successCallBack(data);
				}
			},
			errorCallBack: function (data) {
				if(typeof errorCallBack === "function"){
					errorCallBack(data);
				}
			}
		});
	},
	postWithFormData: function(url, formData, successCallBack, errorCallBack) {
		$.ajax({
			type: "POST",
			url: url,
			contentType	: false,
			processData	: false,
		    data: formData == null ? "" : formData,
		    dataType: "json",
		    async: true,
			cache: false,
			success: function(data){
				if(typeof successCallBack === "function"){
					successCallBack(data);
				}
			},
			errorCallBack: function (data) {
				if(typeof errorCallBack === "function"){
					errorCallBack(data);
				}
			}
		});
	}
}