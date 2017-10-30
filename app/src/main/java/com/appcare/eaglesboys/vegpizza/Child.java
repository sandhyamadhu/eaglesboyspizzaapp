
package com.appcare.eaglesboys.vegpizza;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Child {

    private List<Size> size = null;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public List<Size> getSize() {
        return size;
    }

    public void setSize(List<Size> size) {
        this.size = size;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
