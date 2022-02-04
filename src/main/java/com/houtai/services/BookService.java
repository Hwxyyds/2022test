package com.houtai.services;

import com.houtai.pojo.Book;
import com.houtai.pojo.PageBean;

import java.util.List;

public interface BookService {

    /**
     * 查询所有
     * @return
     */
    List<Book> selectAll();

    /**
     * 添加数据
     * @param book
     */
    void add(Book book);


    /**
     * 批量删除
     * @param ids
     */
    void deleteByIds( int[] ids);

    /**
     * 分页条件查询
     * @param currentPage
     * @param pageSize
     * @param book
     * @return
     */
    PageBean<Book> selectByPageAndCondition(int currentPage, int pageSize, Book book);
}
