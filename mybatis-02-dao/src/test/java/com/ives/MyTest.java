package com.ives;

import com.ives.domain.Student;
import com.ives.utils.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

public class MyTest {
    @Test
    public void testSelectById(){
        // 1.獲取SqlSession
        SqlSession session = MyBatisUtil.getSqlSession();
        // 2.指定sqlId
        String sqlId = "com.ives.dao.StudentDao.selectById";
        // 3.執行SqlSession的方法，表示執行sql語句
        Student student = session.selectOne(sqlId,1005);
        System.out.println("查詢的結果 === "+student);
        // 4.關閉SqlSession對象
        session.close();
    }

    @Test
    public void testSelectStudents(){
        // 1.獲取SqlSession
        SqlSession session = MyBatisUtil.getSqlSession();
        // 2.指定sqlId
        String sqlId = "com.ives.dao.StudentDao.selectStudents";
        // 3.執行SqlSession的方法，表示執行sql語句
        List<Student> students = session.selectList(sqlId);

        for(Student stu:students){
            System.out.println("查詢的結果 === "+stu);
        }

        // 4.關閉SqlSession對象
        session.close();
    }

    @Test
    public void testInsertStudent(){
        // 1.獲取SqlSession
        SqlSession session = MyBatisUtil.getSqlSession();
        // 2.指定sqlId
        String sqlId = "com.ives.dao.StudentDao.insertStudent";
        // 3.執行SqlSession的方法，表示執行sql語句
        Student student = new Student();
        student.setId(1008);
        student.setName("依林");
        student.setEmail("jolin@gmail.com");
        student.setAge(35);
        int rows = session.insert(sqlId,student);
        session.commit();
        System.out.println("insert的行數 === "+rows);

        // 4.關閉SqlSession對象
        session.close();
    }
}
