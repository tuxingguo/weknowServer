package cn.edu.cqut.weknow.service.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.edu.cqut.weknow.po.Admins;
import cn.edu.cqut.weknow.service.IAdminsService;
import cn.edu.cqut.weknow.utils.pages.PagedResult;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:spring/applicationContext-*.xml")
public class TestAdminsService
{
	@Autowired
	private IAdminsService adminsService;
	@Test
	public void testFind() throws Exception
	{
		Admins admin = adminsService.find(1);
		System.out.println(admin);
	}
	@Test
	public void testList() throws Exception
	{
		PagedResult<Admins> admins = adminsService.list(null, 2);
		for (Admins admin : admins.getDataList())
			System.out.println(admin);
	}
}
