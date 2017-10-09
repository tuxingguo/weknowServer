package cn.edu.cqut.weknow.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.github.pagehelper.PageHelper;

import cn.edu.cqut.weknow.customer.po.UsersCustomer;
import cn.edu.cqut.weknow.customer.po.WeKnowException;
import cn.edu.cqut.weknow.customer.po.mapper.UsersCustomerMapper;
import cn.edu.cqut.weknow.po.UserAuths;
import cn.edu.cqut.weknow.po.UserAuthsExample;
import cn.edu.cqut.weknow.po.Users;
import cn.edu.cqut.weknow.po.UsersExample;
import cn.edu.cqut.weknow.po.mapper.UserAuthsMapper;
import cn.edu.cqut.weknow.po.mapper.UsersMapper;
import cn.edu.cqut.weknow.service.IUserService;
import cn.edu.cqut.weknow.utils.pages.BeanUtil;
import cn.edu.cqut.weknow.utils.pages.PagedResult;

public class UsersServiceImpl implements IUserService
{
	@Autowired
	private UsersMapper usersMapper;
	@Autowired
	private UserAuthsMapper userAuthsMapper;
	@Autowired
	private UsersCustomerMapper usersCustomerMapper;
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
			return usersCustomerMapper.insert(record);
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

	@Override
	public UsersCustomer find(UserAuthsExample userAuthsExample) throws Exception
	{
		try
		{
			List<UserAuths> userAuths = userAuthsMapper.selectByExample(userAuthsExample);
			if(userAuths.size()<=0)
				return null;
			UserAuths userAuth = userAuths.get(0);
			Users users = usersMapper.selectByPrimaryKey(userAuth.getUserid());
			if(users == null)
				return null;
			UsersCustomer usersCustomer = new UsersCustomer();
			usersCustomer.setId(users.getId());
			usersCustomer.setNick(users.getNick());
			usersCustomer.setUserName(users.getUsername());
			usersCustomer.setPhone(users.getPhone());
			usersCustomer.setEmail(users.getEmail());
			usersCustomer.setAvatarUrl(users.getAvatarurl());
			usersCustomer.setRegisterTime(users.getRegistertime());
			usersCustomer.setRegisterIp(users.getRegisterip());
			usersCustomer.setPersonalizedSignature(users.getPersonalizedsignature());
			usersCustomer.setNote(users.getNote());
			usersCustomer.setIdentifyType(userAuth.getIdentifytype());
			usersCustomer.setIdentifier(userAuth.getIdentifier());
			usersCustomer.setCredential(userAuth.getCredential());
			usersCustomer.setVerified(userAuth.getVerified());
			usersCustomer.setLoginTime(userAuth.getLogintime());
			usersCustomer.setLoginIp(userAuth.getLoginip());
			usersCustomer.setImei(userAuth.getLoginimei());
			return usersCustomer;
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			throw new WeKnowException(e.getMessage());
		}
	}

}
