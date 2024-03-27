package hello.hellospring.controller;

import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MemberController {
    private final MemberService memberService; // 객체 생성 새로x

    //생성자로 연결
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
}
