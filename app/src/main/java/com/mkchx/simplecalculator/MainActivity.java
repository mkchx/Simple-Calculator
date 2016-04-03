package com.mkchx.simplecalculator;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends BaseCalculator {

    private TextView uiOutputText, uiOutputPerformedText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        uiOutputText = (TextView) findViewById(R.id.output_text);
        uiOutputText.setTypeface(Typeface.create("sans-serif-light", Typeface.NORMAL));

        uiOutputPerformedText = (TextView) findViewById(R.id.output_result);
        uiOutputPerformedText.setTypeface(Typeface.create("sans-serif-light", Typeface.NORMAL));
    }

    public void onButtonClick(View view) {

        savedOutput = String.valueOf(uiOutputText.getText());
        newInput = String.valueOf(((TextView) view).getText());

        if (replaceFirst) {
            replaceFirst = false;
            savedOutput = "";
        }

        boolean inputIsOperation = isOperation(newInput);
        int lastIndex = (savedOutput.equals(" ") ? -1 : savedOutput.length() - 1);

        if (inputIsOperation) {

            if (lastIndex == -1) {
                return;
            } else if (isOperation(savedOutput.substring(lastIndex))) {
                savedOutput = savedOutput.substring(0, lastIndex) + newInput;
            } else {
                savedOutput += newInput;
            }

        } else if (newInput.equals(getString(R.string.backspace))) {

            removeLastChar();

            uiOutputPerformedText.setText(performOperation());

        } else if (isNumeric(newInput)) {

            savedOutput += newInput;

            uiOutputPerformedText.setText(performOperation());

        } else if (newInput.equals(getString(R.string.clear))) {

            replaceFirst = false;

            savedOutput = " ";
            uiOutputPerformedText.setText(savedOutput);

        } else if (newInput.equals(getString(R.string.result))) {

            uiOutputPerformedText.setText(" ");
            savedOutput = performOperation();

            if (savedOutput.equals("")) {
                savedOutput = "0";
                replaceFirst = true;
            }

        } else {

            boolean isItDot = isDot(newInput);

            if (lastIndex == -1 && isItDot ||
                    lastIndex != -1 && isItDot && containsDotFromLastOperator(savedOutput.split(""))) {
                return;
            }

            savedOutput += newInput;
        }

        runOnUiThread(new Runnable() {

            @Override
            public void run() {

                uiOutputText.setText(savedOutput);
            }
        });


    }

}
