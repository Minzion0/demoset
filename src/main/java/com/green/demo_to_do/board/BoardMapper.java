package com.green.demo_to_do.board;

import com.green.demo_to_do.board.model.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {
    Long insBoard(BoardEntity entity);
    int maxBoardPage();
    List<BoardSelVo>selBoard(BoardSelDto dto);
    BoardDetSelVo selDetBoard(BoardDetSelDto dto);
    int updBoard(BoardEntity entity);
    int delBoard(BoardDelDto dto);
}
