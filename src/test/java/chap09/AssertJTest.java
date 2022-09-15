package chap09;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.assertj.core.api.Assertions.*;

public class AssertJTest {

    @Test
    void ThrownTest() {
        assertThatThrownBy(()-> DivideZero());
    }

    @Test
    void ThrownTest2() {
        assertThatThrownBy(()-> DivideZero()).isInstanceOf(ArithmeticException.class);

        assertThatExceptionOfType(ArithmeticException.class)
                .isThrownBy(() -> DivideZero());
    }

    @Test
    void NotThrownTest() {
        assertThatCode(()-> {
            DoNothing();
        }).doesNotThrowAnyException();

    }

    @Test
    void SoftAssertionTest() {
        SoftAssertions soft = new SoftAssertions();
        soft.assertThat(1).isBetween(0,2);
        soft.assertThat(1).isGreaterThan(0);
        soft.assertThat(1).isLessThan(2);
        soft.assertAll();
    }


    int DivideZero() {
        return 1 / 0;
    }

    void DoNothing() {
        return;
    }
}
