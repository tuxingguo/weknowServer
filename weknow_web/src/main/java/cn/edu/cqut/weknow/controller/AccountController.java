package cn.edu.cqut.weknow.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.edu.cqut.weknow.customer.po.Result;
import cn.edu.cqut.weknow.po.UserAuths;
import cn.edu.cqut.weknow.po.Users;
import cn.edu.cqut.weknow.service.IUserAuthsService;
import cn.edu.cqut.weknow.service.IUserService;
import cn.edu.cqut.weknow.utils.RegExpValidator;

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
			//登录名不区分大小写，数据库中存储的全部为大写
			String loginName = httpServletRequest.getParameter("loginName").trim().toUpperCase();
			String loginPassword = httpServletRequest.getParameter("loginPassword").trim();
			System.out.println("loginName: " + loginName);
			System.out.println("loginPassword: " + loginPassword);
			//1. 校验用户名和密码
			if("".equals(loginName) || "".equals(loginPassword))
			{
				 result.setResult(Result.FAIL);
				 result.setErrorMsg("用户名或密码为空！");
				 return result;
			}
			//2. 存储用户信息
			Users users = new Users();
			UserAuths userAuths = new UserAuths();
			users.setUsername(loginName);
			users.setNick(loginName);
			if(RegExpValidator.isEmail(loginName))
				users.setEmail(loginName);
			if(RegExpValidator.isMobileNO(loginName))
				users.setPhone(loginName);
			
			
			
			// // 用户名不区分大小写
			// admins.setUsername(admins.getUsername().trim().toLowerCase());
			// // 判断用户名是否重复
			// AdminsExample adminsExample = new AdminsExample();
			// adminsExample.createCriteria().andUsernameEqualTo(admins.getUsername());
			// PagedResult<Admins> pagedResult =
			// adminsService.list(adminsExample, 1);
			// if (pagedResult.getDataList().isEmpty() == false)
			// {
			// result.setResult(Result.FAIL);
			// result.setErrorMsg("该用户名已经存在！");
			// return result;
			// }
			// // 加密密码
			// admins.setUserpwd(Utils.encodeStringByMD5(admins.getUserpwd()));
			// adminsService.add(admins);
			// result.setResult(Result.SUCCESS);
			
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
