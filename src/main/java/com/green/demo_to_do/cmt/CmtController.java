package com.green.demo_to_do.cmt;

import com.green.demo_to_do.cmt.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/board")
public class CmtController {
    private final CmtService service;
    @Autowired
    public CmtController(CmtService service) {
        this.service = service;
    }
    @PostMapping("/{iboard}/cmt")
    public Long insBoardCmt(@RequestParam Long iboard, @RequestBody CmtInsDto dto){
        CmtEntity entity = new CmtEntity();
        entity.setCtnt(dto.getCtnt());
        entity.setIuser(dto.getIuser());
        entity.setIboard(iboard);
        return service.insBoardCmt(entity);
    }
    @GetMapping("/{iboard}/cmt")
    public CmtRes selBoardCmt(@RequestParam Long iboard,@RequestParam (defaultValue = "1")int page){
        CmtPgDto dto = new CmtPgDto();
        dto.setPage(page);
        dto.setIboard(iboard);
        return service.selBoardCmt(dto);
    }
    @PutMapping("/cmt")
    public int reBoardCmt(@RequestBody CmtUpDto dto){
         return service.reBoardCmt(dto);
    }
    @DeleteMapping("/cmt/{iboardCmt}")
    public int delBoardCtm(@PathVariable Long iboardCmt,@RequestParam Long iuser){
        CmtDelDto dto = new CmtDelDto();
        dto.setIboardCmt(iboardCmt);
        dto.setIuser(iuser);
        return service.delBoardCtm(dto);
    }
}
