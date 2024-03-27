package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

// 아래 MemoryMemberRepository를 테스트 할 것임.
@Repository
public class MemoryMemberRepository implements MemberRepository{ // 옵션 엔터

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id)); // 없으면 null일 수 있게
        // store에서 꺼내기
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name)) // 람다
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values()); // store에 있는 values = 멤버들
    }

    public void clearStore() {
        store.clear();
    }
}
