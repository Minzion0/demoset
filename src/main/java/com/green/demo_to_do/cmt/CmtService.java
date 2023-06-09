package com.green.demo_to_do.cmt;

import com.green.demo_to_do.cmt.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CmtService {
    private final CmtMapper mapper;
    private final int ROW=5;

    @Autowired
    public CmtService(CmtMapper mapper) {
        this.mapper = mapper;
    }
    public Long insBoardCmt(CmtEntity entity){
        mapper.insBoardCmt(entity);
        return entity.getIboardCmt();
    }
    public CmtRes selBoardCmt(CmtPgDto dto){
        int page = dto.getPage() - 1;
        int stidx = page * ROW;
        double pageM = mapper.maxBoardcmt(dto);
        int maxPage = (int) Math.ceil(pageM / ROW);
        int isMore = maxPage > dto.getPage() ? 1 : 0;
        dto.setStidx(stidx);
        dto.setRow(ROW);
        dto.setIsMore(isMore);
        dto.setMaxPage(maxPage);

        List<CmtSelVo> cmtSelVos = mapper.selBoardCmt(dto);

        CmtRes build = CmtRes.builder().cmtList(cmtSelVos).pl(dto).build();
        return build;

    }
    public int reBoardCmt(CmtUpDto dto){
        return mapper.reBoardCmt(dto);
    }
    public int delBoardCtm(CmtDelDto dto){
        return mapper.delBoardCtm(dto);
    }
}
