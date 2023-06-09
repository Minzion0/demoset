package com.green.demo_to_do.cmt.model;

import lombok.Data;

@Data
public class CmtPgDto {
    private int isMore;
    private int maxPage;
    private int page;
    private int row;
    private int stidx;
    private Long iboard;
}
