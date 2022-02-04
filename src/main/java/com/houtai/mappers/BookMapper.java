package com.houtai.mappers;
import com.houtai.pojo.Book;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface BookMapper {
    /**
     * 查询所有
     * @return
     */
    @Select("select * from t_book")
    @ResultMap("bookResultMap")
    List<Book> selectAll();
    /**
     * 添加数据
     * @param book
     */
    @Insert("insert into t_book values(null,#{name},#{author},#{price},null,null,null)")
    void add(Book book);


    /**
     * 批量删除
     * @param ids
     */
    void deleteByIds(@Param("ids") int[] ids);
    /**
     * 分页条件查询
     * @param begin
     * @param size
     * @return
     */
    List<Book> selectByPageAndCondition(@Param("begin") int begin, @Param("size") int size, @Param("book") Book book);

    /**
     * 根据条件查询总记录数
     * @return
     */
    int selectTotalCountByCondition(Book brand);
}
