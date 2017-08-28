package cn.edu.cqut.weknow.service;

import cn.edu.cqut.weknow.po.UserAuths;
import cn.edu.cqut.weknow.po.UserAuthsExample;
import cn.edu.cqut.weknow.utils.pages.PagedResult;


public interface IUserAuthsService
{
	PagedResult<UserAuths> list(UserAuthsExample example, Integer pageNo) throws Exception;
	UserAuths find(Integer id) throws Exception;
	int add(UserAuths record) throws Exception;
	int delete(Integer id) throws Exception;
	int update(UserAuths record) throws Exception;
}
