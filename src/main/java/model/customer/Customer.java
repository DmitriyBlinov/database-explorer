package model.customer;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Customer implements Comparable<Customer> {
    //Это у нас как раз сущность каждого кастомера
    private transient Integer id;
    @SerializedName("name")
    private String name;
    @SerializedName("purchases")
    private List<Purchase> purchases;
    @SerializedName("totalExpenses")
    private int totalExpenses;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Purchase> getPurchases() {
        return purchases;
    }

    public void addPurchase(Purchase purchase) {
        this.purchases.add(purchase);
    }

    public void setPurchases(List<Purchase> purchases) {
        this.purchases = purchases;
    }

    public int getTotalExpenses() {
        return totalExpenses;
    }

    public void setTotalExpenses(int totalExpenses) {
        this.totalExpenses = totalExpenses;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean checkId(int id) {
        return this.id.equals(id);
    }

    @Override
    public int compareTo(Customer customer) {
        int totalExpenses = customer.getTotalExpenses();
        return this.getTotalExpenses() - totalExpenses;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        Customer tempCustomer = (Customer) obj;
        return this.id.equals(tempCustomer.getId());
    }

    @Override
    public String toString() {
        return "Customer:" + name + purchases.toString() + ", totalExpenses: " + totalExpenses;
    }


}