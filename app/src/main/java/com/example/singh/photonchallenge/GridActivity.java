package com.example.singh.photonchallenge;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.singh.photonchallenge.solution.CalculatePaths;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GridActivity extends AppCompatActivity {

    @BindView(R.id.tvHeight)
    TextView textViewHeight;

    @BindView(R.id.tvWidth)
    TextView textViewWidth;

    @BindView(R.id.btnCalculatePath)
    Button btnCalculatePath;

    @BindView((R.id.result))
    TextView tvResult;


    private int matrixHeight, matrixWidth;
    private String value = "";
    private List<List<EditText>> editTextGrid;
    int[][] matrix;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        matrixHeight = Integer.parseInt(intent.getStringExtra("Height"));
        matrixWidth = Integer.parseInt(intent.getStringExtra("Width"));

        textViewHeight.setText(intent.getStringExtra("Height"));
        textViewWidth.setText((intent.getStringExtra("Width")));

        createMatrix(matrixHeight, matrixWidth);
    }

    @OnClick(R.id.btnCalculatePath)
    public void calculatePath() {
        convertMatrix();
        if (!validateDimentions()) return;
        CalculatePaths calculatePaths = new CalculatePaths(matrix);
        tvResult.setText(calculatePaths.findShortestPath());

    }


    private void createMatrix(int matrixHeight, int matrixWidth) {

        LinearLayout gridLayout = (LinearLayout) findViewById(R.id.layoutContainer);
        editTextGrid = new ArrayList<>();

        for (int widthIndex = 0; widthIndex < matrixWidth; widthIndex++) {
            List<EditText> column = new ArrayList<>();
            LinearLayout linearLayout = new LinearLayout(this);
            linearLayout.setOrientation(LinearLayout.VERTICAL);
            for (int heightIndex = 0; heightIndex < matrixHeight; heightIndex++) {

                EditText editText = new EditText(this);
                editText.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                editText.setTextSize(25);
                editText.setWidth(175);
                editText.setHeight(175);
                editText.setBackgroundResource(R.drawable.edit_text_border);
                editText.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_SIGNED);
                column.add(editText);
                linearLayout.addView(editText);
            }
            editTextGrid.add(column);
            gridLayout.addView(linearLayout);
        }


    }


    public void convertMatrix() {
        matrix = new int[matrixHeight][matrixWidth];

        for (int widthIndex = 0; widthIndex < matrixWidth; widthIndex++) {
            for (int heightIndex = 0; heightIndex < matrixHeight; heightIndex++) {
                matrix[heightIndex][widthIndex] = Integer.parseInt(editTextGrid.get(widthIndex).get(heightIndex).getText().toString());
            }
        }


    }


    public boolean validateDimentions() {

        for (int widthIndex = 0; widthIndex < matrixWidth; widthIndex++) {
            for (int heightIndex = 0; heightIndex < matrixHeight; heightIndex++) {
                value = editTextGrid.get(widthIndex).get(heightIndex).getText().toString();

                if (TextUtils.isEmpty(value)) {
                    Toast.makeText(this, "Please enter all values", Toast.LENGTH_SHORT).show();
                    return false;
                }
            }
        }
        return true;
    }

}

