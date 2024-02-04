package cs.spring.repository;

import cs.spring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//Spring Data JPA 가 JpaRepository를 가지고 있으면 인터페이스에 대한 구현체를 만들어서 등록해준다. (구현 클래스 필요 없음)
public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {

    //JPQL select m from Member m where m.name = ? <- findBy__ 필요한 부분을 넣으면 알아서 쿼리를 만들어서 구현함.
    @Override
    Optional<Member> findByName(String name);
}
