
package com.appcare.eaglesboys.nonvegpiza;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Child {

    private List<SelectCrust> selectCrust = null;
    private List<PizzaPrice> pizzaPrice = null;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public List<SelectCrust> getSelectCrust() {
        return selectCrust;
    }

    public void setSelectCrust(List<SelectCrust> selectCrust) {
        this.selectCrust = selectCrust;
    }

    public List<PizzaPrice> getPizzaPrice() {
        return pizzaPrice;
    }

    public void setPizzaPrice(List<PizzaPrice> pizzaPrice) {
        this.pizzaPrice = pizzaPrice;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
