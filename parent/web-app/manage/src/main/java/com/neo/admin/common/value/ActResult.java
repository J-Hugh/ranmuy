package com.neo.admin.common.value;

/**
 * 
 * @author Neo
 *
 * @param <T>
 */
public class ActResult<T> {


	private int status;
	
	private String msg;
	
	private T dataSet;

	
	public void setSuccess(T t){
		
		this.status=200;
		
		this.dataSet=t;
	}
	
	public void setSuccess(String msg){
		
		this.status=200;
		
		this.msg=msg;
	}
	
	public void setFail(String msg){
		
		this.status=500;
		
		this.msg=msg;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public T getDataSet() {
		return dataSet;
	}

	public void setDataSet(T dataSet) {
		this.status= 200;
		this.dataSet = dataSet;
	}
	
	
}

