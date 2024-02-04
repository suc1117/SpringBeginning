package cs.spring;

import cs.spring.repository.JpaMemberRepository;
import cs.spring.repository.MemberRepository;
import cs.spring.repository.MemoryMemberRepository;
import cs.spring.service.MemberService;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/* 직접 Spring bean 등록 */
/*  상황에 따라 구현 클래스를 변경해야 하면 Config를 통해 스프링 빈을 등록해서 사용하자. */
@Configuration
public class SpringConfig {

    private final MemberRepository memberRepository;

    @Autowired
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository);
    }

    /*
    @Bean
    public MemberRepository memberRepository() {
        //return new MemoryMemberRepository();
        return new JpaMemberRepository(em);     //구현 클래스를 변경하는 경우 단순히 반환값만 바꿔주면 된다. (OCP, Open-Closed Principle)
                                                //확장은 열려있고 변경, 수정은 닫혀있다.
    }
     */
}
