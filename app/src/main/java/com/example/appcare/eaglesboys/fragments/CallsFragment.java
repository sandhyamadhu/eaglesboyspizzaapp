package com.example.appcare.eaglesboys.fragments;


import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.appcare.eaglesboys.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class CallsFragment extends Fragment {
    TextView name,atettext,add,cost;
    ImageView im1,addcarthere;
    ImageView up,down;
    CardView one,two;
    Button customizetopping,addtocart,aplus,aminus;
    LinearLayout popup;
    CheckBox cb1,cb2,cb3,cb5;
    CheckBox cb6,cb7,cb8,cb9,cb10;
    FrameLayout f1;
    String abc,bcd;
    Toolbar t1;
    RadioButton r1,r2;
    RadioGroup radioGroup1;

    public CallsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_calls, container, false);
        final View rootView = inflater.inflate(R.layout.fragment_calls, container, false);
        t1=(Toolbar)rootView.findViewById(R.id.toolbar);
        down=(ImageView) rootView.findViewById(R.id.expanddown);
        up=(ImageView) rootView.findViewById(R.id.expandup);
        one=(CardView) rootView.findViewById(R.id.cardView1);
        two=(CardView) rootView.findViewById(R.id.cardView2);
        customizetopping=(Button) rootView.findViewById(R.id.customize);
        popup=(LinearLayout) rootView.findViewById(R.id.design);
        addtocart=(Button) rootView.findViewById(R.id.btn1);
        cb1=(CheckBox) rootView.findViewById(R.id.checkBox1);
        cb2=(CheckBox) rootView.findViewById(R.id.checkBox2);
        cb3=(CheckBox) rootView.findViewById(R.id.checkBox3);
        cb6=(CheckBox) rootView.findViewById(R.id.checkBox6);
        cb7=(CheckBox) rootView.findViewById(R.id.checkBox7);
        cb8=(CheckBox) rootView.findViewById(R.id.checkBox8);
//        cb9=(CheckBox) rootView.findViewById(R.id.checkBox9);
//        cb10=(CheckBox) rootView.findViewById(R.id.checkBox10);
        cb5=(CheckBox) rootView.findViewById(R.id.checkBox5);
        aplus=(Button) rootView.findViewById(R.id.plus);
        aminus=(Button) rootView.findViewById(R.id.minus);
        atettext=(TextView) rootView.findViewById(R.id.texttext);
        add=(TextView) rootView.findViewById(R.id.add);
        addcarthere=(ImageView) rootView.findViewById(R.id.showcart);
        cost=(TextView) rootView.findViewById(R.id.costexp);





        aplus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abc=atettext.getText().toString();
                String getcount= increment(abc);
                atettext.setText(getcount);
//                multiplyprice(atettext.getText().toString());

            }
        });
        aminus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int a=Integer.parseInt(atettext.getText().toString());
                if(!(a<=0)) {
                    int b = a - 1;
                    String c = String.valueOf(b);
                    atettext.setText(c);
//                    multiplyprice(atettext.getText().toString());
                }
            }
        });

        customizetopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showInputDialog();

            }
        });
        down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                one.setVisibility(View.INVISIBLE);
                two.setVisibility(View.VISIBLE);
            }
        });
        up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                two.setVisibility(View.INVISIBLE);
                one.setVisibility(View.VISIBLE);

            }
        });
