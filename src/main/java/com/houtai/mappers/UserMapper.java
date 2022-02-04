package com.houtai.mappers;


import com.houtai.pojo.User;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper {
    /**
     * 查询所有
     * @return
     */
    @Select("select * from t_user")
    public List<User> querry();
}
