package com.ives.dao;

import com.ives.domain.Student;
import com.ives.vo.CustomObject;
import com.ives.vo.QueryParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface StudentDao {

    Student selectById(Integer id);

    CustomObject selectById2(@Param("stuid") Integer id);

    long countStudent();

    // 查詢結果返回是一個Map
    Map<Object,Object> selectMap(@Param("stuid") Integer id);
}
