package com.lambo.auth.dao.api;
import com.lambo.auth.dao.model.UpmsUser;
import com.lambo.auth.dao.model.UpmsUserExample;

import java.util.List;

public interface AuthClientUserMapper {

    List<UpmsUser> selectByExample(UpmsUserExample example);

    UpmsUser selectByPrimaryKey(Integer userId);


}