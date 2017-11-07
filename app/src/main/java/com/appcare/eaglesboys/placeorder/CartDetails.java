package com.appcare.eaglesboys.placeorder;

import android.widget.Button;

/**
 * Created by Appcare on 07-11-2017.
 */

public class CartDetails {
    private String pizzaName;
    private String pizzaQty;
    private String pizzaPrice;
    private Button pizzaDelete;

    public String getPizzaName() {
        return pizzaName;
    }

    public void setPizzaName(String pizzaName) {
        this.pizzaName = pizzaName;
    }

    public String getPizzaQty() {
        return pizzaQty;
    }

    public void setPizzaQty(String pizzaQty) {
        this.pizzaQty = pizzaQty;
    }

    public String getPizzaPrice() {
        return pizzaPrice;
    }

    public void setPizzaPrice(String pizzaPrice) {
        this.pizzaPrice = pizzaPrice;
    }

    public Button getPizzaDelete() {
        return pizzaDelete;
    }

    public void setPizzaDelete(Button pizzaDelete) {
        this.pizzaDelete = pizzaDelete;
    }
}