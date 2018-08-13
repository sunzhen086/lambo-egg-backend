package com.lambo.mock.manage.model;

import java.util.ArrayList;
import java.util.List;

public class MockSettingExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MockSettingExample() {
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

        public Criteria andMockNameIsNull() {
            addCriterion("mock_name is null");
            return (Criteria) this;
        }

        public Criteria andMockNameIsNotNull() {
            addCriterion("mock_name is not null");
            return (Criteria) this;
        }

        public Criteria andMockNameEqualTo(String value) {
            addCriterion("mock_name =", value, "mockName");
            return (Criteria) this;
        }

        public Criteria andMockNameNotEqualTo(String value) {
            addCriterion("mock_name <>", value, "mockName");
            return (Criteria) this;
        }

        public Criteria andMockNameGreaterThan(String value) {
            addCriterion("mock_name >", value, "mockName");
            return (Criteria) this;
        }

        public Criteria andMockNameGreaterThanOrEqualTo(String value) {
            addCriterion("mock_name >=", value, "mockName");
            return (Criteria) this;
        }

        public Criteria andMockNameLessThan(String value) {
            addCriterion("mock_name <", value, "mockName");
            return (Criteria) this;
        }

        public Criteria andMockNameLessThanOrEqualTo(String value) {
            addCriterion("mock_name <=", value, "mockName");
            return (Criteria) this;
        }

        public Criteria andMockNameLike(String value) {
            addCriterion("mock_name like", value, "mockName");
            return (Criteria) this;
        }

        public Criteria andMockNameNotLike(String value) {
            addCriterion("mock_name not like", value, "mockName");
            return (Criteria) this;
        }

        public Criteria andMockNameIn(List<String> values) {
            addCriterion("mock_name in", values, "mockName");
            return (Criteria) this;
        }

        public Criteria andMockNameNotIn(List<String> values) {
            addCriterion("mock_name not in", values, "mockName");
            return (Criteria) this;
        }

        public Criteria andMockNameBetween(String value1, String value2) {
            addCriterion("mock_name between", value1, value2, "mockName");
            return (Criteria) this;
        }

        public Criteria andMockNameNotBetween(String value1, String value2) {
            addCriterion("mock_name not between", value1, value2, "mockName");
            return (Criteria) this;
        }

        public Criteria andMockUrlIsNull() {
            addCriterion("mock_url is null");
            return (Criteria) this;
        }

        public Criteria andMockUrlIsNotNull() {
            addCriterion("mock_url is not null");
            return (Criteria) this;
        }

        public Criteria andMockUrlEqualTo(String value) {
            addCriterion("mock_url =", value, "mockUrl");
            return (Criteria) this;
        }

        public Criteria andMockUrlNotEqualTo(String value) {
            addCriterion("mock_url <>", value, "mockUrl");
            return (Criteria) this;
        }

        public Criteria andMockUrlGreaterThan(String value) {
            addCriterion("mock_url >", value, "mockUrl");
            return (Criteria) this;
        }

        public Criteria andMockUrlGreaterThanOrEqualTo(String value) {
            addCriterion("mock_url >=", value, "mockUrl");
            return (Criteria) this;
        }

        public Criteria andMockUrlLessThan(String value) {
            addCriterion("mock_url <", value, "mockUrl");
            return (Criteria) this;
        }

        public Criteria andMockUrlLessThanOrEqualTo(String value) {
            addCriterion("mock_url <=", value, "mockUrl");
            return (Criteria) this;
        }

        public Criteria andMockUrlLike(String value) {
            addCriterion("mock_url like", value, "mockUrl");
            return (Criteria) this;
        }

        public Criteria andMockUrlNotLike(String value) {
            addCriterion("mock_url not like", value, "mockUrl");
            return (Criteria) this;
        }

        public Criteria andMockUrlIn(List<String> values) {
            addCriterion("mock_url in", values, "mockUrl");
            return (Criteria) this;
        }

        public Criteria andMockUrlNotIn(List<String> values) {
            addCriterion("mock_url not in", values, "mockUrl");
            return (Criteria) this;
        }

