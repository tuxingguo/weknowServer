package cn.edu.cqut.weknow.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.github.pagehelper.PageHelper;

import cn.edu.cqut.weknow.customer.po.WeKnowException;
import cn.edu.cqut.weknow.po.Users;
import cn.edu.cqut.weknow.po.UsersExample;
import cn.edu.cqut.weknow.po.mapper.UsersMapper;
import cn.edu.cqut.weknow.service.IUserService;
import cn.edu.cqut.weknow.utils.pages.BeanUtil;
import cn.edu.cqut.weknow.utils.pages.PagedResult;

public class UsersServiceImpl implements IUserService
{
	@Autowired
	private UsersMapper usersMapper;
	@Override
	public PagedResult<Users> list(UsersExample example, Integer pageNo) throws Exception
	{
		try
		{
			if(pageNo<=-1)
				return BeanUtil.toPagedResult(usersMapper.selectByExample(example));
			else
			{
				pageNo = (pageNo == null) ? 1 : pageNo;
//				pageSize = pageSize == null ? 10 : pageSize;
				Integer pageSize = 5;
				PageHelper.startPage(pageNo, pageSize); // startPage是告诉拦截器说我要开始分页了。分页参数是这两个。
				return BeanUtil.toPagedResult(usersMapper.selectByExample(example));				
			}
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			throw new WeKnowException(e.getMessage());
		}
	}

	@Override
	public Users find(Integer id) throws Exception
	{
		try
		{
			return usersMapper.selectByPrimaryKey(id);
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			throw new WeKnowException(e.getMessage());
		}
	}

	@Override
	public int add(Users record) throws Exception
	{
		try
		{
			return usersMapper.insert(record);
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
			return usersMapper.deleteByPrimaryKey(id);
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			throw new WeKnowException(e.getMessage());
		}
	}

	@Override
	public int update(Users record) throws Exception
	{
		try
		{
			return usersMapper.updateByPrimaryKey(record);
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			throw new WeKnowException(e.getMessage());
		}
	}

}
