package cn.imakerlab.bbs.web.controller;

import cn.imakerlab.bbs.enums.RoleEnum;
import cn.imakerlab.bbs.constant.DefaultConstant;
import cn.imakerlab.bbs.constant.ErrorConstant;
import cn.imakerlab.bbs.enums.FileUploadEnum;
import cn.imakerlab.bbs.enums.ArticleTypeEnum;
import cn.imakerlab.bbs.model.po.Article;
import cn.imakerlab.bbs.model.po.ContributionMap;
import cn.imakerlab.bbs.model.po.User;
import cn.imakerlab.bbs.model.vo.ArticleVo;
import cn.imakerlab.bbs.model.vo.ArticleVoWithComments;

import cn.imakerlab.bbs.model.vo.BackContentVo;
import cn.imakerlab.bbs.model.vo.CommentVo;
import cn.imakerlab.bbs.security.utils.SecurityUtils;
import cn.imakerlab.bbs.service.Imp.*;
import cn.imakerlab.bbs.utils.MyUtils;
import cn.imakerlab.bbs.utils.ResultUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
public class ArticleController {
    @Autowired
    ArticleServiceImp articleService;

    @Autowired
    UserServiceImp userService;

    @Autowired
    LikesServiceImp likesService;

    @Autowired
    CommentServiceImp commentService;

    @Autowired
    ContributionMapServiceImp contributionMapService;

    @ResponseBody
    @GetMapping("/articles/{type}")
    public ResultUtils getArticlesMsgByType(@PathVariable(value = "type")
                                                    String type,
                                            @RequestParam(value = "pn", required = false, defaultValue = "1")
                                                    Integer pn) {

        log.info("查询第" + pn + "页的信息");

        PageHelper.startPage(pn, DefaultConstant.Page.PAGE_SIZE);

        List<ArticleVo> articleVoList = articleService.listArticleVosByType(ArticleTypeEnum.getArticleTypeEnumByType(type));

        PageInfo<ArticleVo> articlePageInfo = new PageInfo<>(articleVoList, DefaultConstant.Page.NAVAGATE_PAGES);

        return ResultUtils.success(articlePageInfo);
    }

    @ResponseBody
    @GetMapping("/search")
    public ResultUtils searchMsgByKey(@RequestParam("key") String key) {
        if (!StringUtils.isEmpty(key)) {

            log.info("得到的关键字是 " + key);

            List<BackContentVo> backContentVoList = articleService.searchMsgByKey(key);

            Map<String, List<BackContentVo>> map = new HashMap<>();
            map.put("set", backContentVoList);

            return ResultUtils.success(map);
        } else {
            return ResultUtils.failure(ErrorConstant.Article.SEARCH_CONTENT_NULL);
        }
    }

    /**
     * 获取标签
     * 如果是普通用户，获得非管理员的标签。管理员用户，获得所有标签
     *
     * @param request
     * @return
     */
    @ResponseBody
    @GetMapping("/label")
    public ResultUtils getLabel(HttpServletRequest request) {

        Integer userId = SecurityUtils.getUserIdFromRequest(request);

        User user = userService.getUserByUserId(userId);

        boolean isAdmin = user.getAuthority().contains(RoleEnum.ROLE_ADMIN.toString());

        List<String> labelList = articleService.getLabels(userId, isAdmin);

        Map<String, List<String>> map = new HashMap<>();
        map.put("label", labelList);

        return ResultUtils.success(map);
    }

    /**
     * 使用文章的id获取文章和评论和评论者的头像信息
     * 把文章和评论的对象封装在ArticleWithComments.class的对象
     *
     * @param articleId
     * @return
     */
    @ResponseBody
    @GetMapping("/article/{id}")
    public ResultUtils getDetailMsgOfArticleByArticleId(@PathVariable("id") int articleId) {

        ArticleVo articleVo = articleService.getNotDeletedArticleVoByArticleId(articleId);

        List<CommentVo> commentVoList = commentService.listCommentVosByArticleId(articleId);

        ArticleVoWithComments articleWithComments = new ArticleVoWithComments(articleVo, commentVoList);

        return ResultUtils.success(articleWithComments);
    }

