
package com.appcare.eaglesboys.nonvegpiza;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NVegPizza {

    private List<NonVegPizza> nonVegPizza = null;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public List<NonVegPizza> getNonVegPizza() {
        return nonVegPizza;
    }

    public void setNonVegPizza(List<NonVegPizza> nonVegPizza) {
        this.nonVegPizza = nonVegPizza;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
