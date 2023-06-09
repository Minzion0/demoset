package com.green.demo_to_do.user;

import com.green.demo_to_do.user.model.UserDelDto;
import com.green.demo_to_do.user.model.UserInsDto;
import com.green.demo_to_do.user.model.UserPicDto;
import com.green.demo_to_do.user.model.UserSelDto;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService service;
    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping("/join")
    public int joinUser(@RequestBody UserInsDto dto){
      return service.joinUser(dto);
    }

    @PostMapping("/login")
    public int loginUser(@RequestBody UserSelDto dto){
        return service.loginUser(dto);
    }

    @PutMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public int updUserPic(@RequestPart MultipartFile pic, @RequestParam Long iuser){
        UserPicDto dto = new UserPicDto();
        dto.setIuser(iuser);
      return   service.userMinPic(pic,dto);
    }

    @DeleteMapping
    public int delUser(@RequestParam Long iuser,@RequestParam String upw){
        UserDelDto dto = new UserDelDto();
        dto.setUpw(upw);
        dto.setIuser(iuser);
        try {
          return service.delUser(dto);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}
