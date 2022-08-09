package chap01;

public class PasswordStrengthMeter {
    public static final int MINIMUM_PASSWORD_LENGTH_CRITERIA = 8;

    /**
     * 비밀번호 보안 수준 측정
     */
    public PasswordStrength meter(String s) {
        if(s == null || s.isEmpty()) return PasswordStrength.INVALID;

        int meetCounts = 0;

        if(s.length() >= MINIMUM_PASSWORD_LENGTH_CRITERIA) meetCounts++;
        if(isContainsNumber(s)) meetCounts++;
        if(isContainsUpperCase(s)) meetCounts++;

        return PasswordStrength.getPasswordStrength(meetCounts);
    }

    /**
     * 대문자 포함 여부
     * @param s
     * @return
     */
    private boolean isContainsUpperCase(String s) {
        for(char ch: s.toCharArray()) {
            if(Character.isUpperCase(ch)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 숫자 포함 여부
     * @param s
     * @return
     */
    private boolean isContainsNumber(String s) {

        for(char ch : s.toCharArray()) {
            if(ch >= '0' && ch <= '9') {
                return true;
            }
        }
        return false;
    }
}
