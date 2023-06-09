package com.green.demo_to_do.board.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BoardDetSelVo {
    private Long iboard;
    private String title;
    private String ctnt;
    private String createdAt;
}
