package com.appcare.eaglesboys.nonvegpiza;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.appcare.eaglesboys.Constants.CommonFragment;
import com.appcare.eaglesboys.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class NonVegPizzaFragment extends CommonFragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View mNonVegPizza = inflater.inflate(R.layout.fragment_nonvegpizza,null);
        return mNonVegPizza;
    }

    String mNonVegPizza = "{\"NonVegPizza\":[{\"name\":\"Veg Piza\",\"description\":\"Onion | Capsium | Olive | Mushrom\",\"price\":\"365\",\"image\":\"http://marssofttech.com/demos/eaglepizza//uploads/foods/39_2017/dafc942952aab8e3fb6a29e99a14b1a4.png\",\"selectCrust\":[{\"crustSelect\":\"true\",\"crustName\":\"New Hand Tossed\"},{\"crustSelect\":\"false\",\"crustName\":\"Wheet Thin Crush\"},{\"crustSelect\":\"false\",\"crustName\":\"Cheese Crush\"},{\"crustSelect\":\"false\",\"crustName\":\"Fresh Pan Pizza\"},{\"crustSelect\":\"false\",\"crustName\":\"Classic Hand Tossed\"}]}]}";


    ArrayList<NonVegPizza> mNonVegPizzas = new ArrayList<>();
    ArrayList<SelectCrust> mSelectCrusts = new ArrayList<>();
    ArrayList<NVegPizza> mNVegPizzas = new ArrayList<>();

    @Override
    public void handleResponse(Object response, String tag) {
        super.handleResponse(response, tag);

        response = mNonVegPizza;

        try {
            JSONObject mJSObject = new JSONObject(response.toString());
            JSONArray mJsonArray = mJSObject.getJSONArray("NonVegPizza");
            for (int i=0; i<mJsonArray.length(); i++){


                NonVegPizza mNonVegPizza = new NonVegPizza();

                JSONObject jsonObject = mJsonArray.getJSONObject(i);

                mNonVegPizza.setName(jsonObject.getString("name"));
                mNonVegPizza.setDescription(jsonObject.getString("description"));
                mNonVegPizza.setImage(jsonObject.getString("image"));
                mNonVegPizza.setPrice(jsonObject.getString("price"));


                JSONArray mSelectCrustArray = jsonObject.getJSONArray("selectCrust");

                for (int j = 0; j< mSelectCrustArray.length(); j++) {
                    SelectCrust mSelectCrust = new SelectCrust();
                    JSONObject mSelectCrustObject = mJsonArray.getJSONObject(j);
                    mSelectCrust.setCrustName(mSelectCrustObject.getString("crustName"));
                    mSelectCrust.setCrustSelect(mSelectCrustObject.getString("crustSelect"));

                    mSelectCrusts.add(mSelectCrust);
                    mNonVegPizza.setSelectCrust(mSelectCrusts);
                }

                mNonVegPizzas.add(mNonVegPizza);
                NVegPizza mNVegPizza = new NVegPizza();
                mNVegPizza.setNonVegPizza(mNonVegPizzas);
                mNVegPizzas.add(mNVegPizza);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

}
