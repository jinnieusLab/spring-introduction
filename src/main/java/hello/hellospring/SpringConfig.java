package hello.hellospring;

import hello.hellospring.repository.JdbcMemberRepository;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    private DataSource dataSource;

    @Autowired // 생성자 DI
    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository()); //command + P 하면 생성자 괄호 사이에 뭘 적어줘야할지 뜬다.
    }

    @Bean
    public MemberRepository memberRepository() { // 인터페이스
//        return new MemoryMemberRepository(); // 구현체인 메모리멤버리포지토리를 넣어줘야함.
        return new JdbcMemberRepository(dataSource);
    }
}
