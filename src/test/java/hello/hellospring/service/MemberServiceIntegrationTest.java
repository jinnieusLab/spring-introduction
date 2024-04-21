package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
class MemberServiceIntegrationTest {

    @Autowired MemberService  memberService;
    @Autowired MemberRepository memberRepository;

    @Test
    void 회원가입() {
        //given <- 주어진 데이터 기반
        Member member = new Member();
        member.setName("spring");
        // 얘도 이름이 spring이라면 당연히 안될 것. 왜냐면 데이터 쌓여서 join X -> 메모리 리포지토리 clear의 중요성
        // 데이터 clear 구현해주면 이름 spring이어도 괜찮음.

        //when <- 이걸 검증하는구나
        Long saveId = memberService.join(member);

        //then <- 검증부
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외(){
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring"); // 이름이 똑같은 상황

        //when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

        //then
    }
}