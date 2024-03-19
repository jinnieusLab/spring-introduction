package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional; //null일 수 있는 멤버 변수에 사용

public interface MemberRepository {
    // 기능 4가지
    Member save(Member member);
    Optional<Member> findById(Long id);
    Optional<Member> findByName(String name);
    List<Member> findAll(); // 모든 고객 반환
}
