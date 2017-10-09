package cn.edu.cqut.weknow.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import com.github.pagehelper.PageHelper;
import cn.edu.cqut.weknow.customer.po.WeKnowException;
import cn.edu.cqut.weknow.po.News;
import cn.edu.cqut.weknow.po.NewsExample;
import cn.edu.cqut.weknow.po.mapper.NewsMapper;
import cn.edu.cqut.weknow.service.INewsService;
import cn.edu.cqut.weknow.utils.pages.BeanUtil;
import cn.edu.cqut.weknow.utils.pages.PagedResult;

public class NewsServiceImpl implements INewsService {

	@Autowired
	private NewsMapper newsMapper;
	
	@Override
	public PagedResult<News> list(NewsExample example, Integer pageNo) throws Exception {
		try
		{
			if(pageNo<=-1)
				return BeanUtil.toPagedResult(newsMapper.selectByExample(example));
			else
			{
				pageNo = (pageNo == null) ? 1 : pageNo;
				Integer pageSize = 5;
				PageHelper.startPage(pageNo, pageSize); 
				return BeanUtil.toPagedResult(newsMapper.selectByExample(example));				
			}
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			throw new WeKnowException(e.getMessage());
		}
	}
	
	@Override
	public News find(Integer id) throws Exception {
		
		try
		{
			return newsMapper.selectByPrimaryKey(id);
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			throw new WeKnowException(e.getMessage());
		}
	}

	@Override
	public int add(News record) throws Exception {
		
		try
		{
			return newsMapper.insert(record);
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			throw new WeKnowException(e.getMessage());
		}
	}

	@Override
	public int delete(Integer id) throws Exception {
		
		try
		{
			return newsMapper.deleteByPrimaryKey(id);
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			throw new WeKnowException(e.getMessage());
		}
	}

	@Override
	public int update(News record) throws Exception {
		
		try
		{
			return newsMapper.updateByPrimaryKey(record);
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			throw new WeKnowException(e.getMessage());
		}
	}

}
