package cn.edu.cqut.weknow.dao.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.edu.cqut.weknow.po.Admins;
import cn.edu.cqut.weknow.po.mapper.AdminsMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/applicationContext-*.xml")
public class AdminsMapperTest
{
	@Autowired
	private AdminsMapper adminsMapper;
	
	@Test
	public void testFindAdminById()
	{
		Admins admins = adminsMapper.selectByPrimaryKey(1);
		System.out.println(admins);
	}
}
