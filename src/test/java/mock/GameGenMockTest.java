package mock;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.mock;

public class GameGenMockTest {

    // mock 조건에 설정된 값 리턴
    @Test
    void mockTest() {
        GameNumGen genMock = mock(GameNumGen.class);
        given(genMock.generate(GameLevel.EASY)).willReturn("123");

        String num = genMock.generate(GameLevel.EASY);
        assertEquals(num, "123");
    }

    // 리턴값이 존재하는 메소드의 exception 발생 테스트 방법
    // .willThrow(객체 타입)
    @Test
    void mockThrowTestWithReturn1() {
        GameNumGen genMock = mock(GameNumGen.class);
        given(genMock.generate(null)).willThrow(IllegalArgumentException.class);

        assertThrows(IllegalArgumentException.class,
                ()-> genMock.generate(null));
    }

    // 리턴값이 존재하는 메소드의 exception 발생 테스트 방법
    // 아래와 같이 타입 대신에 객체를 인자로 넘겨도 됨
    //  .willThrow(new IllegalArgumentException());
    //
    //  객체로 전달 시 예외 값까지 검증 가능
    @Test
    void mockThrowTestWithReturn2() {
        GameNumGen genMock = mock(GameNumGen.class);
        given(genMock.generate(null)).willThrow(new IllegalArgumentException("예외상황1"));

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                ()-> genMock.generate(null));

        assertEquals("예외상황1", exception.getMessage());
    }

    // 리턴값이 존재하지 않는 void 메소드의 exception 발생 테스트 방법
    @Test
    void mockThrowTestWithVoidMethod() {
        GameNumGen genMock = mock(GameNumGen.class);
        willThrow(RuntimeException.class)
        .given(genMock).doSomething();

        assertThrows(RuntimeException.class,
                ()-> genMock.doSomething()
        );
    }

    // 추가 예) List 객체의 void clear() 메소드
    @Test
    void voidMethodWillThrowTest() {
        List<String> mockList = mock(List.class);
        willThrow(UnsupportedOperationException.class)
                .given(mockList)
                .clear();

        assertThrows(UnsupportedOperationException.class,
                () -> mockList.clear()
                );
    }

    // 인자 비매칭 시 기본값 리턴함
    //  리턴 타입이 숫자이면 0, boolean이면 false, 참조 타입이면 null을 리턴한다.
    @Test
    void notMactingGivenParamTest() {
        GameNumGen genMock = mock(GameNumGen.class);
        given(genMock.generate(GameLevel.EASY)).willReturn("123");

        String num = genMock.generate(GameLevel.NORMAL);
        assertEquals(num, null);
    }

    // 인자 any 매칭 테스트
    /**
     * ArgumentMatchers 메서드 종류
     *  임의의 숫자 : anyInt(), anyShort(), anyLong(), anyByte(), anyChar(), anyDouble(), anyFloat(), anyBoolean()
     *  임의의 문자 : anyString()
     *  임의 타입 : any()
     *  임의 콜렉션 : anyList(), anySet(), anyMap(), anyCollection()
     *  eq(값) : 특정값
     */
    @Test
    void anyMactingGivenParamTest() {
        GameNumGen genMock = mock(GameNumGen.class);
        given(genMock.generate(any())).willReturn("123");

        String num = genMock.generate(GameLevel.NORMAL);
        assertEquals(num, "123");
    }

    /**
     * ArgumentMatchers.eq() 사용 방법 : 임의의 값과, 지정된 값을 복합적으로 사용하는 테스트 케이스에서 사용
     */
    @Test
    void maxAnyAndEq() {
        List<String> mockList = mock(List.class);

        given(mockList.set(anyInt(), eq("123"))).willReturn("456");

        String old = mockList.set(5, "123");
        assertEquals("456", old);

    }

    // 메서드가 호출되었는지 여부 확인
    @Test
    void init() {
        GameNumGen genMock = mock(GameNumGen.class);
        Game game = new Game(genMock);
        game.init(GameLevel.EASY);

        then(genMock).should().generate(GameLevel.EASY);
    }

    // 메서드가 호출되었는지 여부 확인
    @Test
    void init_any() {
        GameNumGen genMock = mock(GameNumGen.class);
        Game game = new Game(genMock);
        game.init(GameLevel.EASY);

        then(genMock).should().generate(any());
    }

    // 메서드가 한 번만 호출되었는지 검증
    /**
     * 메서드 호출횟수 검증
     * only() : 한 번만 호출
     * times(int) : 지정된 횟수만큼 호출
     * never() : 호출되지 않음
     * atLeast(int) : 적어도 지정한 횟수만큼 호출
     * atLeastOnce() : atLeast(1)과 동일
     * atMost(int) : 최대 지정한 횟수만큼 호출
     */

    @Test
    void init_only() {
        GameNumGen genMock = mock(GameNumGen.class);
        Game game = new Game(genMock);
        game.init(GameLevel.EASY);

        then(genMock).should(only()).generate(any());
    }


    /**
     * 인자 캡처 테스트
     */
    @Test
    void captor_test() {
        //init
        GameNumGen genMock = mock(GameNumGen.class);
        ArgumentCaptor<GameLevel> captor = ArgumentCaptor.forClass(GameLevel.class);

        //when
        Game game = new Game(genMock);
        game.init();

        //then
        then(genMock).should(only()).generate(captor.capture());

        GameLevel gLevel = captor.getValue();
        assertEquals(GameLevel.EASY, gLevel);
    }

    /**
     * 간단한 인자 캡처 테스트
     */
    @Test
    void captureTest() {

        //init
        List<String> list = mock(List.class);
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);

        //when
        list.add("홍길동");

        //then
        verify(list, atMostOnce()).add(any());
        verify(list).add(captor.capture());

        String captureName = captor.getValue();
        assertEquals("홍길동", captureName);
    }


}

