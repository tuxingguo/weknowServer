package cn.edu.cqut.weknow.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.edu.cqut.weknow.customer.po.Result;
import cn.edu.cqut.weknow.po.UserAuths;
import cn.edu.cqut.weknow.po.UserAuthsExample;
import cn.edu.cqut.weknow.po.Users;
import cn.edu.cqut.weknow.service.IUserAuthsService;
import cn.edu.cqut.weknow.service.IUserService;
import cn.edu.cqut.weknow.utils.RegExpValidator;
import cn.edu.cqut.weknow.utils.Utils;

@Controller
@RequestMapping("/account")
public class AccountController
{
	@Autowired
	private IUserService userService;
	@Autowired
	private IUserAuthsService userAuthService;

	@RequestMapping("/register")
	@ResponseBody
	public Result register(HttpServletRequest httpServletRequest) throws Exception
	{
		System.out.println("===================AccountController::register()======================");
		Result result = new Result();
		try
		{
			// 登录名不区分大小写，数据库中存储的全部为大写
			String loginName = httpServletRequest.getParameter("loginName").trim().toUpperCase();
			String loginPassword = httpServletRequest.getParameter("loginPassword").trim();
//			System.out.println("loginName: " + loginName);
//			System.out.println("loginPassword: " + loginPassword);
			// 1. 校验用户名和密码
			if ("".equals(loginName) || "".equals(loginPassword))
			{
				result.setResult(Result.FAIL);
				result.setErrorMsg("用户名或密码为空！");
				return result;
			}
			// 2. 查看用户名是否可用
			UserAuthsExample userAuthsExample = new UserAuthsExample();
			userAuthsExample.createCriteria().andIdentifierEqualTo(loginName);
			if (userAuthService.list(userAuthsExample, 1).getDataList().size() > 0)
			{
				result.setResult(Result.FAIL);
				result.setErrorMsg("该用户名已经存在！");
				return result;
			}
			// 3. 存储用户信息
			Users users = new Users();
			UserAuths userAuths = new UserAuths();
			users.setUsername(loginName);
			users.setNick(loginName);
			if (RegExpValidator.isEmail(loginName))
			{
				userAuths.setIdentifytype(Utils.LOGIN_TYPE_EMAIL);
				users.setEmail(loginName);
			}
			else if (RegExpValidator.isMobileNO(loginName))
			{
				userAuths.setIdentifytype(Utils.LOGIN_TYPE_PHONE);
				users.setPhone(loginName);
			}
			else
			{
				userAuths.setIdentifytype(Utils.LOGIN_TYPE_USER_NAME);
			}
			// 获取系统当前日期作为注册日期
			DateFormat dateFormatDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			users.setRegistertime(dateFormatDate.format(new Date()));
			users.setRegisterip(httpServletRequest.getRemoteAddr());

			userAuths.setUserid(users.getId());
			userAuths.setIdentifier(loginName);
			userAuths.setCredential(loginPassword);
			userAuths.setVerified(false);
			
			userService.add(users);
			userAuthService.add(userAuths);
			
			result.setResult(Result.SUCCESS);
			return result;
		}
		catch (Exception e)
		{
			result.setResult(Result.FAIL);
			result.setErrorMsg(e.getMessage());
		}
		return result;
	}

}
