package com.appcare.eaglesboys.Profile;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.appcare.eaglesboys.R;
import com.appcare.eaglesboys.menuotp.MainActivity;
import com.appcare.eaglesboys.utils.ConstantsHelper;
import com.appcare.eaglesboys.utils.RuntimePermissions;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static android.app.Activity.RESULT_OK;
import static com.appcare.eaglesboys.utils.ConstantsHelper.compress;


public class ProfileFragment extends RuntimePermissions implements View.OnClickListener {

    private static final int REQUEST_PERMISSIONS = 1111;
    Button btnSubmit;
    ImageView profilepic;
    Button edtBtn, logoutBtn;
    EditText ename, enumber, eaddress;
    int RESULT_LOAD_IMAGE = 1;
    private static final int CAMERA_REQUEST = 1888;
    private Uri picUri;
    final int PIC_CROP = 1;
    String encoded = "";
    File f1;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View mProfile = inflater.inflate(R.layout.fragment_profile, null);
        initCreateViews(mProfile);
        checkRunTimePermission();
        return mProfile;
    }

    private void initCreateViews(View mProfile) {

        btnSubmit = (Button) mProfile.findViewById(R.id.btnSubmit);
        edtBtn = (Button) mProfile.findViewById(R.id.btnEdit);
        profilepic = (ImageView) mProfile.findViewById(R.id.profilepic);
        logoutBtn = (Button) mProfile.findViewById(R.id.btnLogout);
        ename = (EditText) mProfile.findViewById(R.id.txtname);
        enumber = (EditText) mProfile.findViewById(R.id.txtnumber);
        eaddress = (EditText) mProfile.findViewById(R.id.txtaddress);

        profilepic.setOnClickListener(this);
        logoutBtn.setOnClickListener(this);
        edtBtn.setOnClickListener(this);
        btnSubmit.setOnClickListener(this);

    }

    private boolean isEmpty(EditText etText) {
        return etText.getText().toString().trim().length() == 0;
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.profilepic:
                uploading();
                break;
            case R.id.btnEdit:
                ename.setFocusableInTouchMode(true);
                eaddress.setFocusableInTouchMode(true);
                break;
            case R.id.btnLogout:
                Intent i = new Intent(getActivity(), MainActivity.class);
                startActivity(i);
                break;
            case R.id.btnSubmit:
                Validation();
                break;


        }

    }

    private void uploading() {

        final CharSequence[] items = {"Take Photo", "Choose from Gallery", "Cancel"};
        android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(getActivity());
        builder.setTitle("Add Photo!");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (items[item].equals("Take Photo")) {
                    Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(cameraIntent, CAMERA_REQUEST);

                } else if (items[item].equals("Choose from Gallery")) {
                    Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(i, RESULT_LOAD_IMAGE);

                } else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }
    private void Validation() {

        if (isEmpty(ename)) {

            new AlertDialog.Builder(getContext()).setMessage("Please Enter Name").setPositiveButton("OK", null).show();

        } else if (isEmpty(eaddress)) {

            new AlertDialog.Builder(getContext()).setMessage("Please Enter the Email Address").setPositiveButton("OK", null).show();

        } else {

            String mName = ename.getText().toString();
            String mAddress = eaddress.getText().toString();
            String mPhone = enumber.getText().toString();
            ProfileModel model = new ProfileModel(mName, "", mAddress, "email", "5", encoded);

            new sendData().execute(model);
        }
    }


    public void checkRunTimePermission() {
        if (Build.VERSION.SDK_INT >= 23) {
            if ((ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
                    && (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)) {

                ProfileFragment.super.requestAppPermissions(new
                        String[]{Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE,}, R.string.runtime_permissions_txt, REQUEST_PERMISSIONS);
            }
        }
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            photo = compress(photo, Bitmap.CompressFormat.PNG, 100);
            profilepic.setImageBitmap(photo);
            encoded = ConstantsHelper.toBase64(photo);

        } else if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK) {
            try {

                String mPicturePath;
                picUri = data.getData();
                Uri selectedImage = data.getData();
                String[] filePathColumn = {MediaStore.Images.Media.DATA};

                Cursor cursor = getActivity().getContentResolver().query(selectedImage,
                        filePathColumn, null, null, null);
                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                mPicturePath = cursor.getString(columnIndex);
                cursor.close();
            } catch (NullPointerException e) {
                e.printStackTrace();
            }


        }
    }
    private void performCrop() {
        try {
            Intent cropIntent = new Intent("com.android.camera.action.CROP");
            cropIntent.setDataAndType(picUri, "image");
            cropIntent.putExtra("crop", "true");
            cropIntent.putExtra("aspectX", 1);
            cropIntent.putExtra("aspectY", 1);
            cropIntent.putExtra("outputX", 312);
            cropIntent.putExtra("outputY", 312);
            cropIntent.putExtra("return-data", true);
            startActivityForResult(cropIntent, PIC_CROP);
        } catch (ActivityNotFoundException anfe) {

        }
    }



















    @Override
    public void onPermissionsGranted(int requestCode) {
        Toast.makeText(getActivity(), "Permissions Received.", Toast.LENGTH_LONG).show();
    }

    public class sendData extends AsyncTask<ProfileModel, Void, Void> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // This is called before sending actual HTTP call...
        }

        @Override
        protected Void doInBackground(final ProfileModel... profileModels) {


            String url = "http://marssofttech.com/demos/eaglepizza/api/users_api/update_profile";
            StringRequest postRequest = new StringRequest(Request.Method.POST, url,

                    new Response.Listener<String>() {

                        @Override
                        public void onResponse(String response) {
                            // response

                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                String message = jsonObject.getString("message");
                                new AlertDialog.Builder(getContext()).setMessage(message).setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {

                                    }
                                }).show();

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }


                            Log.d("Response", response);
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            // error
                            Log.d("Error.Response", error.toString());
                        }
                    }
            ) {
                @Override

                protected Map<String, String> getParams() {

                    Map<String, String> params = new HashMap<String, String>();
                    params.put("id", profileModels[0].getId());
                    params.put("phone", profileModels[0].getNumber());
                    params.put("address", profileModels[0].getAddress());
                    params.put("email", profileModels[0].getEmail());
                    params.put("full_name", profileModels[0].getName());

                    byte[] decodedString1 = Base64.decode( profileModels[0].getAvt(), Base64.DEFAULT);
                    Bitmap decodedByte1 = BitmapFactory.decodeByteArray(decodedString1, 0, decodedString1.length);

                    if (decodedByte1 != null) {

                        f1= new File(mContext.getCacheDir(), decodedByte1 + ".png");
                        try {
                            f1.createNewFile();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        Bitmap bitmap1 = decodedByte1;
                        ByteArrayOutputStream bos1 = new ByteArrayOutputStream();
                        bitmap1.compress(Bitmap.CompressFormat.PNG, 0 /*ignored for PNG*/, bos1);
                        byte[] bitmapdata1 = bos1.toByteArray();
                        FileOutputStream fos1 = null;
                        try {
                            fos1 = new FileOutputStream(f1);
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                        try {
                            fos1.write(bitmapdata1);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        try {
                            fos1.flush();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        try {
                            fos1.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    if (f1 != null) {
                        params.put("avt", String.valueOf(f1));
                    }
                    Log.e("hai","hsii"+params.toString());
                    return params;
                }
            };
            RequestQueue rQueue = Volley.newRequestQueue(getContext());
            rQueue.add(postRequest);


            return null;
        }
    }

}