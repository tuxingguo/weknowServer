package cn.edu.cqut.weknow.service;

import cn.edu.cqut.weknow.customer.po.UsersCustomer;
import cn.edu.cqut.weknow.po.UserAuthsExample;
import cn.edu.cqut.weknow.po.Users;
import cn.edu.cqut.weknow.po.UsersExample;
import cn.edu.cqut.weknow.utils.pages.PagedResult;

public interface IUserService
{
	PagedResult<Users> list(UsersExample example, Integer pageNo) throws Exception;
	Users find(Integer id) throws Exception;
	int add(Users record) throws Exception;
	int delete(Integer id) throws Exception;
	int update(Users record) throws Exception;
	
	UsersCustomer find(UserAuthsExample userAuthsExample) throws Exception;
}
