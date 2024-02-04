package cs.spring.repository;

import cs.spring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Repository /* 데이터를 저장 */
public class MemoryMemberRepository implements MemberRepository {

    private static Map<Long, Member> store = new HashMap<>();
    private static AtomicLong sequence = new AtomicLong(0);

    @Override
    public Member save(Member member) {
        member.setId(sequence.addAndGet(1));      //set member id
        store.put(member.getId(), member);              //insert member in store
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));  // returns a member that matches the given id
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))    //returns a member that matches the given name
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values()); //return all members
    }

    public void clearStore() {
        store.clear();
    }
}
