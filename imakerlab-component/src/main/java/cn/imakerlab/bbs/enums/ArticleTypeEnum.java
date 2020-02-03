package cn.imakerlab.bbs.enums;

public enum ArticleTypeEnum {
    FAVOR("favor","likes DESC"),
    TIME("time","release_time DESC"),
    QUESTION("question","release_time DESC"),
    ACTIVITY("activity","release_time DESC");

    private String type;
    private String sort;

    ArticleTypeEnum(String type, String sort) {
        this.type=type;
        this.sort=sort;
    }

    public static String getSortByByte(String type) {
        for (ArticleTypeEnum articleTypeEnum : ArticleTypeEnum.values()) {
            if (articleTypeEnum.getType().equals(type)) {
                return articleTypeEnum.getSort();
            }
        }
        return "";
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