        public Criteria andMockUrlBetween(String value1, String value2) {
            addCriterion("mock_url between", value1, value2, "mockUrl");
            return (Criteria) this;
        }

        public Criteria andMockUrlNotBetween(String value1, String value2) {
            addCriterion("mock_url not between", value1, value2, "mockUrl");
            return (Criteria) this;
        }

        public Criteria andMockTypeIsNull() {
            addCriterion("mock_type is null");
            return (Criteria) this;
        }

        public Criteria andMockTypeIsNotNull() {
            addCriterion("mock_type is not null");
            return (Criteria) this;
        }

        public Criteria andMockTypeEqualTo(String value) {
            addCriterion("mock_type =", value, "mockType");
            return (Criteria) this;
        }

        public Criteria andMockTypeNotEqualTo(String value) {
            addCriterion("mock_type <>", value, "mockType");
            return (Criteria) this;
        }

        public Criteria andMockTypeGreaterThan(String value) {
            addCriterion("mock_type >", value, "mockType");
            return (Criteria) this;
        }

        public Criteria andMockTypeGreaterThanOrEqualTo(String value) {
            addCriterion("mock_type >=", value, "mockType");
            return (Criteria) this;
        }

        public Criteria andMockTypeLessThan(String value) {
            addCriterion("mock_type <", value, "mockType");
            return (Criteria) this;
        }

        public Criteria andMockTypeLessThanOrEqualTo(String value) {
            addCriterion("mock_type <=", value, "mockType");
            return (Criteria) this;
        }

        public Criteria andMockTypeLike(String value) {
            addCriterion("mock_type like", value, "mockType");
            return (Criteria) this;
        }

        public Criteria andMockTypeNotLike(String value) {
            addCriterion("mock_type not like", value, "mockType");
            return (Criteria) this;
        }

        public Criteria andMockTypeIn(List<String> values) {
            addCriterion("mock_type in", values, "mockType");
            return (Criteria) this;
        }

        public Criteria andMockTypeNotIn(List<String> values) {
            addCriterion("mock_type not in", values, "mockType");
            return (Criteria) this;
        }

        public Criteria andMockTypeBetween(String value1, String value2) {
            addCriterion("mock_type between", value1, value2, "mockType");
            return (Criteria) this;
        }

        public Criteria andMockTypeNotBetween(String value1, String value2) {
            addCriterion("mock_type not between", value1, value2, "mockType");
            return (Criteria) this;
        }

        public Criteria andProviderIsNull() {
            addCriterion("provider is null");
            return (Criteria) this;
        }

        public Criteria andProviderIsNotNull() {
            addCriterion("provider is not null");
            return (Criteria) this;
        }

        public Criteria andProviderEqualTo(String value) {
            addCriterion("provider =", value, "provider");
            return (Criteria) this;
        }

        public Criteria andProviderNotEqualTo(String value) {
            addCriterion("provider <>", value, "provider");
            return (Criteria) this;
        }

        public Criteria andProviderGreaterThan(String value) {
            addCriterion("provider >", value, "provider");
            return (Criteria) this;
        }

        public Criteria andProviderGreaterThanOrEqualTo(String value) {
            addCriterion("provider >=", value, "provider");
            return (Criteria) this;
        }

        public Criteria andProviderLessThan(String value) {
            addCriterion("provider <", value, "provider");
            return (Criteria) this;
        }

        public Criteria andProviderLessThanOrEqualTo(String value) {
            addCriterion("provider <=", value, "provider");
            return (Criteria) this;
        }

        public Criteria andProviderLike(String value) {
            addCriterion("provider like", value, "provider");
            return (Criteria) this;
        }

        public Criteria andProviderNotLike(String value) {
            addCriterion("provider not like", value, "provider");
            return (Criteria) this;
        }

        public Criteria andProviderIn(List<String> values) {
            addCriterion("provider in", values, "provider");
            return (Criteria) this;
        }

        public Criteria andProviderNotIn(List<String> values) {
            addCriterion("provider not in", values, "provider");
            return (Criteria) this;
        }

