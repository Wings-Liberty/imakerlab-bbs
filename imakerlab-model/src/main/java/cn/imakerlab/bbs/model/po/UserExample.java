package cn.imakerlab.bbs.model.po;

import java.util.ArrayList;
import java.util.List;

public class UserExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public UserExample() {
        oredCriteria = new ArrayList<>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("user.id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("user.id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("user.id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("user.id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("user.id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("user.id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("user.id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("user.id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("user.id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("user.id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("user.id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("user.id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andUsernameIsNull() {
            addCriterion("user.username is null");
            return (Criteria) this;
        }

        public Criteria andUsernameIsNotNull() {
            addCriterion("user.username is not null");
            return (Criteria) this;
        }

        public Criteria andUsernameEqualTo(String value) {
            addCriterion("user.username =", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotEqualTo(String value) {
            addCriterion("user.username <>", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameGreaterThan(String value) {
            addCriterion("user.username >", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameGreaterThanOrEqualTo(String value) {
            addCriterion("user.username >=", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameLessThan(String value) {
            addCriterion("user.username <", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameLessThanOrEqualTo(String value) {
            addCriterion("user.username <=", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameLike(String value) {
            addCriterion("user.username like", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotLike(String value) {
            addCriterion("user.username not like", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameIn(List<String> values) {
            addCriterion("user.username in", values, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotIn(List<String> values) {
            addCriterion("user.username not in", values, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameBetween(String value1, String value2) {
            addCriterion("user.username between", value1, value2, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotBetween(String value1, String value2) {
            addCriterion("user.username not between", value1, value2, "username");
            return (Criteria) this;
        }

        public Criteria andPasswordIsNull() {
            addCriterion("user.`password` is null");
            return (Criteria) this;
        }

        public Criteria andPasswordIsNotNull() {
            addCriterion("user.`password` is not null");
            return (Criteria) this;
        }

        public Criteria andPasswordEqualTo(String value) {
            addCriterion("user.`password` =", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotEqualTo(String value) {
            addCriterion("user.`password` <>", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordGreaterThan(String value) {
            addCriterion("user.`password` >", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordGreaterThanOrEqualTo(String value) {
            addCriterion("user.`password` >=", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLessThan(String value) {
            addCriterion("user.`password` <", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLessThanOrEqualTo(String value) {
            addCriterion("user.`password` <=", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLike(String value) {
            addCriterion("user.`password` like", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotLike(String value) {
            addCriterion("user.`password` not like", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordIn(List<String> values) {
            addCriterion("user.`password` in", values, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotIn(List<String> values) {
            addCriterion("user.`password` not in", values, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordBetween(String value1, String value2) {
            addCriterion("user.`password` between", value1, value2, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotBetween(String value1, String value2) {
            addCriterion("user.`password` not between", value1, value2, "password");
            return (Criteria) this;
        }

        public Criteria andFigureUrlIsNull() {
            addCriterion("user.figure_url is null");
            return (Criteria) this;
        }

        public Criteria andFigureUrlIsNotNull() {
            addCriterion("user.figure_url is not null");
            return (Criteria) this;
        }

        public Criteria andFigureUrlEqualTo(String value) {
            addCriterion("user.figure_url =", value, "figureUrl");
            return (Criteria) this;
        }

        public Criteria andFigureUrlNotEqualTo(String value) {
            addCriterion("user.figure_url <>", value, "figureUrl");
            return (Criteria) this;
        }

        public Criteria andFigureUrlGreaterThan(String value) {
            addCriterion("user.figure_url >", value, "figureUrl");
            return (Criteria) this;
        }

        public Criteria andFigureUrlGreaterThanOrEqualTo(String value) {
            addCriterion("user.figure_url >=", value, "figureUrl");
            return (Criteria) this;
        }

        public Criteria andFigureUrlLessThan(String value) {
            addCriterion("user.figure_url <", value, "figureUrl");
            return (Criteria) this;
        }

        public Criteria andFigureUrlLessThanOrEqualTo(String value) {
            addCriterion("user.figure_url <=", value, "figureUrl");
            return (Criteria) this;
        }

        public Criteria andFigureUrlLike(String value) {
            addCriterion("user.figure_url like", value, "figureUrl");
            return (Criteria) this;
        }

        public Criteria andFigureUrlNotLike(String value) {
            addCriterion("user.figure_url not like", value, "figureUrl");
            return (Criteria) this;
        }

        public Criteria andFigureUrlIn(List<String> values) {
            addCriterion("user.figure_url in", values, "figureUrl");
            return (Criteria) this;
        }

        public Criteria andFigureUrlNotIn(List<String> values) {
            addCriterion("user.figure_url not in", values, "figureUrl");
            return (Criteria) this;
        }

        public Criteria andFigureUrlBetween(String value1, String value2) {
            addCriterion("user.figure_url between", value1, value2, "figureUrl");
            return (Criteria) this;
        }

        public Criteria andFigureUrlNotBetween(String value1, String value2) {
            addCriterion("user.figure_url not between", value1, value2, "figureUrl");
            return (Criteria) this;
        }

        public Criteria andSloganIsNull() {
            addCriterion("user.slogan is null");
            return (Criteria) this;
        }

        public Criteria andSloganIsNotNull() {
            addCriterion("user.slogan is not null");
            return (Criteria) this;
        }

        public Criteria andSloganEqualTo(String value) {
            addCriterion("user.slogan =", value, "slogan");
            return (Criteria) this;
        }

        public Criteria andSloganNotEqualTo(String value) {
            addCriterion("user.slogan <>", value, "slogan");
            return (Criteria) this;
        }

        public Criteria andSloganGreaterThan(String value) {
            addCriterion("user.slogan >", value, "slogan");
            return (Criteria) this;
        }

        public Criteria andSloganGreaterThanOrEqualTo(String value) {
            addCriterion("user.slogan >=", value, "slogan");
            return (Criteria) this;
        }

        public Criteria andSloganLessThan(String value) {
            addCriterion("user.slogan <", value, "slogan");
            return (Criteria) this;
        }

        public Criteria andSloganLessThanOrEqualTo(String value) {
            addCriterion("user.slogan <=", value, "slogan");
            return (Criteria) this;
        }

        public Criteria andSloganLike(String value) {
            addCriterion("user.slogan like", value, "slogan");
            return (Criteria) this;
        }

        public Criteria andSloganNotLike(String value) {
            addCriterion("user.slogan not like", value, "slogan");
            return (Criteria) this;
        }

        public Criteria andSloganIn(List<String> values) {
            addCriterion("user.slogan in", values, "slogan");
            return (Criteria) this;
        }

        public Criteria andSloganNotIn(List<String> values) {
            addCriterion("user.slogan not in", values, "slogan");
            return (Criteria) this;
        }

        public Criteria andSloganBetween(String value1, String value2) {
            addCriterion("user.slogan between", value1, value2, "slogan");
            return (Criteria) this;
        }

        public Criteria andSloganNotBetween(String value1, String value2) {
            addCriterion("user.slogan not between", value1, value2, "slogan");
            return (Criteria) this;
        }

        public Criteria andStarsIsNull() {
            addCriterion("user.stars is null");
            return (Criteria) this;
        }

        public Criteria andStarsIsNotNull() {
            addCriterion("user.stars is not null");
            return (Criteria) this;
        }

        public Criteria andStarsEqualTo(Integer value) {
            addCriterion("user.stars =", value, "stars");
            return (Criteria) this;
        }

        public Criteria andStarsNotEqualTo(Integer value) {
            addCriterion("user.stars <>", value, "stars");
            return (Criteria) this;
        }

        public Criteria andStarsGreaterThan(Integer value) {
            addCriterion("user.stars >", value, "stars");
            return (Criteria) this;
        }

        public Criteria andStarsGreaterThanOrEqualTo(Integer value) {
            addCriterion("user.stars >=", value, "stars");
            return (Criteria) this;
        }

        public Criteria andStarsLessThan(Integer value) {
            addCriterion("user.stars <", value, "stars");
            return (Criteria) this;
        }

        public Criteria andStarsLessThanOrEqualTo(Integer value) {
            addCriterion("user.stars <=", value, "stars");
            return (Criteria) this;
        }

        public Criteria andStarsIn(List<Integer> values) {
            addCriterion("user.stars in", values, "stars");
            return (Criteria) this;
        }

        public Criteria andStarsNotIn(List<Integer> values) {
            addCriterion("user.stars not in", values, "stars");
            return (Criteria) this;
        }

        public Criteria andStarsBetween(Integer value1, Integer value2) {
            addCriterion("user.stars between", value1, value2, "stars");
            return (Criteria) this;
        }

        public Criteria andStarsNotBetween(Integer value1, Integer value2) {
            addCriterion("user.stars not between", value1, value2, "stars");
            return (Criteria) this;
        }

        public Criteria andAuthorityIsNull() {
            addCriterion("user.authority is null");
            return (Criteria) this;
        }

        public Criteria andAuthorityIsNotNull() {
            addCriterion("user.authority is not null");
            return (Criteria) this;
        }

        public Criteria andAuthorityEqualTo(String value) {
            addCriterion("user.authority =", value, "authority");
            return (Criteria) this;
        }

        public Criteria andAuthorityNotEqualTo(String value) {
            addCriterion("user.authority <>", value, "authority");
            return (Criteria) this;
        }

        public Criteria andAuthorityGreaterThan(String value) {
            addCriterion("user.authority >", value, "authority");
            return (Criteria) this;
        }

        public Criteria andAuthorityGreaterThanOrEqualTo(String value) {
            addCriterion("user.authority >=", value, "authority");
            return (Criteria) this;
        }

        public Criteria andAuthorityLessThan(String value) {
            addCriterion("user.authority <", value, "authority");
            return (Criteria) this;
        }

        public Criteria andAuthorityLessThanOrEqualTo(String value) {
            addCriterion("user.authority <=", value, "authority");
            return (Criteria) this;
        }

        public Criteria andAuthorityLike(String value) {
            addCriterion("user.authority like", value, "authority");
            return (Criteria) this;
        }

        public Criteria andAuthorityNotLike(String value) {
            addCriterion("user.authority not like", value, "authority");
            return (Criteria) this;
        }

        public Criteria andAuthorityIn(List<String> values) {
            addCriterion("user.authority in", values, "authority");
            return (Criteria) this;
        }

        public Criteria andAuthorityNotIn(List<String> values) {
            addCriterion("user.authority not in", values, "authority");
            return (Criteria) this;
        }

        public Criteria andAuthorityBetween(String value1, String value2) {
            addCriterion("user.authority between", value1, value2, "authority");
            return (Criteria) this;
        }

        public Criteria andAuthorityNotBetween(String value1, String value2) {
            addCriterion("user.authority not between", value1, value2, "authority");
            return (Criteria) this;
        }

        public Criteria andIsDeletedIsNull() {
            addCriterion("user.is_deleted is null");
            return (Criteria) this;
        }

        public Criteria andIsDeletedIsNotNull() {
            addCriterion("user.is_deleted is not null");
            return (Criteria) this;
        }

        public Criteria andIsDeletedEqualTo(Byte value) {
            addCriterion("user.is_deleted =", value, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedNotEqualTo(Byte value) {
            addCriterion("user.is_deleted <>", value, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedGreaterThan(Byte value) {
            addCriterion("user.is_deleted >", value, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedGreaterThanOrEqualTo(Byte value) {
            addCriterion("user.is_deleted >=", value, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedLessThan(Byte value) {
            addCriterion("user.is_deleted <", value, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedLessThanOrEqualTo(Byte value) {
            addCriterion("user.is_deleted <=", value, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedIn(List<Byte> values) {
            addCriterion("user.is_deleted in", values, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedNotIn(List<Byte> values) {
            addCriterion("user.is_deleted not in", values, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedBetween(Byte value1, Byte value2) {
            addCriterion("user.is_deleted between", value1, value2, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedNotBetween(Byte value1, Byte value2) {
            addCriterion("user.is_deleted not between", value1, value2, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andArticleNumIsNull() {
            addCriterion("user.article_num is null");
            return (Criteria) this;
        }

        public Criteria andArticleNumIsNotNull() {
            addCriterion("user.article_num is not null");
            return (Criteria) this;
        }

        public Criteria andArticleNumEqualTo(Integer value) {
            addCriterion("user.article_num =", value, "articleNum");
            return (Criteria) this;
        }

        public Criteria andArticleNumNotEqualTo(Integer value) {
            addCriterion("user.article_num <>", value, "articleNum");
            return (Criteria) this;
        }

        public Criteria andArticleNumGreaterThan(Integer value) {
            addCriterion("user.article_num >", value, "articleNum");
            return (Criteria) this;
        }

        public Criteria andArticleNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("user.article_num >=", value, "articleNum");
            return (Criteria) this;
        }

        public Criteria andArticleNumLessThan(Integer value) {
            addCriterion("user.article_num <", value, "articleNum");
            return (Criteria) this;
        }

        public Criteria andArticleNumLessThanOrEqualTo(Integer value) {
            addCriterion("user.article_num <=", value, "articleNum");
            return (Criteria) this;
        }

        public Criteria andArticleNumIn(List<Integer> values) {
            addCriterion("user.article_num in", values, "articleNum");
            return (Criteria) this;
        }

        public Criteria andArticleNumNotIn(List<Integer> values) {
            addCriterion("user.article_num not in", values, "articleNum");
            return (Criteria) this;
        }

        public Criteria andArticleNumBetween(Integer value1, Integer value2) {
            addCriterion("user.article_num between", value1, value2, "articleNum");
            return (Criteria) this;
        }

        public Criteria andArticleNumNotBetween(Integer value1, Integer value2) {
            addCriterion("user.article_num not between", value1, value2, "articleNum");
            return (Criteria) this;
        }
    }

    /**
     */
    public static class Criteria extends GeneratedCriteria {
        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}