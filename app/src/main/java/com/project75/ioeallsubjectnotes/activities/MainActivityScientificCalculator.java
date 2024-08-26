package com.project75.ioeallsubjectnotes.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.project75.ioeallsubjectnotes.R;

public class MainActivityScientificCalculator extends AppCompatActivity {
    private EditText display;
    Button b1, b2, b3, b4, b5, b6, b7, b8, b9, b0, bdot, bpi, bequal, bplus, bmin, bmul, bdiv, binv, bsqrt, bsquare, bfact, bln, blog, btan, bcos, bsin, bb1, bb2, bc, bac;
    TextView tvmain, tvsec;
    String pi = "3.14159265";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_scientific_calculator);

        b1 = findViewById(R.id.b1);
        b2 = findViewById(R.id.b2);
        b3 = findViewById(R.id.b3);
        b4 = findViewById(R.id.b4);
        b5 = findViewById(R.id.b5);
        b6 = findViewById(R.id.b6);
        b7 = findViewById(R.id.b7);
        b8 = findViewById(R.id.b8);
        b9 = findViewById(R.id.b9);
        b0 = findViewById(R.id.b0);
        bpi = findViewById(R.id.bpi);
        bdot = findViewById(R.id.bdot);
        bequal = findViewById(R.id.bequal);
        bplus = findViewById(R.id.bplus);
        bmin = findViewById(R.id.bmin);
        bmul = findViewById(R.id.bmul);
        bdiv = findViewById(R.id.bdiv);
        binv = findViewById(R.id.binv);
        bsqrt = findViewById(R.id.bsqrt);
        bsquare = findViewById(R.id.bsquare);
        bfact = findViewById(R.id.bfact);
        bln = findViewById(R.id.bln);
        blog = findViewById(R.id.blog);
        btan = findViewById(R.id.btan);
        bsin = findViewById(R.id.bsin);
        bcos = findViewById(R.id.bcos);
        bb1 = findViewById(R.id.bb1);
        bb2 = findViewById(R.id.bb2);
        bc = findViewById(R.id.bc);
        bac = findViewById(R.id.bac);

        tvmain = findViewById(R.id.tvmain);
        tvsec = findViewById(R.id.tvsec);

        // Set up digit buttons
        setDigitButtonListener(b1, "1");
        setDigitButtonListener(b2, "2");
        setDigitButtonListener(b3, "3");
        setDigitButtonListener(b4, "4");
        setDigitButtonListener(b5, "5");
        setDigitButtonListener(b6, "6");
        setDigitButtonListener(b7, "7");
        setDigitButtonListener(b8, "8");
        setDigitButtonListener(b9, "9");
        setDigitButtonListener(b0, "0");

        bdot.setOnClickListener(v -> appendToDisplay("."));
        bac.setOnClickListener(v -> clearDisplay());
        bc.setOnClickListener(v -> deleteLastCharacter());
        bplus.setOnClickListener(v -> appendToDisplay("+"));
        bmin.setOnClickListener(v -> appendToDisplay("-"));
        bmul.setOnClickListener(v -> appendToDisplay("×"));
        bdiv.setOnClickListener(v -> appendToDisplay("÷"));
        bsqrt.setOnClickListener(v -> applySquareRoot());
        bb1.setOnClickListener(v -> appendToDisplay("("));
        bb2.setOnClickListener(v -> appendToDisplay(")"));
        bpi.setOnClickListener(v -> appendToDisplay(pi));
        bsin.setOnClickListener(v -> appendToDisplay("sin"));
        bcos.setOnClickListener(v -> appendToDisplay("cos"));
        btan.setOnClickListener(v -> appendToDisplay("tan"));
        binv.setOnClickListener(v -> appendToDisplay("^(-1)"));
        bfact.setOnClickListener(v -> applyFactorial());
        bsquare.setOnClickListener(v -> applySquare());
        bln.setOnClickListener(v -> appendToDisplay("ln"));
        blog.setOnClickListener(v -> appendToDisplay("log"));
        bequal.setOnClickListener(v -> calculateResult());
    }

    // Helper methods
    private void setDigitButtonListener(Button button, String digit) {
        button.setOnClickListener(v -> appendToDisplay(digit));
    }

    private void appendToDisplay(String text) {
        tvmain.append(text);
    }

    private void clearDisplay() {
        tvmain.setText("");
        tvsec.setText("");
    }

    private void deleteLastCharacter() {
        String val = tvmain.getText().toString();
        if (!val.isEmpty()) {
            tvmain.setText(val.substring(0, val.length() - 1));
        }
    }

    private void applySquareRoot() {
        try {
            String val = tvmain.getText().toString();
            double r = Math.sqrt(Double.parseDouble(val));
            tvmain.setText(String.valueOf(r));
            tvsec.setText("√" + val);
        } catch (NumberFormatException e) {
            showError("Invalid input for square root.");
        }
    }

    private void applyFactorial() {
        try {
            int val = Integer.parseInt(tvmain.getText().toString());
            int fact = factorial(val);
            tvmain.setText(String.valueOf(fact));
            tvsec.setText(val + "!");
        } catch (NumberFormatException e) {
            showError("Invalid input for factorial.");
        } catch (StackOverflowError e) {
            showError("Number too large for factorial.");
        }
    }

    private void applySquare() {
        try {
            double d = Double.parseDouble(tvmain.getText().toString());
            double square = d * d;
            tvmain.setText(String.valueOf(square));
            tvsec.setText(d + "²");
        } catch (NumberFormatException e) {
            showError("Invalid input for square.");
        }
    }

    private void calculateResult() {
        try {
            String val = tvmain.getText().toString();
            String replacedstr = val.replace('÷', '/').replace('×', '*');
            double result = eval(replacedstr);
            tvmain.setText(String.valueOf(result));
            tvsec.setText(val);
        } catch (Exception e) {
            showError("Error in expression.");
        }
    }

    private void showError(String message) {
        tvmain.setText(message);
        tvsec.setText("");
    }

    private int factorial(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Negative number for factorial.");
        }
        return (n == 1 || n == 0) ? 1 : n * factorial(n - 1);
    }

    //eval function
    public static double eval(final String str) {
        return new Object() {
            int pos = -1, ch;

            void nextChar() {
                ch = (++pos < str.length()) ? str.charAt(pos) : -1;
            }

            boolean eat(int charToEat) {
                while (ch == ' ') nextChar();
                if (ch == charToEat) {
                    nextChar();
                    return true;
                }
                return false;
            }

            double parse() {
                nextChar();
                double x = parseExpression();
                if (pos < str.length()) throw new RuntimeException("Unexpected: " + (char)ch);
                return x;
            }

            // Grammar:
            // expression = term | expression + term | expression - term
            // term = factor | term * factor | term / factor
            // factor = + factor | - factor | ( expression )
            //        | number | functionName factor | factor ^ factor

            double parseExpression() {
                double x = parseTerm();
                for (;;) {
                    if      (eat('+')) x += parseTerm(); // addition
                    else if (eat('-')) x -= parseTerm(); // subtraction
                    else return x;
                }
            }

            double parseTerm() {
                double x = parseFactor();
                for (;;) {
                    if      (eat('*')) x *= parseFactor(); // multiplication
                    else if (eat('/')) x /= parseFactor(); // division
                    else return x;
                }
            }

            double parseFactor() {
                if (eat('+')) return parseFactor(); // unary plus
                if (eat('-')) return -parseFactor(); // unary minus

                double x;
                int startPos = this.pos;
                if (eat('(')) { // parentheses
                    x = parseExpression();
                    eat(')');
                } else if ((ch >= '0' && ch <= '9') || ch == '.') { // numbers
                    while ((ch >= '0' && ch <= '9') || ch == '.') nextChar();
                    x = Double.parseDouble(str.substring(startPos, this.pos));
                } else if (ch >= 'a' && ch <= 'z') { // functions
                    while (ch >= 'a' && ch <= 'z') nextChar();
                    String func = str.substring(startPos, this.pos);
                    x = parseFactor();
                    if (func.equals("sqrt")) x = Math.sqrt(x);
                    else if (func.equals("sin")) x = Math.sin(Math.toRadians(x));
                    else if (func.equals("cos")) x = Math.cos(Math.toRadians(x));
                    else if (func.equals("tan")) x = Math.tan(Math.toRadians(x));
                    else if (func.equals("log")) x = Math.log10(x);
                    else if (func.equals("ln")) x = Math.log(x);
                    else throw new RuntimeException("Unknown function: " + func);
                } else {
                    throw new RuntimeException("Unexpected: " + (char)ch);
                }

                if (eat('^')) x = Math.pow(x, parseFactor()); // exponentiation

                return x;
            }
        }.parse();
    }
}

