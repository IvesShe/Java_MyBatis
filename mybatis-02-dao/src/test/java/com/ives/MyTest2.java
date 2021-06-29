package com.ives;

import com.ives.dao.StudentDao;
import com.ives.dao.impl.StudentDaoImpl;
import com.ives.domain.Student;
import org.junit.Test;

import java.util.List;

/**
 *
 */
public class MyTest2 {

    /**
     * 測試方法中：調用dao的方法
     * Student student = dao.selectById(1003)
     *
     * 1. dao：通過反射能夠得到 全限定名稱
     *      dao是從StudentDao類似的，全限定名稱 com.ives.dao.StudentDao
     * 2. selectById：dao中的方法名稱，方法名稱就是mapper文件中標籤的id
     *      通過dao.selectById()能得到 sqlId = "com.ives.dao.StudentDao.selectById";
     * 3. 確定調用SqlSession的那個方法
     *      1. 根據dao接口的方法返回中，如果返回是一個對象，例如Student，調用SqlSession.selectOne();
     *          如果dao接口中的方法返回List，調用SqlSession的selectList();
     *      2. 根據mapper文件的標籤，如果標籤是<insert>，調用SqlSession.insert()方法
     *
     *  mybatis框架，發現使用dao的方法調用能確定 執行sql語句的必要信息，mybatis簡化dao對象的實現。
     *  由mybatis框架在程序執行期間，根據Dao接口，創建一個內存中的 接口的實現類對象。
     *  mybatis把這個技術叫作 dao技術(動態代理，dao的動態代理)。
     *
     *  dao代理技術： 由mybatis創建StudentDao接口的實現類 Proxy(StudentDaoImpl)，
     *              使用框架創建的StudentDaoImpl代替自己手工實現的StudentDaoImpl類的功能
     *              ，不用開發人員寫dao接口的實現類。
     *  使用dao的代理要求：
     *  1. mapper文件中的namespace，必須是dao接口的全限定名稱
     *  2. mapper文件中標籤的id是dao接口的方法名稱(一模一樣)
     */

    @Test
    public void testSelectOne(){
        // 要使用Dao的方法
        // 接口類型 變量 = new 接口的實現類();
        StudentDao dao = new StudentDaoImpl();

        Student student = dao.selectById(1003);
        System.out.println("通過dao執行方法，得到的對象 === "+student);
    }

    @Test
    public void testSelectStudents(){
        StudentDao dao = new StudentDaoImpl();
        List<Student> students = dao.selectStudents();
        students.forEach(stu-> System.out.println("stu = "+stu));
    }

    @Test
    public void testInsert(){
        StudentDao dao = new StudentDaoImpl();

        Student student = new Student();
        student.setId(1009);
        student.setName("小芳");
        student.setEmail("feng@gmail.com");
        student.setAge(20);

        dao.insertStudent(student);
    }
}
