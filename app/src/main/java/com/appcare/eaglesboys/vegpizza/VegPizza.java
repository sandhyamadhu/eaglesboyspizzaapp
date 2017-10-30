
package com.appcare.eaglesboys.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VegPizza {

    private List<ClassicVeg> classicVeg = null;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public List<ClassicVeg> getClassicVeg() {
        return classicVeg;
    }

    public void setClassicVeg(List<ClassicVeg> classicVeg) {
        this.classicVeg = classicVeg;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
