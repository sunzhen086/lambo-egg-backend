package com.lambo.mock.manage.model;

import java.util.ArrayList;
import java.util.List;

public class MockStruExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MockStruExample() {
        oredCriteria = new ArrayList<Criteria>();
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
            criteria = new ArrayList<Criterion>();
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

        public Criteria andStruIdIsNull() {
            addCriterion("stru_id is null");
            return (Criteria) this;
        }

        public Criteria andStruIdIsNotNull() {
            addCriterion("stru_id is not null");
            return (Criteria) this;
        }

        public Criteria andStruIdEqualTo(String value) {
            addCriterion("stru_id =", value, "struId");
            return (Criteria) this;
        }

        public Criteria andStruIdNotEqualTo(String value) {
            addCriterion("stru_id <>", value, "struId");
            return (Criteria) this;
        }

        public Criteria andStruIdGreaterThan(String value) {
            addCriterion("stru_id >", value, "struId");
            return (Criteria) this;
        }

        public Criteria andStruIdGreaterThanOrEqualTo(String value) {
            addCriterion("stru_id >=", value, "struId");
            return (Criteria) this;
        }

        public Criteria andStruIdLessThan(String value) {
            addCriterion("stru_id <", value, "struId");
            return (Criteria) this;
        }

        public Criteria andStruIdLessThanOrEqualTo(String value) {
            addCriterion("stru_id <=", value, "struId");
            return (Criteria) this;
        }

        public Criteria andStruIdLike(String value) {
            addCriterion("stru_id like", value, "struId");
            return (Criteria) this;
        }

        public Criteria andStruIdNotLike(String value) {
            addCriterion("stru_id not like", value, "struId");
            return (Criteria) this;
        }

        public Criteria andStruIdIn(List<String> values) {
            addCriterion("stru_id in", values, "struId");
            return (Criteria) this;
        }

        public Criteria andStruIdNotIn(List<String> values) {
            addCriterion("stru_id not in", values, "struId");
            return (Criteria) this;
        }

        public Criteria andStruIdBetween(String value1, String value2) {
            addCriterion("stru_id between", value1, value2, "struId");
            return (Criteria) this;
        }

        public Criteria andStruIdNotBetween(String value1, String value2) {
            addCriterion("stru_id not between", value1, value2, "struId");
            return (Criteria) this;
        }

        public Criteria andStruNameIsNull() {
            addCriterion("stru_name is null");
            return (Criteria) this;
        }

        public Criteria andStruNameIsNotNull() {
            addCriterion("stru_name is not null");
            return (Criteria) this;
        }

        public Criteria andStruNameEqualTo(String value) {
            addCriterion("stru_name =", value, "struName");
            return (Criteria) this;
        }

        public Criteria andStruNameNotEqualTo(String value) {
            addCriterion("stru_name <>", value, "struName");
            return (Criteria) this;
        }

        public Criteria andStruNameGreaterThan(String value) {
            addCriterion("stru_name >", value, "struName");
            return (Criteria) this;
        }

        public Criteria andStruNameGreaterThanOrEqualTo(String value) {
            addCriterion("stru_name >=", value, "struName");
            return (Criteria) this;
        }

        public Criteria andStruNameLessThan(String value) {
            addCriterion("stru_name <", value, "struName");
            return (Criteria) this;
        }

        public Criteria andStruNameLessThanOrEqualTo(String value) {
            addCriterion("stru_name <=", value, "struName");
            return (Criteria) this;
        }

        public Criteria andStruNameLike(String value) {
            addCriterion("stru_name like", value, "struName");
            return (Criteria) this;
        }

        public Criteria andStruNameNotLike(String value) {
            addCriterion("stru_name not like", value, "struName");
            return (Criteria) this;
        }

        public Criteria andStruNameIn(List<String> values) {
            addCriterion("stru_name in", values, "struName");
            return (Criteria) this;
        }

        public Criteria andStruNameNotIn(List<String> values) {
            addCriterion("stru_name not in", values, "struName");
            return (Criteria) this;
        }

        public Criteria andStruNameBetween(String value1, String value2) {
            addCriterion("stru_name between", value1, value2, "struName");
            return (Criteria) this;
        }

        public Criteria andStruNameNotBetween(String value1, String value2) {
            addCriterion("stru_name not between", value1, value2, "struName");
            return (Criteria) this;
        }

        public Criteria andStruUrlIsNull() {
            addCriterion("stru_url is null");
            return (Criteria) this;
        }

