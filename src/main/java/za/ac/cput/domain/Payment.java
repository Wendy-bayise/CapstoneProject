package za.ac.cput.domain;

public class Payment {
    private final String paymentRef;
    private final double amount;
    private final String paymentDate;
    private final String paymentMethod;
    private final String status;

    private Payment(Builder builder) {
        this.paymentRef = builder.paymentRef;
        this.amount = builder.amount;
        this.paymentDate = builder.paymentDate;
        this.paymentMethod = builder.paymentMethod;
        this.status = builder.status;
    }

    public String getPaymentRef() {
        return paymentRef;
    }

    public double getAmount() {
        return amount;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public String getStatus() {
        return status;
    }

    public static class Builder {
        private final String paymentRef;
        private double amount;
        private String paymentDate;
        private String paymentMethod;
        private String status;

        public Builder(String paymentRef) {  // required field
            this.paymentRef = paymentRef;
        }

        public Builder amount(double amount) {
            this.amount = amount;
            return this;
        }

        public Builder paymentDate(String paymentDate) {
            this.paymentDate = paymentDate;
            return this;
        }

        public Builder paymentMethod(String paymentMethod) {
            this.paymentMethod = paymentMethod;
            return this;
        }

        public Builder status(String status) {
            this.status = status;
            return this;
        }

        public Payment build() {
            return new Payment(this);
        }
    }
}
