package com.houtai.web;

import javax.servlet.annotation.WebServlet;
import com.alibaba.fastjson.JSON;
import com.houtai.pojo.Book;
import com.houtai.pojo.PageBean;
import com.houtai.services.BookService;
import com.houtai.services.imp.BookServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;
/**
 * @author: hwx
 * @create: 2021-12-22 09:13
 **/

@WebServlet("/book/*")
public class BookServlet extends BaseServlet{
    private BookService bookService = new BookServiceImpl();

    public void selectAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1. 调用service查询
        List<Book> books = bookService.selectAll();

        //2. 转为JSON
        String jsonString = JSON.toJSONString(books);
        //3. 写数据
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }


    public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //1. 接收书籍数据
        BufferedReader br = request.getReader();
        String params = br.readLine();//json字符串

        //转为Book对象
       Book book = JSON.parseObject(params, Book.class);

        //2. 调用service添加
        bookService.add(book);

        //3. 响应成功的标识
        response.getWriter().write("success");
    }
    /**
     * 批量删除
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void deleteByIds(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //1. 接收数据  [1,2,3]
        BufferedReader br = request.getReader();
        String params = br.readLine();//json字符串

        //转为 int[]
        int[] ids = JSON.parseObject(params, int[].class);

        //2. 调用service添加
        bookService.deleteByIds(ids);

        //3. 响应成功的标识
        response.getWriter().write("success");
    }
    /**
     * 分页条件查询
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */

    public void selectByPageAndCondition(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1. 接收 当前页码 和 每页展示条数    url?currentPage=1&pageSize=5
        String _currentPage = request.getParameter("currentPage");
        String _pageSize = request.getParameter("pageSize");

        int currentPage = Integer.parseInt(_currentPage);
        int pageSize = Integer.parseInt(_pageSize);

        // 获取查询条件对象
        BufferedReader br = request.getReader();
        String params = br.readLine();//json字符串

        //转为 Book
        Book book = JSON.parseObject(params, Book.class);


        //2. 调用service查询
        PageBean<Book> pageBean = bookService.selectByPageAndCondition(currentPage,pageSize,book);

        //2. 转为JSON
        String jsonString = JSON.toJSONString(pageBean);
        System.out.println("===="+jsonString);
        //3. 写数据
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

}
