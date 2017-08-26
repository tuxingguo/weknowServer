package cn.edu.cqut.weknow.service;

import cn.edu.cqut.weknow.po.Admins;
import cn.edu.cqut.weknow.po.AdminsExample;
import cn.edu.cqut.weknow.utils.pages.PagedResult;


public interface IAdminsService
{
	PagedResult<Admins> list(AdminsExample example, Integer pageNo) throws Exception;
	Admins find(Integer id) throws Exception;
	int add(Admins record) throws Exception;
	int delete(Integer id) throws Exception;
	int update(Admins record) throws Exception;
}
