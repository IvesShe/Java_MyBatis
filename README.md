# Java MyBatis

MyBatis 筆記


# 三層架構模式和框架

- 界面層 - SpringMVC
- 業務層 - Spring
- 持久層 - MyBatis

# 安裝MySQL環境

這次使用PHPStudy建立數據環境

https://www.xp.cn/download.html

![image](./images/20210627202432.png)

因為版本的問題，後面的操作有將mysql換成5.1.6
![image](./images/20210627203605.png)

Sqlyog連接成功

![image](./images/20210627203633.png)

新建springdb數據庫及student表

![image](./images/20210627204059.png)


```sql
CREATE TABLE `student` (
  `id` int(11) NOT NULL COMMENT '主鍵',
  `name` varchar(80) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '學生姓名',
  `email` varchar(80) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '郵箱',
  `age` int(11) DEFAULT NULL COMMENT '年齡',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci
```
# MyBatis安裝

參考官網

https://mybatis.org/mybatis-3/zh/getting-started.html

![image](./images/20210627204348.png)
 
MyBatis Github

https://github.com/mybatis/mybatis-3

![image](./images/20210627204547.png)

# 檢查IDEA環境

專案進行前，先檢查IDEA環境的設置

![image](./images/20210628101506.png)

![image](./images/20210628101527.png)

![image](./images/20210628102007.png)

# 創建新的模塊

![image](./images/20210628102619.png)

![image](./images/20210628102638.png)

![image](./images/20210628102718.png)

![image](./images/20210628102845.png)

![image](./images/20210628102855.png)

![image](./images/20210628102944.png)

![image](./images/20210628103038.png)

![image](./images/20210628103108.png)

創建完成

![image](./images/20210628103357.png)

修改jdk版本

![image](./images/20210628103716.png)
# 修改pom.xml

- 加入依賴mybatis依賴、mysql驅動
    ```xml
    <!--mybatis-->
    <dependency>
      <groupId>org.mybatis</groupId>
      <artifactId>mybatis</artifactId>
      <version>3.5.1</version>
    </dependency>

    <!--mysql驅動-->
    <!-- https://mvnrepository.com/artifact/mysql/mysql-connector-java -->
   <dependency>
      <groupId>mysql</groupId>
      <artifactId>mysql-connector-java</artifactId>
      <version>5.1.6</version>
    </dependency>

    ```

- 在 build 加入資源插件
    ```xml
    <build>
        <!--資源插件-->
        <resources>
        <resource>
            <!--所在的目錄-->
            <directory>src/main/java</directory>
            <includes>
            <!--包括目錄下的.properties.xml文件都會掃描到-->
            <include>**/*.properties</include>
            <include>**/*.xml</include>
            </includes>
            <!--filtering 選項false不啟用過濾器， *.property已經起到過濾的作用了-->
            <filtering>false</filtering>
        </resource>
        </resources>
  </build>
    ```

pom.xml完整
```xml
<?xml version="1.0" encoding="UTF-8"?>

<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
  <modelVersion>4.0.0</modelVersion>

  <!--當前項目的座標-->
  <groupId>com.ives</groupId>
  <artifactId>mybatis-01-first</artifactId>
  <version>1.0</version>

  <name>mybatis-01-first</name>
  <!-- FIXME change it to the project's website -->
  <url>http://www.example.com</url>

  <properties>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    <maven.compiler.source>1.8</maven.compiler.source>
    <maven.compiler.target>1.8</maven.compiler.target>
  </properties>

  <!--依賴列表-->
  <dependencies>
    <!--mybatis-->
    <dependency>
      <groupId>org.mybatis</groupId>
      <artifactId>mybatis</artifactId>
      <version>3.5.1</version>
    </dependency>

    <!--mysql驅動-->
    <!-- https://mvnrepository.com/artifact/mysql/mysql-connector-java -->
   <dependency>
      <groupId>mysql</groupId>
      <artifactId>mysql-connector-java</artifactId>
      <version>5.1.6</version>
    </dependency>


    <!--單元測試-->
    <dependency>
      <groupId>junit</groupId>
      <artifactId>junit</artifactId>
      <version>4.11</version>
      <scope>test</scope>
    </dependency>
  </dependencies>

  <build>
     <!--資源插件-->
    <resources>
        <resource>
            <!--所在的目錄-->
            <directory>src/main/java</directory>
            <includes>
                <!--包括目錄下的.properties.xml文件都會掃描到-->
                <include>**/*.properties</include>
                <include>**/*.xml</include>
            </includes>
            <!--filtering 選項false不啟用過濾器， *.property已經起到過濾的作用了-->
            <filtering>false</filtering>
        </resource>
    </resources>
  </build>
</project>

```

# 創建實體類Student，定義屬性，屬性名和列名保持一致

![image](./images/20210628114241.png)

