package song.junit.study.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
public class Member {
    private Long id;
    private String name;
    private String email;

    @Builder
    public Member(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }
}
