package com.ives.dao;

import com.ives.domain.Student;

public interface StudentDao {

    // 查詢一個學生
    Student selectStudentById(Integer id);

    // 添加學生
    // 返回值int：表示本次操作影響的數據庫的行數
    int insertStudent(Student student);
}
