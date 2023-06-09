package com.green.demo_to_do.user;

import com.green.demo_to_do.board.BoardService;
import com.green.demo_to_do.board.model.BoardDelDto;
import com.green.demo_to_do.cmt.CmtService;
import com.green.demo_to_do.cmt.model.CmtDelDto;
import com.green.demo_to_do.user.model.*;
import com.green.demo_to_do.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

@Service
public class UserService {

    private final UserMapper mapper;
    private final CommonUtils utils;
    private final CmtService cmtService;
    private final BoardService boardService;
    @Value("${file.dir}")
    private String fileDir;

    @Autowired
    public UserService(UserMapper mapper,CommonUtils utils,CmtService cmtService,BoardService boardService) {
        this.cmtService=cmtService;
        this.boardService=boardService;
        this.mapper = mapper;
        this.utils=utils;
    }
    public int joinUser(UserInsDto dto){
        String npw = utils.encodeSha256(dto.getUpw());
        dto.setUpw(npw);
        return mapper.joinUser(dto);
    }
    public int loginUser(UserSelDto dto){
        String pw = utils.encodeSha256(dto.getUpw());
        UserSelVo Vo = mapper.loginUser(dto);
        if (Vo==null){
            return 3;
        }

        if (Vo.getUpw().equals(pw)){
            return 1;
        }else {
            return 2;
        }


    }
    public int userMinPic(MultipartFile pic, UserPicDto dto){

        String exe = pic.getOriginalFilename().substring(pic.getOriginalFilename().lastIndexOf("."));
        String uuid = UUID.randomUUID().toString();
        String filenm = uuid + exe;

        String cemtpath = String.format("user/%d/", dto.getIuser());
        String dirpath = String.format("%s/%s", fileDir, cemtpath);

        File dic = new File(dirpath);
        if (!dic.exists()){
            dic.mkdirs();
        }

        String saveFilePath = dirpath + filenm;
        File file = new File(saveFilePath);


        try {
            pic.transferTo(file);
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
        dto.setMainPic(cemtpath+uuid);
        try {
            int result = mapper.userMinPic(dto);
            if (result==0){
                throw new Exception("사진등록 할수없음");
            }
        }catch (Exception e){
            file.delete();
            return 0;
        }
        return 1;
    }
    @Transactional(rollbackFor = Exception.class)
    public int delUser(UserDelDto dto)throws Exception{
        String sPw = utils.encodeSha256(dto.getUpw());
        dto.setUpw(sPw);
        String s = mapper.userPw(dto);
        if (!s.equals(dto.getUpw())){
            return 3;
        }
        BoardDelDto dto2 = new BoardDelDto();
        dto2.setIuser(dto.getIuser());
         boardService.delBoard(dto2);
        int deluser = mapper.delUser(dto);
        if (deluser==0){
            throw new Exception();
        }

        return deluser;
    }
}
