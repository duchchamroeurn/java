package com.sangkhim.spring.base.message;

public class ResponseMessage<T> {
	
	private ResponseHeader header;
	private T body;
	
	/**
	 * get header
	 * @return header
	 */
	public ResponseHeader getHeader() {
		if(header == null){
			header = new ResponseHeader();
		}
		return header;
	}
	
	/**
	 * set header
	 * @param header
	 */
	public void setHeader(ResponseHeader header) {
		this.header = header;
	}
	
	/**
	 * get body
	 * @return body
	 */
	public T getBody() {
		return body;
	}

	/**
	 * set body
	 * @param body
	 */
	public void setBody(T body) {
		this.body = body;
	}

	@Override
	public String toString() {
		return "ResponseMessage [header=" + header + ", body=" + body + "]";
	}
	
}