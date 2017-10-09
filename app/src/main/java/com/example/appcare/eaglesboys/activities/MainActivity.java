package com.example.appcare.eaglesboys.activities;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.appcare.eaglesboys.R;

public class MainActivity extends AppCompatActivity {

    private AlertDialog.Builder builder;
    RelativeLayout one,two;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        onCreateDialog();

    }

    private void onCreateDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.otppopup,null);
        builder.setView(dialogView);
        final AlertDialog dialog = builder.create();
        dialog.show();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private void onCreateDialog2() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.otpagain,null);

        TextView txt = (TextView)dialogView.findViewById(R.id.txtResendOTP);
        txt.setPaintFlags(txt.getPaintFlags()|Paint.UNDERLINE_TEXT_FLAG);

        builder.setView(dialogView);
        AlertDialog dialog = builder.create();
        dialog.show();

    }


    public void onSendOTP(View view) {
        onCreateDialog2();
    }

    public void onClickSkip(View view) {
        Intent i=new Intent(MainActivity.this,Clickskip.class);
        startActivity(i);
    }

    public void addNewAddress(View view) {
        Intent i=new Intent(MainActivity.this,AddAddress.class);
        startActivity(i);
    }
}
