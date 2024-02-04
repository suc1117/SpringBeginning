package cs.spring.controller;

import cs.spring.domain.Member;
import cs.spring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

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

    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    // 등록은 POST 방식 사용
    @PostMapping("/members/new")    //html에 submit 버튼을 누르면 동일 action과 method="post" 에 해당하는 이 곳 메소드 호출
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());

        System.out.println("member = " + member.getName());
        memberService.join(member);

        return "redirect:/";    //홈화면으로 감
    }

    @GetMapping("/members")
    public String list(Model model) {   //model -> html
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
