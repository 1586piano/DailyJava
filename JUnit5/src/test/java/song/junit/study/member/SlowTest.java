package song.junit.study.member;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD) //메서드에서 사용할 것이라고 명시.
@Retention(RetentionPolicy.RUNTIME) //이 어노테이션을 사용한 코드가 런타임 될 동안까지 실행.
@Test	//메타 어노테이션
@Tag("slow")	//메타 어노테이션
public @interface SlowTest {
}
