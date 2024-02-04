package cs.spring.controller;

import cs.spring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller /* 외부 요청을 받음 */
public class MemberController {

    private final MemberService memberService;

    @Autowired  //DI
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
}
