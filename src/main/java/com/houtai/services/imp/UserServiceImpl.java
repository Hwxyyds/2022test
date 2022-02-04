package com.houtai.services.imp;

import com.houtai.mappers.UserMapper;
import com.houtai.pojo.User;
import com.houtai.services.UserService;
import com.houtai.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

/**
 * @author: hwx
 * @create: 2021-12-21 19:11
 **/
public class UserServiceImpl implements UserService {
    SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
    @Override
    public List<User> selectAll() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<User> querry = mapper.querry();
        sqlSession.close();
        return querry;
    }
}
