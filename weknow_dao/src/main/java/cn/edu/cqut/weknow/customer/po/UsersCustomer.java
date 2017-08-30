package cn.edu.cqut.weknow.customer.po;

public class UsersCustomer
{
    private long id;
    private String nick;
    private String userName;//真实姓名
    private String phone;
    private String email;
    private String avatarUrl;//头像地址
    private String registerTime;//注册时间（格式：格式：YYYY-MM-DD HH:MM:SS）
    private String registerIp;
    private String personalizedSignature;//个性签名（最多150个汉字）
    private String note;

    private String identifyType;//登录类型（手机号、邮箱、用户名）或第三方应用名称（weixin、weibo等）
    private String identifier;//登录标识（手机号、邮箱、用户名或第三方应用的唯一标识）
    private String credential;//密码凭证（站内的保存密码，站外的不保存或保存token）
    private Boolean verified;//是否验证（false-未验证，true-已验证）
    private String loginTime;//最后登录时间（格式：格式：YYYY-MM-DD HH:MM:SS）
    private String loginIp;//最后登录IP

    private String imei;//手机唯一标识

	public long getId()
	{
		return id;
	}

	public void setId(long id)
	{
		this.id = id;
	}

	public String getNick()
	{
		return nick;
	}

	public void setNick(String nick)
	{
		this.nick = nick;
	}

	public String getUserName()
	{
		return userName;
	}

	public void setUserName(String userName)
	{
		this.userName = userName;
	}

	public String getPhone()
	{
		return phone;
	}

	public void setPhone(String phone)
	{
		this.phone = phone;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public String getAvatarUrl()
	{
		return avatarUrl;
	}

	public void setAvatarUrl(String avatarUrl)
	{
		this.avatarUrl = avatarUrl;
	}

	public String getRegisterTime()
	{
		return registerTime;
	}

	public void setRegisterTime(String registerTime)
	{
		this.registerTime = registerTime;
	}

	public String getRegisterIp()
	{
		return registerIp;
	}

	public void setRegisterIp(String registerIp)
	{
		this.registerIp = registerIp;
	}

	public String getPersonalizedSignature()
	{
		return personalizedSignature;
	}

	public void setPersonalizedSignature(String personalizedSignature)
	{
		this.personalizedSignature = personalizedSignature;
	}

	public String getNote()
	{
		return note;
	}

	public void setNote(String note)
	{
		this.note = note;
	}

	public String getIdentifyType()
	{
		return identifyType;
	}

	public void setIdentifyType(String identifyType)
	{
		this.identifyType = identifyType;
	}

	public String getIdentifier()
	{
		return identifier;
	}

	public void setIdentifier(String identifier)
	{
		this.identifier = identifier;
	}

	public String getCredential()
	{
		return credential;
	}

	public void setCredential(String credential)
	{
		this.credential = credential;
	}

	public Boolean getVerified()
	{
		return verified;
	}

	public void setVerified(Boolean verified)
	{
		this.verified = verified;
	}

	public String getLoginTime()
	{
		return loginTime;
	}

	public void setLoginTime(String loginTime)
	{
		this.loginTime = loginTime;
	}

	public String getLoginIp()
	{
		return loginIp;
	}

	public void setLoginIp(String loginIp)
	{
		this.loginIp = loginIp;
	}

	public String getImei()
	{
		return imei;
	}

	public void setImei(String imei)
	{
		this.imei = imei;
	}
}
