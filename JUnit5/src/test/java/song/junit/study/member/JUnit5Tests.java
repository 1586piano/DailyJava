package song.junit.study.member;

import org.junit.jupiter.params.provider.NullAndEmptySource;
import song.junit.study.domain.Member;
import song.junit.study.domain.Post;
import song.junit.study.domain.PostStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.IOException;
import java.time.Duration;
import java.util.Map;
import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

class JUnit5Tests {

    @Test
    @DisplayName("JUnit5 기본 검증")
    void testBasicAssert() {
        // Execute
        Post newPost = Post.builder().id(1L).build();

        // Assert
        // TODO : 초기 생성된 Post 의 상태가 CREATED 인지 검증
        //assertEquals(x, y), x: Expected Value, y: Real Value
        assertEquals(PostStatus.CREATED, newPost.getPostStatus(), "초기상태는 CREATE입니다.");
    }

    @Test
    @DisplayName("Java8 Supplier 통한 Assertion")
    void testBasicAssertWithSupplier() {
        // Execute
        Post newPost = Post.builder().id(1L).build();

        // Assert
        // TODO : 의도적으로 오류를 검증하고, 메소드의 3번째 Supplier 인자를 이용하여 get 메소드를 Override 하여 메시지 출력 검증을 하세요
        assertEquals(PostStatus.UPDATED, newPost.getPostStatus(), new Supplier<String>() {
            public String get() {
                return "Post 생성 시 처음 값은 'CREATED'여야 한다.";
            }
        });
    }

    @Test
    @DisplayName("Java8 Lambda 통한 Assertion")
    void testBasicAssertWithLambda() {
        // Execute
        Post newPost = Post.builder().id(1L).build();

        // Assert
        // TODO : 의도적으로 오류를 검증하고, 메소드의 3번째 Supplier 대신 Lambda 함수를 이용하여 메시지 출력 검증을 하세요
        assertEquals(PostStatus.DELETED, newPost.getPostStatus(), () -> {
            return "Post 생성 시 처음 값은 'CREATED'여야 한다.";
        });
    }

    @Test
    @DisplayName("assertAll 구문을 통한 Assertion")
    void testBasicAssertWithAssertAll() {
        // Execute
        Post newPost = Post.builder().id(1L).build();

        // Assert
        // TODO : 아래의 빈칸에 [Answer] 들어갈 검증 명령어는 ?
        assertAll (
                () -> assertNotNull(newPost),
                () -> assertEquals(1L, newPost.getId()),
                () -> assertEquals(PostStatus.CREATED, newPost.getPostStatus(), () -> "처음 생성된 Post 의 상태는 CREATED 입니다"),
                () -> assertTrue(false, "첫번째실패"), // Test Fail
                () -> assertTrue(true, "두번쨰는 성공")
        );
    }

    @Test
    @DisplayName("assertThrows 테스트")
    void testAssertThrows() {
        // Execute
        Post.PostBuilder postBuilder = Post.builder();
        //Post newPost = postBuilder.postStatus(PostStatus.CREATED).contents("test").id(1L).build();

        // Assert
        // TODO : postBuilder.build() 함수 호출을 하고, IllegalArgumentException 예외를 검증하세요
        assertThrows(IllegalArgumentException.class, ()->{
            postBuilder.build(); //IllegalArgumentException !!
        });

        // Expected :java.lang.ArrayIndexOutOfBoundsException
        // Actual   :java.lang.IllegalArgumentException
        assertThrows(ArrayIndexOutOfBoundsException.class, ()->{
            postBuilder.build(); //IllegalArgumentException !!
        });
    }

    @Test
    @DisplayName("assetTimeout 테스트")
    void testAssertTimeout() throws InterruptedException {
        // Execute
        Post post = Post.builder().id(1L).build();

        // Assert
        // TODO : post.sleep(5000) 메소드 호출을 통해 5초 sleep 시에 1초 타임아웃 검증을 수행하세요
        assertTimeout(Duration.ofMillis(5000), ()->{
            post.sleep(5000);
        });
    }

    @Test
    @DisplayName("assetTimeoutPreemptively 테스트")
    void testAssertTimeoutPreemptively() {
        // Execute
        Post post = Post.builder().id(1L).build();

        // Assert
        // TODO : post.sleep(5000) 메소드 호출을 통해 5초 sleep 하되, 1초 타임아웃 즉시 바로 종료되도록 검증하세요
        assertTimeoutPreemptively(Duration.ofMillis(1000), ()->{
            post.sleep(5000);
        });

    }

