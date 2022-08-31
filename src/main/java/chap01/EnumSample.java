package chap01;

public enum EnumSample {
    EASYADMIN("EASYADMIN", "이지어드민"),
    NAVER_CENTER("NAVERCENTER", "네이버풀필먼트센터"),
    NAVER_SHOPPING("NAVERSHOPPING", "네이버장보기")
    ;

    private final String code;
    private final String codeName;

    EnumSample(String code, String codeName) {
        this.code = code;
        this.codeName = codeName;
    }
}
