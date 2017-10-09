package cn.edu.cqut.weknow.service.test;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.edu.cqut.weknow.po.Admins;
import cn.edu.cqut.weknow.po.Feedbacks;
import cn.edu.cqut.weknow.po.News;
import cn.edu.cqut.weknow.po.Users;
import cn.edu.cqut.weknow.service.IAdminsService;
import cn.edu.cqut.weknow.service.IFeedbacksService;
import cn.edu.cqut.weknow.service.INewsService;
import cn.edu.cqut.weknow.service.IUserService;
import cn.edu.cqut.weknow.utils.pages.PagedResult;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:spring/applicationContext-*.xml")
public class TestAdminsService
{
	@Autowired
	private IAdminsService adminsService;
	@Autowired
	private IFeedbacksService feedbacksService;
	@Autowired
	private IUserService userService;
	
	@Autowired
	private INewsService newsService;
	
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
	//测试User
	@Test
	public void testUsersFindByPrimarykey(){
		Users user = new Users();
		
		 try {
			user = userService.find(1);
			System.out.println(user.getId()+"++"+user.getUsername()+"++"+user.getEmail()+"++"+user.getPhone());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test
	public void testUsersUpdate(){
		Users record = new Users();
		record.setId(1);
		record.setEmail("1207043975@qq.com");
		record.setPersonalizedsignature("我的青春我做主");
		record.setPhone("18323176951");
		record.setUsername("花花");
		record.setRegistertime("2016-02-06");
		record.setRegisterip("963852");
		 try {
			userService.update(record);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//测试News
	@Test
	public void testNewsList() throws Exception
	{
		PagedResult<News> newsList = newsService.list(null, 1);
		for (News news : newsList.getDataList())
			System.out.println(news.getTitle()+"++"+news.getContent());
	}
	@Test
	public void testNewsAdd() throws Exception
	{
		Byte i = 10;
		News record = new News();
		record.setTitle("你好");
		record.setContent("回不去的小时候");
		record.setType(i);
		record.setUploadip("8080");
		record.setUploadtime("2017-05-30");
		record.setAuthorid(123);
		record.setPosition("北京");
		record.setAddresscode("0");
		newsService.add(record);
	}
	@Test
	public void testNewsdelete() throws Exception
	{
		int i = newsService.delete(1);
		System.out.println(i);
	}
	
	
	
}
