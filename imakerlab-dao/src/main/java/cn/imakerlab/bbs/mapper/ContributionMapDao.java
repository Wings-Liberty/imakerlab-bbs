package cn.imakerlab.bbs.mapper;

import cn.imakerlab.bbs.model.po.ContributionMap;
import cn.imakerlab.bbs.model.po.ContributionMapExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ContributionMapDao {
    long countByExample(ContributionMapExample example);

    int deleteByExample(ContributionMapExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ContributionMap record);

    int insertSelective(ContributionMap record);

    List<ContributionMap> selectByExample(ContributionMapExample example);

    ContributionMap selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ContributionMap record, @Param("example") ContributionMapExample example);

    int updateByExample(@Param("record") ContributionMap record, @Param("example") ContributionMapExample example);

    int updateByPrimaryKeySelective(ContributionMap record);

    int updateByPrimaryKey(ContributionMap record);
}