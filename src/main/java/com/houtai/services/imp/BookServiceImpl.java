package com.houtai.services.imp;

import com.houtai.mappers.BookMapper;
import com.houtai.pojo.Book;
import com.houtai.pojo.PageBean;
import com.houtai.services.BookService;
import com.houtai.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;
/**
 * @author: hwx
 * @create: 2021-12-22 09:22
 **/
public class BookServiceImpl implements BookService{

    //1. 创建SqlSessionFactory 工厂对象
    SqlSessionFactory factory = SqlSessionFactoryUtils.getSqlSessionFactory();


    @Override
    public List<Book> selectAll() {
        //2. 获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //3. 获取BrandMapper
        BookMapper mapper = sqlSession.getMapper(BookMapper.class);

        //4. 调用方法
        List<Book> book = mapper.selectAll();

        //5. 释放资源
        sqlSession.close();

        return book;
    }


    public void add(Book Book) {
        //2. 获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //3. 获取BookMapper
        BookMapper mapper = sqlSession.getMapper(BookMapper.class);

        //4. 调用方法
        mapper.add(Book);
        sqlSession.commit();//提交事务

        //5. 释放资源
        sqlSession.close();
    }

    public void deleteByIds(int[] ids) {
        //2. 获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //3. 获取BookMapper
        BookMapper mapper = sqlSession.getMapper(BookMapper.class);

        //4. 调用方法
        mapper.deleteByIds(ids);

        sqlSession.commit();//提交事务

        //5. 释放资源
        sqlSession.close();
    }

    public PageBean<Book> selectByPageAndCondition(int currentPage, int pageSize, Book book) {
        //2. 获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //3. 获取BookMapper
        BookMapper mapper = sqlSession.getMapper(BookMapper.class);


        //4. 计算开始索引
        int begin = (currentPage - 1) * pageSize;
        // 计算查询条目数
        int size = pageSize;

        // 处理Book条件，模糊表达式
        String Name = book.getName();
        if (Name != null && Name.length() > 0) {
            book.setName("%" + Name + "%");
        }

        String author = book.getAuthor();
        if (author != null && author.length() > 0) {
            book.setAuthor("%" + author + "%");
        }


        //5. 查询当前页数据
        List<Book> rows = mapper.selectByPageAndCondition(begin, size, book);

        //6. 查询总记录数
        int totalCount = mapper.selectTotalCountByCondition(book);

        //7. 封装PageBean对象
        PageBean<Book> pageBean = new PageBean<>();
        pageBean.setRows(rows);
        pageBean.setTotalCount(totalCount);


        //8. 释放资源
        sqlSession.close();

        return pageBean;
    }

}
