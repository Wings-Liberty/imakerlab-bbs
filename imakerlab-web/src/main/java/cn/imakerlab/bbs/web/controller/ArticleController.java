package cn.imakerlab.bbs.web.controller;

import cn.imakerlab.bbs.constant.ErrorConstant;
import cn.imakerlab.bbs.mapper.UserDao;
import cn.imakerlab.bbs.model.exception.MyException;
import cn.imakerlab.bbs.model.po.Article;
import cn.imakerlab.bbs.model.po.UserExample;
import cn.imakerlab.bbs.model.vo.UserAndArticleAndCommentsVo;
import cn.imakerlab.bbs.model.vo.backContentVo;
import cn.imakerlab.bbs.service.ArticleService;
import cn.imakerlab.bbs.service.Imp.ArticleServiceImp;
import cn.imakerlab.bbs.service.Imp.UserServiceImp;
import cn.imakerlab.bbs.utils.ResultUtils;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Slf4j
@Controller
public class ArticleController {
    @Autowired
    ArticleServiceImp articleServiceImp;

    @Autowired
    UserServiceImp userServiceImp;
    @ResponseBody
    @GetMapping("/article/{type}")
    public ResultUtils getArticlesMsgByType(@PathVariable("type")  String type,@RequestParam("pn")  Integer pn){
        System.out.println(type);
        List<Article> articlesMsgByType = articleServiceImp.getArticlesMsgByType(type);
        for (Article article : articlesMsgByType) {
            System.out.println(article);
        }
        if (articlesMsgByType.size()>5){
            articlesMsgByType.subList(0,5);
        }
        PageHelper.startPage(pn,5);
        PageInfo<Article> articlePageInfo = new PageInfo<>(articlesMsgByType);
        return ResultUtils.success(articlePageInfo);
    }


    @ResponseBody
    @GetMapping("/search")
    public ResultUtils searchMsgByKey(@RequestParam("key") String key) throws Exception {
        if (key != null && key != "") {
            List<backContentVo> backContentVos = articleServiceImp.searchMsgByKey(key);
            if (backContentVos==null||backContentVos.size()==0){
                return ResultUtils.failure(100);
            }
            return ResultUtils.success(backContentVos);
        } else {
            throw new MyException(ErrorConstant.Article.SEARCH_CONTENT_NULL);
        }
    }

    @ResponseBody
    @GetMapping("/label")
    public ResultUtils getLabel(Authentication authentication){
        if (authentication==null){
            throw new MyException("请先登录");
        }
//        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
//        if (authorities.contains("ROLE_ADMIN")){
//
//        }
        String username = authentication.getName();
        String authority = articleServiceImp.getAuthority(username);
        if (authority.equals("ROLE_ADMIN")){
            ArrayList<String> sorts = new ArrayList<>();
            sorts.add("Notices");
            sorts.add("Activities");
            HashMap<String, List<String>> map = new HashMap<>();
            map.put("label",sorts);
            return ResultUtils.success(map);
        }else {
            ArrayList<String> sorts = new ArrayList<>();
            sorts.add("Javascript");
            sorts.add("java");
            sorts.add("php");
            sorts.add("前端");
            sorts.add("c/c++");
            HashMap<String, List<String>> map = new HashMap<>();
            map.put("label",sorts);
            return ResultUtils.success(map);
        }
    }

    @ResponseBody
    @GetMapping("/article/msg/{id}")
    public ResultUtils getDetailMsgOfArticleByArticleId(@PathVariable("id") String id){

        if (Integer.parseInt(id)<=0){
            throw new MyException("文章id不存在");
        }
        UserAndArticleAndCommentsVo detailMsgOfArticleByArticleId = articleServiceImp.getDetailMsgOfArticleByArticleId(Integer.parseInt(id));
        return ResultUtils.success(detailMsgOfArticleByArticleId);
    }

    @ResponseBody
    @DeleteMapping("/article")
    public ResultUtils deleteArticlesByUser(@RequestBody List<Integer> delArticle,Authentication authentication){
        if (authentication==null){
            throw new MyException("请先登录");
        }

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        if (authorities.contains("ROLE_ADMIN")){
            if (delArticle.size()==0){
                throw new MyException("没有选择公告");
            }

            if (delArticle==null){
                throw new MyException("没有选择公告");
            }

            articleServiceImp.deleteNoticesByAdmin(delArticle,authentication.getName());
            return ResultUtils.success();
        }

        if (delArticle.size()==0){
            throw new MyException("没有选择文章");
        }

        if (delArticle==null){
            throw new MyException("没有选择文章");
        }

        articleServiceImp.deleteArticlesByUser(delArticle,authentication.getName());
        return ResultUtils.success();
    }

    @ResponseBody
    @PutMapping("/article")
    public ResultUtils putArticlesByself(@RequestBody JSONObject jsonObject,Authentication authentication){

        if (authentication==null){
            throw new MyException("请先登录");
        }
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        String articleId = jsonObject.getString("articleId");
        String authorId = jsonObject.getString("authorId");
        String text = jsonObject.getString("text");
        String title = jsonObject.getString("title");
        String label = jsonObject.getString("label");

        if (authorities.contains("ROLE_ADMIN")){
            Integer count = articleServiceImp.putNoticesByAdmin(authorId, articleId, text, title, label);
            if (count==0){
                return ResultUtils.failure(101);
            }else  {
                return ResultUtils.success();
            }
        }

        Integer count = articleServiceImp.putArticlesByUser(authorId, articleId, text, title, label);

        if (count==0){
            return ResultUtils.failure(101);
        }else  {
            return ResultUtils.success();
        }

    }
}
