package com;

import com.houtai.mappers.BookMapper;
import com.houtai.pojo.Book;
import com.houtai.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;

import java.util.List;

/**
 * @author: hwx
 * @create: 2021-12-22 10:29
 **/
public class BookTest {
    @Test
    public void demo(){
        SqlSessionFactory factory = SqlSessionFactoryUtils.getSqlSessionFactory();

        //2. 获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //3. 获取BrandMapper
        BookMapper mapper = sqlSession.getMapper(BookMapper.class);

        //4. 调用方法
        List<Book> book = mapper.selectAll();
        System.out.println(book);
        //5. 释放资源
        sqlSession.close();

    }
}
