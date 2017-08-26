package cn.edu.cqut.weknow.customer.po;

public class NavigatorAnchor
{
	private String url;
	private String desc;
	public NavigatorAnchor(String url, String desc)
	{
		this.url = url;
		this.desc = desc;
	}
	public String getUrl()
	{
		return url;
	}
	public String getDesc()
	{
		return desc;
	}
}
