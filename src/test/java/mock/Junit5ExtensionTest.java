package mock;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class Junit5ExtensionTest {

    @Mock
    private GameNumGen genMock;

    @Test
    void Test() {
        given(genMock.generate(any())).willReturn("123");

        String num = genMock.generate(GameLevel.NORMAL);
        assertEquals(num, "123");
    }
}
