
package com.appcare.eaglesboys.nonvegpiza;

import java.util.HashMap;
import java.util.Map;

public class PizzaPrice {

    private String crustSelect;
    private String crustName;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public String getCrustSelect() {
        return crustSelect;
    }

    public void setCrustSelect(String crustSelect) {
        this.crustSelect = crustSelect;
    }

    public String getCrustName() {
        return crustName;
    }

    public void setCrustName(String crustName) {
        this.crustName = crustName;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
