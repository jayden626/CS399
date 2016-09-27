package com.urch.dance.calculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

//import com.urch.dance.javaluator.DoubleEvaluator;
import com.urch.dance.calculator.JCalc.JCalcList;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    public final static String EXTRA_MESSAGE = "com.urch.dance.MESSAGE";
    private String expression = "";
    private JCalcList calcList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        calcList = new JCalcList();
    }

    /** Called when the user clicks the Send button */
    public void sendMessage(View view) {
        String tag = view.getTag().toString();
        TextView text = (TextView) findViewById(R.id.expression);

        switch(tag){
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
        }

       // if(tag.compareTo("=") != 0){
                text.setText(calcList.printList());
        //}
       /* Intent intent = new Intent(this, DisplayMessageActivity.class);
        EditText editText = (EditText) findViewById(R.id.edit_message);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);*/
        // Do something in response to button
    }
}
