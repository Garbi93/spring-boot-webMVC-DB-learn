package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    // 한 메서드가 작동을 마칠때마다 해당 어노테이션이 붙은 메소드를 실행 시킨다.
    // 테스트 코드는 서로 의존 관계가 없이 설계가 되어야 하고
    // 그렇기 위해서는 하나의 메서드가 실행되고 난 후에 공용 데이터를 클리어 해주어야 한다.
    // 테스트 코드 부터 작성 해서 코드를 만드는 것을 테스트 주도 개발 이라하고 TDD 라고 한다.
    public void afterEach() {
        repository.clearStore();
        // 저장소를 지워주는 메소드 실행
    }

    @Test
    public void save () {
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();
        assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();

        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll () {
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