```java
package com.ives.domain;

public class Student {

    // 屬性名和列名一樣
    private  Integer id;
    private String name;
    private String email;
    private Integer age;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "學生實體的信息Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                '}';
    }
}

```

# 創建Dao接口，定義操作數據庫的方法

StudentDao.java
```java
package com.ives.dao;

import com.ives.domain.Student;

public interface StudentDao {

    // 查詢一個學生
    Student selectStudentById(Integer id);
}

```

# 創建xml文件(mapper文件)，寫sql語句

mybatis框架推薦把sql語句和java代碼分開

mapper文件：定義和dao接口在同一目錄，一個表一個mapper文件

StudentDao.xml
```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.ives.dao.StudentDao">
<!--    <select id="selectBlog" resultType="Blog">-->
<!--        select * from Blog where id = #{id}-->
<!--    </select>-->

    <!--查詢一個學生的student
        <select>： 表示查詢操作，裡面是select語句
        id：要執行的sql語句的唯一標籤，是一個自定義字符串。
            推薦使用dao接口中的方法名稱
        resultType：告訴mybatis，執行sql語句,把數據賦值給那個類型的java對象。
                     resultType的值現在使用的java對象的全限定名稱。
    -->
    <select id="selectStudentById" resultType="com.ives.domain.Student">
        select id,name,email,age from student where id = 1001
    </select>
</mapper>

<!--
    1. 約束文件
    http://mybatis.org/dtd/mybatis-3-mapper.dtd
    約束文件作用：定義和限制當前文件中可以使用的標籤和屬性，以及標籤出現的順序。

    2. mapper是根標籤
    namespace：命名空間，必須有值，不能為空，唯一值。
               推薦使用Dao接口的全限定名稱。
    作用： 參與識別sql語句的作用。

    3. 在mapper裡面可以寫<insert>、<update>、<delete>、<select>等標籤
       <insert>裡面是insert語句，表示執行insert操作
       <update>裡面是update語句
       <delete>裡面是delete語句
       <select>裡面是select語句
-->
```

# 創建mybatis的主配置文件(xml文件)

放在resources目錄下

- 定義創建連接實例的數據源(DataSource)對象
- 指定其它mapper文件的位置

mybatis.xml
```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>
    <environments default="development">
        <environment id="development">
            <transactionManager type="JDBC"/>
            <!--配置數據源：創建Connection對象-->
            <dataSource type="POOLED">
                <!--driver：驅動的內容-->
                <property name="driver" value="com.mysql.jdbc.Driver"/>
                <!--連接數據庫的url-->
                <property name="url" value="jdbc:mysql://localhost:3306/springdb?useUnicode=true&amp;characterEncoding"/>
                <!--用戶名-->
                <property name="username" value="root"/>
                <!--密碼-->
                <property name="password" value="root"/>
            </dataSource>
        </environment>
    </environments>

    <!--指定其它mapper文件的位置：
        其它mapper文件的目的是找到其它文件的sql語句
    -->
    <mappers>
        <!--
            使用mapper的resource屬性指定mapper文件的路徑。
            這個路徑是從target/classes路徑開啟的

            使用注意：
                resource="mapper文件的路徑，使用/分割路徑"
                一個mapper resource 指定一個mapper文件
        -->
        <mapper resource="com/ives/dao/StudentDao.xml"/>
    </mappers>
</configuration>
```

# 創建測試的內容

使用main方法，測試mybatis訪問數據庫，也可以使用junit訪問數據庫

MyTest.java
```java
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
}

```

# 先手動新增一筆資料

![image](./images/20210628194947.png)


# 運行

成功獲取結果

![image](./images/20210628195111.png)

# 占位符


```xml
<!-- #{studentId}：占位符，表示從java程序中傳入過來的數據 -->
<select id="selectStudentById" resultType="com.ives.domain.Student">
        select id,name,email,age from student where id = #{studentId}
    </select>
```

# 設置日誌

![image](./images/20210628210727.png)

![image](./images/20210628210755.png)

mybatix.xml
```xml
    <!--設置日誌-->
    <settings>
        <setting name="logImpl" value="STDOUT_LOGGING"/>
    </settings>
```

# 自動提交

Setting autocommit

當sql語句執行完畢後，提交事務。數據庫更新操作直接保存數據。

# 手動提交

在需要提交事務的位置，執行方法，提交事務或者回願事務。

# 若資源檔無法正確拷貝到target目錄的解決方法

## 方法一

重新build專案、模塊

![image](./images/20210628213023.png)

## 方法二

點擊pom.xml reload專案

![image](./images/20210628213103.png)

## 方法三

maven先clean，再complie

![image](./images/20210628213152.png)

## 方法四

Invalidate and Restart

![image](./images/20210628213227.png)

![image](./images/20210628213247.png)

## 方法五

手動拷貝對應的資源檔...