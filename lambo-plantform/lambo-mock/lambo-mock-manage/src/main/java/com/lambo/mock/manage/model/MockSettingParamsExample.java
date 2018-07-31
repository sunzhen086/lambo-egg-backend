package com.lambo.mock.manage.model;

import java.util.ArrayList;
import java.util.List;

public class MockSettingParamsExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MockSettingParamsExample() {
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

        public Criteria andParamKeyIsNull() {
            addCriterion("param_key is null");
            return (Criteria) this;
        }

        public Criteria andParamKeyIsNotNull() {
            addCriterion("param_key is not null");
            return (Criteria) this;
        }

        public Criteria andParamKeyEqualTo(String value) {
            addCriterion("param_key =", value, "paramKey");
            return (Criteria) this;
        }

        public Criteria andParamKeyNotEqualTo(String value) {
            addCriterion("param_key <>", value, "paramKey");
            return (Criteria) this;
        }

        public Criteria andParamKeyGreaterThan(String value) {
            addCriterion("param_key >", value, "paramKey");
            return (Criteria) this;
        }

        public Criteria andParamKeyGreaterThanOrEqualTo(String value) {
            addCriterion("param_key >=", value, "paramKey");
            return (Criteria) this;
        }

        public Criteria andParamKeyLessThan(String value) {
            addCriterion("param_key <", value, "paramKey");
            return (Criteria) this;
        }

        public Criteria andParamKeyLessThanOrEqualTo(String value) {
            addCriterion("param_key <=", value, "paramKey");
            return (Criteria) this;
        }

        public Criteria andParamKeyLike(String value) {
            addCriterion("param_key like", value, "paramKey");
            return (Criteria) this;
        }

        public Criteria andParamKeyNotLike(String value) {
            addCriterion("param_key not like", value, "paramKey");
            return (Criteria) this;
        }

        public Criteria andParamKeyIn(List<String> values) {
            addCriterion("param_key in", values, "paramKey");
            return (Criteria) this;
        }

        public Criteria andParamKeyNotIn(List<String> values) {
            addCriterion("param_key not in", values, "paramKey");
            return (Criteria) this;
        }

        public Criteria andParamKeyBetween(String value1, String value2) {
            addCriterion("param_key between", value1, value2, "paramKey");
            return (Criteria) this;
        }

        public Criteria andParamKeyNotBetween(String value1, String value2) {
            addCriterion("param_key not between", value1, value2, "paramKey");
            return (Criteria) this;
        }

        public Criteria andParamTypeIsNull() {
            addCriterion("param_type is null");
            return (Criteria) this;
        }

        public Criteria andParamTypeIsNotNull() {
            addCriterion("param_type is not null");
            return (Criteria) this;
        }

        public Criteria andParamTypeEqualTo(String value) {
            addCriterion("param_type =", value, "paramType");
            return (Criteria) this;
        }

        public Criteria andParamTypeNotEqualTo(String value) {
            addCriterion("param_type <>", value, "paramType");
            return (Criteria) this;
        }

        public Criteria andParamTypeGreaterThan(String value) {
            addCriterion("param_type >", value, "paramType");
            return (Criteria) this;
        }

        public Criteria andParamTypeGreaterThanOrEqualTo(String value) {
            addCriterion("param_type >=", value, "paramType");
            return (Criteria) this;
        }

        public Criteria andParamTypeLessThan(String value) {
            addCriterion("param_type <", value, "paramType");
            return (Criteria) this;
        }

        public Criteria andParamTypeLessThanOrEqualTo(String value) {
            addCriterion("param_type <=", value, "paramType");
            return (Criteria) this;
        }

        public Criteria andParamTypeLike(String value) {
            addCriterion("param_type like", value, "paramType");
            return (Criteria) this;
        }

        public Criteria andParamTypeNotLike(String value) {
            addCriterion("param_type not like", value, "paramType");
            return (Criteria) this;
        }

