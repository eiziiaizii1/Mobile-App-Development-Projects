package com.example.calculatorapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Stack;

public class MainActivity extends AppCompatActivity {

    private EditText display;
    private StringBuilder input = new StringBuilder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = findViewById(R.id.display);
        display.setClickable(false);
        display.setFocusable(false);

        setupButtonListeners();
    }

    private void setupButtonListeners() {
        int[] numberButtonIds = {
                R.id.btn_0, R.id.btn_1, R.id.btn_2, R.id.btn_3, R.id.btn_4,
                R.id.btn_5, R.id.btn_6, R.id.btn_7, R.id.btn_8, R.id.btn_9
        };

        View.OnClickListener numberListener = v -> {
            Button button = (Button) v;
            input.append(button.getText().toString());
            display.setText(input.toString());
        };

        for (int id : numberButtonIds) {
            findViewById(id).setOnClickListener(numberListener);
        }

        int[] operatorButtonIds = {
                R.id.btn_add, R.id.btn_subtract, R.id.btn_multiply, R.id.btn_divide
        };

        View.OnClickListener operatorListener = v -> {
            if (input.length() > 0 && !endsWithOperator()) {
                Button button = (Button) v;
                input.append(" ").append(button.getText().toString()).append(" ");
                display.setText(input.toString());
            }
        };

        for (int id : operatorButtonIds) {
            findViewById(id).setOnClickListener(operatorListener);
        }

        findViewById(R.id.btn_equals).setOnClickListener(v -> calculateResult());
        findViewById(R.id.btn_clear).setOnClickListener(v -> clearAll());
        findViewById(R.id.btn_backspace).setOnClickListener(v -> deleteLastChar());
        findViewById(R.id.btn_decimal).setOnClickListener(v -> addDecimal());
    }

    private void calculateResult() {
        if (input.length() > 0 && !endsWithOperator()) {
            try {
                double result = evaluateExpression(input.toString());
                display.setText(String.valueOf(result));
                input.setLength(0);
                input.append(result);
            } catch (Exception e) {
                display.setText("Error");
                input.setLength(0);
            }
        }
    }

    private double evaluateExpression(String expression) {
        Stack<Double> numbers = new Stack<>();
        Stack<Character> operators = new Stack<>();

        String[] tokens = expression.split(" ");

        for (String token : tokens) {
            if (token.matches("-?\\d+(\\.\\d+)?")) { // If it's a number
                numbers.push(Double.parseDouble(token));
            } else if (token.matches("[+\\-*/]")) { // If it's an operator
                while (!operators.isEmpty() && precedence(operators.peek()) >= precedence(token.charAt(0))) {
                    numbers.push(applyOperation(operators.pop(), numbers.pop(), numbers.pop()));
                }
                operators.push(token.charAt(0));
            }
        }

        while (!operators.isEmpty()) {
            numbers.push(applyOperation(operators.pop(), numbers.pop(), numbers.pop()));
        }

        return numbers.pop();
    }

    private int precedence(char operator) {
        switch (operator) {
            case '+': case '-':
                return 1;
            case '*': case '/':
                return 2;
            default:
                return 0;
        }
    }

    private double applyOperation(char operator, double b, double a) {
        switch (operator) {
            case '+': return a + b;
            case '-': return a - b;
            case '*': return a * b;
            case '/': return (b != 0) ? a / b : 0;
            default: return 0;
        }
    }

    private void clearAll() {
        input.setLength(0);
        display.setText("0");
    }

    private void deleteLastChar() {
        if (input.length() > 0) {
            input.deleteCharAt(input.length() - 1);
            display.setText(input.toString());
        }
    }

    private void addDecimal() {
        if (input.length() == 0 || endsWithOperator()) {
            input.append("0.");
        } else if (!input.toString().contains(".")) {
            input.append(".");
        }
        display.setText(input.toString());
    }

    private boolean endsWithOperator() {
        if (input.length() == 0) return false;
        char lastChar = input.charAt(input.length() - 1);
        return lastChar == '+' || lastChar == '-' || lastChar == '*' || lastChar == '/';
    }
}
