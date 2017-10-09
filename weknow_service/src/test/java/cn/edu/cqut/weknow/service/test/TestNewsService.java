package cn.edu.cqut.weknow.service.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.edu.cqut.weknow.po.News;
import cn.edu.cqut.weknow.po.NewsExample;
import cn.edu.cqut.weknow.service.INewsService;
import cn.edu.cqut.weknow.utils.pages.PagedResult;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:spring/applicationContext-*.xml")
public class TestNewsService
{
	@Autowired
	private INewsService newsService;
	
	@Test
	public void testList() throws Exception
	{
		NewsExample example = new NewsExample();
		NewsExample.Criteria criteria = example.createCriteria();
		criteria.andTypeEqualTo(Byte.valueOf("0"));
//		criteria.andAddresscodeEqualTo("700001");
		criteria.andAddresscodeBetween("700001", "700099");
		PagedResult<News> newss = newsService.list(example, 1);
//		PagedResult<News> newss = newsService.list(null, 4);
		for (News news : newss.getDataList())
			System.out.println(news);
	}
}
