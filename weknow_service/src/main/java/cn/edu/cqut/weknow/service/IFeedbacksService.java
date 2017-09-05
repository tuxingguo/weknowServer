package cn.edu.cqut.weknow.service;

import cn.edu.cqut.weknow.po.Feedbacks;
import cn.edu.cqut.weknow.po.FeedbacksExample;
import cn.edu.cqut.weknow.utils.pages.PagedResult;

/*
 * 涂兴国
 * 投诉建议模块service接口文件，参照IAdminsService完成
 */
public interface IFeedbacksService
{	
	PagedResult<Feedbacks> list(FeedbacksExample example, Integer pageNo) throws Exception;
	Feedbacks find(Integer id) throws Exception;
	int add(Feedbacks record)throws Exception;
	int delete(Integer id) throws Exception;
	int update(Feedbacks record) throws Exception;
}
