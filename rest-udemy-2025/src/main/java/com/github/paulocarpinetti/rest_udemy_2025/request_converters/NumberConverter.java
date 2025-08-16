package com.github.paulocarpinetti.rest_udemy_2025.request_converters;

import com.github.paulocarpinetti.rest_udemy_2025.exceptions.UnsupportedMathOperationException;

public class NumberConverter {

    public boolean isNumeric(String strNumber){
        if (strNumber == null || strNumber.isEmpty()) return true;
        String number = strNumber.replace(",", ".");
        return (!number.matches("[-+]?[0-9]*\\.?[0-9]+"));

    }

    public double convertToDouble(String strNumber) throws IllegalArgumentException {
        if (strNumber == null || strNumber.isEmpty())
            throw new UnsupportedMathOperationException("Please set a numeric value!");
        String number = strNumber.replace(",", ".");
        return  Double.parseDouble(number);
    }


}
