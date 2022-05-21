package data.api;

import com.google.gson.annotations.SerializedName;

public class Product {
    private transient Integer id;
    @SerializedName("productName")
    private String name;
    @SerializedName("price")
    private Double price;

    public Product() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product["
                + "id=" + id
                + ", name=" + name
                + ", price=" + price
                + ']';
    }
}