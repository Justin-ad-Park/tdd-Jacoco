package billing;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class BillingService {

    Map<String, Customer> customerMap = new HashMap<>();

    public LocalDate pay(Billing bill) {
        Customer cust = customerMap.get(String.valueOf(bill.getCustomerID()));

        if(cust == null) {
            cust = new Customer(bill);
            customerMap.put(cust.toString(), cust);
        } else {
            cust.addBilling(bill);
        }

        return cust.getExpireDate();
    }

//    public static void main(String[] args) {
//        BillingService bs = new BillingService();
//
//        // 1월 1일에 시작하면, 1월 31일 만료
//        Billing bill = new Billing(1, "2022/01/01", 10000);
//
//        LocalDate expireDT = bs.pay(bill);
//
//        System.out.println(expireDT);
//    }
}
