package com.ives;

import com.ives.dao.StudentDao;
import com.ives.domain.Student;
import com.ives.utils.MyBatisUtil;
import com.ives.vo.CustomObject;
import com.ives.vo.QueryParam;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyTest {
    @Test
    public void testSelectById(){
        // 1.獲取SqlSession
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        // 2.獲取dao的代理
        StudentDao dao = sqlSession.getMapper(StudentDao.class);
        Student student = dao.selectById(1005);
        System.out.println("student = "+student);
        // 4.關閉SqlSession對象
        sqlSession.close();
    }

    @Test
    public void testSelectById2(){
        // 1.獲取SqlSession
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        // 2.獲取dao的代理
        StudentDao dao = sqlSession.getMapper(StudentDao.class);
        CustomObject co = dao.selectById2(1006);
        System.out.println("CustomObject = "+co);
        // 4.關閉SqlSession對象
        sqlSession.close();
    }

    @Test
    public void testCountStudent(){
        // 1.獲取SqlSession
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        // 2.獲取dao的代理
        StudentDao dao = sqlSession.getMapper(StudentDao.class);
        long nums = dao.countStudent();
        System.out.println("nums = "+nums);
        // 4.關閉SqlSession對象
        sqlSession.close();
    }

    @Test
    public void testSelectMap(){
        // 1.獲取SqlSession
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        // 2.獲取dao的代理
        StudentDao dao = sqlSession.getMapper(StudentDao.class);
        Map<Object,Object> map = dao.selectMap(1005);
        System.out.println("map === "+map);
        // 4.關閉SqlSession對象
        sqlSession.close();

        System.out.println("name === "+map.get("name"));
        System.out.println("id === "+map.get("id"));
        System.out.println("email === "+map.get("email"));
    }

    @Test
    public void selectByIdUseMap(){
        // 1.獲取SqlSession
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        // 2.獲取dao的代理
        StudentDao dao = sqlSession.getMapper(StudentDao.class);
        CustomObject co = dao.selectByIdUseMap(1006);
        System.out.println("CustomObject = "+co);
        // 4.關閉SqlSession對象
        sqlSession.close();
    }

    @Test
    public void selectByIdUseMap2(){
        // 1.獲取SqlSession
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        // 2.獲取dao的代理
        StudentDao dao = sqlSession.getMapper(StudentDao.class);
        CustomObject co = dao.selectByIdUseMap2(1008);
        System.out.println("CustomObject = "+co);
        // 4.關閉SqlSession對象
        sqlSession.close();
    }

    @Test
    public void selectLikeOne(){
        // 1.獲取SqlSession
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        // 2.獲取dao的代理
        StudentDao dao = sqlSession.getMapper(StudentDao.class);

        String name = "%娜%";
        List<Student> students = dao.selectLikeOne(name);
        // 4.關閉SqlSession對象
        sqlSession.close();

        students.forEach(stu-> System.out.println(stu));
    }

    @Test
    public void selectLikeTwo(){
        // 1.獲取SqlSession
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        // 2.獲取dao的代理
        StudentDao dao = sqlSession.getMapper(StudentDao.class);

        String name = "娜";
        List<Student> students = dao.selectLikeTwo(name);
        // 4.關閉SqlSession對象
        sqlSession.close();

        students.forEach(stu-> System.out.println(stu));
    }
}
