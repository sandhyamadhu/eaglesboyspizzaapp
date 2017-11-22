package com.appcare.eaglesboys.Product;

/**
 * Created by Appcare on 21-11-2017.
 */


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.appcare.eaglesboys.R;
import com.appcare.eaglesboys.menu.MenuActivity;
import com.appcare.eaglesboys.utils.CenterRepository;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.VersionViewHolder> {
    private int itemCount = 0;
    private String ImageUrl;
    private List<ProductModel> productList = new ArrayList<ProductModel>();
    private Context context;


    public ProductAdapter(Context context, ArrayList<ProductModel> productList) {
        this.context = context;
        this.productList = productList;



    }

    @Override
    public VersionViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.productlistitem_row, viewGroup, false);
        VersionViewHolder viewHolder = new VersionViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final VersionViewHolder holder, final int position) {

        final ProductModel tempObj = productList.get(position);

        final List<String> mList;

        mList = new ArrayList<String> ();

        for (int i = 0; i < productList.size(); i++) {
            String price = tempObj.getMrp();
            mList.add(price);
        }
        holder.itemName.setText(productList.get(position).getProductName());
        holder.itemCost.setText(productList.get(position).getMrp());
        ImageUrl = productList.get(position).getImageUrl();

        Picasso.with(context).load(ImageUrl).error(R.drawable.ic_menu_camera).into(holder.imagView);

        holder.addItem.setOnClickListener(
                new View.OnClickListener () {

                    @Override
                    public void onClick(View v) {
                        //current object

                        if (CenterRepository.getCenterRepository().getListOfProductsInShoppingList().contains(tempObj)) {

                            //get position of current item in shopping list
                            int indexOfTempInShopingList = CenterRepository
                                    .getCenterRepository().getListOfProductsInShoppingList()
                                    .indexOf(tempObj);

                            // update quanity in shopping list
                            CenterRepository
                                    .getCenterRepository()
                                    .getListOfProductsInShoppingList()
                                    .get(indexOfTempInShopingList)
                                    .setQuantity(
                                            String.valueOf(Integer
                                                    .valueOf(tempObj
                                                            .getQuantity()) + 1));
                            // update quanity in cart list
                            CenterRepository
                                    .getCenterRepository()
                                    .getListOfProductsInShoppingList()
                                    .get(indexOfTempInShopingList)
                                    // .setMrp(String.valueOf((" ₹ ") + "" + (Integer.valueOf(mList.get(position)) * Integer.valueOf(tempObj.getQuantity()))));
                                    .setMrp(String.valueOf((Integer.valueOf(mList.get(position)))));


                            // update current item quanitity
                            holder.quanitity.setText(tempObj.getQuantity());
                            holder.itemCost.setText(String.valueOf((Integer.valueOf(mList.get(position)))));

                            String text = holder.quanitity.getText().toString();
                            String finalcost = holder.itemCost.getText().toString();
                            Log.e("hai add", text);
                            Log.e("hai add", finalcost);

                        } else {

                            tempObj.setQuantity(String.valueOf(1));
                            holder.quanitity.setText(tempObj.getQuantity());
                            CenterRepository.getCenterRepository().getListOfProductsInShoppingList().add(tempObj);

                        }


                    }
                });


        holder.removeItem.setOnClickListener(new View.OnClickListener () {

            @Override
            public void onClick(View v) {


                if (CenterRepository.getCenterRepository().getListOfProductsInShoppingList()
                        .contains(tempObj)) {

                    int indexOfTempInShopingList = CenterRepository.getCenterRepository().getListOfProductsInShoppingList().indexOf(tempObj);

                    if (Integer.valueOf(tempObj.getQuantity()) != 1) {

                        CenterRepository.getCenterRepository().getListOfProductsInShoppingList().get(indexOfTempInShopingList).setQuantity(
                                String.valueOf(Integer.valueOf(tempObj
                                        .getQuantity()) - 1));
                        CenterRepository.getCenterRepository().getListOfProductsInShoppingList().get(indexOfTempInShopingList)
                                //.setMrp(String.valueOf(Integer.valueOf(mList.get(position)) / Integer.valueOf(tempObj.getQuantity())));
                                .setMrp(String.valueOf(Integer.valueOf(mList.get(position))));

                        holder.quanitity.setText(CenterRepository
                                .getCenterRepository().getListOfProductsInShoppingList()
                                .get(indexOfTempInShopingList).getQuantity());
                        holder.itemCost.setText(tempObj.getMrp());

                        if (Integer.valueOf(CenterRepository
                                .getCenterRepository().getListOfProductsInShoppingList()
                                .get(indexOfTempInShopingList).getQuantity()) == 1) {

                            CenterRepository.getCenterRepository().getListOfProductsInShoppingList().remove(indexOfTempInShopingList);
                            // ((MenuActivity) context).updateItemCount(false);
                            notifyDataSetChanged();

                        }

                    }

                } else {

                }

            }

        });
        holder.btnBeveragesAddToCart.setOnClickListener(new View.OnClickListener () {
            @Override
            public void onClick(View view) {

                String orderqty = holder.quanitity.getText().toString();
                String itemCost = holder.itemCost.getText().toString();
                String itemname = holder.itemName.getText().toString();
                String id = productList.get(position).getProductId();

                ProductModel model = new ProductModel(itemCost, orderqty, "", itemname, id);
                Log.e("Data", model.toString());


                if (Integer.valueOf(tempObj.getQuantity()) != 1) {
                    CenterRepository.getCenterRepository().getlistOfProductsCartingList().add(model);
                    MenuActivity.itemCountTextView.setText(String.valueOf(CenterRepository.getCenterRepository().getlistOfProductsCartingList().size()));

                } else {
                    CenterRepository.getCenterRepository().getlistOfProductsCartingList().add(model);
                }
                for (int i = 0; i < CenterRepository.getCenterRepository().getlistOfProductsCartingList().size(); i++) {

                    for (int j = i + 1; j < CenterRepository.getCenterRepository().getlistOfProductsCartingList().size(); j++) {
                        if (CenterRepository.getCenterRepository().getlistOfProductsCartingList().get(i).getProductId().equals(CenterRepository.getCenterRepository().getlistOfProductsCartingList().get(j).getProductId())) {
                            CenterRepository.getCenterRepository().getlistOfProductsCartingList().remove(j);
                            j--;
                        }
                    }

                }
                MenuActivity.itemCountTextView.setText(String.valueOf(CenterRepository.getCenterRepository().getlistOfProductsCartingList().size()));
                //new AlertDialog.Builder(context).setMessage(model.toString()).setPositiveButton("ok", null).show();
            }
        });


    }


    @Override
    public int getItemCount() {
        return productList == null ? 0 : productList.size();
    }

    class VersionViewHolder extends RecyclerView.ViewHolder {

        TextView itemName, itemCost, addItem, removeItem, quanitity;
        ImageView imagView;
        Button btnBeveragesAddToCart;

        public VersionViewHolder(View itemView) {
            super(itemView);
            btnBeveragesAddToCart = (Button) itemView.findViewById(R.id.btnBeveragesAddToCart);
            quanitity = (TextView) itemView.findViewById(R.id.edtStartPrice);
            itemName = (TextView) itemView.findViewById(R.id.txtBeveragesName);
            imagView = ((ImageView) itemView.findViewById(R.id.imgBeverages));
            addItem = ((TextView) itemView.findViewById(R.id.imgBeveragesPlusPrice));
            removeItem = ((TextView) itemView.findViewById(R.id.imgBeveragesMinusPrice));
            itemCost = ((TextView) itemView.findViewById(R.id.txtBeveragesPrice));

        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

}