package cn.edu.cqut.weknow.po.mapper;

import cn.edu.cqut.weknow.po.Domains;
import cn.edu.cqut.weknow.po.DomainsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DomainsMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table domains
     *
     * @mbggenerated Wed Aug 30 08:56:41 CST 2017
     */
    int countByExample(DomainsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table domains
     *
     * @mbggenerated Wed Aug 30 08:56:41 CST 2017
     */
    int deleteByExample(DomainsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table domains
     *
     * @mbggenerated Wed Aug 30 08:56:41 CST 2017
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table domains
     *
     * @mbggenerated Wed Aug 30 08:56:41 CST 2017
     */
    int insert(Domains record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table domains
     *
     * @mbggenerated Wed Aug 30 08:56:41 CST 2017
     */
    int insertSelective(Domains record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table domains
     *
     * @mbggenerated Wed Aug 30 08:56:41 CST 2017
     */
    List<Domains> selectByExample(DomainsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table domains
     *
     * @mbggenerated Wed Aug 30 08:56:41 CST 2017
     */
    Domains selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table domains
     *
     * @mbggenerated Wed Aug 30 08:56:41 CST 2017
     */
    int updateByExampleSelective(@Param("record") Domains record, @Param("example") DomainsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table domains
     *
     * @mbggenerated Wed Aug 30 08:56:41 CST 2017
     */
    int updateByExample(@Param("record") Domains record, @Param("example") DomainsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table domains
     *
     * @mbggenerated Wed Aug 30 08:56:41 CST 2017
     */
    int updateByPrimaryKeySelective(Domains record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table domains
     *
     * @mbggenerated Wed Aug 30 08:56:41 CST 2017
     */
    int updateByPrimaryKey(Domains record);
}