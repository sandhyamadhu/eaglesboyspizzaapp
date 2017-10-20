package com.appcare.eaglesboys.sides;

/**
 * Created by Appcare on 20-10-2017.
 */

public class SideDetails {

    private String image;
    private String name;
    private String product_type;
    private String category;
    private String price;

    public SideDetails(String image, String name, String product_type, String category, String price) {
        this.image = image;
        this.name = name;
        this.product_type = product_type;
        this.category = category;
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

    public String getProduct_type() {
        return product_type;
    }

    public void setProduct_type(String product_type) {
        this.product_type = product_type;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
