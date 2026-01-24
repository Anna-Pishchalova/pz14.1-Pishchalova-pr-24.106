package com.example.pz14;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends MainActivity implements View.OnClickListener {

    EditText etNum1;
    EditText etNum2;
    Button btnAdd;
    Button btnSub;
    Button btnMult;
    Button btnDiv;
    TextView tvResult;
    String oper = "";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        set ContentView;(R.layout.activity_main);

        etNum1 = (EditText) etNum1.findViewById(R.id.n1);
        etNum2 = (EditText) etNum2.findViewById(R.id.n2);
        btnAdd = (Button) etNum2.findViewById(R.id.add);
        btnSub = (Button) btnAdd.findViewById(R.id.sub);
        btnMult = (Button) btnMult.findViewById(R.id.mul);
        btnDiv = (Button) btnDiv.findViewById(R.id.div);
        tvResult = (TextView) tvResult.findViewById(R.id.resultField);

        btnAdd.setOnClickListener((View.OnClickListener) this);
        btnSub.setOnClickListener((View.OnClickListener) this);
        btnMult.setOnClickListener((View.OnClickListener) this);
        btnDiv.setOnClickListener((View.OnClickListener) this);

    }

    @Override
    public void onClick(View v) {

        float num1 = 0;
        float num2 = 0;
        float result = 0;

        if(TextUtils.isEmpty(etNum1.getText().toString())
                || TextUtils.isEmpty(etNum2.getText().toString())) {
            return;
        }

        num1 = Float.parseFloat(etNum1.getText().toString());
        num2 = Float.parseFloat(etNum2.getText().toString());

        switch(v.getId()) {
            case R.id.add:
            oper = "+";
            result = num1 + num2;
            break;
            case R.id.sub:
            oper = "-";
            result = num1 - num2;
            break;
            case R.id.mul:
            oper = "*";
            result = num1 * num2;
            break;
            case R.id.div:
            oper = "/";
            result = num1 / num2;
            break;
            default:
                break;
        }
        tvResult.setText(num1 + " "+ oper + " "+ num2 + " = "+ result);
    }
}
