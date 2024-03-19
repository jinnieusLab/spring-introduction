package hello.hellospring.repository;


import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

// MemoryMemberRepository를 테스트 할 것임.
public class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository(); // MemoryMemberRepository를 테스트 할 것임. 인터페이스 대신

    // 콜백 함수. 각 Test 끝나고 돌아가게
    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring");

        repository.save(member);
        Member result = repository.findById(member.getId()).get();

//        System.out.println("result = " + (result == member));
//        Assertions.assertEquals(member, result);
        assertThat(result).isEqualTo(member);
//        assertThat(member).isEqualTo(null); // 빨간불 뜨겠쥬
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get(); // get() 중요
        // null아면 에러

        assertThat(result).isEqualTo(member1);
        //member2면 에러
    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }

}
