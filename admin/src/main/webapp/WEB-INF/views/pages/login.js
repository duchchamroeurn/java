$(function ($) {
	if(loginError == "true"){
		toastr.options = {
            closeButton: true,
            progressBar: true,
            showMethod: 'slideDown',
            timeOut: 4000
        };
        toastr.error('Wrong user or password.', 'Login Fail');
	}
});