package xiiyuoo.com.models;

import java.util.ArrayList;

public class ListPaymentMethod {

    ArrayList<PaymentMethod> paymentMethods;

    public ListPaymentMethod() {
        paymentMethods = new ArrayList<>();
    }

    public ArrayList<PaymentMethod> getPaymentMethods() {
        return paymentMethods;
    }

    public void setPaymentMethods(ArrayList<PaymentMethod> paymentMethods) {
        this.paymentMethods = paymentMethods;
    }
}