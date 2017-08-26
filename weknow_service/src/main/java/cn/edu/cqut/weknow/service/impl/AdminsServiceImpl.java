package cn.edu.cqut.weknow.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.github.pagehelper.PageHelper;

import cn.edu.cqut.weknow.customer.po.WeKnowException;
import cn.edu.cqut.weknow.po.Admins;
import cn.edu.cqut.weknow.po.AdminsExample;
import cn.edu.cqut.weknow.po.mapper.AdminsMapper;
import cn.edu.cqut.weknow.service.IAdminsService;
import cn.edu.cqut.weknow.utils.pages.BeanUtil;
import cn.edu.cqut.weknow.utils.pages.PagedResult;

public class AdminsServiceImpl implements IAdminsService
{
	@Autowired
	private AdminsMapper adminsMapper;

	@Override
	public PagedResult<Admins> list(AdminsExample example, Integer pageNo) throws Exception
	{
		try
		{
			if(pageNo<=-1)
				return BeanUtil.toPagedResult(adminsMapper.selectByExample(example));
			else
			{
				pageNo = (pageNo == null) ? 1 : pageNo;
//				pageSize = pageSize == null ? 10 : pageSize;
				Integer pageSize = 5;
				PageHelper.startPage(pageNo, pageSize); // startPage是告诉拦截器说我要开始分页了。分页参数是这两个。
				return BeanUtil.toPagedResult(adminsMapper.selectByExample(example));				
			}
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			throw new WeKnowException(e.getMessage());
		}
	}

	@Override
	public Admins find(Integer id) throws Exception
	{
		try
		{
			return adminsMapper.selectByPrimaryKey(id);
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			throw new WeKnowException(e.getMessage());
		}
	}

	@Override
	public int add(Admins record) throws Exception
	{
		try
		{
			return adminsMapper.insert(record);
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
			return adminsMapper.deleteByPrimaryKey(id);
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			throw new WeKnowException(e.getMessage());
		}
	}

	@Override
	public int update(Admins record) throws Exception
	{
		try
		{
			return adminsMapper.updateByPrimaryKey(record);
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			throw new WeKnowException(e.getMessage());
		}
	}
}
