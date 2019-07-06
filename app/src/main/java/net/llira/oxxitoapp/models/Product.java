package net.llira.oxxitoapp.models;

public class Product {
    private String code;
    private String name;
    private double price;
    private int stock;
    private String date;

    public Product() {
    }

    public Product(String code, String name, double price, int stock, String date) {
        this.code = code;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.date = date;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Product{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                ", date='" + date + '\'' +
                '}';
    }

}//End class
