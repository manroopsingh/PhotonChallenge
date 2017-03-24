package com.example.singh.photonchallenge;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.editTextHeight)
    EditText etHeight;

    @BindView(R.id.editTextWidth)
    EditText etWidth;


    String matrixHeight="", matrixWidth="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btnCreateMatrix)
    public void createMatrix() {
        matrixHeight = etHeight.getText().toString();
        matrixWidth = etWidth.getText().toString();
        if (!validateDimentions()) return;
        Intent intent = new Intent(this, GridActivity.class);
        intent.putExtra("Height", matrixHeight);
        intent.putExtra("Width", matrixWidth);
        startActivity(intent);

    }

    public boolean validateDimentions() {

        Log.e("MAIN_ACTIVITY", "validateDimentions: " +matrixHeight);
        if (TextUtils.isEmpty(matrixHeight) || TextUtils.isEmpty(matrixWidth)) {
            Toast.makeText(this, "Enter both dimentions.", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (Integer.parseInt(matrixHeight) > 10 || Integer.parseInt(matrixHeight) < 1) {
            Toast.makeText(this, "Enter height within 1 and 10", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (Integer.parseInt(matrixWidth) > 10 || Integer.parseInt(matrixWidth) < 5) {
            Toast.makeText(this, "Enter width within 5 and 10", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}