        public Criteria andStruUrlIsNotNull() {
            addCriterion("stru_url is not null");
            return (Criteria) this;
        }

        public Criteria andStruUrlEqualTo(String value) {
            addCriterion("stru_url =", value, "struUrl");
            return (Criteria) this;
        }

        public Criteria andStruUrlNotEqualTo(String value) {
            addCriterion("stru_url <>", value, "struUrl");
            return (Criteria) this;
        }

        public Criteria andStruUrlGreaterThan(String value) {
            addCriterion("stru_url >", value, "struUrl");
            return (Criteria) this;
        }

        public Criteria andStruUrlGreaterThanOrEqualTo(String value) {
            addCriterion("stru_url >=", value, "struUrl");
            return (Criteria) this;
        }

        public Criteria andStruUrlLessThan(String value) {
            addCriterion("stru_url <", value, "struUrl");
            return (Criteria) this;
        }

        public Criteria andStruUrlLessThanOrEqualTo(String value) {
            addCriterion("stru_url <=", value, "struUrl");
            return (Criteria) this;
        }

        public Criteria andStruUrlLike(String value) {
            addCriterion("stru_url like", value, "struUrl");
            return (Criteria) this;
        }

        public Criteria andStruUrlNotLike(String value) {
            addCriterion("stru_url not like", value, "struUrl");
            return (Criteria) this;
        }

        public Criteria andStruUrlIn(List<String> values) {
            addCriterion("stru_url in", values, "struUrl");
            return (Criteria) this;
        }

        public Criteria andStruUrlNotIn(List<String> values) {
            addCriterion("stru_url not in", values, "struUrl");
            return (Criteria) this;
        }

        public Criteria andStruUrlBetween(String value1, String value2) {
            addCriterion("stru_url between", value1, value2, "struUrl");
            return (Criteria) this;
        }

        public Criteria andStruUrlNotBetween(String value1, String value2) {
            addCriterion("stru_url not between", value1, value2, "struUrl");
            return (Criteria) this;
        }

        public Criteria andStruTypeIsNull() {
            addCriterion("stru_type is null");
            return (Criteria) this;
        }

        public Criteria andStruTypeIsNotNull() {
            addCriterion("stru_type is not null");
            return (Criteria) this;
        }

        public Criteria andStruTypeEqualTo(String value) {
            addCriterion("stru_type =", value, "struType");
            return (Criteria) this;
        }

        public Criteria andStruTypeNotEqualTo(String value) {
            addCriterion("stru_type <>", value, "struType");
            return (Criteria) this;
        }

        public Criteria andStruTypeGreaterThan(String value) {
            addCriterion("stru_type >", value, "struType");
            return (Criteria) this;
        }

        public Criteria andStruTypeGreaterThanOrEqualTo(String value) {
            addCriterion("stru_type >=", value, "struType");
            return (Criteria) this;
        }

        public Criteria andStruTypeLessThan(String value) {
            addCriterion("stru_type <", value, "struType");
            return (Criteria) this;
        }

        public Criteria andStruTypeLessThanOrEqualTo(String value) {
            addCriterion("stru_type <=", value, "struType");
            return (Criteria) this;
        }

        public Criteria andStruTypeLike(String value) {
            addCriterion("stru_type like", value, "struType");
            return (Criteria) this;
        }

        public Criteria andStruTypeNotLike(String value) {
            addCriterion("stru_type not like", value, "struType");
            return (Criteria) this;
        }

        public Criteria andStruTypeIn(List<String> values) {
            addCriterion("stru_type in", values, "struType");
            return (Criteria) this;
        }

        public Criteria andStruTypeNotIn(List<String> values) {
            addCriterion("stru_type not in", values, "struType");
            return (Criteria) this;
        }

        public Criteria andStruTypeBetween(String value1, String value2) {
            addCriterion("stru_type between", value1, value2, "struType");
            return (Criteria) this;
        }

        public Criteria andStruTypeNotBetween(String value1, String value2) {
            addCriterion("stru_type not between", value1, value2, "struType");
            return (Criteria) this;
        }

        public Criteria andMockIdIsNull() {
            addCriterion("mock_id is null");
            return (Criteria) this;
        }

        public Criteria andMockIdIsNotNull() {
            addCriterion("mock_id is not null");
            return (Criteria) this;
        }

        public Criteria andMockIdEqualTo(String value) {
            addCriterion("mock_id =", value, "mockId");
            return (Criteria) this;
        }

