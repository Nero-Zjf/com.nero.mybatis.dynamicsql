package com.nero.mybatis.dynamicsql.mapper;

import com.nero.mybatis.dynamicsql.domain.po.UserPO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    List<UserPO> getUserByName(@Param("name") String name, @Param("isLike") boolean isLike);

    List<UserPO> getUserByName2(@Param("name") String name, @Param("isLike") int isLike);

    List<UserPO> getUserByNameSex(@Param("name") String name, @Param("sex") String sex);

    List<UserPO> getUserByNameSex2(@Param("name") String name, @Param("sex") String sex);

    int updUser(UserPO userPO);

    List<UserPO> getUserByIds(@Param("ids") int[] ids);
}
