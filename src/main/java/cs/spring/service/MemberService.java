package cs.spring.service;

import cs.spring.domain.Member;
import cs.spring.repository.MemberRepository;
import cs.spring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service /* 비즈니스 로직을 만듬 */
public class MemberService {

    private final MemberRepository memberRepository;

    //Dependency Injection(DI) - Injecting one object into another to connect two objects with dependencies
    @Autowired  //DI
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * Sign up (회원 가입)
     */
    public Long join(Member member) {
        validateDuplicatedMember(member); //Checking for members who have same name

        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicatedMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("A member that already exists.");
                });
    }

    /**
     * Find all members (전체 조회)
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
