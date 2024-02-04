package cs.spring.controller;

import cs.spring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MemberController {

    /*  Setter Injection : public 으로 노출되는 문제가 있다 -> 누구나 종속된 클래스의 Setter를 쓸 수 있게됨.
    private MemberService memberService;

    @Autowired
    public void setMemberService(MemberService memberService) {
        this.memberService = memberService;
    }
    */

    /* Constructor Injection
    *  특징
    *  1) 불변(실행부터 종료까지 변경되지 않음)
    *  2) 누락(의존관계 누락 시, 실행되지 않음)
    *  3) final 키워드 사용
    *  4) 순환 잠조 방지(BeanCurrentlyInCreationException 발생으로 문제를 알 수 있음)
    */
    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

}
