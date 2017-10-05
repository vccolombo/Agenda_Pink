package com.example.agendapink.agendapink;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;

public class CadastroContato extends AppCompatActivity {

    static final int REQUEST_IMAGE_CAPTURE = 1;
    static  final int PLACE_PICKER_REQUEST = 2;


    ImageView mImageView;
    Button openMapButton;
    EditText addressEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_contato);

        mImageView = (ImageView) findViewById(R.id.sign_up_image);
        openMapButton = (Button) findViewById(R.id.sign_up_map_button);
        addressEditText = (EditText) findViewById(R.id.sign_up_address);

        openMapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Intent openMapIntent = new Intent(Intent.ACTION_VIEW);
                Uri gmmIntentUri = Uri.parse("geo:0,0?q=my+street+address");
                openMapIntent.setPackage("com.google.android.apps.maps");
                startActivity(openMapIntent);*/
                PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
                try {
                    startActivityForResult(builder.build(CadastroContato.this), PLACE_PICKER_REQUEST);
                } catch (GooglePlayServicesRepairableException e) {
                    e.printStackTrace();
                } catch (GooglePlayServicesNotAvailableException e) {
                    e.printStackTrace();
                }

            }
        });


    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            mImageView.setImageBitmap(imageBitmap);
        }

        if (requestCode == PLACE_PICKER_REQUEST) {
            if (resultCode == RESULT_OK) {
                Place place = PlacePicker.getPlace(this, data);
                String msg = String.format("%s", place.getAddress());
                //Toast.makeText(this, toastMsg, Toast.LENGTH_LONG).show();
                addressEditText.setText(msg);
            }
        }
    }

    public void onSignUpImageClick(View view) {
        dispatchTakePictureIntent();
    }
}
