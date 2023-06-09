package com.green.demo_to_do.board.model;

import com.green.demo_to_do.cmt.model.CmtRes;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Getter
@Builder
public class BoardDetRes {
    private BoardDetSelVo board;
    private CmtRes cmts;
}
