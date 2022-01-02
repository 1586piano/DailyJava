package song.junit.study.post;

import song.junit.study.domain.Post;
import song.junit.study.member.NotExistsException;

public interface IPostService {
    Post createNewPost(Long memberId, Post post) throws NotExistsException;
    void validate();
    void notify(Long memberId);
}
