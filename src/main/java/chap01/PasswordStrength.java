package chap01;

public enum PasswordStrength {
    INVALID(-1),
    WEAK(0),
    NORMAL(2),
    STRONG(3);

    private int passwordCriteriaCnt;

    PasswordStrength(int passwordCriteriaCnt) {
        this.passwordCriteriaCnt = passwordCriteriaCnt;
    }

    public static PasswordStrength getPasswordStrength(int meetCriteriaCnt) {
        if(meetCriteriaCnt == PasswordStrength.STRONG.passwordCriteriaCnt) return PasswordStrength.STRONG;

        if(meetCriteriaCnt == PasswordStrength.NORMAL.passwordCriteriaCnt) return PasswordStrength.NORMAL;

        return PasswordStrength.WEAK;
    }
}
