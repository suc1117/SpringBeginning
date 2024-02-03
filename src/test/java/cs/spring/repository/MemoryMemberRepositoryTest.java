package cs.spring.repository;

import cs.spring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    // Clear the shared data at the end of each test case
    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("Test");
        repository.save(member);

        Member result = repository.findById(member.getId()).get();  //Optional
        assertThat(result).isEqualTo(member);    //Check that both the result(actual) and the member(expected) are same
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("Test1");
        repository.save(member1);

        //Shift + F6 = rename all
        Member member2 = new Member();
        member2.setName("Test2");
        repository.save(member2);

        Member result = repository.findByName("Test1").get();
        //Member result = repository.findByName("Test2").get(); //Fail

        assertThat(result).isEqualTo(member1);
    }


    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("Test1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("Test2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }
}
