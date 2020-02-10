package cn.imakerlab.bbs.service.Imp;

import cn.imakerlab.bbs.mapper.LikesDao;
import cn.imakerlab.bbs.model.po.Likes;
import cn.imakerlab.bbs.model.po.LikesExample;
import cn.imakerlab.bbs.service.LikesService;
import cn.imakerlab.bbs.utils.MyUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class LikesServiceImp implements LikesService {

    @Autowired
    LikesDao likesDao;

    @Override
    public boolean updateLikesByUserIdAndArticleId(Integer userId, Integer articleId) {
        boolean flag = true;

        LikesExample example = new LikesExample();
        example.createCriteria()
                .andArticleIdEqualTo(articleId)
                .andUserIdEqualTo(userId);

        Likes likes = MyUtils.ListToOne(likesDao.selectByExample(example));

        if (likes == null) {
            likes = new Likes();
            likes.setArticleId(articleId);
            likes.setUserId(userId);
            likes.setIsLike((byte) 1);

            likesDao.insertSelective(likes);

            log.info("点赞成功");

        } else {

            if (likes.getIsLike() == 1) {
                likes.setIsLike((byte) 0);
                flag = false;
                log.info("取消点赞成功");
            } else {
                likes.setIsLike((byte) 1);
                log.info("点赞成功");
            }

            likesDao.updateByPrimaryKeySelective(likes);
        }

        return flag;
    }
}