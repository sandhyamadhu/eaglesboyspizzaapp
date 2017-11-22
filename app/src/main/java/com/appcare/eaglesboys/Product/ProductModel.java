package com.appcare.eaglesboys.Product;

/**
 * Created by Appcare on 21-11-2017.
 */

public class ProductModel {
    private String mrp;
    private String quantity;
    private String imageUrl = "";
    private String productName ;
    private String productId ;

    public ProductModel() {
    }

    public ProductModel(String mrp, String quantity, String imageUrl, String productName, String productId) {
        this.mrp = mrp;
        this.quantity = quantity;
        this.imageUrl = imageUrl;
        this.productName = productName;
        this.productId = productId;
    }

    public String getMrp() {
        return mrp;
    }

    public void setMrp(String mrp) {
        this.mrp = mrp;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    @Override
    public String toString() {
        return "BerveragesDetails{" +
                "mrp='" + mrp + '\'' +
                ", orderQty='" + quantity + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", productName='" + productName + '\'' +
                ", productId='" + productId + '\'' +
                '}';
    }
}