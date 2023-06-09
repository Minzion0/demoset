package com.green.demo_to_do.user.model;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public class UserSelVo {
    private Long iuser;
    private String uid;
    private String upw;
    private String nm;
}
