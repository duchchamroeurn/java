package com.sangkhim.spring.base.message;

import org.springframework.http.HttpStatus;

public abstract class ResponseMessageUtils {
	
	/**
	 * Make success response
	 * @param body
	 * @return ResponseMessage<T>
	 */
	public static<T> ResponseMessage<T> makeSuccessResponse(T body){
		
		ResponseMessage<T> response = new ResponseMessage<T>();
		ResponseHeader header = response.getHeader();
		header.setResult(true);
		header.setStatusCode(HttpStatus.OK.value());
		
		if (body instanceof Boolean) {
			return response;
		}
		
		response.setBody(body);
		return response;
		
	}
	
	/**
	 * Make failure response
	 * @param ex
	 * @return ResponseMessage<T>
	 */
	public static<T> ResponseMessage<T> makeFailureResponse(Exception ex){
		
		ResponseMessage<T> response = new ResponseMessage<T>();
		ResponseHeader header = response.getHeader();
		
		header.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		Throwable cause = ex.getCause();
		if (cause != null) {
			header.setErrorText(cause.getMessage());
		}
		else {
			header.setErrorText(ex.getMessage());
		}
		
		header.setResult(false);
		
		return response;
		
	}
	
	/**
	 * Make response with defining success status
	 * @param success
	 * @param body
	 * @return
	 */
	public static<T> ResponseMessage<T> makeResponse(boolean success, T body){
		
		ResponseMessage<T> response = new ResponseMessage<T>();
		
		ResponseHeader header = response.getHeader();
		header.setResult(success);
		header.setStatusCode(HttpStatus.OK.value());
		response.setBody(body);
		
		return response;
		
	}
	
	/**
	 * Make response with defining success status
	 * @param success
	 * @param body
	 * @param statusCode
	 * @return
	 */
	public static<T> ResponseMessage<T> makeResponse(boolean success, String errorText, int statusCode){
		
		ResponseMessage<T> response = new ResponseMessage<T>();
		
		ResponseHeader header = response.getHeader();
		header.setResult(success);
		header.setStatusCode(statusCode);
		header.setErrorText(errorText);
		
		return response;
		
	}
}