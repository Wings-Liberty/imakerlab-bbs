package cn.imakerlab.bbs.service;

public interface LikesService {

    /**
     * 修改点赞表的指定用户对指定文章的点赞情况
     * 如果该用户从没有对该文章进行过点赞操作，那么该方法就执行插入操作
     * @param userId
     * @param articleId
     * @return 如果用户是点赞，返回true；是取消点赞，返回false
     */
    boolean updateLikesByUserIdAndArticleId(Integer userId, Integer articleId);


}
