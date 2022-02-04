package com;
import com.houtai.mappers.UserMapper;
import com.houtai.pojo.User;
import com.houtai.util.SqlSessionFactoryUtils;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.stream.IntStream;

/**
 * @author: hwx
 * @create: 2021-12-21 16:56
 **/
public class UserMapperTest {
    private UserMapper mapper;
    @Before
    public void before() throws IOException {
        //获得核心配置文件
        InputStream resourceAsStream = Resources.getResourceAsStream("Mybatis-config.xml");
        //获得session工厂对象
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
        //获得session会话对象
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        mapper = sqlSession.getMapper(UserMapper.class);
    }

    @Test
    public void test1() throws IOException {
        //执行操作
//        List<Object> userMapper = sqlSession.selectList("userMapper.findAll");
//        mapper= sqlSession.getMapper(UserMapper.class);
        List<User> all = mapper.querry();
        //打印数据
        System.out.println(all);
        //释放资源

    }
}
