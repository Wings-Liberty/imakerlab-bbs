package cn.imakerlab.bbs.service;

import cn.imakerlab.bbs.model.po.ContributionMap;

import java.util.List;

public interface ContributionMapService {

    List<ContributionMap> listCalendarsByUserId(int userId);

    /**
     * 通过用户id，和年份，当前年份的所在周查询唯一的一条数据
     * @param year
     * @param week
     * @param userId
     * @return
     */
    ContributionMap getDataByYearAndWeekAndUserId(int year, int week, Integer userId);

    void insertData(int year, int week, Integer userId);

    void updateData(ContributionMap contributionMap);
}
