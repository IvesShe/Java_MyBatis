package com.ives.dao;

import com.ives.domain.Student;

public interface StudentDao {

    // 查詢一個學生
    Student selectStudentById(Integer id);
}
