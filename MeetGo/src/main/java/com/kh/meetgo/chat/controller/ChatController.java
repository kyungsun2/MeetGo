package com.kh.meetgo.chat.controller;

import com.google.gson.Gson;
import com.kh.meetgo.chat.model.dto.ChatListDto;
import com.kh.meetgo.chat.model.service.ChatService;
import com.kh.meetgo.chat.model.vo.Chat;
import com.kh.meetgo.common.config.S3Uploader;
import com.kh.meetgo.gosu.model.vo.CategorySmall;
import com.kh.meetgo.gosu.model.vo.Estimate;
import com.kh.meetgo.member.model.vo.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@Controller
@RequiredArgsConstructor
public class ChatController {
    private final ChatService chatService;
    private final S3Uploader s3Uploader;

    @GetMapping(value = "/chat.ct")
    public String chatViewForm(String type, Model model, HttpSession session) {
        model.addAttribute("type", type);
        Member m = (Member) session.getAttribute("loginUser");
        ArrayList<ChatListDto> chatroomList = chatService.selectChatroomList(m);
        model.addAttribute("chatroomList", chatroomList);
        return "chat/chat";
    }

    @ResponseBody
    @GetMapping(value = "/chatlist")
    public String selectChatList(String chatroomNo) {
        ArrayList<Chat> chatList = null;
        if (chatroomNo != null) {
            int roomNo = Integer.parseInt(chatroomNo);
            chatList = chatService.selectChatList(roomNo);
        }
        return new Gson().toJson(chatList);
    }

    @ResponseBody
    @GetMapping(value = "/chatUserInfo")
    public String selectChatUserInfo(String chatroomNo) {

        Member m = chatService.selectChatUserInfo(chatroomNo);
        return new Gson().toJson(m);
    }

    @GetMapping(value = "/estimate.ct")
    public String estimateForm() {
        return "chat/estimate";
    }


    @ResponseBody
    @PostMapping(value = "/insertEstimate")
    public String insertEstimate(@RequestBody Estimate estimate) {
        int result = chatService.insertEstimate(estimate);
        return String.valueOf(estimate.getEstNo());
    }

    @ResponseBody
    @GetMapping(value = "selectAllCategory", produces = "text/json; charset=UTF-8")
    public String selectAllCategory(String gosuNo){
        ArrayList<CategorySmall> list = chatService.selectAllCategory(gosuNo);
        return new Gson().toJson(list);
    }

    @ResponseBody
    @GetMapping(value = "/searchEstimate", produces = "text/json; charset=UTF-8")
    public String searchEstimate(@RequestParam(value = "estimateNo", required = false) String estimateNo) {
        int estNo = 0;
        if (estimateNo != null && !estimateNo.isEmpty()) {
            try {
                estNo = Integer.parseInt(estimateNo.trim());
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        Estimate estimate = chatService.searchEstimate(estNo);
        return new Gson().toJson(estimate);
    }
    @ResponseBody
    @PostMapping(value = "/uploadChatImg", produces = "text/json; charset=UTF-8")
    public String uploadChatImg(@RequestPart(value = "chatImg", required = false) MultipartFile file, String uNo,String cNo) throws IOException {
        String content = s3Uploader.upload(file, "chat");
        int userNo = 0; int chatroomNo = 0;
        if(!uNo.isEmpty()) userNo = Integer.parseInt(uNo);
        if(!cNo.isEmpty()) chatroomNo = Integer.parseInt(cNo);
        Chat chat = new Chat();
        chat.setContent(content);
        chat.setSender(userNo);
        chat.setChatroomNo(chatroomNo);
        chat.setType("P");
        System.out.println(chat);
        return new Gson().toJson(content);
    }
}