        public Criteria andProviderBetween(String value1, String value2) {
            addCriterion("provider between", value1, value2, "provider");
            return (Criteria) this;
        }

        public Criteria andProviderNotBetween(String value1, String value2) {
            addCriterion("provider not between", value1, value2, "provider");
            return (Criteria) this;
        }

        public Criteria andUserIsNull() {
            addCriterion("user is null");
            return (Criteria) this;
        }

        public Criteria andUserIsNotNull() {
            addCriterion("user is not null");
            return (Criteria) this;
        }

        public Criteria andUserEqualTo(String value) {
            addCriterion("user =", value, "user");
            return (Criteria) this;
        }

        public Criteria andUserNotEqualTo(String value) {
            addCriterion("user <>", value, "user");
            return (Criteria) this;
        }

        public Criteria andUserGreaterThan(String value) {
            addCriterion("user >", value, "user");
            return (Criteria) this;
        }

        public Criteria andUserGreaterThanOrEqualTo(String value) {
            addCriterion("user >=", value, "user");
            return (Criteria) this;
        }

        public Criteria andUserLessThan(String value) {
            addCriterion("user <", value, "user");
            return (Criteria) this;
        }

        public Criteria andUserLessThanOrEqualTo(String value) {
            addCriterion("user <=", value, "user");
            return (Criteria) this;
        }

        public Criteria andUserLike(String value) {
            addCriterion("user like", value, "user");
            return (Criteria) this;
        }

        public Criteria andUserNotLike(String value) {
            addCriterion("user not like", value, "user");
            return (Criteria) this;
        }

        public Criteria andUserIn(List<String> values) {
            addCriterion("user in", values, "user");
            return (Criteria) this;
        }

        public Criteria andUserNotIn(List<String> values) {
            addCriterion("user not in", values, "user");
            return (Criteria) this;
        }

        public Criteria andUserBetween(String value1, String value2) {
            addCriterion("user between", value1, value2, "user");
            return (Criteria) this;
        }

        public Criteria andUserNotBetween(String value1, String value2) {
            addCriterion("user not between", value1, value2, "user");
            return (Criteria) this;
        }

        public Criteria andAuthMethodIsNull() {
            addCriterion("auth_method is null");
            return (Criteria) this;
        }

        public Criteria andAuthMethodIsNotNull() {
            addCriterion("auth_method is not null");
            return (Criteria) this;
        }

        public Criteria andAuthMethodEqualTo(String value) {
            addCriterion("auth_method =", value, "authMethod");
            return (Criteria) this;
        }

        public Criteria andAuthMethodNotEqualTo(String value) {
            addCriterion("auth_method <>", value, "authMethod");
            return (Criteria) this;
        }

        public Criteria andAuthMethodGreaterThan(String value) {
            addCriterion("auth_method >", value, "authMethod");
            return (Criteria) this;
        }

        public Criteria andAuthMethodGreaterThanOrEqualTo(String value) {
            addCriterion("auth_method >=", value, "authMethod");
            return (Criteria) this;
        }

        public Criteria andAuthMethodLessThan(String value) {
            addCriterion("auth_method <", value, "authMethod");
            return (Criteria) this;
        }

        public Criteria andAuthMethodLessThanOrEqualTo(String value) {
            addCriterion("auth_method <=", value, "authMethod");
            return (Criteria) this;
        }

        public Criteria andAuthMethodLike(String value) {
            addCriterion("auth_method like", value, "authMethod");
            return (Criteria) this;
        }

        public Criteria andAuthMethodNotLike(String value) {
            addCriterion("auth_method not like", value, "authMethod");
            return (Criteria) this;
        }

        public Criteria andAuthMethodIn(List<String> values) {
            addCriterion("auth_method in", values, "authMethod");
            return (Criteria) this;
        }

        public Criteria andAuthMethodNotIn(List<String> values) {
            addCriterion("auth_method not in", values, "authMethod");
            return (Criteria) this;
        }

