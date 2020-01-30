package cn.imakerlab.bbs.service.Imp;


import cn.imakerlab.bbs.mapper.ContributionMapDao;
import cn.imakerlab.bbs.model.po.ContributionMap;
import cn.imakerlab.bbs.model.po.ContributionMapExample;
import cn.imakerlab.bbs.service.ContributionMapService;
import cn.imakerlab.bbs.utils.MyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContributionMapServiceImp implements ContributionMapService {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    ContributionMapDao contributionMapDao;

    @Override
    public List<ContributionMap> getCalendarByUserId(int id) {

        ContributionMapExample example = new ContributionMapExample();

        example.createCriteria().andUserIdEqualTo(id);

        List<ContributionMap> list = contributionMapDao.selectByExample(example);

        return list;
    }
}
