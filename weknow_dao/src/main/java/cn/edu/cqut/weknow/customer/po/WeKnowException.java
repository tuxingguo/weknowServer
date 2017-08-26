package cn.edu.cqut.weknow.customer.po;

public class WeKnowException extends Exception
{
	private String message;
	public WeKnowException(String msg)
	{
		super(msg);
		this.message = msg;
	}
	public String getMessage()
	{
		return message;
	}
}
