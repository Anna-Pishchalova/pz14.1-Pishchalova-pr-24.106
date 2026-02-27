package com.example.pz14;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private TextView resultField;
    private EditText numberField;
    private TextView operationField;
    private Double operand = null;
    private String lastOperation = "=";
    private final DecimalFormat decimalFormat = new DecimalFormat("#.##########", new DecimalFormatSymbols(Locale.getDefault()));

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resultField = findViewById(R.id.resultField);
        numberField = findViewById(R.id.numberField);
        operationField = findViewById(R.id.operationField);
        findViewById(R.id.add).setOnClickListener(v -> onOperationClick("+"));
        findViewById(R.id.sub).setOnClickListener(v -> onOperationClick("-"));
        findViewById(R.id.mul).setOnClickListener(v -> onOperationClick("*"));
        findViewById(R.id.div).setOnClickListener(v -> onOperationClick("/"));
        findViewById(R.id.rav).setOnClickListener(v -> onOperationClick("="));
        findViewById(R.id.n0).setOnClickListener(v -> onNumberClick("0"));
        findViewById(R.id.n1).setOnClickListener(v -> onNumberClick("1"));
        findViewById(R.id.n2).setOnClickListener(v -> onNumberClick("2"));
        findViewById(R.id.n3).setOnClickListener(v -> onNumberClick("3"));
        findViewById(R.id.n4).setOnClickListener(v -> onNumberClick("4"));
        findViewById(R.id.n5).setOnClickListener(v -> onNumberClick("5"));
        findViewById(R.id.n6).setOnClickListener(v -> onNumberClick("6"));
        findViewById(R.id.n7).setOnClickListener(v -> onNumberClick("7"));
        findViewById(R.id.n8).setOnClickListener(v -> onNumberClick("8"));
        findViewById(R.id.n9).setOnClickListener(v -> onNumberClick("9"));
        findViewById(R.id.del).setOnClickListener(v -> onNumberClick(","));
        Button clearBtn = findViewById(R.id.clear);
        Button deleteBtn = findViewById(R.id.del);
        clearBtn.setOnClickListener(v -> clearAll());
        deleteBtn.setOnClickListener(v -> deleteLastChar());
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("OPERATION", lastOperation);
        if (operand != null) outState.putDouble("OPERAND", operand);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        lastOperation = savedInstanceState.getString("OPERATION", "=");
        operand = savedInstanceState.containsKey("OPERAND") ? savedInstanceState.getDouble("OPERAND") : null;
        resultField.setText(operand != null ? formatDouble(operand) : "");
        operationField.setText(lastOperation);
    }

    public void onNumberClick(String number) {
        if (lastOperation.equals("=") && operand != null) {
            clearAll();
        }
        numberField.append(number);
    }

    public void onOperationClick(String op) {
        String numberStr = numberField.getText().toString();
        if (numberStr.isEmpty()) {
            if (op.equals("=") && operand != null) {
                operationField.setText(op);
                lastOperation = op;
            }
            return;
        }
        numberStr = numberStr.replace(',', '.');
        double number;
        try {
            number = Double.parseDouble(numberStr);
        } catch (NumberFormatException e) {
            numberField.setText("");
            return;
        }

        performOperation(number, op);
        lastOperation = op;
        operationField.setText(lastOperation);
    }

    private void performOperation(double number, String operation) {
        if (operand == null) {
            operand = number;
        } else {
            if (lastOperation.equals("=")) {
                lastOperation = operation;
            }

            switch (lastOperation) {
                case "+":
                    operand += number;
                    break;
                case "-":
                    operand -= number;
                    break;
                case "*":
                    operand *= number;
                    break;
                case "/":
                    if (number == 0) {
                        resultField.setText("Error");
                        operand = null;
                        lastOperation = "=";
                        return;
                    } else {
                        operand /= number;
                    }
                    break;
                default:
                    operand = number;
                    break;
            }
        }

        resultField.setText(operand != null ? formatDouble(operand) : "");
        numberField.setText("");
    }
    private void clearAll() {
        operand = null;
        lastOperation = "=";
        resultField.setText("");
        operationField.setText(lastOperation);
        numberField.setText("");
    }
    private void deleteLastChar() {
        String current = numberField.getText().toString();
        if (!current.isEmpty()) {
            numberField.setText(current.substring(0, current.length() - 1));
        }
    }
    private String formatDouble(double value) {
        return decimalFormat.format(value);
    }
}