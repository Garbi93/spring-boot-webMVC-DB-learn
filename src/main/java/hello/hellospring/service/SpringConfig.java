package hello.hellospring.service;

import hello.hellospring.repository.*;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

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

//    @Bean
//    public MemberRepository memberRepository() {
//        // Repository 입맛대로 사용하기
//        // return new MemoryMemberRepository(); //
//        // return new JdbcMemberRepository(dataSource); // 순수 JDBC
//        // return new JdbcTemplateMemberRepository(dataSource); // JDBCTemplate 사용
//        // return new JpaMemberRepository(em); // JPA 사용
//    }

}
