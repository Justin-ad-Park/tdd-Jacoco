package chap01;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StringTest {

    @Test
    void substring() {
        String str = "abcde";

        //assertEquals 인자로 받은 두 값이 일치하는지 검증한다.
        assertEquals("cd", str.substring(2,4));
    }
}
