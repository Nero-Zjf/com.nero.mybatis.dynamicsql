package com.nero.mybatis.dynamicsql;

import com.nero.mybatis.dynamicsql.domain.po.UserPO;
import com.nero.mybatis.dynamicsql.mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class Test {
    public static void main(String[] args) throws IOException {
        try {
            //-------------------------使用 XML 构建 SqlSessionFactory-------------------------
            // 读取配置文件 mybatis-config.xml
            InputStream config = Resources
                    .getResourceAsStream("mybatis-config.xml");
            // 根据配置文件构建SqlSessionFactory
            SqlSessionFactory ssf = new SqlSessionFactoryBuilder()
                    .build(config);
            //--------------------------------------------------------

            // 通过 SqlSessionFactory 创建 SqlSession
            SqlSession ss = ssf.openSession();

            //获取Mapper
            UserMapper userMapper = ss.getMapper(UserMapper.class);

            // 根据用户名称（精确查询或模糊查询）查询用户
            System.out.println("--------------根据用户名称（精确查询或模糊查询）查询用户--------------------------------");
            List<UserPO> userPOList = userMapper.getUserByName("to", true);
            System.out.println(userPOList);

            // 根据用户名称（精确查询或模糊查询）查询用户 2
            System.out.println("--------------根据用户名称（精确查询或模糊查询）查询用户 2--------------------------------");
            List<UserPO> userPOList2 = userMapper.getUserByName2("to", -1);
            System.out.println(userPOList2);

            //  根据用户名称或性别 查询用户
            System.out.println("-------------- 根据用户名称或性别 查询用户--------------------------------");
            List<UserPO> userPOList3 = userMapper.getUserByNameSex("tom", "1");
            System.out.println(userPOList3);

            //  根据用户名称或性别 查询用户 2
            System.out.println("-------------- 根据用户名称或性别 查询用户 2--------------------------------");
            List<UserPO> userPOList4 = userMapper.getUserByNameSex2("tom", "1");
            System.out.println(userPOList4);

            // 更新用户
            System.out.println("更新用户");
            UserPO updUserPO = userPOList4.get(0);
            updUserPO.setSex("1");
            int reInt = userMapper.updUser(updUserPO);

            //  根据id范围 查询用户
            System.out.println("-------------- 根据id范围 查询用户--------------------------------");
            List<UserPO> userPOList5 = userMapper.getUserByIds(new int[]{1, 2, 3});
            System.out.println(userPOList5);

            // 提交事务
            ss.commit();
            // 关闭 SqlSession
            ss.close();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
