package com.appcare.eaglesboys.vegpizza;

/**
 * Created by Appcare on 20-10-2017.
 */

public class Product {
   private String image;
    private String name;
    private String id;
    private String product_type;
    private String price;


    public Product(String image, String name, String id) {
        this.image = image;
        this.name = name;
        this.id = id;
        this.product_type = product_type;
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProduct_type() {
        return product_type;
    }

    public void setProduct_type(String product_type) {
        this.product_type = product_type;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