        public Criteria andAuthMethodBetween(String value1, String value2) {
            addCriterion("auth_method between", value1, value2, "authMethod");
            return (Criteria) this;
        }

        public Criteria andAuthMethodNotBetween(String value1, String value2) {
            addCriterion("auth_method not between", value1, value2, "authMethod");
            return (Criteria) this;
        }

        public Criteria andIsPagingIsNull() {
            addCriterion("is_paging is null");
            return (Criteria) this;
        }

        public Criteria andIsPagingIsNotNull() {
            addCriterion("is_paging is not null");
            return (Criteria) this;
        }

        public Criteria andIsPagingEqualTo(Boolean value) {
            addCriterion("is_paging =", value, "isPaging");
            return (Criteria) this;
        }

        public Criteria andIsPagingNotEqualTo(Boolean value) {
            addCriterion("is_paging <>", value, "isPaging");
            return (Criteria) this;
        }

        public Criteria andIsPagingGreaterThan(Boolean value) {
            addCriterion("is_paging >", value, "isPaging");
            return (Criteria) this;
        }

        public Criteria andIsPagingGreaterThanOrEqualTo(Boolean value) {
            addCriterion("is_paging >=", value, "isPaging");
            return (Criteria) this;
        }

        public Criteria andIsPagingLessThan(Boolean value) {
            addCriterion("is_paging <", value, "isPaging");
            return (Criteria) this;
        }

        public Criteria andIsPagingLessThanOrEqualTo(Boolean value) {
            addCriterion("is_paging <=", value, "isPaging");
            return (Criteria) this;
        }

        public Criteria andIsPagingIn(List<Boolean> values) {
            addCriterion("is_paging in", values, "isPaging");
            return (Criteria) this;
        }

        public Criteria andIsPagingNotIn(List<Boolean> values) {
            addCriterion("is_paging not in", values, "isPaging");
            return (Criteria) this;
        }

        public Criteria andIsPagingBetween(Boolean value1, Boolean value2) {
            addCriterion("is_paging between", value1, value2, "isPaging");
            return (Criteria) this;
        }

        public Criteria andIsPagingNotBetween(Boolean value1, Boolean value2) {
            addCriterion("is_paging not between", value1, value2, "isPaging");
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

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(String value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(String value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(String value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(String value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(String value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(String value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLike(String value) {
            addCriterion("create_time like", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotLike(String value) {
            addCriterion("create_time not like", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<String> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<String> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(String value1, String value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(String value1, String value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(String value) {
            addCriterion("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(String value) {
            addCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(String value) {
            addCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(String value) {
            addCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(String value) {
            addCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(String value) {
            addCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLike(String value) {
            addCriterion("update_time like", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotLike(String value) {
            addCriterion("update_time not like", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<String> values) {
            addCriterion("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<String> values) {
            addCriterion("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(String value1, String value2) {
            addCriterion("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(String value1, String value2) {
            addCriterion("update_time not between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andCreateUserIsNull() {
            addCriterion("create_user is null");
            return (Criteria) this;
        }

        public Criteria andCreateUserIsNotNull() {
            addCriterion("create_user is not null");
            return (Criteria) this;
        }

        public Criteria andCreateUserEqualTo(String value) {
            addCriterion("create_user =", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserNotEqualTo(String value) {
            addCriterion("create_user <>", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserGreaterThan(String value) {
            addCriterion("create_user >", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserGreaterThanOrEqualTo(String value) {
            addCriterion("create_user >=", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserLessThan(String value) {
            addCriterion("create_user <", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserLessThanOrEqualTo(String value) {
            addCriterion("create_user <=", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserLike(String value) {
            addCriterion("create_user like", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserNotLike(String value) {
            addCriterion("create_user not like", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserIn(List<String> values) {
            addCriterion("create_user in", values, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserNotIn(List<String> values) {
            addCriterion("create_user not in", values, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserBetween(String value1, String value2) {
            addCriterion("create_user between", value1, value2, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserNotBetween(String value1, String value2) {
            addCriterion("create_user not between", value1, value2, "createUser");
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