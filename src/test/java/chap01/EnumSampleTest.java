package chap01;

import com.fasterxml.jackson.databind.deser.std.EnumDeserializer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EnumSampleTest {

    @Test
    void EnumTest() {
        EnumSample es = EnumSample.valueOf("NAVER_CENTER");

        assertEquals(EnumSample.NAVER_CENTER, es);
    }
}
