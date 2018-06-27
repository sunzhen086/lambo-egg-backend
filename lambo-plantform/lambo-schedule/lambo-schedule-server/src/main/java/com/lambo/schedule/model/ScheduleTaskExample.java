package com.lambo.schedule.model;

import java.util.ArrayList;
import java.util.List;

public class ScheduleTaskExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ScheduleTaskExample() {
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

        public Criteria andTaskIdIsNull() {
            addCriterion("TASK_ID is null");
            return (Criteria) this;
        }

        public Criteria andTaskIdIsNotNull() {
            addCriterion("TASK_ID is not null");
            return (Criteria) this;
        }

        public Criteria andTaskIdEqualTo(Integer value) {
            addCriterion("TASK_ID =", value, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdNotEqualTo(Integer value) {
            addCriterion("TASK_ID <>", value, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdGreaterThan(Integer value) {
            addCriterion("TASK_ID >", value, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("TASK_ID >=", value, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdLessThan(Integer value) {
            addCriterion("TASK_ID <", value, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdLessThanOrEqualTo(Integer value) {
            addCriterion("TASK_ID <=", value, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdIn(List<Integer> values) {
            addCriterion("TASK_ID in", values, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdNotIn(List<Integer> values) {
            addCriterion("TASK_ID not in", values, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdBetween(Integer value1, Integer value2) {
            addCriterion("TASK_ID between", value1, value2, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdNotBetween(Integer value1, Integer value2) {
            addCriterion("TASK_ID not between", value1, value2, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskNameIsNull() {
            addCriterion("TASK_NAME is null");
            return (Criteria) this;
        }

        public Criteria andTaskNameIsNotNull() {
            addCriterion("TASK_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andTaskNameEqualTo(String value) {
            addCriterion("TASK_NAME =", value, "taskName");
            return (Criteria) this;
        }

        public Criteria andTaskNameNotEqualTo(String value) {
            addCriterion("TASK_NAME <>", value, "taskName");
            return (Criteria) this;
        }

        public Criteria andTaskNameGreaterThan(String value) {
            addCriterion("TASK_NAME >", value, "taskName");
            return (Criteria) this;
        }

        public Criteria andTaskNameGreaterThanOrEqualTo(String value) {
            addCriterion("TASK_NAME >=", value, "taskName");
            return (Criteria) this;
        }

        public Criteria andTaskNameLessThan(String value) {
            addCriterion("TASK_NAME <", value, "taskName");
            return (Criteria) this;
        }

        public Criteria andTaskNameLessThanOrEqualTo(String value) {
            addCriterion("TASK_NAME <=", value, "taskName");
            return (Criteria) this;
        }

        public Criteria andTaskNameLike(String value) {
            addCriterion("TASK_NAME like", value, "taskName");
            return (Criteria) this;
        }

        public Criteria andTaskNameNotLike(String value) {
            addCriterion("TASK_NAME not like", value, "taskName");
            return (Criteria) this;
        }

        public Criteria andTaskNameIn(List<String> values) {
            addCriterion("TASK_NAME in", values, "taskName");
            return (Criteria) this;
        }

        public Criteria andTaskNameNotIn(List<String> values) {
            addCriterion("TASK_NAME not in", values, "taskName");
            return (Criteria) this;
        }

        public Criteria andTaskNameBetween(String value1, String value2) {
            addCriterion("TASK_NAME between", value1, value2, "taskName");
            return (Criteria) this;
        }

        public Criteria andTaskNameNotBetween(String value1, String value2) {
            addCriterion("TASK_NAME not between", value1, value2, "taskName");
            return (Criteria) this;
        }

        public Criteria andMonthIsNull() {
            addCriterion("MONTH is null");
            return (Criteria) this;
        }

        public Criteria andMonthIsNotNull() {
            addCriterion("MONTH is not null");
            return (Criteria) this;
        }

        public Criteria andMonthEqualTo(String value) {
            addCriterion("MONTH =", value, "month");
            return (Criteria) this;
        }

        public Criteria andMonthNotEqualTo(String value) {
            addCriterion("MONTH <>", value, "month");
            return (Criteria) this;
        }

        public Criteria andMonthGreaterThan(String value) {
            addCriterion("MONTH >", value, "month");
            return (Criteria) this;
        }

        public Criteria andMonthGreaterThanOrEqualTo(String value) {
            addCriterion("MONTH >=", value, "month");
            return (Criteria) this;
        }

        public Criteria andMonthLessThan(String value) {
            addCriterion("MONTH <", value, "month");
            return (Criteria) this;
        }

        public Criteria andMonthLessThanOrEqualTo(String value) {
            addCriterion("MONTH <=", value, "month");
            return (Criteria) this;
        }

        public Criteria andMonthLike(String value) {
            addCriterion("MONTH like", value, "month");
            return (Criteria) this;
        }

        public Criteria andMonthNotLike(String value) {
            addCriterion("MONTH not like", value, "month");
            return (Criteria) this;
        }

        public Criteria andMonthIn(List<String> values) {
            addCriterion("MONTH in", values, "month");
            return (Criteria) this;
        }

        public Criteria andMonthNotIn(List<String> values) {
            addCriterion("MONTH not in", values, "month");
            return (Criteria) this;
        }

        public Criteria andMonthBetween(String value1, String value2) {
            addCriterion("MONTH between", value1, value2, "month");
            return (Criteria) this;
        }

        public Criteria andMonthNotBetween(String value1, String value2) {
            addCriterion("MONTH not between", value1, value2, "month");
            return (Criteria) this;
        }

        public Criteria andDayofmonthIsNull() {
            addCriterion("DAYOFMONTH is null");
            return (Criteria) this;
        }

        public Criteria andDayofmonthIsNotNull() {
            addCriterion("DAYOFMONTH is not null");
            return (Criteria) this;
        }

        public Criteria andDayofmonthEqualTo(String value) {
            addCriterion("DAYOFMONTH =", value, "dayofmonth");
            return (Criteria) this;
        }

        public Criteria andDayofmonthNotEqualTo(String value) {
            addCriterion("DAYOFMONTH <>", value, "dayofmonth");
            return (Criteria) this;
        }

        public Criteria andDayofmonthGreaterThan(String value) {
            addCriterion("DAYOFMONTH >", value, "dayofmonth");
            return (Criteria) this;
        }

        public Criteria andDayofmonthGreaterThanOrEqualTo(String value) {
            addCriterion("DAYOFMONTH >=", value, "dayofmonth");
            return (Criteria) this;
        }

        public Criteria andDayofmonthLessThan(String value) {
            addCriterion("DAYOFMONTH <", value, "dayofmonth");
            return (Criteria) this;
        }

        public Criteria andDayofmonthLessThanOrEqualTo(String value) {
            addCriterion("DAYOFMONTH <=", value, "dayofmonth");
            return (Criteria) this;
        }

        public Criteria andDayofmonthLike(String value) {
            addCriterion("DAYOFMONTH like", value, "dayofmonth");
            return (Criteria) this;
        }

        public Criteria andDayofmonthNotLike(String value) {
            addCriterion("DAYOFMONTH not like", value, "dayofmonth");
            return (Criteria) this;
        }

        public Criteria andDayofmonthIn(List<String> values) {
            addCriterion("DAYOFMONTH in", values, "dayofmonth");
            return (Criteria) this;
        }

        public Criteria andDayofmonthNotIn(List<String> values) {
            addCriterion("DAYOFMONTH not in", values, "dayofmonth");
            return (Criteria) this;
        }

        public Criteria andDayofmonthBetween(String value1, String value2) {
            addCriterion("DAYOFMONTH between", value1, value2, "dayofmonth");
            return (Criteria) this;
        }

        public Criteria andDayofmonthNotBetween(String value1, String value2) {
            addCriterion("DAYOFMONTH not between", value1, value2, "dayofmonth");
            return (Criteria) this;
        }

        public Criteria andDayofweekIsNull() {
            addCriterion("DAYOFWEEK is null");
            return (Criteria) this;
        }

        public Criteria andDayofweekIsNotNull() {
            addCriterion("DAYOFWEEK is not null");
            return (Criteria) this;
        }

        public Criteria andDayofweekEqualTo(String value) {
            addCriterion("DAYOFWEEK =", value, "dayofweek");
            return (Criteria) this;
        }

        public Criteria andDayofweekNotEqualTo(String value) {
            addCriterion("DAYOFWEEK <>", value, "dayofweek");
            return (Criteria) this;
        }

        public Criteria andDayofweekGreaterThan(String value) {
            addCriterion("DAYOFWEEK >", value, "dayofweek");
            return (Criteria) this;
        }

        public Criteria andDayofweekGreaterThanOrEqualTo(String value) {
            addCriterion("DAYOFWEEK >=", value, "dayofweek");
            return (Criteria) this;
        }

        public Criteria andDayofweekLessThan(String value) {
            addCriterion("DAYOFWEEK <", value, "dayofweek");
            return (Criteria) this;
        }

        public Criteria andDayofweekLessThanOrEqualTo(String value) {
            addCriterion("DAYOFWEEK <=", value, "dayofweek");
            return (Criteria) this;
        }

        public Criteria andDayofweekLike(String value) {
            addCriterion("DAYOFWEEK like", value, "dayofweek");
            return (Criteria) this;
        }

        public Criteria andDayofweekNotLike(String value) {
            addCriterion("DAYOFWEEK not like", value, "dayofweek");
            return (Criteria) this;
        }

        public Criteria andDayofweekIn(List<String> values) {
            addCriterion("DAYOFWEEK in", values, "dayofweek");
            return (Criteria) this;
        }

        public Criteria andDayofweekNotIn(List<String> values) {
            addCriterion("DAYOFWEEK not in", values, "dayofweek");
            return (Criteria) this;
        }

        public Criteria andDayofweekBetween(String value1, String value2) {
            addCriterion("DAYOFWEEK between", value1, value2, "dayofweek");
            return (Criteria) this;
        }

        public Criteria andDayofweekNotBetween(String value1, String value2) {
            addCriterion("DAYOFWEEK not between", value1, value2, "dayofweek");
            return (Criteria) this;
        }

        public Criteria andHourIsNull() {
            addCriterion("HOUR is null");
            return (Criteria) this;
        }

        public Criteria andHourIsNotNull() {
            addCriterion("HOUR is not null");
            return (Criteria) this;
        }

        public Criteria andHourEqualTo(String value) {
            addCriterion("HOUR =", value, "hour");
            return (Criteria) this;
        }

        public Criteria andHourNotEqualTo(String value) {
            addCriterion("HOUR <>", value, "hour");
            return (Criteria) this;
        }

        public Criteria andHourGreaterThan(String value) {
            addCriterion("HOUR >", value, "hour");
            return (Criteria) this;
        }

        public Criteria andHourGreaterThanOrEqualTo(String value) {
            addCriterion("HOUR >=", value, "hour");
            return (Criteria) this;
        }

        public Criteria andHourLessThan(String value) {
            addCriterion("HOUR <", value, "hour");
            return (Criteria) this;
        }

        public Criteria andHourLessThanOrEqualTo(String value) {
            addCriterion("HOUR <=", value, "hour");
            return (Criteria) this;
        }

        public Criteria andHourLike(String value) {
            addCriterion("HOUR like", value, "hour");
            return (Criteria) this;
        }

        public Criteria andHourNotLike(String value) {
            addCriterion("HOUR not like", value, "hour");
            return (Criteria) this;
        }

        public Criteria andHourIn(List<String> values) {
            addCriterion("HOUR in", values, "hour");
            return (Criteria) this;
        }

        public Criteria andHourNotIn(List<String> values) {
            addCriterion("HOUR not in", values, "hour");
            return (Criteria) this;
        }

        public Criteria andHourBetween(String value1, String value2) {
            addCriterion("HOUR between", value1, value2, "hour");
            return (Criteria) this;
        }

        public Criteria andHourNotBetween(String value1, String value2) {
            addCriterion("HOUR not between", value1, value2, "hour");
            return (Criteria) this;
        }

        public Criteria andMinuteIsNull() {
            addCriterion("MINUTE is null");
            return (Criteria) this;
        }

        public Criteria andMinuteIsNotNull() {
            addCriterion("MINUTE is not null");
            return (Criteria) this;
        }

        public Criteria andMinuteEqualTo(String value) {
            addCriterion("MINUTE =", value, "minute");
            return (Criteria) this;
        }

        public Criteria andMinuteNotEqualTo(String value) {
            addCriterion("MINUTE <>", value, "minute");
            return (Criteria) this;
        }

        public Criteria andMinuteGreaterThan(String value) {
            addCriterion("MINUTE >", value, "minute");
            return (Criteria) this;
        }

        public Criteria andMinuteGreaterThanOrEqualTo(String value) {
            addCriterion("MINUTE >=", value, "minute");
            return (Criteria) this;
        }

        public Criteria andMinuteLessThan(String value) {
            addCriterion("MINUTE <", value, "minute");
            return (Criteria) this;
        }

        public Criteria andMinuteLessThanOrEqualTo(String value) {
            addCriterion("MINUTE <=", value, "minute");
            return (Criteria) this;
        }

        public Criteria andMinuteLike(String value) {
            addCriterion("MINUTE like", value, "minute");
            return (Criteria) this;
        }

        public Criteria andMinuteNotLike(String value) {
            addCriterion("MINUTE not like", value, "minute");
            return (Criteria) this;
        }

        public Criteria andMinuteIn(List<String> values) {
            addCriterion("MINUTE in", values, "minute");
            return (Criteria) this;
        }

        public Criteria andMinuteNotIn(List<String> values) {
            addCriterion("MINUTE not in", values, "minute");
            return (Criteria) this;
        }

        public Criteria andMinuteBetween(String value1, String value2) {
            addCriterion("MINUTE between", value1, value2, "minute");
            return (Criteria) this;
        }

        public Criteria andMinuteNotBetween(String value1, String value2) {
            addCriterion("MINUTE not between", value1, value2, "minute");
            return (Criteria) this;
        }

        public Criteria andCurrentStateIsNull() {
            addCriterion("CURRENT_STATE is null");
            return (Criteria) this;
        }

        public Criteria andCurrentStateIsNotNull() {
            addCriterion("CURRENT_STATE is not null");
            return (Criteria) this;
        }

        public Criteria andCurrentStateEqualTo(String value) {
            addCriterion("CURRENT_STATE =", value, "currentState");
            return (Criteria) this;
        }

        public Criteria andCurrentStateNotEqualTo(String value) {
            addCriterion("CURRENT_STATE <>", value, "currentState");
            return (Criteria) this;
        }

        public Criteria andCurrentStateGreaterThan(String value) {
            addCriterion("CURRENT_STATE >", value, "currentState");
            return (Criteria) this;
        }

        public Criteria andCurrentStateGreaterThanOrEqualTo(String value) {
            addCriterion("CURRENT_STATE >=", value, "currentState");
            return (Criteria) this;
        }

        public Criteria andCurrentStateLessThan(String value) {
            addCriterion("CURRENT_STATE <", value, "currentState");
            return (Criteria) this;
        }

        public Criteria andCurrentStateLessThanOrEqualTo(String value) {
            addCriterion("CURRENT_STATE <=", value, "currentState");
            return (Criteria) this;
        }

        public Criteria andCurrentStateLike(String value) {
            addCriterion("CURRENT_STATE like", value, "currentState");
            return (Criteria) this;
        }

        public Criteria andCurrentStateNotLike(String value) {
            addCriterion("CURRENT_STATE not like", value, "currentState");
            return (Criteria) this;
        }

        public Criteria andCurrentStateIn(List<String> values) {
            addCriterion("CURRENT_STATE in", values, "currentState");
            return (Criteria) this;
        }

        public Criteria andCurrentStateNotIn(List<String> values) {
            addCriterion("CURRENT_STATE not in", values, "currentState");
            return (Criteria) this;
        }

        public Criteria andCurrentStateBetween(String value1, String value2) {
            addCriterion("CURRENT_STATE between", value1, value2, "currentState");
            return (Criteria) this;
        }

        public Criteria andCurrentStateNotBetween(String value1, String value2) {
            addCriterion("CURRENT_STATE not between", value1, value2, "currentState");
            return (Criteria) this;
        }

        public Criteria andOperationIsNull() {
            addCriterion("OPERATION is null");
            return (Criteria) this;
        }

        public Criteria andOperationIsNotNull() {
            addCriterion("OPERATION is not null");
            return (Criteria) this;
        }

        public Criteria andOperationEqualTo(String value) {
            addCriterion("OPERATION =", value, "operation");
            return (Criteria) this;
        }

        public Criteria andOperationNotEqualTo(String value) {
            addCriterion("OPERATION <>", value, "operation");
            return (Criteria) this;
        }

        public Criteria andOperationGreaterThan(String value) {
            addCriterion("OPERATION >", value, "operation");
            return (Criteria) this;
        }

        public Criteria andOperationGreaterThanOrEqualTo(String value) {
            addCriterion("OPERATION >=", value, "operation");
            return (Criteria) this;
        }

        public Criteria andOperationLessThan(String value) {
            addCriterion("OPERATION <", value, "operation");
            return (Criteria) this;
        }

        public Criteria andOperationLessThanOrEqualTo(String value) {
            addCriterion("OPERATION <=", value, "operation");
            return (Criteria) this;
        }

        public Criteria andOperationLike(String value) {
            addCriterion("OPERATION like", value, "operation");
            return (Criteria) this;
        }

        public Criteria andOperationNotLike(String value) {
            addCriterion("OPERATION not like", value, "operation");
            return (Criteria) this;
        }

        public Criteria andOperationIn(List<String> values) {
            addCriterion("OPERATION in", values, "operation");
            return (Criteria) this;
        }

        public Criteria andOperationNotIn(List<String> values) {
            addCriterion("OPERATION not in", values, "operation");
            return (Criteria) this;
        }

        public Criteria andOperationBetween(String value1, String value2) {
            addCriterion("OPERATION between", value1, value2, "operation");
            return (Criteria) this;
        }

        public Criteria andOperationNotBetween(String value1, String value2) {
            addCriterion("OPERATION not between", value1, value2, "operation");
            return (Criteria) this;
        }

        public Criteria andExtrainfoIsNull() {
            addCriterion("EXTRAINFO is null");
            return (Criteria) this;
        }

        public Criteria andExtrainfoIsNotNull() {
            addCriterion("EXTRAINFO is not null");
            return (Criteria) this;
        }

        public Criteria andExtrainfoEqualTo(String value) {
            addCriterion("EXTRAINFO =", value, "extrainfo");
            return (Criteria) this;
        }

        public Criteria andExtrainfoNotEqualTo(String value) {
            addCriterion("EXTRAINFO <>", value, "extrainfo");
            return (Criteria) this;
        }

        public Criteria andExtrainfoGreaterThan(String value) {
            addCriterion("EXTRAINFO >", value, "extrainfo");
            return (Criteria) this;
        }

        public Criteria andExtrainfoGreaterThanOrEqualTo(String value) {
            addCriterion("EXTRAINFO >=", value, "extrainfo");
            return (Criteria) this;
        }

        public Criteria andExtrainfoLessThan(String value) {
            addCriterion("EXTRAINFO <", value, "extrainfo");
            return (Criteria) this;
        }

        public Criteria andExtrainfoLessThanOrEqualTo(String value) {
            addCriterion("EXTRAINFO <=", value, "extrainfo");
            return (Criteria) this;
        }

        public Criteria andExtrainfoLike(String value) {
            addCriterion("EXTRAINFO like", value, "extrainfo");
            return (Criteria) this;
        }

        public Criteria andExtrainfoNotLike(String value) {
            addCriterion("EXTRAINFO not like", value, "extrainfo");
            return (Criteria) this;
        }

        public Criteria andExtrainfoIn(List<String> values) {
            addCriterion("EXTRAINFO in", values, "extrainfo");
            return (Criteria) this;
        }

        public Criteria andExtrainfoNotIn(List<String> values) {
            addCriterion("EXTRAINFO not in", values, "extrainfo");
            return (Criteria) this;
        }

        public Criteria andExtrainfoBetween(String value1, String value2) {
            addCriterion("EXTRAINFO between", value1, value2, "extrainfo");
            return (Criteria) this;
        }

        public Criteria andExtrainfoNotBetween(String value1, String value2) {
            addCriterion("EXTRAINFO not between", value1, value2, "extrainfo");
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