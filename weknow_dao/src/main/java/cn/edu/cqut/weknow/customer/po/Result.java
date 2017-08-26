package cn.edu.cqut.weknow.customer.po;

public class Result
{
	private String result;
	private String errorMsg;
	
	public static final String SUCCESS = "success";
	public static final String FAIL = "fail";
	public String getResult()
	{
		return result;
	}
	public void setResult(String result)
	{
		this.result = result;
	}
	public String getErrorMsg()
	{
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg)
	{
		this.errorMsg = errorMsg;
	}
}
