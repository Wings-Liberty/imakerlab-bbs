package cn.imakerlab.bbs.enums;

import cn.imakerlab.bbs.model.exception.MyException;
import org.apache.ibatis.jdbc.Null;

public enum ArticleTypeEnum {
    FAVOR("favor","likes DESC",false),
    TIME("time","release_time DESC",false),
    QUESTION("question","release_time DESC",true),
    ACTIVITY("activity","release_time DESC",true),
    NOTICE("notice",null,null);

    private String type;
    private String sort;
    private Boolean isArticle;

    ArticleTypeEnum(String type, String sort,Boolean isArticle) {
        this.type=type;
        this.sort=sort;
        this.isArticle=isArticle;
    }

    public static String getSortByType(String type) {
        for (ArticleTypeEnum articleTypeEnum : ArticleTypeEnum.values()) {
            if (articleTypeEnum.getType().equals(type)) {
                return articleTypeEnum.getSort();
            }
        }
        return "";
    }

    public static ArticleTypeEnum getArticleTypeEnumByType(String type) {
        for (ArticleTypeEnum value : ArticleTypeEnum.values()) {
            if (value.getType().equals(type)) {
                return value;
            }
        }
        throw new MyException("该文章类型不存在");
    }

    public Boolean getArticle() {
        return isArticle;
    }

    public void setArticle(Boolean article) {
        isArticle = article;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }
}
