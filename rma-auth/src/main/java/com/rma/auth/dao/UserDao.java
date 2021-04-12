package com.rma.auth.dao;


import com.rma.auth.vo.UserDto;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 * @version 1.0
 **/
@Repository
public class UserDao {

    //根据账号查询用户信息
    public UserDto getUserByUsername(String username){
        UserDto user = new UserDto();
        user.setUsername("glf");
        user.setPassword("$2a$10$aFsOFzujtPCnUCUKcozsHux0rQ/3faAHGFSVb9Y.B1ntpmEhjRtru");
        user.setId("1");
        return user;
    }

    //根据用户id查询用户权限
    public List<String> findPermissionsByUserId(String userId){
        List<String> permissions = new ArrayList<>();
        permissions.add("add");
        permissions.add("modify");
        permissions.add("select");
        return permissions;
    }
}
