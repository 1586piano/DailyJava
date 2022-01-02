package song.junit.study.post;

import song.junit.study.domain.Member;
import song.junit.study.domain.Post;
import song.junit.study.member.MemberService;
import song.junit.study.member.NotExistsException;

public class PostService implements IPostService {
    private MemberService memberService;
    private PostRepository postRepository;

    public PostService(MemberService memberService, PostRepository postRepository) {
        assert(memberService != null);
        assert(postRepository != null);
        this.memberService = memberService;
        this.postRepository = postRepository;
    }

    @Override
    public Post createNewPost(Long memberId, Post post) throws NotExistsException {
        String message = String.format("입력한 멤버 ID(%d)에 대한 이용자가 없습니다 - ", memberId);
        Member member = memberService.findById(memberId).orElseThrow(() -> new NotExistsException(message));
        Post newPost = Post.builder().id(member.getId()).title(post.getTitle()).contents(post.getContents()).build();
        return postRepository.save(newPost);
    }

    @Override
    public void validate() {

    }

    @Override
    public void notify(Long memberId) {

    }
}
