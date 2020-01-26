package cn.imakerlab.bbs.service;

import cn.imakerlab.bbs.model.po.ContributionMap;

import java.util.List;

public interface ContributionMapService {

    List<ContributionMap> getCalendarByUserId(int id);

}
