
package com.appcare.eaglesboys.nonvegpiza;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NonVegPizza {

    private String name;
    private String description;
    private String price;
    private String image;
    private List<SelectCrust> selectCrust = null;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<SelectCrust> getSelectCrust() {
        return selectCrust;
    }

    public void setSelectCrust(List<SelectCrust> selectCrust) {
        this.selectCrust = selectCrust;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
