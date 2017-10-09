package cn.edu.cqut.weknow.service;

import cn.edu.cqut.weknow.po.News;
import cn.edu.cqut.weknow.po.NewsExample;
import cn.edu.cqut.weknow.utils.pages.PagedResult;

public interface INewsService {
	PagedResult<News> list(NewsExample example, Integer pageNo) throws Exception;
	News find(Integer id) throws Exception;
	int add(News record) throws Exception;
	int delete(Integer id) throws Exception;
	int update(News record) throws Exception;
}