//        addtocart.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String Item = getActivity().getIntent().getExtras().getString("count");
//                String getcount= increment(Item);
//                Intent inn=new Intent(getActivity(),RealMenu.class);
//                inn.putExtra("newcount",getcount);
//                startActivity(inn);
//
//
//            }
//        });

        cb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cb8.setVisibility(View.INVISIBLE);
                cb8.setChecked(false);
                cb6.setChecked(true);
                cb1.setChecked(true);

                if(cb2.isChecked()) {
                    cb3.setChecked(false);
                    cb2.setChecked(false);
                    changeprice(-100);
                }
                if(cb3.isChecked())
                {
                    cb3.setChecked(false);
                    cb2.setChecked(false);
                    changeprice(-200);
                }


            }
        });
        cb3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cb8.setVisibility(View.INVISIBLE);
                cb3.setChecked(true);
                cb8.setChecked(false);
                cb6.setChecked(true);

                if(cb1.isChecked()) {
                    cb1.setChecked(false);
                    cb2.setChecked(false);
                    changeprice(200);
                }
                if(cb2.isChecked())
                {
                    cb1.setChecked(false);
                    cb2.setChecked(false);
                    changeprice(100);
                }


            }
        });
        cb2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cb8.setVisibility(View.VISIBLE);
                cb2.setChecked(true);
                if(cb1.isChecked()) {
                    cb1.setChecked(false);
                    cb3.setChecked(false);
                    changeprice(100);
                }
                if(cb3.isChecked())
                { cb1.setChecked(false);
                    cb3.setChecked(false);
                    changeprice(-100);

                }


            }
        });

        cb5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cb5.isChecked())
                {
                    changeprice(50);
                }
                else
                {
                    changeprice(-50);
                }


            }
        });
        cb6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cb6.setChecked(true);
                if(cb7.isChecked())
                {
                    cb7.setChecked(false);
                    cb8.setChecked(false);
                    changeprice(-10);
                }
               if(cb8.isChecked())
                {
                    cb7.setChecked(false);
                    cb8.setChecked(false);
                    changeprice(-20);
                }

            }
        });

        cb7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                cb7.setChecked(true);
                if(cb6.isChecked())
                {
                    cb6.setChecked(false);
                    cb8.setChecked(false);
                    changeprice(10);
                }
                if(cb8.isChecked())
                {
                    cb6.setChecked(false);
                    cb8.setChecked(false);
                    changeprice(-10);
                }
            }
        });
        cb8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                cb8.setChecked(true);
                if(cb7.isChecked())
                {
                    cb7.setChecked(false);
                    cb6.setChecked(false);
                    changeprice(10);
                }
                if(cb6.isChecked())
                {
                    cb7.setChecked(false);
                    cb6.setChecked(false);
                    changeprice(20);
                }

            }
        });
//        cb9.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                cb6.setChecked(false);
//                cb8.setChecked(false);
//                cb7.setChecked(false);
//                cb10.setChecked(false);
//            }
//        });
//        cb10.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                cb6.setChecked(false);
//                cb8.setChecked(false);
//                cb9.setChecked(false);
//                cb7.setChecked(false);
//            }
//        });
//

        CheckBox cb = (CheckBox) rootView.findViewById(R.id.checkBox1);

        cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) { buttonView.setTextColor(getResources().getColor(R.color.customeColor)); }
                if (!isChecked) { buttonView.setTextColor(getResources().getColor(R.color.blackcolor)); }
            }
        });

        return rootView;
    }

//    private void multiplyprice( String newwprice) {
//        int oldcost=Integer.parseInt(cost.getText().toString());
//        int intoldcost=Integer.parseInt(newwprice);
//        int newcost=(oldcost*intoldcost);
//        cost.setText(String.valueOf(newcost));    }

    private void changeprice(int newprice) {

        int oldcost=Integer.parseInt(cost.getText().toString());
        int newcost=oldcost+newprice;
        cost.setText(String.valueOf(newcost));

    }

    protected void showInputDialog() {
        LayoutInflater layoutInflater = LayoutInflater.from(CallsFragment.this.getActivity());
        View promptView = layoutInflater.inflate(R.layout.topping, null);
        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                CallsFragment.this.getActivity());
        alertDialogBuilder.setView(promptView);
        final ImageView  aa= (ImageView) promptView
                .findViewById(R.id.imgaa);
        final RadioGroup radioGroup1 = (RadioGroup)promptView.findViewById(R.id.radioGroup1);
        final RadioButton  r1=(RadioButton) promptView.findViewById(R.id.radioAndroid);
        final RadioButton  r2=(RadioButton) promptView.findViewById(R.id.radioWindows);
        r1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Intent i=new Intent(getActivity(),CallsFragment.class);
                startActivity(i);
            }
        });
        r2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Intent i=new Intent(getActivity(),CallsFragment.class);
                startActivity(i);
            }
        });



        AlertDialog alert = alertDialogBuilder.create();
        alert.show();
//       alert.getWindow().setLayout(1000,1500);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        Window window = alert.getWindow();
        lp.copyFrom(window.getAttributes());
//This makes the dialog take up the full width
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        window.setAttributes(lp);
    }
    public String increment(String takenumber)
    {
        int az=Integer.parseInt(takenumber.toString());
        int bz=az+1;
        String c = String.valueOf(bz);

        return c;
    }

}

//    @Override
//    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        inflater.inflate(R.menu.menu_calls_fragment, menu);
//        super.onCreateOptionsMenu(menu, inflater);
//    }


