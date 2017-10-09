package cn.edu.cqut.weknow.service.impl;
import org.springframework.beans.factory.annotation.Autowired;
import com.github.pagehelper.PageHelper;
import cn.edu.cqut.weknow.customer.po.WeKnowException;
import cn.edu.cqut.weknow.po.Domains;
import cn.edu.cqut.weknow.po.DomainsExample;
import cn.edu.cqut.weknow.po.mapper.DomainsMapper;
import cn.edu.cqut.weknow.service.IDomainsService;
import cn.edu.cqut.weknow.utils.pages.BeanUtil;
import cn.edu.cqut.weknow.utils.pages.PagedResult;

/*
 * 李海旺
 * 区域管理模块service实现文件，参照AdminsServiceImpl完成
 */
public class DomainsServiceImpl implements IDomainsService {
	
    @Autowired
    private DomainsMapper domainsMapper;

    @Override
    public PagedResult<Domains> list(DomainsExample example, Integer pageNo) throws Exception {
	try {
	    if (pageNo <= -1)
		return BeanUtil.toPagedResult(domainsMapper.selectByExample(example));
	    else {
		pageNo = (pageNo == null) ? 1 : pageNo;
		Integer pageSize = 5;
		PageHelper.startPage(pageNo, pageSize);
		return BeanUtil.toPagedResult(domainsMapper.selectByExample(example));
	    }

	} catch (Exception e) {

	    System.out.println(e.getMessage());
	    throw new WeKnowException(e.getMessage());

	}
    }

    @Override
    public Domains find(String id) throws Exception {
	try {
	    return domainsMapper.selectByPrimaryKey(id);
	} catch (Exception e) {

	    System.out.println(e.getMessage());
	    throw new WeKnowException(e.getMessage());

	}
    }

    @Override
    public int add(Domains record) throws Exception {
	try {

	    return domainsMapper.insert(record);

	} catch (Exception e) {

	    System.out.println(e.getMessage());

	    throw new WeKnowException(e.getMessage());

	}
    }

    @Override
    public int delete(String id) throws Exception {
	try {

	    return domainsMapper.deleteByPrimaryKey(id);

	} catch (Exception e) {

	    System.out.println(e.getMessage());

	    throw new WeKnowException(e.getMessage());
	}
    }

    @Override
    public int update(Domains record) throws Exception {
	try {
	    return domainsMapper.updateByPrimaryKey(record);
	} catch (Exception e) {
	    System.out.println(e.getMessage());
	    throw new WeKnowException(e.getMessage());
	}
    }

}