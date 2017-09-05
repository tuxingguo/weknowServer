package cn.edu.cqut.weknow.service.test;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.edu.cqut.weknow.po.Admins;
import cn.edu.cqut.weknow.po.Feedbacks;
import cn.edu.cqut.weknow.service.IAdminsService;
import cn.edu.cqut.weknow.service.IFeedbacksService;
import cn.edu.cqut.weknow.utils.pages.PagedResult;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:spring/applicationContext-*.xml")
public class TestAdminsService
{
	@Autowired
	private IAdminsService adminsService;
	@Autowired
	private IFeedbacksService feedbacksService;
	
	//测试admin
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
	
	//测试feedbacks
	@Test
	public void testAdd()throws Exception{
		
		Feedbacks record = new Feedbacks();
		record.setUserid(2);
		record.setContent("helloworld");
		record.setSendtime(new Date().toString());
		int i = feedbacksService.add(record);
		System.out.println(i);
	}
	@Test
	public void testList2()throws Exception{
		PagedResult<Feedbacks> feedbacks = feedbacksService.list(null, 1);
		for (Feedbacks feedback : feedbacks.getDataList())
			System.out.println(feedback);
	}
	@Test
	public void testDelete()throws Exception{
		
		int i = feedbacksService.delete(1);
		System.out.println(i);
	}
}
