package com.green.demo_to_do.board;

import com.green.demo_to_do.board.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/board")
public class BoardController {
    private final BoardService service;
    @Autowired
    public BoardController(BoardService service) {
        this.service = service;
    }
    @PostMapping
    public Long postBoard(@RequestBody BoardInsDto dto){
        return service.insBoard(dto);
    }
    @GetMapping
    public BoardRes selBoard(@RequestParam (defaultValue = "1") int page, @RequestParam (defaultValue = "30") int row){
        BoardSelDto dto = new BoardSelDto();
        dto.setRow(row);
        dto.setPage(page);
        return service.selBoard(dto);
    }
    @GetMapping("/{iboard}")
    public BoardDetRes selDetBoard(@PathVariable Long iboard){
        BoardDetSelDto dto = new BoardDetSelDto();
        dto.setIboard(iboard);
        return service.selDetBoard(dto);
    }
    @PutMapping("/{iboard}")
    public int updBoard(@RequestBody BoardUpdDto dto,@RequestParam Long iboard){
        BoardEntity entity = new BoardEntity();
        entity.setIboard(iboard);
        entity.setCtnt(dto.getCtnt());
        entity.setTitle(dto.getTitle());
        entity.setIuser(dto.getIuser());
        return service.updBoard(entity);
    }
    @DeleteMapping("/{iboard}")
    public int delBoard(@PathVariable Long iboard,@RequestParam Long iuser){
        BoardDelDto dto = new BoardDelDto();
        dto.setIboard(iboard);
        dto.setIuser(iuser);

        try {
            return service.delBoard(dto);
        } catch (Exception e) {
            return 0;
        }
    }
}
