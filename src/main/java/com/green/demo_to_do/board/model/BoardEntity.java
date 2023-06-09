package com.green.demo_to_do.board.model;

import lombok.Data;

@Data
public class BoardEntity {
    private Long iboard;
    private String title;
    private String ctnt;
    private Long iuser;
    private String createdAt;
    private String updatedAt;

}
