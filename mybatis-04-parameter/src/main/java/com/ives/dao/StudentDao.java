package com.ives.dao;

import com.ives.domain.Student;
import com.ives.vo.QueryParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface StudentDao {

    Student selectById(Integer id);

    // dao接口的方法形參是一個簡單類型的
    // 簡單型態： java基本數據類型和String
    Student selectByEmail(String email);

    /*
        多個簡單類型的參數
        使用@Param命名參數，注解是mybatis提供的
        位置： 在形參定義的前面
        屬性： value自定義的參數名稱
     */

    List<Student> selectByNameOrAge(@Param("myname") String name,
                                    @Param("myage") Integer age);

    /*
        一個java對象作為參數(對象由屬性，每個屬性有set、get方法)
     */
    List<Student> selectByObject(Student student);

    List<Student> selectByQueryParam(QueryParam param);

    /*
        使用位置，獲取參數
     */
    List<Student> selectByPosition(String name,Integer age);

    /*
        使用Map作為參數
     */
    List<Student> selectStudentByMap(Map<String,Object> map);

    // 更新
    int updateStudent(Student student);

    // ${}占位符的使用，使用@Param命名
    List<Student> queryStudent(@Param("studentName") String name);

    List<Student> queryStudentOrderById();

    List<Student> queryStudentOrderByName();

    List<Student> queryStudentOrderByColName(@Param("colName")String name);

    List<Student> queryStudentOrderByColName2(@Param("myage")Integer age,
                                              @Param("colName")String colName,
                                              @Param("tableName")String tableName);

}
