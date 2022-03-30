package Model;

import java.util.Date;

public class Product {

    private int productId;
    private String productName;
    private double unitPrice;
    private int available;
    private Date addDate;
    private Date updateDate;

    private Category category;
    private Brand brand;

    public Product(int productId, String productName, double unitPrice, int available, Date addDate,
                   Date updateDate, Category category, Brand brand) {
        this.productId = productId;
        this.productName = productName;
        this.unitPrice = unitPrice;
        this.available = available;
        this.addDate = addDate;
        this.updateDate = updateDate;
        this.category = category;
        this.brand = brand;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }

    public Date getAddDate() {
        return addDate;
    }

    public void setAddDate(Date addDate) {
        this.addDate = addDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", unitPrice=" + unitPrice +
                ", available=" + available +
                ", addDate=" + addDate +
                ", updateDate=" + updateDate +
                ", category=" + category +
                ", brand=" + brand +
                '}';
    }
}