    @Test
    @DisplayName("org.assertj.core.api.AssertionsForInterfaceTypes.assertThat 테스트")
    void testAssertMatcher() {
        // Execute
        Post post1 = Post.builder().id(1L).title("제목").build();
        Post post2 = Post.builder().id(1L).title("제").build();

        // Assert
        // TODO : Post 객체의 제목의 문자열의 수가 1보다 큰 것을 검증하세요
        //org.assertj.core.api.AssertionsForInterfaceTypes.assertThat
        //assertThat(post.getTitle().length()).isGreaterThan(1);
        assertAll(
                () -> assertThat(post2.getTitle().length(), greaterThan(1)),
                () -> assertThat(post1.getTitle().length(), greaterThan(1))
        );
    }

    @Test
    @DisplayName("assumeTrue 조건에 따른 테스트")
    void testAssumeTrue() throws IOException {
        // Execute
        String debug = System.getenv("DEBUG");

        // Assert
        // TODO : 환경변수 DEBUG=true 값을 지정하고, 해당 값이 true 인 경우에만 build() 함수가 호출되도록 작성하세요
        // Run-Edit configuration-Environment variables
        assumeTrue("true".equalsIgnoreCase(debug)); // 환경변수 debug true?

        //true..
        Post.builder().build(); // throw illegal-argument-exception
    }

    @Test
    @Tag("fast")
    @DisplayName("@Tag('fast') 조건에 따른 테스트1")
    void testTagFast() throws InterruptedException {
        Post fastPost = Post.builder().id(1L).build();
        assertTimeout(Duration.ofMillis(50), () -> fastPost.sleep(10));
        // TODO : 터미널에서 fast tag 작업만 수행하고, 소요되는 시간을 작성하세요 : 22s
        // gradlew fast
    }

    @Test
    @Tag("fast")
    @DisplayName("@Tag('fast') 조건에 따른 테스트2")
    void testTagFast2() throws InterruptedException {
        Post fastPost = Post.builder().id(1L).build();
        assertTimeout(Duration.ofMillis(50), () -> fastPost.sleep(10));
        // TODO : 터미널에서 fast tag 작업만 수행하고, 소요되는 시간을 작성하세요 : 22s
    }

    @Test
    @Tag("slow")
    @DisplayName("@Tag('slow') 조건에 따른 테스트")
    void testTagSlow() throws InterruptedException {
        Post slowPost = Post.builder().id(1L).build();
        assertTimeout(Duration.ofMillis(10000), () -> slowPost.sleep(5000));
        // TODO : 터미널에서 slow tag 작업만 수행하고, 소요되는 시간을 작성하세요 : 15s
    }

    @FastTest
    @DisplayName("커스텀 어노테이션 태그를 통한 테스트")
    void testFastTag() {
        Post fastPost = Post.builder().id(1L).build();
        assertTimeout(Duration.ofMillis(50), () -> fastPost.sleep(10));
        // TODO : SlowTest 커스텀 어노테이션 태그를 작성하고, 5초 소요되는 단위 테스트 test5Seconds 함수를 작성하세요 : 5s
    }

    @SlowTest
    @DisplayName("커스텀 어노테이션 태그를 통한 테스트")
    void testSlowTag() {
        Post slowPost = Post.builder().id(1L).build();
        assertTimeout(Duration.ofMillis(50), () -> slowPost.sleep(5));
    }

    // TODO : testParameterizedTest 검증이 성공하도록 값을 넣어주는 어노테이션을 활용하여 적절한 문자열 3개 이상을 작성하세요
    @ParameterizedTest
    @ValueSource(strings = {
            "aaaaaa",
            "bbbbbb",
            "cccccc"
    })
    @DisplayName("Parameterized 테스트")
    void testParameterizedTest(String word) {
        assertThat(word.length(), greaterThan(5));
    }

    // TODO : 아래의 함수가 정상적으로 동작하도록 수정하세요
    @ParameterizedTest
    @DisplayName("다수의 파라메터와 인자를 가진 테스트")
    @CsvSource(value = {"1,'박수혁',CREATED", "2,'박송희',UPDATED", "3,'백건호',DELETED"})
    void testMultipleParameterized(Long id, String name, PostStatus postStatus) {
        Member member = Member.builder().id(id).name(name).build();
        assertNotNull(member);
        //assertThat(member.getName().length()).isGreaterThan(1);
        Post post = Post.builder().postStatus(postStatus).id(id).build();
        assertNotNull(post);
    }

    // TODO : 입력 name 값으로 널값과 빈값을 자동으로 넣어주도록 어노테이션을 이용하여 작성하세요
    @ParameterizedTest
    @DisplayName("널과 빈값 인자 테스트")
    @ValueSource(strings = {"박수혁", "박송희", "백건호"})
    @NullAndEmptySource
    void testNullAndEmptySource(String name) {
        System.out.println(String.format("[%s]", name));
    }
}