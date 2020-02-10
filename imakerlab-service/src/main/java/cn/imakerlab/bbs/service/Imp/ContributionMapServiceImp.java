package cn.imakerlab.bbs.service.Imp;


import cn.imakerlab.bbs.mapper.ContributionMapDao;
import cn.imakerlab.bbs.model.po.ContributionMap;
import cn.imakerlab.bbs.model.po.ContributionMapExample;
import cn.imakerlab.bbs.service.ContributionMapService;
import cn.imakerlab.bbs.utils.MyUtils;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Transactional(rollbackFor = Exception.class)
@Service
public class ContributionMapServiceImp implements ContributionMapService {

    @Autowired
    ContributionMapDao contributionMapDao;

    @Override
    public List<ContributionMap> listCalendarsByUserId(int userId) {

        ContributionMapExample example = new ContributionMapExample();

        example.createCriteria().andUserIdEqualTo(userId);

        List<ContributionMap> list = contributionMapDao.selectByExample(example);

        return list;
    }

    @Override
    public ContributionMap getDataByYearAndWeekAndUserId(int year, int week, Integer userId) {

        ContributionMapExample example = new ContributionMapExample();
        example.createCriteria()
                .andYearEqualTo(year)
                .andWeekEqualTo(week)
                .andUserIdEqualTo(userId);

        ContributionMap one = MyUtils.ListToOne(contributionMapDao.selectByExample(example));

        return one;
    }

    @Override
    public void insertData(int year, int week, Integer userId) {

        ContributionMap contributionMap = new ContributionMap();
        contributionMap.setYear(year);
        contributionMap.setWeek(week);
        contributionMap.setUserId(userId);
        contributionMap.setNum(1);

        contributionMapDao.insertSelective(contributionMap);

    }

    @Override
    public void updateData(ContributionMap contributionMap) {

        contributionMap.setNum( contributionMap.getNum() + 1 );

        contributionMapDao.updateByPrimaryKeySelective(contributionMap);

    }
}
