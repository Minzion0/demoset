package com.green.demo_to_do.board.model;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class BoardRes {
    private int isMore;
    private int page;
    private int row;
    private int maxPage;
    private List<BoardSelVo> liset;
}
