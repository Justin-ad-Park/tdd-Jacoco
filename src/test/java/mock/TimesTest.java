package mock;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.test.web.reactive.server.JsonPathAssertions;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

public class TimesTest {
    private Times mockTimes = Mockito.mock(Times.class);

    @Test
    void loadToday() {
         given(mockTimes.today()).willReturn(LocalDate.of(2019,1,1));
        LocalDate date = mockTimes.today();

        assertEquals("2019-01-01", date.toString());

    }
}
