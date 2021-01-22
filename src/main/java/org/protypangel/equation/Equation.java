package org.protypangel.equation;

import org.protypangel.equation.math.*;

import java.util.ArrayList;
import java.util.List;

public class Equation {
    public static Function cos         = x -> Math.cos(x),
                        sin            = x -> Math.sin(x),
                        tan            = x -> Math.tan(x),
                        exp            = x -> Math.pow(Math.E, x),
                        ln             = x -> Math.log(x);
    public static Function2Args pow    = (a, b) -> Math.pow(a, b),
                        racine         = (a, b) -> Math.pow(a, 1 / b),
                        log            = (a, b) -> Math.log(a) / Math.log(b),
                        around = (a, b) -> {
                            long pow = (long) Math.pow(10, b);
                            for (long power = pow * 10, index = 1;; power*=10, index++) {
                                long value = (long) (a * power);
                                if(value % 10 < 5) {
                                    long v = value / (long) Math.pow(10, index);
                                    return (double) v / pow;
                                }
                                else if (value % 10 > 5) {
                                    long v = value / (long) Math.pow(10, index) + 1;
                                    return (double) v / pow;
                                }
                            }
                        };
    public static Function getFunction (String value) {
        String [] functions = value.split("\\+|-");
        List<Function> arguments = new ArrayList<>();
        for (String function : functions) {
            function = signe(value, function);
            arguments.add(v(function));
        }
        return x -> {
            double v = 0;
            for (Function argument : arguments) {
                double v1 = argument.function(x);
                v += v1;
            }
            return v;
        };
    }

    private static String signe(String value, String function) {
        String []s1   = value.split(function);
        if (s1.length == 0) return function;
        String s = s1[s1.length - 1];
        if (s.length() == 0) return function;
        char signe = s.charAt(s.length() - 1);
        return signe == '-' ? '-' + function : function;
    }

    private static Function v (String value) {
        if (value.contains("x^")) {
            String []s = value.split("x\\^");
            return x -> {
                if (s.length == 0 || s[0].equals("")) return pow.function(x, Double.parseDouble(s[1]));
                return Double.parseDouble(s[0]) * pow.function(x, Double.parseDouble(s[1]));
            };
        }
        if (value.contains("x")) {
            return x -> {
                String []s = value.split("x");
                if (s.length == 0 || s[0].equals("")) return x;
                return x * Double.parseDouble(s[0]);
            };
        }
        return x -> Double.parseDouble(value);
    }
    public static double sum (int start, int end, int dash, Function function) {
        return sum(start, end, dash, function, true);
    }
    public static double sum (int start, int end, int dash, Function function, boolean withEnd) {
        double value = 0;
        if (withEnd) {
            for (int index = start; index <= end; index+=dash)
                value += function.function(index);
        } else {
            for (int index = start; index < end; index+=dash)
                value += function.function(index);
        }
        return value;
    }
}
