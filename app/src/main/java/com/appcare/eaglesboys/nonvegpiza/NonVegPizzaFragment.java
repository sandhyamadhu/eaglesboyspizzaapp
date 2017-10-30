package com.appcare.eaglesboys.nonvegpiza;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.appcare.eaglesboys.constants.CommonFragment;
import com.appcare.eaglesboys.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class NonVegPizzaFragment extends CommonFragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View mNonVegPizza = inflater.inflate(R.layout.fragment_nonvegpizza,null);

        initNonVegPizzaViews(mNonVegPizza);
        return mNonVegPizza;
    }

    private ExpandableListView mNonVegPizzaListView;
    private void initNonVegPizzaViews(View mNonVegPizza) {
        mNonVegPizzaListView = (ExpandableListView)mNonVegPizza.findViewById(R.id.lvNonVegPizza);
        init();
    }

    //String mNonVegPizza = "{\"NonVegPizza\":[{\"name\":\"Veg Piza\",\"description\":\"Onion | Capsium | Olive | Mushrom\",\"price\":\"365\",\"image\":\"http://marssofttech.com/demos/eaglepizza//uploads/foods/39_2017/dafc942952aab8e3fb6a29e99a14b1a4.png\",\"selectCrust\":[{\"crustSelect\":\"true\",\"crustName\":\"New Hand Tossed\"},{\"crustSelect\":\"false\",\"crustName\":\"Wheet Thin Crush\"},{\"crustSelect\":\"false\",\"crustName\":\"Cheese Crush\"},{\"crustSelect\":\"false\",\"crustName\":\"Fresh Pan Pizza\"},{\"crustSelect\":\"false\",\"crustName\":\"Classic Hand Tossed\"}]}]}";

    String mNonVegPizza = "{\"NonVegPizza\":[{\"name\":\"Veg Piza\",\"description\":\"Onion | Capsium | Olive | Mushrom\",\"price\":\"365\",\"image\":\"http://marssofttech.com/demos/eaglepizza//uploads/foods/39_2017/dafc942952aab8e3fb6a29e99a14b1a4.png\",\"Child\":[{\"selectCrust\":[{\"crustSelect\":\"true\",\"crustName\":\"New Hand Tossed\"},{\"crustSelect\":\"false\",\"crustName\":\"Wheet Thin Crush\"}],\"pizzaPrice\":[{\"crustSelect\":\"true\",\"crustName\":\"123\"},{\"crustSelect\":\"false\",\"crustName\":\"456\"}]}]},{\"name\":\"Veg Piza\",\"description\":\"Onion | Capsium | Olive | Mushrom\",\"price\":\"2235\",\"image\":\"http://marssofttech.com/demos/eaglepizza//uploads/foods/39_2017/dafc942952aab8e3fb6a29e99a14b1a4.png\",\"Child\":[{\"selectCrust\":[{\"crustSelect\":\"true\",\"crustName\":\"Hand Tossed\"},{\"crustSelect\":\"false\",\"crustName\":\"Thin Crush\"}],\"pizzaPrice\":[{\"crustSelect\":\"true\",\"crustName\":\"789\"},{\"crustSelect\":\"false\",\"crustName\":\"910\"}]}]}]}";
    ArrayList<NonVegPizza> mNonVegPizzas = new ArrayList<>();
    ArrayList<SelectCrust> mSelectCrusts = new ArrayList<>();
    ArrayList<PizzaPrice> mPizzaPrice = new ArrayList<>();
    ArrayList<Child> mChild = new ArrayList<>();



    private void init(){


        try {
            JSONObject mJSObject = new JSONObject(mNonVegPizza.toString());
            JSONArray mJsonArray = mJSObject.getJSONArray("NonVegPizza");
            for (int i=0; i<mJsonArray.length(); i++){


                NonVegPizza mNonVegPizza = new NonVegPizza();

                JSONObject jsonObject = mJsonArray.getJSONObject(i);

                mNonVegPizza.setName(jsonObject.getString("name"));
                mNonVegPizza.setDescription(jsonObject.getString("description"));
                mNonVegPizza.setImage(jsonObject.getString("image"));
                mNonVegPizza.setPrice(jsonObject.getString("price"));

                JSONArray mChildArray = jsonObject.getJSONArray("Child");
                for (int j = 0; j< mChildArray.length(); j++) {

                    Child mInnerChild = new Child();

                    JSONObject custJosn1 = mChildArray.getJSONObject(j);

                    JSONArray selectCrust = custJosn1.getJSONArray("selectCrust");
                    JSONArray pizzaPrice = custJosn1.getJSONArray("pizzaPrice");

                    for (int K = 0; K< selectCrust.length(); K++){
                        SelectCrust mSelectCrust1 = new SelectCrust();
                        JSONObject custJosn = selectCrust.getJSONObject(K);
                        mSelectCrust1.setCrustName(custJosn.getString("crustName"));
                        mSelectCrusts.add(mSelectCrust1);

                        mChild.clear();

                    }

                    for (int l = 0; l< pizzaPrice.length(); l++){


                        PizzaPrice pizzaPrice1 = new PizzaPrice();
                        JSONObject pizzaJson = pizzaPrice.getJSONObject(l);
                        pizzaPrice1.setCrustName(pizzaJson.getString("crustName"));
                        mPizzaPrice.add(pizzaPrice1);
                    }

                    mInnerChild.setSelectCrust(mSelectCrusts);
                    mInnerChild.setPizzaPrice(mPizzaPrice);
                    mChild.add(mInnerChild);

                }

                mNonVegPizza.setChild(mChild);
                mNonVegPizzas.add(mNonVegPizza);

            }

            initNonVegPizzaAdapter();

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /*@Override
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


                mNonVegPizzas.add(mNonVegPizza);

               *//* JSONArray mSelectCrustArray = jsonObject.getJSONArray("selectCrust");

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
                mNVegPizzas.add(mNVegPizza);*//*
            }

            initNonVegPizzaAdapter();

        } catch (JSONException e) {
            e.printStackTrace();
        }
        


    }*/

    private void initNonVegPizzaAdapter() {

        NonVegListAdapter mNonVegListAdapter = new NonVegListAdapter(getContext(), mNonVegPizzas, mChild);
        mNonVegPizzaListView.setAdapter(mNonVegListAdapter);
        mNonVegListAdapter.notifyDataSetChanged();

        mNonVegPizzaListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            int previousGroup = -1;

            @Override
            public void onGroupExpand(int groupPosition) {
                if(groupPosition != previousGroup)
                    mNonVegPizzaListView.collapseGroup(previousGroup);
                previousGroup = groupPosition;
            }
        });



    }

}
