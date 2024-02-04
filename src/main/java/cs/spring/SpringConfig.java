package cs.spring;

import cs.spring.repository.MemberRepository;
import cs.spring.repository.MemoryMemberRepository;
import cs.spring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/* 직접 Spring bean 등록 */
/*  상황에 따라 구현 클래스를 변경해야 하면 Config를 통해 스프링 빈을 등록해서 사용하자. */
@Configuration
public class SpringConfig {

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
}
