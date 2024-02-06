package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {
    // 서비스는 용어를 비지니스에 가깝게 네이밍 해준다.

    private final MemberRepository memberRepository = new MemoryMemberRepository();

    /*
    * 회원 가입
    * */
    public Long join(Member member) {
        validateDuplicateMember(member);// 중복 회원 검증

        memberRepository.save(member);
        return member.getId();
    }

    // 해당 기능은 ctrl + T 를 누른 후 extract Method 로 추출 하여 외부로 뺀것
    private void validateDuplicateMember(Member member) {
        // 같은 이름이 있는 중복 회원X
        memberRepository.findByName(member.getName())
            // ifPresent 는 내부에 값이 존재 하면 내부 코드가 작동
            .ifPresent(m -> {
                // 이미 존재하는 회원 이라면  exception 던지기
                throw new IllegalStateException("이미 존재하는 회원 입니다.");
            });
    }

    /**
    *전체 회원 조회
    **/
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    /*
    *회원 한명 찾기
     */
    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
