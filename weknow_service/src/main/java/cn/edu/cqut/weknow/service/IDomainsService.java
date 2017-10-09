package cn.edu.cqut.weknow.service;

import cn.edu.cqut.weknow.po.Domains;

import cn.edu.cqut.weknow.po.DomainsExample;

import cn.edu.cqut.weknow.utils.pages.PagedResult;

/*
 * 李海旺
 * 区域管理模块service接口文件，参照IAdminsService完成
 */

public interface IDomainsService {

    PagedResult<Domains> list(DomainsExample example, Integer pageNo) throws Exception;

    Domains find(String id) throws Exception;

    int add(Domains record) throws Exception;

    int delete(String id) throws Exception;
    
    int update(Domains record) throws Exception; 
}