package com.ives;

import com.ives.domain.Student;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

public class MyTest {

    // 測試mybatis執行sql語句
    @Test
    public void testSelectStudentById() throws IOException {
        // 調用mybatis某個對象的方法，執行mapper文件中的sql語句
        // mybatis核心類： SqlSessionFactory

        // 1.定義mybatis主配置文件的位置，從類路徑開始的相對路徑
        String config = "mybatis.xml";
        // 2.讀取主配置文件。使用mybatis框架中的Resources類
        InputStream inputStream = Resources.getResourceAsStream(config);
        // 3.創建SqlSessionFactory對象，使用SqlSessionFactoryBuilder類
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(inputStream);

        // 4.獲取SqlSession對象
        SqlSession session = factory.openSession();

        // 5.指定要執行的sql語句的id
        // sql的id = namespace + "." + select|update|insert|delete 標籤的id屬性值
        String sqlId = "com.ives.dao.StudentDao" + "." + "selectStudentById";

        // 6.通過SqlSession的方法，執行sql語句
        Student student = session.selectOne(sqlId);
        System.out.println("使用mybatis查詢一個學生："+student);

        // 7.關閉SqlSession對象
        session.close();
    }

    @Test
    public void testSelectStudentById2() throws IOException {
        // 調用mybatis某個對象的方法，執行mapper文件中的sql語句
        // mybatis核心類： SqlSessionFactory

        // 1.定義mybatis主配置文件的位置，從類路徑開始的相對路徑
        String config = "mybatis.xml";
        // 2.讀取主配置文件。使用mybatis框架中的Resources類
        InputStream inputStream = Resources.getResourceAsStream(config);
        // 3.創建SqlSessionFactory對象，使用SqlSessionFactoryBuilder類
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(inputStream);

        // 4.獲取SqlSession對象
        SqlSession session = factory.openSession();

        // 5.指定要執行的sql語句的id
        // sql的id = namespace + "." + select|update|insert|delete 標籤的id屬性值
        String sqlId = "com.ives.dao.StudentDao" + "." + "selectStudentById";

        // 6.通過SqlSession的方法，執行sql語句
        Student student = session.selectOne(sqlId,1001);
        System.out.println("使用mybatis查詢一個學生："+student);

        // 7.關閉SqlSession對象
        session.close();
    }

    @Test
    public void testInsertStudent() throws IOException {
        String config = "mybatis.xml";
        InputStream inputStream = Resources.getResourceAsStream(config);
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session = factory.openSession();

        String sqlId = "com.ives.dao.StudentDao" + "." + "insertStudent";
        Student student = new Student();
        student.setId(1005);
        student.setName("小雪");
        student.setEmail("snow@gmail.com");
        student.setAge(29);
        int rows = session.insert(sqlId,student);
        System.out.println("使用mybatis添加一個學生,rows = ："+rows);

        //mybatis默認執行sql語句是 手工提交事務模式，在作insert、update、delete後需要提交事務
        session.commit();

        session.close();
    }
}
