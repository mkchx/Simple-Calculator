package com.mkchx.simplecalculator;

import android.support.v7.app.AppCompatActivity;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class BaseCalculator extends AppCompatActivity {

    protected String newInput = "", savedOutput = "";
    protected String[] defaultOperations = {"+", "-", "×", "÷"};
    protected boolean replaceFirst = false;

    protected String performOperation() {

        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        double sum = 0;
        int c = 0;
        String[] numbs = savedOutput.split("[-+×÷]");
        String[] mathOperations = stripEmptyIndex(savedOutput.replaceAll("[0-9]", "").split(""));

        for (int i = 0; i < numbs.length - 1; i++) {

            double p = (sum == 0 ? Double.parseDouble(numbs[i]) : sum);

            switch (mathOperations[c]) {
                case "+":
                    sum = p + Double.parseDouble(numbs[i + 1]);
                    break;
                case "-":
                    sum = p - Double.parseDouble(numbs[i + 1]);
                    break;
                case "×":
                    sum = p * Double.parseDouble(numbs[i + 1]);
                    break;
                case "÷":
                    sum = p / Double.parseDouble(numbs[i + 1]);
                    break;
            }
            c++;
        }

        return String.valueOf((sum == 0 ? "" : decimalFormat.format(sum)));
    }

    protected String[] stripEmptyIndex(String[] arr) {

        List<String> newArr = new ArrayList<>();
        for (String item : arr) {
            if (item != null && !item.isEmpty() && !item.equals(" ") && !item.equals(".")) {
                newArr.add(item);
            }
        }
        return newArr.toArray(new String[newArr.size()]);
    }

    protected void removeLastChar() {

        int lastIndex = savedOutput.length() - 1;

        if (lastIndex <= 0) {
            savedOutput = " ";
            return;
        }

        savedOutput = savedOutput.substring(0, lastIndex);
    }

    protected boolean isDot(String input) {

        if (input.equals(getString(R.string.dot))) {
            return true;
        }

        return false;
    }

    protected boolean containsDotFromLastOperator(String[] arr) {

        for (int i = arr.length - 1; i >= 0; i--) {

            if (isDot(arr[i])) {
                return true;
            }

            if (isOperation(arr[i])) {
                return false;
            }

        }

        return false;
    }

    protected boolean isOperation(String input) {

        for (String o : defaultOperations) {

            if (input.contains(o)) {
                return true;
            }
        }
        return false;
    }

    protected boolean isNumeric(String value) {
        if (value.matches("\\d+(?:\\.\\d+)?")) {
            return true;
        }
        return false;
    }

}