        public Criteria andMockIdNotEqualTo(String value) {
            addCriterion("mock_id <>", value, "mockId");
            return (Criteria) this;
        }

        public Criteria andMockIdGreaterThan(String value) {
            addCriterion("mock_id >", value, "mockId");
            return (Criteria) this;
        }

        public Criteria andMockIdGreaterThanOrEqualTo(String value) {
            addCriterion("mock_id >=", value, "mockId");
            return (Criteria) this;
        }

        public Criteria andMockIdLessThan(String value) {
            addCriterion("mock_id <", value, "mockId");
            return (Criteria) this;
        }

        public Criteria andMockIdLessThanOrEqualTo(String value) {
            addCriterion("mock_id <=", value, "mockId");
            return (Criteria) this;
        }

        public Criteria andMockIdLike(String value) {
            addCriterion("mock_id like", value, "mockId");
            return (Criteria) this;
        }

        public Criteria andMockIdNotLike(String value) {
            addCriterion("mock_id not like", value, "mockId");
            return (Criteria) this;
        }

        public Criteria andMockIdIn(List<String> values) {
            addCriterion("mock_id in", values, "mockId");
            return (Criteria) this;
        }

        public Criteria andMockIdNotIn(List<String> values) {
            addCriterion("mock_id not in", values, "mockId");
            return (Criteria) this;
        }

        public Criteria andMockIdBetween(String value1, String value2) {
            addCriterion("mock_id between", value1, value2, "mockId");
            return (Criteria) this;
        }

        public Criteria andMockIdNotBetween(String value1, String value2) {
            addCriterion("mock_id not between", value1, value2, "mockId");
            return (Criteria) this;
        }

        public Criteria andParentIdIsNull() {
            addCriterion("parent_id is null");
            return (Criteria) this;
        }

        public Criteria andParentIdIsNotNull() {
            addCriterion("parent_id is not null");
            return (Criteria) this;
        }

