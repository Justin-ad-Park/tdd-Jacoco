package billing;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Customer {
    private String customerNo;
    private LocalDate startServiceDate;
    private LocalDate expireDate;
    private int servicePeriodOfMonth = 0;

    private List<Billing> billings;

    public Customer(Billing bill) {
        billings = new ArrayList<>();

        this.customerNo = bill.getCustomerID();
        this.addBilling(bill);
    }

    public void addBilling(Billing bill) {
        if(this.startServiceDate == null) {
            this.startServiceDate = bill.getBillingDate();
        }

        this.servicePeriodOfMonth += bill.getPeriodOfMonths();

        this.updateExpireDate();
        billings.add(bill);
    }

    public int getServicePeriodOfMonth() {
        return this.servicePeriodOfMonth;
    }

    private void updateExpireDate() {
        expireDate = startServiceDate.plusMonths(this.servicePeriodOfMonth).minusDays(1);
    }

    public LocalDate getExpireDate() {
        return this.expireDate;
    }

    @Override
    public String toString() {
        return String.valueOf(customerNo);
    }

}
