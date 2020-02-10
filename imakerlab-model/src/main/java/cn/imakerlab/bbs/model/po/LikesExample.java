package cn.imakerlab.bbs.model.po;

import java.util.ArrayList;
import java.util.List;

public class LikesExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public LikesExample() {
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
            addCriterion("likes.id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("likes.id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("likes.id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("likes.id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("likes.id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("likes.id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("likes.id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("likes.id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("likes.id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("likes.id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("likes.id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("likes.id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNull() {
            addCriterion("likes.user_id is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("likes.user_id is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(Integer value) {
            addCriterion("likes.user_id =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(Integer value) {
            addCriterion("likes.user_id <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(Integer value) {
            addCriterion("likes.user_id >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("likes.user_id >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(Integer value) {
            addCriterion("likes.user_id <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("likes.user_id <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<Integer> values) {
            addCriterion("likes.user_id in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<Integer> values) {
            addCriterion("likes.user_id not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(Integer value1, Integer value2) {
            addCriterion("likes.user_id between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("likes.user_id not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andArticleIdIsNull() {
            addCriterion("likes.article_id is null");
            return (Criteria) this;
        }

        public Criteria andArticleIdIsNotNull() {
            addCriterion("likes.article_id is not null");
            return (Criteria) this;
        }

        public Criteria andArticleIdEqualTo(Integer value) {
            addCriterion("likes.article_id =", value, "articleId");
            return (Criteria) this;
        }

        public Criteria andArticleIdNotEqualTo(Integer value) {
            addCriterion("likes.article_id <>", value, "articleId");
            return (Criteria) this;
        }

        public Criteria andArticleIdGreaterThan(Integer value) {
            addCriterion("likes.article_id >", value, "articleId");
            return (Criteria) this;
        }

        public Criteria andArticleIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("likes.article_id >=", value, "articleId");
            return (Criteria) this;
        }

        public Criteria andArticleIdLessThan(Integer value) {
            addCriterion("likes.article_id <", value, "articleId");
            return (Criteria) this;
        }

        public Criteria andArticleIdLessThanOrEqualTo(Integer value) {
            addCriterion("likes.article_id <=", value, "articleId");
            return (Criteria) this;
        }

        public Criteria andArticleIdIn(List<Integer> values) {
            addCriterion("likes.article_id in", values, "articleId");
            return (Criteria) this;
        }

        public Criteria andArticleIdNotIn(List<Integer> values) {
            addCriterion("likes.article_id not in", values, "articleId");
            return (Criteria) this;
        }

        public Criteria andArticleIdBetween(Integer value1, Integer value2) {
            addCriterion("likes.article_id between", value1, value2, "articleId");
            return (Criteria) this;
        }

        public Criteria andArticleIdNotBetween(Integer value1, Integer value2) {
            addCriterion("likes.article_id not between", value1, value2, "articleId");
            return (Criteria) this;
        }

        public Criteria andIsLikeIsNull() {
            addCriterion("likes.is_like is null");
            return (Criteria) this;
        }

        public Criteria andIsLikeIsNotNull() {
            addCriterion("likes.is_like is not null");
            return (Criteria) this;
        }

        public Criteria andIsLikeEqualTo(Byte value) {
            addCriterion("likes.is_like =", value, "isLike");
            return (Criteria) this;
        }

        public Criteria andIsLikeNotEqualTo(Byte value) {
            addCriterion("likes.is_like <>", value, "isLike");
            return (Criteria) this;
        }

        public Criteria andIsLikeGreaterThan(Byte value) {
            addCriterion("likes.is_like >", value, "isLike");
            return (Criteria) this;
        }

        public Criteria andIsLikeGreaterThanOrEqualTo(Byte value) {
            addCriterion("likes.is_like >=", value, "isLike");
            return (Criteria) this;
        }

        public Criteria andIsLikeLessThan(Byte value) {
            addCriterion("likes.is_like <", value, "isLike");
            return (Criteria) this;
        }

        public Criteria andIsLikeLessThanOrEqualTo(Byte value) {
            addCriterion("likes.is_like <=", value, "isLike");
            return (Criteria) this;
        }

        public Criteria andIsLikeIn(List<Byte> values) {
            addCriterion("likes.is_like in", values, "isLike");
            return (Criteria) this;
        }

        public Criteria andIsLikeNotIn(List<Byte> values) {
            addCriterion("likes.is_like not in", values, "isLike");
            return (Criteria) this;
        }

        public Criteria andIsLikeBetween(Byte value1, Byte value2) {
            addCriterion("likes.is_like between", value1, value2, "isLike");
            return (Criteria) this;
        }

        public Criteria andIsLikeNotBetween(Byte value1, Byte value2) {
            addCriterion("likes.is_like not between", value1, value2, "isLike");
            return (Criteria) this;
        }

        public Criteria andIsCollectedIsNull() {
            addCriterion("likes.is_collected is null");
            return (Criteria) this;
        }

        public Criteria andIsCollectedIsNotNull() {
            addCriterion("likes.is_collected is not null");
            return (Criteria) this;
        }

        public Criteria andIsCollectedEqualTo(Byte value) {
            addCriterion("likes.is_collected =", value, "isCollected");
            return (Criteria) this;
        }

        public Criteria andIsCollectedNotEqualTo(Byte value) {
            addCriterion("likes.is_collected <>", value, "isCollected");
            return (Criteria) this;
        }

        public Criteria andIsCollectedGreaterThan(Byte value) {
            addCriterion("likes.is_collected >", value, "isCollected");
            return (Criteria) this;
        }

        public Criteria andIsCollectedGreaterThanOrEqualTo(Byte value) {
            addCriterion("likes.is_collected >=", value, "isCollected");
            return (Criteria) this;
        }

        public Criteria andIsCollectedLessThan(Byte value) {
            addCriterion("likes.is_collected <", value, "isCollected");
            return (Criteria) this;
        }

        public Criteria andIsCollectedLessThanOrEqualTo(Byte value) {
            addCriterion("likes.is_collected <=", value, "isCollected");
            return (Criteria) this;
        }

        public Criteria andIsCollectedIn(List<Byte> values) {
            addCriterion("likes.is_collected in", values, "isCollected");
            return (Criteria) this;
        }

        public Criteria andIsCollectedNotIn(List<Byte> values) {
            addCriterion("likes.is_collected not in", values, "isCollected");
            return (Criteria) this;
        }

        public Criteria andIsCollectedBetween(Byte value1, Byte value2) {
            addCriterion("likes.is_collected between", value1, value2, "isCollected");
            return (Criteria) this;
        }

        public Criteria andIsCollectedNotBetween(Byte value1, Byte value2) {
            addCriterion("likes.is_collected not between", value1, value2, "isCollected");
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