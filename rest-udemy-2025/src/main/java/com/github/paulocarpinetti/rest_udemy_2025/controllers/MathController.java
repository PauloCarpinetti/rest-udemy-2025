package com.github.paulocarpinetti.rest_udemy_2025.controllers;

import com.github.paulocarpinetti.rest_udemy_2025.exceptions.UnsupportedMathOperationException;
import com.github.paulocarpinetti.rest_udemy_2025.math.SimpleMath;
import com.github.paulocarpinetti.rest_udemy_2025.request_converters.NumberConverter;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/math")
public class MathController {

    private final SimpleMath math = new SimpleMath();
    private final NumberConverter numConv = new NumberConverter();

    // http://localhost:8080/math/sum/3/5
    @RequestMapping("/sum/{numberOne}/{numberTwo}")
    public double sum(
            @PathVariable("numberOne") String numberOne,
            @PathVariable("numberTwo") String numberTwo
    ) throws Exception {
        if (numConv.isNumeric(numberOne) || numConv.isNumeric(numberTwo))
            throw new UnsupportedMathOperationException("Please set a numeric value!");
        return math.sum(numConv.convertToDouble(numberOne),
                numConv.convertToDouble(numberTwo));
    }

    @RequestMapping("/sub/{numberOne}/{numberTwo}")
    public double sub(
            @PathVariable("numberOne") String numberOne,
            @PathVariable("numberTwo") String numberTwo
    ) throws Exception {
        if (numConv.isNumeric(numberOne) || numConv.isNumeric(numberTwo))
            throw new UnsupportedMathOperationException("Please set a numeric value!");
        return math.sub(numConv.convertToDouble(numberOne),
                numConv.convertToDouble(numberTwo));
    }

    @RequestMapping("/mult/{numberOne}/{numberTwo}")
    public double mult(
            @PathVariable("numberOne") String numberOne,
            @PathVariable("numberTwo") String numberTwo
    ) throws Exception {
        if (numConv.isNumeric(numberOne) || numConv.isNumeric(numberTwo))
            throw new UnsupportedMathOperationException("Please set a numeric value!");
        return math.mult(numConv.convertToDouble(numberOne),
                numConv.convertToDouble(numberTwo));
    }

    @RequestMapping("/div/{numberOne}/{numberTwo}")
    public double div(
            @PathVariable("numberOne") String numberOne,
            @PathVariable("numberTwo") String numberTwo
    ) throws Exception {
        if (numConv.isNumeric(numberOne) || numConv.isNumeric(numberTwo))
            throw new UnsupportedMathOperationException("Please set a numeric value!");
        return math.div(numConv.convertToDouble(numberOne),
                numConv.convertToDouble(numberTwo));
    }

    @RequestMapping("/mean/{numberOne}/{numberTwo}")
    public double mean(
            @PathVariable("numberOne") String numberOne,
            @PathVariable("numberTwo") String numberTwo
    ) throws Exception {
        if (numConv.isNumeric(numberOne) || numConv.isNumeric(numberTwo))
            throw new UnsupportedMathOperationException("Please set a numeric value!");
        return math.mean(numConv.convertToDouble(numberOne),
                numConv.convertToDouble(numberTwo));
    }

    @RequestMapping("/root/{number}")
    public double root(
            @PathVariable("number") String number) throws Exception {
        if (numConv.isNumeric(number))
            throw new UnsupportedMathOperationException("Please set a numeric value!");
        return math.root(numConv.convertToDouble(number));
    }

}