    /**
     * 批量删除文章
     * 作者的文章数量减少相应的数量，获赞数减少相应的数量
     * 文章的相关评论没有删除
     *
     * @param delArticle
     * @param request
     * @return
     */
    @ResponseBody
    @DeleteMapping("/article")
    public ResultUtils deleteArticlesByUser(@RequestBody List<Integer> delArticle,
                                            HttpServletRequest request) {

        int userId = SecurityUtils.getUserIdFromRequest(request);

        User user = userService.getUserByUserId(userId);

        articleService.deleteArticlesByUserIdAndDelList(delArticle, userId);
        log.info("删除文章");

        // 作者的文章数量减少相应的数量，获赞数减少相应的数量
        List<Article> articleList = articleService.listArticleByList(delArticle);
        user.setArticleNum(user.getArticleNum() - articleList.size());
        for (Article article : articleList) {
            user.setStars(user.getStars() - article.getLikes());
        }

        userService.updateUserByUserPo(user);
        log.info("修改用户总文章数");

        return ResultUtils.success();

    }

    @ResponseBody
    @PutMapping("/article")
    public ResultUtils putArticlesByUser(@RequestParam(value = "articleId") int articleId,
                                         @RequestParam(value = "text") String text,
                                         @RequestParam(value = "title") String title,
                                         @RequestParam(value = "label") String label,
                                         HttpServletRequest request) {

        Integer userId = SecurityUtils.getUserIdFromRequest(request);

        articleService.putArticlesByUser(userId, articleId, text, title, label);

        return ResultUtils.success();

    }

    /**
     * 发布文章
     * 修改用户文章数量
     * 修改热点图
     *
     * @param authorId
     * @param label
     * @param text
     * @param title
     * @param summary
     * @param file
     * @param request
     * @return
     */
    @ResponseBody
    @PostMapping("/article")
    public ResultUtils postArticleByUser(@RequestParam(value = "authorId") Integer authorId,
                                         @RequestParam(value = "label") List<String> label,
                                         @RequestParam(value = "text") String text,
                                         @RequestParam(value = "title") String title,
                                         @RequestParam(value = "summary") String summary,
                                         @RequestParam(required = false) MultipartFile file,
                                         HttpServletRequest request) {
        Integer userId = SecurityUtils.getUserIdFromRequest(request);

        String coverUrl = "";

        //上传图片
        if (file != null) {
            coverUrl = MyUtils.uplode(file, FileUploadEnum.PICTURE);
            log.info("文件路径为" + coverUrl);
        }

        //上传文章
        articleService.postArticleByUser(authorId, label, text, title, summary, coverUrl);

        //修改热点图
        ContributionMap contributionMap = contributionMapService.getDataByYearAndWeekAndUserId(
                LocalDateTime.now().getYear(),
                LocalDateTime.now().getDayOfYear() / 7,
                userId);
        if (contributionMap == null) {
            contributionMapService.insertData(
                    LocalDateTime.now().getYear(),
                    LocalDateTime.now().getDayOfYear() / 7,
                    userId);
        } else {
            contributionMapService.updateData(contributionMap);
        }
        log.info("成功修改热点图");


        //user里的文章总数+1
        userService.updateUserArticleNumAddOneByUserId(userId);
        log.info("user里的文章总数+1");

        return ResultUtils.success();
    }

    /**
     * 上传图片文件
     *
     * @param articleId
     * @param file
     * @param request
     * @return
     */
    @ResponseBody
    @PostMapping("/upload")
    public ResultUtils postImage(@RequestParam("articleId") int articleId,
                                 MultipartFile file,
                                 HttpServletRequest request) {
        int userId = SecurityUtils.getUserIdFromRequest(request);

        String coverUrl = MyUtils.uplode(file, FileUploadEnum.PICTURE);

        articleService.postImage(userId, articleId, coverUrl);

        return ResultUtils.success();
    }

    /**
     * 修改或添加点赞表的数据，修改指定文章表里的点赞数，修改该文章用户表的总获赞数
     *
     * @param articleId
     * @return
     */
    @ResponseBody
    @PutMapping("/article/likes")
    public ResultUtils postLikesByUser(@RequestParam(value = "articleId") Integer articleId,
                                       HttpServletRequest request) {

        int userId = SecurityUtils.getUserIdFromRequest(request);

        // 获取作者的id
        int authorId = articleService.getNotDeletedArticleByArticleId(articleId).getAuthorId();

        // 修改点赞表
        // like为true，表示点赞。false，表示取消点赞
        boolean like = likesService.updateLikesByUserIdAndArticleId(userId, articleId);

        // 修改指定文章的点赞数
        articleService.updateAtricleLikesByArticleId(articleId, like);

        // 修改作者的总获赞数
        userService.updateStarts(authorId, like);

        return ResultUtils.success();

    }

}