package cn.edu.cqut.weknow.service.impl;

import cn.edu.cqut.weknow.po.Feedbacks;
import cn.edu.cqut.weknow.po.FeedbacksExample;
import cn.edu.cqut.weknow.service.IFeedbacksService;
import cn.edu.cqut.weknow.utils.pages.PagedResult;
/*
 * 李海旺
 * 区域管理模块service实现文件，参照AdminsServiceImpl完成
 */
public class DomainsServiceImpl implements IFeedbacksService
{

	@Override
	public PagedResult<Feedbacks> list(FeedbacksExample example, Integer pageNo) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Feedbacks find(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int add(Feedbacks record) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Feedbacks record) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}
}
