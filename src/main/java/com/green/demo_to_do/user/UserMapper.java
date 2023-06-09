package com.green.demo_to_do.user;

import com.green.demo_to_do.user.model.*;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    int joinUser(UserInsDto dto);
    UserSelVo loginUser(UserSelDto dto);
    int userMinPic(UserPicDto dto);
    int delUser(UserDelDto dto);
    String userPw(UserDelDto dto);
}
