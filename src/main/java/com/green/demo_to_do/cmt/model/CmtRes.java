package com.green.demo_to_do.cmt.model;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.util.List;
@Getter
@Builder
public class CmtRes {
    private  CmtPgDto pl;
    private List<CmtSelVo> cmtList;
}
