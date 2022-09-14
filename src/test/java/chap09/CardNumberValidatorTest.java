package chap09;

import com.github.tomakehurst.wiremock.WireMockServer;
import org.junit.jupiter.api.*;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CardNumberValidatorTest {

    private WireMockServer wireMockServer;

    @BeforeEach
    void setUp() {
        wireMockServer = new WireMockServer(8089);
        wireMockServer.start();
    }

    @AfterEach
    void tearDown() {
        wireMockServer.stop();
    }

    @Test
    void valid() {
        //Given
        wireMockServer.stubFor(post("/card")
                .withRequestBody(equalTo("1234567890"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "text/plain")
                        .withBody("ok")
                )
        );

        //When
        CardNumberValidator validator = new CardNumberValidator("http://localhost:8089");
        CardValidity validity = validator.validate("1234567890");

        //Then
        assertEquals(CardValidity.VALID, validity);
    }

    @Test
    void invalid() {
        //Given
        wireMockServer.stubFor(post("/card")
                .withRequestBody(equalTo("999999999"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "text/plain")
                        .withBody("bad")
                )
        );

        //When
        CardNumberValidator validator = new CardNumberValidator("http://localhost:8089");
        CardValidity validity = validator.validate("999999999");

        //Then
        assertEquals(CardValidity.INVALID, validity);
    }

    @Test
    void timeout() {
        //Given
        wireMockServer.stubFor(post("/card")
                .willReturn(aResponse().withFixedDelay(4000)
                        .withBody("bad")
                )
        );

        //When
        CardNumberValidator validator = new CardNumberValidator("http://localhost:8089");
        CardValidity validity = validator.validate("1234567890");

        //Then
        assertEquals(CardValidity.TIMEOUT, validity);
    }
}
