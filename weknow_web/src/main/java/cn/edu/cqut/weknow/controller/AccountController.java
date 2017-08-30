package cn.edu.cqut.weknow.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.edu.cqut.weknow.customer.po.Result;
import cn.edu.cqut.weknow.customer.po.UsersCustomer;
import cn.edu.cqut.weknow.po.UserAuths;
import cn.edu.cqut.weknow.po.UserAuthsExample;
import cn.edu.cqut.weknow.po.Users;
import cn.edu.cqut.weknow.service.IUserAuthsService;
import cn.edu.cqut.weknow.service.IUserService;
import cn.edu.cqut.weknow.utils.RegExpValidator;
import cn.edu.cqut.weknow.utils.Utils;
import cn.edu.cqut.weknow.utils.pages.PagedResult;

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
			String loginName = httpServletRequest.getParameter("loginName");
			String loginPassword = httpServletRequest.getParameter("loginPassword");
			String loginImei = httpServletRequest.getParameter("loginImei");
			String errorMsg = validateInputData(loginName, loginPassword);
			if(!"".equals(errorMsg))
			{
				result.setResult(Result.FAIL);
				result.setErrorMsg(errorMsg);
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
			userService.add(users);

			userAuths.setUserid(users.getId());
			userAuths.setIdentifier(loginName);
			userAuths.setCredential(loginPassword);
			userAuths.setVerified(false);
			userAuths.setLoginimei(loginImei);
			userAuthService.add(userAuths);

			result.setResult(Result.SUCCESS);
			return result;
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			result.setResult(Result.FAIL);
			result.setErrorMsg("服务器异常，请稍后再试！");
		}
		return result;
	}

	@RequestMapping("/login")
	@ResponseBody
	public Result login(HttpServletRequest httpServletRequest) throws Exception
	{
		System.out.println("===================AccountController::login()======================");
		Result result = new Result();
		try
		{
			// 1. 校验输入数据
			String loginName = httpServletRequest.getParameter("loginName");
			String loginPassword = httpServletRequest.getParameter("loginPassword");
			String loginImei = httpServletRequest.getParameter("loginImei");
			String errorMsg = validateInputData(loginName, loginPassword);
			if(!"".equals(errorMsg))
			{
				result.setResult(Result.FAIL);
				result.setErrorMsg(errorMsg);
				return result;				
			}
			// 2. 验证登录名和密码是否正确
			UserAuthsExample userAuthsExample = new UserAuthsExample();
			userAuthsExample.createCriteria().andIdentifierEqualTo(loginName);
			userAuthsExample.createCriteria().andCredentialEqualTo(loginPassword);
			PagedResult<UserAuths> pagedResult = userAuthService.list(userAuthsExample, 1);
			if (pagedResult.getDataList().size() <= 0)
			{
				result.setResult(Result.FAIL);
				result.setErrorMsg("登录名或密码错误！");
				return result;
			}			
			// 3. 更新最后登录信息
			UserAuths userAuths = pagedResult.getDataList().get(0);
			DateFormat dateFormatDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			userAuths.setLogintime(dateFormatDate.format(new Date()));
			userAuths.setLoginip(httpServletRequest.getRemoteAddr());
			userAuths.setLoginimei(loginImei);
			userAuthService.update(userAuths);
			// 4. 登录成功
			result.setResult(Result.SUCCESS);
			return result;			
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			result.setResult(Result.FAIL);
			result.setErrorMsg("服务器异常，请稍后再试！");
		}
		return result;
	}

	private String validateInputData(String loginName, String loginPassword)
	{
		System.out.println("loginName: " + loginName);
		System.out.println("loginPassword: " + loginPassword);
		if(StringUtils.isEmpty(loginName)||StringUtils.isEmpty(loginPassword))
			return "用户名或密码为空！";
		return "";
	}
	@RequestMapping("/userInfo")
	@ResponseBody
	public UsersCustomer userInfo(HttpServletRequest httpServletRequest) throws Exception
	{
		System.out.println("===================AccountController::userInfo()======================");
		try
		{
			//1. 获取登录名
			String loginName = httpServletRequest.getParameter("loginName");
			//2. 校验数据
			if(StringUtils.isEmpty(loginName))
				return null;
			//3. 从数据库取出数据
			UserAuthsExample userAuthsExample = new UserAuthsExample();
			userAuthsExample.createCriteria().andIdentifierEqualTo(loginName);
			UsersCustomer usersCustomer = userService.find(userAuthsExample);
			//4. 返回数据
			return usersCustomer;
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
}
