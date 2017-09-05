package cn.edu.cqut.weknow.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
/*
 * 涂兴国
 * 投诉建议模块Controller实现文件，参照AdminsController完成
 */
import org.springframework.web.bind.annotation.ResponseBody;

import cn.edu.cqut.weknow.customer.po.NavigatorAnchor;
import cn.edu.cqut.weknow.customer.po.Result;
import cn.edu.cqut.weknow.customer.po.WeKnowException;
import cn.edu.cqut.weknow.po.Feedbacks;
import cn.edu.cqut.weknow.po.FeedbacksExample;
import cn.edu.cqut.weknow.service.IFeedbacksService;
import cn.edu.cqut.weknow.utils.pages.PagedResult;
@Controller
@RequestMapping("/feedbacks")
public class FeedbacksController
{
	@Autowired
	private IFeedbacksService feedbacksService;
	
	@RequestMapping("/main")
	public void main(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
			throws Exception{
		
		System.out.println("===============FeedBacksController::main()============");
		String pageNoString = httpServletRequest.getParameter("pageNo");
		int pageNo = 1;
		if(pageNoString!=null){
			try {
				pageNo = Integer.parseInt(pageNoString);
			} catch (Exception e) {
				pageNo = 1;
			}
		}
		
		PagedResult<Feedbacks> pagedResult = feedbacksService.list(null, pageNo);
		
		httpServletRequest.setAttribute("pagedResult", pagedResult);
		
		List<NavigatorAnchor> anchors = new ArrayList<NavigatorAnchor>();
		NavigatorAnchor navigatorAnchor = new NavigatorAnchor(httpServletRequest.getContextPath() + "/feedbacks/main.action","投诉建议管理");
		anchors.add(navigatorAnchor);
		httpServletRequest.setAttribute("anchors", anchors);
		httpServletRequest.setAttribute("pageTitle", "投诉建议管理");
		
		httpServletRequest.getRequestDispatcher("/WEB-INF/jsps/feedbacks/main.jsp").forward(httpServletRequest, httpServletResponse);		
	}
	
	@RequestMapping("/add")
	public void add(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
			throws Exception{
		
		List<NavigatorAnchor> anchors = new ArrayList<NavigatorAnchor>();
		NavigatorAnchor navigatorAnchor = new NavigatorAnchor(httpServletRequest.getContextPath() + "/feedbacks/add.action","投诉建议管理");
		anchors.add(navigatorAnchor);
		navigatorAnchor = new NavigatorAnchor(httpServletRequest.getContextPath() + "/feedbacks/add.action","新增投诉建议");
		anchors.add(navigatorAnchor);
		httpServletRequest.setAttribute("anchors", anchors);
		httpServletRequest.setAttribute("pageTitle", "新增投诉建议");
		
		httpServletRequest.getRequestDispatcher("/WEB-INF/jsps/feedbacks/edit.jsp").forward(httpServletRequest, httpServletResponse);
	}
	
	@RequestMapping("/edit")
	public void edit(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
			throws Exception{
		//通过id查找到数据库中所对应的记录,编辑该条记录
		try {
			String id = httpServletRequest.getParameter("id");
			Feedbacks feedbacks = feedbacksService.find(Integer.parseInt(id));
			httpServletRequest.setAttribute("feedbacks", feedbacks);
			
			List<NavigatorAnchor> anchors = new ArrayList<NavigatorAnchor>();
			NavigatorAnchor navigatorAnchor = new NavigatorAnchor(httpServletRequest.getContextPath() + "/feedbacks/main.action","投诉建议管理");
			anchors.add(navigatorAnchor);
			navigatorAnchor = new NavigatorAnchor(httpServletRequest.getContextPath() + "/feedbacks/edit.action","修改投诉建议信息");
			anchors.add(navigatorAnchor);
			httpServletRequest.setAttribute("anchors", anchors);
			httpServletRequest.setAttribute("pageTitle", "修改投诉建议信息");				
			httpServletRequest.getRequestDispatcher("/WEB-INF/jsps/feedbacks/edit.jsp").forward(httpServletRequest, httpServletResponse);
		} catch (Exception e) {
			WeKnowException weKnowException = new WeKnowException("获取投诉建议信息：" + e.getMessage());
			throw weKnowException;
		}
		 
	}
	
	
	@RequestMapping("/editSubmit")
	@ResponseBody
	public Result editSubmit(HttpSession session, Feedbacks feedbacks) throws Exception
	{
		Result result = new Result();
		try
		{
			if(feedbacks.getId()==null)//表示新增
			{
				//用户名不区分大小写
				feedbacks.setUserid(feedbacks.getUserid());
				
				//判断用户名是否重复
				FeedbacksExample feedbacksExample = new FeedbacksExample();
				feedbacksExample.createCriteria().andUseridEqualTo(feedbacks.getUserid());
				
				PagedResult<Feedbacks> pagedResult = feedbacksService.list(feedbacksExample, 1);
				if(pagedResult.getDataList().isEmpty() == false)
				{
					result.setResult(Result.FAIL);
					result.setErrorMsg("userid已经存在！");
					return result;
				}
				
				feedbacksService.add(feedbacks);
			}
			else//表示更新
			{
				feedbacksService.update(feedbacks);
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
			throws Exception{
		Result result = new Result();
		try
		{
			String id = httpServletRequest.getParameter("id");
			//从数据库中删除记录
			feedbacksService.delete(Integer.parseInt(id));
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
