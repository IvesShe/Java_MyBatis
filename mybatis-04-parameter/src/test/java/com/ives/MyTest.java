package com.ives;

import com.ives.dao.StudentDao;
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
        // 2.獲取dao的代理
        StudentDao dao = session.getMapper(StudentDao.class);
        Student student = dao.selectById(1005);
        System.out.println("student = "+student);
        // 4.關閉SqlSession對象
        session.close();
    }
}
