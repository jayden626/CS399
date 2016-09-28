package com.urch.dance.calculator;

import android.app.Application;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.HapticFeedbackConstants;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.fathzer.soft.javaluator.DoubleEvaluator;
//import com.urch.dance.calculator.JCalc.JCalcList;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    public final static String EXTRA_MESSAGE = "com.urch.dance.MESSAGE";
    private String expression;
    //private JCalcList calcList;
    DoubleEvaluator calc;
    String result;
    boolean evaluated;
    boolean lastIsOp;
    boolean decimalExists;
    Toast toast;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // calcList = new JCalcList();
        calc = new DoubleEvaluator();
        expression = "";
        result = "";
        evaluated = false;
        lastIsOp = false;
        decimalExists = false;
    }

    /** Called when the user clicks the Send button */
    public void sendMessage(View view) {
        view.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);

        String tag = view.getTag().toString();
        TextView exprText = (TextView) findViewById(R.id.expression);
        TextView resultText = (TextView) findViewById(R.id.result);

        lastIsOp = false;
        boolean tagIsOp = false;

        if(tag.compareTo("+") == 0 || tag.compareTo("x") == 0 || tag.compareTo("÷") == 0 || tag.compareTo("-") == 0){
                tagIsOp = true;
        }

        //Checking if the last entered value is an operator
        if(expression.endsWith("+") || expression.endsWith("x") || expression.endsWith("÷") || expression.endsWith("-")){
            lastIsOp = true;
            decimalExists = false;
        } else if(expression.endsWith(".")){
            decimalExists = true;
        }

        //if last char == operator and tag is an operator, remove last char so the new operation can be added
        if((tag.compareTo("+") == 0 || tag.compareTo("x") == 0 || tag.compareTo("÷") == 0 || tag.compareTo("-") == 0) && lastIsOp){
            expression = expression.substring(0, expression.length()-1);
        }

        try {
            if (tag.compareTo("C") == 0) {
                expression = "";
                result = "";
                //resultText.setText(result);
            } else if (tag.compareTo("D") == 0) {
                if (expression != null && expression.length() > 0) {
                    expression = expression.substring(0, expression.length() - 1);
                    result = "";
                    evaluated = false;
                    //resultText.setText(result);
                }
            } else if(evaluated && tagIsOp) {
                expression = result;
                result = "";
                expression += tag;
                evaluated = false;
            } else if(expression.length() >= 100) {
                if(toast != null){
                    toast.cancel();
                }
                toast = Toast.makeText(getApplicationContext(), getString(R.string.max), Toast.LENGTH_SHORT);
                toast.show();

            } else if (expression.length() == 0 && tag.compareTo("-") != 0 && (tagIsOp || tag.compareTo("=") == 0)){
                //Do nothing as these operators are illegal at the start of the expression
            } else if (tag.compareTo("=") == 0 && expression.length() > 0) {
                evaluated = true;

                //If the last input was an operator, remove it.
                if(expression.endsWith("+") || expression.endsWith("x") || expression.endsWith("÷") || (expression.endsWith("-"))) {
                    expression = expression.substring(0, expression.length() - 1);
                }

                String convertedExpr = expression;
                convertedExpr = convertedExpr.replace("÷","/");
                convertedExpr = convertedExpr.replace("x","*");
                result = String.format(Locale.getDefault(), "%1$,.2f", calc.evaluate(convertedExpr));
                //resultText.setText(result);
            } else {
                if(evaluated){
                    evaluated = false;
                    expression = "";
                    result = "";
                    decimalExists = false;
                    //resultText.setText(result);
                }
                if(tag.compareTo(".") == 0){
                    if(lastIsOp || expression.length() == 0) {
                        tag = "0.";
                    }
                    else if(decimalExists){
                        tag = "";
                    }
                    decimalExists = true;
                }
                expression += tag;
            }
            exprText.setText(expression);
            resultText.setText(result);
        }
        catch(Exception e){
            resultText.setText(getString(R.string.error));
        }
        /*switch(tag){
            case "0":
                calcList.add0();
                break;
            case "1":
                calcList.add1();
                break;
            case "2":
                calcList.add2();
                break;
            case "3":
                calcList.add3();
                break;
            case "4":
                calcList.add4();
                break;
            case "5":
                calcList.add5();
                break;
            case "6":
                calcList.add6();
                break;
            case "7":
                calcList.add7();
                break;
            case "8":
                calcList.add8();
                break;
            case "9":
                calcList.add9();
                break;
            case "+":
                calcList.addPlus();
                break;
            case "-":
                calcList.addMinus();
                break;
            case "x":
                calcList.addMultiply();
                break;
            case "÷":
                calcList.addDivide();
                break;
            case "=":
                Double result = calcList.calculate();
                text.setText(String.format(Locale.getDefault(), "%1$,.2f", result));
                break;
            case ".":
                calcList.addDot();
                break;
            case "D":
                calcList.del();
                break;
            case "C":
                calcList.clear();
                break;
        }*/

       // if(tag.compareTo("=") != 0){
                //text.setText(calcList.printList());
        //}
       /* Intent intent = new Intent(this, DisplayMessageActivity.class);
        EditText editText = (EditText) findViewById(R.id.edit_message);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);*/
        // Do something in response to button
    }
}
