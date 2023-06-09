package com.green.demo_to_do.board;

import com.green.demo_to_do.board.model.*;
import com.green.demo_to_do.cmt.CmtService;
import com.green.demo_to_do.cmt.model.CmtDelDto;
import com.green.demo_to_do.cmt.model.CmtPgDto;
import com.green.demo_to_do.cmt.model.CmtRes;
import com.green.demo_to_do.user.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BoardService {

    private final BoardMapper mapper;
    private final CmtService cmtService;
    @Autowired
    public BoardService(BoardMapper mapper,CmtService cmtService) {
        this.cmtService=cmtService;
        this.mapper = mapper;
    }
    public Long insBoard(BoardInsDto dto){
        BoardEntity entity = new BoardEntity();
        entity.setTitle(dto.getTitle());
        entity.setCtnt(dto.getCtnt());
        entity.setIuser(dto.getIuser());
         mapper.insBoard(entity);
         return entity.getIboard();

    }
    public BoardRes selBoard(BoardSelDto dto){
        int num = dto.getPage() - 1;
        dto.setStidx(num*dto.getRow());
        double maxBoardPage = mapper.maxBoardPage();
        int maxPage = (int)Math.ceil(maxBoardPage / dto.getRow());
        int isMore = dto.getPage()< maxPage ? 1 : 0;

        List<BoardSelVo> boardSelVos = mapper.selBoard(dto);
        BoardRes build = BoardRes.builder().row(dto.getRow()).page(dto.getPage())
                .isMore(isMore).liset(boardSelVos).maxPage(maxPage).build();
        return build;
    }
    public BoardDetRes selDetBoard(BoardDetSelDto dto){
        BoardDetSelVo boardDetSelVo = mapper.selDetBoard(dto);
        CmtPgDto dto1 = new CmtPgDto();
        dto1.setIboard(dto.getIboard());
        dto1.setPage(1);
        CmtRes cmtRes = cmtService.selBoardCmt(dto1);
       return BoardDetRes.builder().cmts(cmtRes).board(boardDetSelVo).build();

    }
    public int updBoard(BoardEntity entity){
        return mapper.updBoard(entity);
    }
    @Transactional(rollbackFor = Exception.class)
    public int delBoard(BoardDelDto dto)throws Exception{
        CmtDelDto dto1 = new CmtDelDto();
        dto1.setIboard(dto.getIboard());
        dto1.setIuser(dto.getIuser());
        cmtService.delBoardCtm(dto1);
        int delBoard = mapper.delBoard(dto);


           if (delBoard==0){

               throw new Exception();
           }
           return delBoard;




    }
}
