package kr.co.adamsoft.club.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Log4j2
//공통 URL 설정
@RequestMapping("/sample/")
public class SampleController {
    @GetMapping("/all")
    public void exAll(){
        log.info("모든 유저가 접근 가능");
    }

    @GetMapping("/member")
    public void exMember(){
        log.info("로그인 한 유저만 접근 가능");
    }

    @GetMapping("/admin")
    public void exAdmin(){
        log.info("관리자만 접근 가능");
    }
}
