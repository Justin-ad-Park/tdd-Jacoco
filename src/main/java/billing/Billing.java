package billing;

import java.security.InvalidParameterException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Billing {
    private final String customerID;
    private final LocalDate billingDate;
    private int amount;
    private int periodOfMonths;

    private final int AMOUNT_OF_YEAR = 100000;
    private final int AMOUNT_OF_MONTH = 10000;

    public Billing(String customerID, String billingDate, int amount) {
        LocalDate date = LocalDate.parse(billingDate, DateTimeFormatter.ofPattern("yyyy/MM/dd"));

        this.customerID = customerID;
        this.billingDate = date;
        this.setAmount(amount);
    }

    public Billing(String customerID, LocalDate billingDate, int amount) {

        this.customerID = customerID;
        this.billingDate = billingDate;
        this.setAmount(amount);
    }

    private void setAmount(int amount) {
        //빌링 금액 단위가 맞지 않으면 에러 처리
        if(amount % AMOUNT_OF_MONTH != 0)
        {
            throw new InvalidParameterException("금액이 잘 못 되었습니다.");
        }

        this.amount = amount;

        updatePeriodOfMonths();
    }

    private void updatePeriodOfMonths() {
        // 년간 구독 금액 납임 시 12개월 구독
        int yearOfSubscription = amount / AMOUNT_OF_YEAR;
        //년간 구독 금액 미만은 월 구독
        int monthsOfSubscription = (amount % AMOUNT_OF_YEAR) / AMOUNT_OF_MONTH;

        this.periodOfMonths = (yearOfSubscription * 12) + monthsOfSubscription;
    }

    public String getCustomerID() {
        return this.customerID;
    }

    public LocalDate getBillingDate() {
        return this.billingDate;
    }

    public int getPeriodOfMonths() {
        return this.periodOfMonths;
    }

}
