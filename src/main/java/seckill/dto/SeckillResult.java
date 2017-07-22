package seckill.dto;
//封装json结果
public class SeckillResult <T>{
	
	private boolean success; //状态码
	
	private String errorMessage;//错误消息 
	
	private T data;


	public SeckillResult(boolean success, String errorMessage) {
		this.success = success;
		this.errorMessage = errorMessage;
	}

	public SeckillResult(boolean success, T data) {
		this.success = success;
		this.data = data;
	}


	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getErrroMessage() {
		return errorMessage;
	}

	public void setErrroMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
	
	

}
