package data.api;

import com.google.gson.annotations.SerializedName;

public class Customer {
    private transient Integer id;
    @SerializedName("name")
    private String customerName;
    private String name;
    private String lastName;

    public Customer() {

    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "Customer["
                + "id=" + id
                + ", name=" + name
                + ", lastName=" + lastName
                + ']';
    }

    public String getCustomerName() {
        return name + " " + lastName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
}