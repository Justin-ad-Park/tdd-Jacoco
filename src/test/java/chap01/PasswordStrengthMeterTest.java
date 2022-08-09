package chap01;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * 비밀번호 보안 수준 테스트
 *  [보안 수준 조건]
 *  -길이 8글자 이상
 *  -0~9 사이의 숫자를 포함
 *  -대문자 포함
 *
 *  위의 세 가지 조건을 충족하면 암호는 강함.
 *  2개의 조건을 충족하면 보통.
 *  1개 이하의 조건을 충족하면 약함.
 *  비밀번호가 공백이거나, 없으면 인식불가.
 */
public class PasswordStrengthMeterTest {
    PasswordStrengthMeter meter = new PasswordStrengthMeter();


    private void assertPasswordStrength(String password, PasswordStrength passwordStrength) {
        PasswordStrength result = meter.meter(password);
        assertEquals(passwordStrength, result);
    }

    @Test
    void 강한암호_테스트() {
        assertPasswordStrength("ab12!@AB", PasswordStrength.STRONG);
    }

    @Test
    void 보통수준암호_대문자숫자_테스트() {
        assertPasswordStrength("AB12!2", PasswordStrength.NORMAL);
    }

    @Test
    void 보통수준암호_대문자8자초과_테스트() {
        assertPasswordStrength("AB!@abcde", PasswordStrength.NORMAL);
    }

    @Test
    void 비밀번호_NULL_테스트() {
        assertPasswordStrength(null, PasswordStrength.INVALID);
    }

    @Test
    void 비밀번호_공백_테스트() {
        assertPasswordStrength("", PasswordStrength.INVALID);
    }

    @Test
    void 보통수준암호_숫자포함8자이상_테스트() {
        assertPasswordStrength("ab12!@df", PasswordStrength.NORMAL);
    }

    @Test
    void 낮은수준암호_8자이내_테스트() {
        assertPasswordStrength("ab12", PasswordStrength.WEAK);
    }
}
