package cs.spring.service;

import cs.spring.domain.Member;
import cs.spring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    // Initialize the shared data at the first of each test case
    @BeforeEach
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }


    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    void 회원가입() {
        //given
        Member member = new Member();
        member.setName("Test1");

        //when
        Long saveId = memberService.join(member);

        //then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    void 중복회원_예외() {
        //given
        Member member1 = new Member();
        member1.setName("Test1");

        Member member2 = new Member();
        member2.setName("Test1");

        //when
        memberService.join(member1);

        //then
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2)); //Pass if get an IllegalStateException
        assertThat(e.getMessage()).isEqualTo("A member that already exists.");
/*
        try {
            memberService.join(member2);
        } catch (IllegalStateException e) {
            assertThat(e.getMessage()).isEqualTo("A member that already exists.");
        }
*/
    }

    @Test
    void 전체조회() {
        //given
        Member member1 = new Member();
        member1.setName("Test1");

        Member member2 = new Member();
        member2.setName("Test2");

        memberService.join(member1);
        memberService.join(member2);

        //when
        List<Member> memberList = memberService.findMembers();

        //then
        assertThat(2).isEqualTo(memberList.size());
    }

    @Test
    void 특정맴버조회() {
        //given
        Member member1 = new Member();
        member1.setName("Test1");

        Member member2 = new Member();
        member2.setName("Test2");

        memberService.join(member1);
        memberService.join(member2);

        //when
        Member findMember = memberService.findOne(member2.getId()).get();

        //then
        assertThat(member2.getId()).isEqualTo(findMember.getId());
    }
}