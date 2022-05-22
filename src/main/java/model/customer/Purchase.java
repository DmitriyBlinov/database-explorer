package model.customer;

import com.google.gson.annotations.SerializedName;

public class Purchase {
    //чтобы получить expenses можно просто перемножить кол-во productName на product.price
    @SerializedName("name")
    private String name;
    @SerializedName("expenses")
    private int expenses;

    public Purchase() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "(name=" + name
                + ", expenses=" + expenses
                + ')';
    }

    public double getExpenses() {
        return expenses;
    }

    public void setExpenses(int expenses) {
        this.expenses = expenses;
    }
}