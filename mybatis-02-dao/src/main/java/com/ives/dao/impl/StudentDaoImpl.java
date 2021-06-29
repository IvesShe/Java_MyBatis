package com.ives.dao.impl;

import com.ives.dao.StudentDao;
import com.ives.domain.Student;
import com.ives.utils.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class StudentDaoImpl implements StudentDao {
    @Override
    public Student selectById(Integer id) {
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        String sqlId = "com.ives.dao.StudentDao.selectById";
        Student student = sqlSession.selectOne(sqlId,id);
        sqlSession.close();
        return student;
    }

    @Override
    public List<Student> selectStudents() {
        // 1.獲取SqlSession
        SqlSession session = MyBatisUtil.getSqlSession();
        // 2.指定sqlId
        String sqlId = "com.ives.dao.StudentDao.selectStudents";
        // 3.執行SqlSession的方法，表示執行sql語句
        List<Student> students = session.selectList(sqlId);
        // 4.關閉SqlSession對象
        session.close();
        return students;
    }

    @Override
    public int insertStudent(Student student) {
        // 1.獲取SqlSession
        SqlSession session = MyBatisUtil.getSqlSession();
        // 2.指定sqlId
        String sqlId = "com.ives.dao.StudentDao.insertStudent";
        // 3.執行SqlSession的方法，表示執行sql語句
        int rows = session.insert(sqlId,student);
        session.commit();
        System.out.println("insert的行數 === "+rows);
        // 4.關閉SqlSession對象
        session.close();
        return rows;
    }
}
