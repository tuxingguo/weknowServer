package cn.edu.cqut.weknow.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.github.pagehelper.PageHelper;

import cn.edu.cqut.weknow.customer.po.WeKnowException;
import cn.edu.cqut.weknow.po.UserAuths;
import cn.edu.cqut.weknow.po.UserAuthsExample;
import cn.edu.cqut.weknow.po.mapper.UserAuthsMapper;
import cn.edu.cqut.weknow.service.IUserAuthsService;
import cn.edu.cqut.weknow.utils.pages.BeanUtil;
import cn.edu.cqut.weknow.utils.pages.PagedResult;

public class UserAuthsServiceImpl implements IUserAuthsService
{
	@Autowired
	private UserAuthsMapper userAuthsMapper;

	@Override
	public PagedResult<UserAuths> list(UserAuthsExample example, Integer pageNo) throws Exception
	{
		try
		{
			if(pageNo<=-1)
				return BeanUtil.toPagedResult(userAuthsMapper.selectByExample(example));
			else
			{
				pageNo = (pageNo == null) ? 1 : pageNo;
//				pageSize = pageSize == null ? 10 : pageSize;
				Integer pageSize = 5;
				PageHelper.startPage(pageNo, pageSize); // startPage是告诉拦截器说我要开始分页了。分页参数是这两个。
				return BeanUtil.toPagedResult(userAuthsMapper.selectByExample(example));				
			}
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			throw new WeKnowException(e.getMessage());
		}
	}

	@Override
	public UserAuths find(Integer id) throws Exception
	{
		try
		{
			return userAuthsMapper.selectByPrimaryKey(id);
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			throw new WeKnowException(e.getMessage());
		}
	}

	@Override
	public int add(UserAuths record) throws Exception
	{
		try
		{
			return userAuthsMapper.insert(record);
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			throw new WeKnowException(e.getMessage());
		}
	}

	@Override
	public int delete(Integer id) throws Exception
	{
		try
		{
			return userAuthsMapper.deleteByPrimaryKey(id);
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			throw new WeKnowException(e.getMessage());
		}
	}

	@Override
	public int update(UserAuths record) throws Exception
	{
		try
		{
			return userAuthsMapper.updateByPrimaryKey(record);
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			throw new WeKnowException(e.getMessage());
		}
	}
}
