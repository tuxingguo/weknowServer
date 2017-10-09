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
import cn.edu.cqut.weknow.po.Domains;
import cn.edu.cqut.weknow.po.DomainsExample;
import cn.edu.cqut.weknow.service.IDomainsService;
import cn.edu.cqut.weknow.utils.pages.PagedResult;
/*
 * 李海旺
 * 区域管理模块Controller实现文件，参照AdminsController完成
 */

@Controller
@RequestMapping("/domains")
public class DomainsController {

    @Autowired
    private IDomainsService domainsService;
    @RequestMapping("/main")
    public void main(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
	System.out.println("===============DomainsController::main()============");
	String pageNoString = httpServletRequest.getParameter("pageNo");
	int pageNo = 1;
	if (pageNoString != null) {
	    try {
		pageNo = Integer.parseInt(pageNoString);
	    } catch (Exception e) {

		pageNo = 1;
	    }
	}
	PagedResult<Domains> pageResult = domainsService.list(null, pageNo);
	httpServletRequest.setAttribute("pageResult", pageResult);
	List<NavigatorAnchor> anchors = new ArrayList<NavigatorAnchor>();
	NavigatorAnchor navigatorAnchor = new NavigatorAnchor(
		httpServletRequest.getContextPath() + "/domains/main.action", "区域管理设置");
	anchors.add(navigatorAnchor);

	String parentName = null;
	List<String> parentNames = new ArrayList<String>();
	List<Domains> domains = pageResult.getDataList();
	for (Domains domain : domains) {

	    if (domain.getName().equals("0")) {
		parentName = "  ───";
	    } else {
		parentName = domainsService.find(domain.getName()).getParentid();
	    }
	    parentNames.add(parentName);
	}

	httpServletRequest.setAttribute("parentNames", parentNames);
	httpServletRequest.setAttribute("anchors", anchors);
	httpServletRequest.setAttribute("pageTitle", "设置区域信息");
	httpServletRequest.getRequestDispatcher("/WEB-INF/jsps/domains/main.jsp").forward(httpServletRequest,

		httpServletResponse);
    }

    @RequestMapping("/add")
    public void add(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
	List<NavigatorAnchor> anchors = new ArrayList<NavigatorAnchor>();
	NavigatorAnchor navigatorAnchor = new NavigatorAnchor(

		httpServletRequest.getContextPath() + "/domains/main.action", "设置区域信息");
	anchors.add(navigatorAnchor);
	navigatorAnchor = new NavigatorAnchor(httpServletRequest.getContextPath() + "/domains/add.action", "新增区域信息");
	anchors.add(navigatorAnchor);
	httpServletRequest.setAttribute("anchors", anchors);
	httpServletRequest.setAttribute("pageTitle", "新增区域信息");
	httpServletRequest.getRequestDispatcher("/WEB-INF/jsps/domains/edit.jsp").forward(httpServletRequest,
		httpServletResponse);

    }

    @RequestMapping("/edit")
    public void edit(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
	try {
	    String id = httpServletRequest.getParameter("id");
	    Domains domains = domainsService.find(id);
	    httpServletRequest.setAttribute("domains", domains);
	    List<NavigatorAnchor> anchors = new ArrayList<NavigatorAnchor>();
	    NavigatorAnchor navigatorAnchor = new NavigatorAnchor(
		    httpServletRequest.getContextPath() + "/domains/main.action", "设置区域信息");
	    anchors.add(navigatorAnchor);
	    navigatorAnchor = new NavigatorAnchor(httpServletRequest.getContextPath() + "/domains/edit.action",
		    "修改区域信息");
	    anchors.add(navigatorAnchor);

	    httpServletRequest.setAttribute("anchors", anchors);
	    httpServletRequest.setAttribute("pageTitle", "修改区域信息");
	    httpServletRequest.getRequestDispatcher("/WEB-INF/jsps/domains/edit.jsp").forward(httpServletRequest,
		    httpServletResponse);

	} catch (Exception e) {

	    WeKnowException weKnowException = new WeKnowException("获取区域信息：" + e.getMessage());
	    throw weKnowException;
	}
    }

    @RequestMapping("/editSubmit")
    @ResponseBody
    public Result editSubmit(HttpSession session, Domains domains) throws Exception {
	Result result = new Result();
	try {
	    DomainsExample domainsExample = new DomainsExample();
	    domainsExample.createCriteria().andIdEqualTo(domains.getId());
	    PagedResult<Domains> pagedResult = domainsService.list(domainsExample, 1);
	    if (pagedResult.getDataList().isEmpty()) {// 表示新增
		DomainsExample domainsExample1 = new DomainsExample();
		domainsExample1.createCriteria().andParentidEqualTo(domains.getParentid());
		PagedResult<Domains> pagedResult1 = domainsService.list(domainsExample1, 1);
		if (pagedResult1.getDataList().isEmpty() == false) {
		    result.setResult(Result.FAIL);
		    result.setErrorMsg("该区域名已经存在！");
		    return result;

		}
		domainsService.add(domains);

	    } else// 表示更新
	    {
		domainsService.update(domains);
	    }
	    result.setResult(Result.SUCCESS);

	} catch (Exception e) {
	    result.setResult(Result.FAIL);
	    result.setErrorMsg(e.getMessage());
	}

	return result;

    }

    @RequestMapping("/delete")
    @ResponseBody
    public Result delete(HttpSession session, HttpServletRequest httpServletRequest) throws Exception {
	Result result = new Result();
	try {
	    String id = httpServletRequest.getParameter("id");
	    // 从数据库中删除记录

	    domainsService.delete(id);
	    // 返回结果
	    result.setResult(Result.SUCCESS);

	} catch (Exception e) {
	    result.setResult(Result.FAIL);
	    result.setErrorMsg(e.getMessage());
	}
	return result;
    }



}