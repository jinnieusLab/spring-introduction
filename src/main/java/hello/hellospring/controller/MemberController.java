package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MemberController {
    private MemberService memberService; // 객체 생성 새로x

        //생성자로 연결
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm"; //template에서 찾음
    }

    @PostMapping("/members/new")
    public String create(MemberForm form) {
        Member member = new Member(); // Member import시 우리가 만든 것으로 하기
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";

    }
}
