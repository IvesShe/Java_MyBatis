package com.ives;

import com.ives.dao.StudentDao;
import com.ives.domain.Student;
import com.ives.utils.MyBatisUtil;
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
        Student student = dao.selectById(1008);
        System.out.println("student = "+student);
        // 4.關閉SqlSession對象
        sqlSession.close();
    }

    @Test
    public void testOneParameter(){
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        StudentDao dao = sqlSession.getMapper(StudentDao.class);
        Student student = dao.selectByEmail("nana@gmail.com");
        System.out.println("email = "+student);
        sqlSession.close();
    }

    @Test
    public void testSelectByNameOrAge(){
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        StudentDao dao = sqlSession.getMapper(StudentDao.class);
        List<Student> students = dao.selectByNameOrAge("娜娜",25);
        students.forEach(stu-> System.out.println("stu = "+stu));
        sqlSession.close();
    }

    @Test
    public void testSelectByObject(){
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        StudentDao dao = sqlSession.getMapper(StudentDao.class);

        Student student = new Student();
        student.setName("琪琪");
        student.setAge(25);
        List<Student> students = dao.selectByObject(student);
        students.forEach(stu-> System.out.println("stu = "+stu));
        sqlSession.close();
    }

    @Test
    public void testSelectByQueryParam(){
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        StudentDao dao = sqlSession.getMapper(StudentDao.class);

        QueryParam param = new QueryParam();
        param.setP1("依林");
        param.setP2(25);
        List<Student> students = dao.selectByQueryParam(param);
        students.forEach(stu-> System.out.println("stu = "+stu));
        sqlSession.close();
    }

    @Test
    public void testSelectByPosition(){
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        StudentDao dao = sqlSession.getMapper(StudentDao.class);

        // 按位置傳遞參數
        List<Student> students = dao.selectByPosition("依林",24);
        students.forEach(stu-> System.out.println("stu = "+stu));
        sqlSession.close();
    }

    @Test
    public void testSelectByMap(){
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        StudentDao dao = sqlSession.getMapper(StudentDao.class);

        // 使用map傳遞參數
        Map<String,Object> data = new HashMap<>();
        data.put("myname","娜娜");
        data.put("myage",25);
        List<Student> students = dao.selectStudentByMap(data);
        students.forEach(stu-> System.out.println("stu = "+stu));
        sqlSession.close();
    }

    @Test
    public void testUpdateStudent(){
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        StudentDao dao = sqlSession.getMapper(StudentDao.class);

        Student student = new Student();
        student.setId(1002);
        student.setName("jack3333");
        student.setEmail("jack3333@gmail.com");
        student.setAge(23);
        int rows = dao.updateStudent(student);
        sqlSession.commit();
        System.out.println("更新學生的rows = "+rows);
        sqlSession.close();
    }

    @Test
    public void testQueryStudent(){
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        StudentDao dao = sqlSession.getMapper(StudentDao.class);

        List<Student> students = dao.queryStudent("'依林' or 1=1");
        students.forEach(stu-> System.out.println("stu = "+stu));
        sqlSession.close();
    }

    @Test
    public void testQueryStudentOrderById(){
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        StudentDao dao = sqlSession.getMapper(StudentDao.class);

        List<Student> students = dao.queryStudentOrderById();
        students.forEach(stu-> System.out.println("stu = "+stu));
        sqlSession.close();
    }

    @Test
    public void testQueryStudentOrderByName(){
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        StudentDao dao = sqlSession.getMapper(StudentDao.class);

        List<Student> students = dao.queryStudentOrderByName();
        students.forEach(stu-> System.out.println("stu = "+stu));
        sqlSession.close();
    }

    @Test
    public void testQueryStudentOrderByColName(){
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        StudentDao dao = sqlSession.getMapper(StudentDao.class);

        List<Student> students = dao.queryStudentOrderByColName("id");
        students.forEach(stu-> System.out.println("stu = "+stu));
        sqlSession.close();
    }

    @Test
    public void testQueryStudentOrderByColName2(){
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        StudentDao dao = sqlSession.getMapper(StudentDao.class);

        List<Student> students = dao.queryStudentOrderByColName2(25,"id","student002");
        students.forEach(stu-> System.out.println("stu = "+stu));
        sqlSession.close();
    }
}
