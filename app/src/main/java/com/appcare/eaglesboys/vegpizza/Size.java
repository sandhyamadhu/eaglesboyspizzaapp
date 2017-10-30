
package com.appcare.eaglesboys.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Size {

    private String sizeId;
    private String name;
    private String price;
    private String extracheese;
    private List<Crust> crust = null;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public String getSizeId() {
        return sizeId;
    }

    public void setSizeId(String sizeId) {
        this.sizeId = sizeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getExtracheese() {
        return extracheese;
    }

    public void setExtracheese(String extracheese) {
        this.extracheese = extracheese;
    }

    public List<Crust> getCrust() {
        return crust;
    }

    public void setCrust(List<Crust> crust) {
        this.crust = crust;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
