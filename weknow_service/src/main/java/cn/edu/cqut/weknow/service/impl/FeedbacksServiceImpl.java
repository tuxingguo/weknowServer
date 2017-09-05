package cn.edu.cqut.weknow.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.github.pagehelper.PageHelper;

import cn.edu.cqut.weknow.customer.po.WeKnowException;
import cn.edu.cqut.weknow.po.Feedbacks;
import cn.edu.cqut.weknow.po.FeedbacksExample;
import cn.edu.cqut.weknow.po.mapper.FeedbacksMapper;
import cn.edu.cqut.weknow.service.IFeedbacksService;
import cn.edu.cqut.weknow.utils.pages.BeanUtil;
import cn.edu.cqut.weknow.utils.pages.PagedResult;
/*
 * 涂兴国
 * 投诉建议模块service实现文件，参照AdminsServiceImpl完成
 */
public class FeedbacksServiceImpl implements IFeedbacksService
{
	@Autowired
	private FeedbacksMapper feedBackMapper;

	@Override
	public PagedResult<Feedbacks> list(FeedbacksExample example, Integer pageNo) throws Exception {
		
		try {
			if(pageNo<=-1)
				return BeanUtil.toPagedResult(feedBackMapper.selectByExample(example));
			else{
				pageNo = (pageNo == null) ? 1 : pageNo;
				Integer pageSize = 5;
				PageHelper.startPage(pageNo, pageSize); 
				return BeanUtil.toPagedResult(feedBackMapper.selectByExample(example));
			}
		} catch (Exception e) {
			
			System.out.println(e.getMessage());
			throw new WeKnowException(e.getMessage());
		}
	}

	@Override
	public Feedbacks find(Integer id) throws Exception {
		
		try {
			return feedBackMapper.selectByPrimaryKey(id);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new WeKnowException(e.getMessage());
		}
		
	}

	@Override
	public int add(Feedbacks record) throws Exception {
		
		try {
			return feedBackMapper.insert(record);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new WeKnowException(e.getMessage());
		}
	}

	@Override
	public int delete(Integer id) throws Exception {
		
		try {
			return feedBackMapper.deleteByPrimaryKey(id);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new WeKnowException(e.getMessage());
		}
	}

	@Override
	public int update(Feedbacks record) throws Exception {
		
		try {
			return feedBackMapper.updateByPrimaryKey(record);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new WeKnowException(e.getMessage());
		}
	}
}
