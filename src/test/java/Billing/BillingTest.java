package Billing;

import billing.Billing;
import billing.BillingService;
import billing.Customer;
import org.junit.jupiter.api.Test;

import java.security.InvalidParameterException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * 1달에 만원인 구독 서비스가 있다.
 * 2달치 이상을 미리 낼 수 있다.
 * 10만원을 내면 1년 정기 구독
 */
public class BillingTest {

    @Test
    void 빌링_금액예외테스트() {
        Billing bill;
        InvalidParameterException exception = assertThrows(InvalidParameterException.class, () ->
                new Billing("1", LocalDate.now(), 11000)
        );

        assertEquals("금액이 잘 못 되었습니다.", exception.getMessage());
    }

    @Test
    void 고객_빌링시작_만원에1달() {
        Billing bill = new Billing("1", "2022/01/31", 10000);
        Customer cust = new Customer(bill);

        assertEquals(cust.getServicePeriodOfMonth(), 1);
    }

    @Test
    void 빌링_기간계산() {
        //1만원 1개월
        Billing billing = new Billing("1", LocalDate.now(), 10000);
        assertEquals(1,billing.getPeriodOfMonths());

        //10만원 12개월
        billing = new Billing("1", LocalDate.now(), 100000);
        assertEquals(12,billing.getPeriodOfMonths());

        //11만원 13개월
        billing = new Billing("1", LocalDate.now(), 110000);
        assertEquals(13,billing.getPeriodOfMonths());

    }

    @Test
    void 만원내면_한달후만료() {
        BillingService bs = new BillingService();

        // 1월 1일에 시작하면, 1월 31일 만료
        Billing bill = new Billing("1", "2022/01/01", 10000);
        assertEqualsDate(bs.pay(bill), "2022/01/31");

        bill = new Billing("1", "2022/02/01", 100000);
        assertEqualsDate(bs.pay(bill), "2023/01/31");
    }


    @Test
    void 만원내고_10만원내면_13달후만료() {
        BillingService bs = new BillingService();

        // 1월 1일에 시작하면, 1월 31일 만료
        Billing bill = new Billing("1", LocalDate.of(2022, 1, 1), 10000);
        LocalDate exDate = bs.pay(bill);
        assertEqualsDate(exDate, "2022/01/31");

        bill = new Billing("1", "2022/02/01", 100000);
        assertEqualsDate(bs.pay(bill), "2023/01/31");

    }

    @Test
    void 빌링_날짜형식오류_테스트() {
        DateTimeParseException exception = assertThrows(DateTimeParseException.class, () ->
                new Billing("1", "2022/02/32", 10000)
        );

    }

    private void assertEqualsDate(LocalDate expireDT, String compareDateTime) {
        assertEquals(expireDT,
                LocalDate.parse(compareDateTime, DateTimeFormatter.ofPattern("yyyy/MM/dd"))
        );
    }

}