        public Criteria andParentIdEqualTo(String value) {
            addCriterion("parent_id =", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdNotEqualTo(String value) {
            addCriterion("parent_id <>", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdGreaterThan(String value) {
            addCriterion("parent_id >", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdGreaterThanOrEqualTo(String value) {
            addCriterion("parent_id >=", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdLessThan(String value) {
            addCriterion("parent_id <", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdLessThanOrEqualTo(String value) {
            addCriterion("parent_id <=", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdLike(String value) {
            addCriterion("parent_id like", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdNotLike(String value) {
            addCriterion("parent_id not like", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdIn(List<String> values) {
            addCriterion("parent_id in", values, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdNotIn(List<String> values) {
            addCriterion("parent_id not in", values, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdBetween(String value1, String value2) {
            addCriterion("parent_id between", value1, value2, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdNotBetween(String value1, String value2) {
            addCriterion("parent_id not between", value1, value2, "parentId");
            return (Criteria) this;
        }

        public Criteria andIsUseIsNull() {
            addCriterion("is_use is null");
            return (Criteria) this;
        }

        public Criteria andIsUseIsNotNull() {
            addCriterion("is_use is not null");
            return (Criteria) this;
        }

        public Criteria andIsUseEqualTo(String value) {
            addCriterion("is_use =", value, "isUse");
            return (Criteria) this;
        }

        public Criteria andIsUseNotEqualTo(String value) {
            addCriterion("is_use <>", value, "isUse");
            return (Criteria) this;
        }

        public Criteria andIsUseGreaterThan(String value) {
            addCriterion("is_use >", value, "isUse");
            return (Criteria) this;
        }

        public Criteria andIsUseGreaterThanOrEqualTo(String value) {
            addCriterion("is_use >=", value, "isUse");
            return (Criteria) this;
        }

        public Criteria andIsUseLessThan(String value) {
            addCriterion("is_use <", value, "isUse");
            return (Criteria) this;
        }

        public Criteria andIsUseLessThanOrEqualTo(String value) {
            addCriterion("is_use <=", value, "isUse");
            return (Criteria) this;
        }

        public Criteria andIsUseLike(String value) {
            addCriterion("is_use like", value, "isUse");
            return (Criteria) this;
        }

        public Criteria andIsUseNotLike(String value) {
            addCriterion("is_use not like", value, "isUse");
            return (Criteria) this;
        }

        public Criteria andIsUseIn(List<String> values) {
            addCriterion("is_use in", values, "isUse");
            return (Criteria) this;
        }

        public Criteria andIsUseNotIn(List<String> values) {
            addCriterion("is_use not in", values, "isUse");
            return (Criteria) this;
        }

        public Criteria andIsUseBetween(String value1, String value2) {
            addCriterion("is_use between", value1, value2, "isUse");
            return (Criteria) this;
        }

        public Criteria andIsUseNotBetween(String value1, String value2) {
            addCriterion("is_use not between", value1, value2, "isUse");
            return (Criteria) this;
        }

        public Criteria andDevStatusIsNull() {
            addCriterion("dev_status is null");
            return (Criteria) this;
        }

        public Criteria andDevStatusIsNotNull() {
            addCriterion("dev_status is not null");
            return (Criteria) this;
        }

        public Criteria andDevStatusEqualTo(String value) {
            addCriterion("dev_status =", value, "devStatus");
            return (Criteria) this;
        }

        public Criteria andDevStatusNotEqualTo(String value) {
            addCriterion("dev_status <>", value, "devStatus");
            return (Criteria) this;
        }

        public Criteria andDevStatusGreaterThan(String value) {
            addCriterion("dev_status >", value, "devStatus");
            return (Criteria) this;
        }

        public Criteria andDevStatusGreaterThanOrEqualTo(String value) {
            addCriterion("dev_status >=", value, "devStatus");
            return (Criteria) this;
        }

        public Criteria andDevStatusLessThan(String value) {
            addCriterion("dev_status <", value, "devStatus");
            return (Criteria) this;
        }

        public Criteria andDevStatusLessThanOrEqualTo(String value) {
            addCriterion("dev_status <=", value, "devStatus");
            return (Criteria) this;
        }

        public Criteria andDevStatusLike(String value) {
            addCriterion("dev_status like", value, "devStatus");
            return (Criteria) this;
        }

        public Criteria andDevStatusNotLike(String value) {
            addCriterion("dev_status not like", value, "devStatus");
            return (Criteria) this;
        }

        public Criteria andDevStatusIn(List<String> values) {
            addCriterion("dev_status in", values, "devStatus");
            return (Criteria) this;
        }

        public Criteria andDevStatusNotIn(List<String> values) {
            addCriterion("dev_status not in", values, "devStatus");
            return (Criteria) this;
        }

        public Criteria andDevStatusBetween(String value1, String value2) {
            addCriterion("dev_status between", value1, value2, "devStatus");
            return (Criteria) this;
        }

        public Criteria andDevStatusNotBetween(String value1, String value2) {
            addCriterion("dev_status not between", value1, value2, "devStatus");
            return (Criteria) this;
        }

        public Criteria andOrderSeqIsNull() {
            addCriterion("order_seq is null");
            return (Criteria) this;
        }

        public Criteria andOrderSeqIsNotNull() {
            addCriterion("order_seq is not null");
            return (Criteria) this;
        }

        public Criteria andOrderSeqEqualTo(Integer value) {
            addCriterion("order_seq =", value, "orderSeq");
            return (Criteria) this;
        }

        public Criteria andOrderSeqNotEqualTo(Integer value) {
            addCriterion("order_seq <>", value, "orderSeq");
            return (Criteria) this;
        }

        public Criteria andOrderSeqGreaterThan(Integer value) {
            addCriterion("order_seq >", value, "orderSeq");
            return (Criteria) this;
        }

        public Criteria andOrderSeqGreaterThanOrEqualTo(Integer value) {
            addCriterion("order_seq >=", value, "orderSeq");
            return (Criteria) this;
        }

        public Criteria andOrderSeqLessThan(Integer value) {
            addCriterion("order_seq <", value, "orderSeq");
            return (Criteria) this;
        }

        public Criteria andOrderSeqLessThanOrEqualTo(Integer value) {
            addCriterion("order_seq <=", value, "orderSeq");
            return (Criteria) this;
        }

        public Criteria andOrderSeqIn(List<Integer> values) {
            addCriterion("order_seq in", values, "orderSeq");
            return (Criteria) this;
        }

        public Criteria andOrderSeqNotIn(List<Integer> values) {
            addCriterion("order_seq not in", values, "orderSeq");
            return (Criteria) this;
        }

        public Criteria andOrderSeqBetween(Integer value1, Integer value2) {
            addCriterion("order_seq between", value1, value2, "orderSeq");
            return (Criteria) this;
        }

        public Criteria andOrderSeqNotBetween(Integer value1, Integer value2) {
            addCriterion("order_seq not between", value1, value2, "orderSeq");
            return (Criteria) this;
        }
    }

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