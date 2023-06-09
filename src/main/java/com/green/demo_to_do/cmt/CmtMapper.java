package com.green.demo_to_do.cmt;

import com.green.demo_to_do.cmt.model.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CmtMapper {
    Long insBoardCmt(CmtEntity entity);
    List<CmtSelVo> selBoardCmt(CmtPgDto dto);
    int maxBoardcmt(CmtPgDto dto);
    int reBoardCmt(CmtUpDto dto);
    int delBoardCtm(CmtDelDto dto);
}