        public Criteria andParamTypeIn(List<String> values) {
            addCriterion("param_type in", values, "paramType");
            return (Criteria) this;
        }

        public Criteria andParamTypeNotIn(List<String> values) {
            addCriterion("param_type not in", values, "paramType");
            return (Criteria) this;
        }

        public Criteria andParamTypeBetween(String value1, String value2) {
            addCriterion("param_type between", value1, value2, "paramType");
            return (Criteria) this;
        }

        public Criteria andParamTypeNotBetween(String value1, String value2) {
            addCriterion("param_type not between", value1, value2, "paramType");
            return (Criteria) this;
        }

        public Criteria andNecessaryIsNull() {
            addCriterion("necessary is null");
            return (Criteria) this;
        }

        public Criteria andNecessaryIsNotNull() {
            addCriterion("necessary is not null");
            return (Criteria) this;
        }

        public Criteria andNecessaryEqualTo(String value) {
            addCriterion("necessary =", value, "necessary");
            return (Criteria) this;
        }

        public Criteria andNecessaryNotEqualTo(String value) {
            addCriterion("necessary <>", value, "necessary");
            return (Criteria) this;
        }

        public Criteria andNecessaryGreaterThan(String value) {
            addCriterion("necessary >", value, "necessary");
            return (Criteria) this;
        }

        public Criteria andNecessaryGreaterThanOrEqualTo(String value) {
            addCriterion("necessary >=", value, "necessary");
            return (Criteria) this;
        }

        public Criteria andNecessaryLessThan(String value) {
            addCriterion("necessary <", value, "necessary");
            return (Criteria) this;
        }

        public Criteria andNecessaryLessThanOrEqualTo(String value) {
            addCriterion("necessary <=", value, "necessary");
            return (Criteria) this;
        }

        public Criteria andNecessaryLike(String value) {
            addCriterion("necessary like", value, "necessary");
            return (Criteria) this;
        }

        public Criteria andNecessaryNotLike(String value) {
            addCriterion("necessary not like", value, "necessary");
            return (Criteria) this;
        }

        public Criteria andNecessaryIn(List<String> values) {
            addCriterion("necessary in", values, "necessary");
            return (Criteria) this;
        }

        public Criteria andNecessaryNotIn(List<String> values) {
            addCriterion("necessary not in", values, "necessary");
            return (Criteria) this;
        }

        public Criteria andNecessaryBetween(String value1, String value2) {
            addCriterion("necessary between", value1, value2, "necessary");
            return (Criteria) this;
        }

        public Criteria andNecessaryNotBetween(String value1, String value2) {
            addCriterion("necessary not between", value1, value2, "necessary");
            return (Criteria) this;
        }

        public Criteria andNoteIsNull() {
            addCriterion("note is null");
            return (Criteria) this;
        }

        public Criteria andNoteIsNotNull() {
            addCriterion("note is not null");
            return (Criteria) this;
        }

        public Criteria andNoteEqualTo(String value) {
            addCriterion("note =", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteNotEqualTo(String value) {
            addCriterion("note <>", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteGreaterThan(String value) {
            addCriterion("note >", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteGreaterThanOrEqualTo(String value) {
            addCriterion("note >=", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteLessThan(String value) {
            addCriterion("note <", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteLessThanOrEqualTo(String value) {
            addCriterion("note <=", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteLike(String value) {
            addCriterion("note like", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteNotLike(String value) {
            addCriterion("note not like", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteIn(List<String> values) {
            addCriterion("note in", values, "note");
            return (Criteria) this;
        }

        public Criteria andNoteNotIn(List<String> values) {
            addCriterion("note not in", values, "note");
            return (Criteria) this;
        }

        public Criteria andNoteBetween(String value1, String value2) {
            addCriterion("note between", value1, value2, "note");
            return (Criteria) this;
        }

        public Criteria andNoteNotBetween(String value1, String value2) {
            addCriterion("note not between", value1, value2, "note");
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