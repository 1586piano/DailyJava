package song.junit.study.member;

import song.junit.study.domain.Member;

import java.util.Optional;

public interface MemberService {
    Optional<Member> findById(Long memberId);
}
