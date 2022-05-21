package data.api;

import java.util.Date;

public class Purchase {
    //чтобы получить expenses можно просто перемножить кол-во productName на product.price
    private transient Integer customerId;
    private transient Integer productName;
    private transient Date dateOfPurchase;

    public Purchase() {

    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getProductName() {
        return productName;
    }

    public void setProductName(Integer productName) {
        this.productName = productName;
    }

    public Date getDateOfPurchase() {
        return dateOfPurchase;
    }

    public void setDateOfPurchase(Date dateOfPurchase) {
        this.dateOfPurchase = dateOfPurchase;
    }

    @Override
    public String toString() {
        return "Purchase["
                + "customerId=" + customerId
                + ", productId=" + productName
                + ", date=" + dateOfPurchase
                + ']';
    }
}