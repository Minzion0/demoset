package com.green.demo_to_do.cmt.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CmtSelVo {
    private Long iboardCmt;
    private Long iuser;
    private String ctnt;
    private String createdAt;
}
