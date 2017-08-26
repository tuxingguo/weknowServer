package cn.edu.cqut.weknow.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.edu.cqut.weknow.customer.po.NavigatorAnchor;
import cn.edu.cqut.weknow.customer.po.Result;
import cn.edu.cqut.weknow.customer.po.WeKnowException;
import cn.edu.cqut.weknow.po.Admins;
import cn.edu.cqut.weknow.po.AdminsExample;
import cn.edu.cqut.weknow.service.IAdminsService;
import cn.edu.cqut.weknow.utils.Utils;
import cn.edu.cqut.weknow.utils.pages.PagedResult;

@Controller
@RequestMapping("/admins")
public class AdminsController
{
	@Autowired
	private IAdminsService adminsService;
	@RequestMapping("/main")
	public void main(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
	{
		System.out.println("===============AdminsController::main()============");
		String pageNoString = httpServletRequest.getParameter("pageNo");
		int pageNo = 1;
		if(pageNoString != null)
		{
			try
			{
				pageNo = Integer.parseInt(pageNoString);
			}
			catch (Exception e) {
				pageNo = 1;
			}
		}
		PagedResult<Admins> pageResult = adminsService.list(null, pageNo);
		httpServletRequest.setAttribute("pageResult", pageResult);
		List<NavigatorAnchor> anchors = new ArrayList<NavigatorAnchor>();
		NavigatorAnchor navigatorAnchor = new NavigatorAnchor(httpServletRequest.getContextPath() + "/admins/main.action","管理员设置");
		anchors.add(navigatorAnchor);
		httpServletRequest.setAttribute("anchors", anchors);
		httpServletRequest.setAttribute("pageTitle", "设置系统管理员");
		httpServletRequest.getRequestDispatcher("/WEB-INF/jsps/admins/main.jsp")
				.forward(httpServletRequest, httpServletResponse);		
	}
	
	@RequestMapping("/add")
	public void add(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
			throws Exception
	{
		List<NavigatorAnchor> anchors = new ArrayList<NavigatorAnchor>();
		NavigatorAnchor navigatorAnchor = new NavigatorAnchor(httpServletRequest.getContextPath() + "/admins/main.action","设置系统管理员");
		anchors.add(navigatorAnchor);
		navigatorAnchor = new NavigatorAnchor(httpServletRequest.getContextPath() + "/admins/add.action","新增系统管理员");
		anchors.add(navigatorAnchor);
		httpServletRequest.setAttribute("anchors", anchors);
		httpServletRequest.setAttribute("pageTitle", "新增系统管理员");	
		httpServletRequest.getRequestDispatcher("/WEB-INF/jsps/admins/edit.jsp")
				.forward(httpServletRequest, httpServletResponse);
	}

	@RequestMapping("/edit")
	public void edit(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
			throws Exception
	{
		try
		{
			String id = httpServletRequest.getParameter("id");
			Admins admins = adminsService.find(Integer.parseInt(id));
			httpServletRequest.setAttribute("admins", admins);
			List<NavigatorAnchor> anchors = new ArrayList<NavigatorAnchor>();
			NavigatorAnchor navigatorAnchor = new NavigatorAnchor(httpServletRequest.getContextPath() + "/admins/main.action","设置系统管理员");
			anchors.add(navigatorAnchor);
			navigatorAnchor = new NavigatorAnchor(httpServletRequest.getContextPath() + "/admins/edit.action","修改管理员信息");
			anchors.add(navigatorAnchor);
			httpServletRequest.setAttribute("anchors", anchors);
			httpServletRequest.setAttribute("pageTitle", "修改管理员信息");				
			httpServletRequest.getRequestDispatcher("/WEB-INF/jsps/admins/edit.jsp")
					.forward(httpServletRequest, httpServletResponse);
		}
		catch (Exception e)
		{
			WeKnowException weKnowException = new WeKnowException("获取管理员信息：" + e.getMessage());
			throw weKnowException;
		}
	}

	@RequestMapping("/editSubmit")
	@ResponseBody
	public Result editSubmit(HttpSession session, Admins admins) throws Exception
	{
//		System.out.println(admins);
		Result result = new Result();
		try
		{
			if(admins.getId()==null)//表示新增
			{
				//用户名不区分大小写
				admins.setUsername(admins.getUsername().trim().toLowerCase());
				//判断用户名是否重复
				AdminsExample adminsExample = new AdminsExample();
				adminsExample.createCriteria().andUsernameEqualTo(admins.getUsername());
				PagedResult<Admins> pagedResult = adminsService.list(adminsExample, 1);
				if(pagedResult.getDataList().isEmpty() == false)
				{
					result.setResult(Result.FAIL);
					result.setErrorMsg("该用户名已经存在！");
					return result;
				}
				//加密密码
				admins.setUserpwd(Utils.encodeStringByMD5(admins.getUserpwd()));
				adminsService.add(admins);
			}
			else//表示更新
			{
				adminsService.update(admins);
			}
			result.setResult(Result.SUCCESS);
		}
		catch (Exception e)
		{
			result.setResult(Result.FAIL);
			result.setErrorMsg(e.getMessage());
		}
		return result;
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public Result delete(HttpSession session, HttpServletRequest httpServletRequest)
			throws Exception
	{
		Result result = new Result();
		try
		{
			String id = httpServletRequest.getParameter("id");
			Admins admins = adminsService.find(Integer.parseInt(id));
			//判断是否为默认管理员，不允许删除默认管理员admin
			if("admin".equals(admins.getUsername()))
			{
				result.setResult(Result.FAIL);
				result.setErrorMsg("默认系统管理员[admin]不允许删除！");
				return result;				
			}
			//从数据库中删除记录
			adminsService.delete(Integer.parseInt(id));
			//返回结果
			result.setResult(Result.SUCCESS);
		}
		catch (Exception e)
		{
			result.setResult(Result.FAIL);
			result.setErrorMsg(e.getMessage());
		}
		return result;
	}	
}
