package com.example.analyzer.utilities;

public class Constants {


    public static String[] identifiers = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "ñ", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};

    public static String[] numeric_constants = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
    public static String[] operators = {"+", "-", "*", "/", "%", "++", "--"};
    public static String[] assignment_symbol = {"="};
    public static String[] parenthesis = {"(", ")"};
    public static String[] relations = {"<", ">","<=",">=","==","!="};
    public static String[] sentence_separator = {";"};
    public static String[] block_indicators = {"{", "}"};
    public static String[] reserved_words = {"R", "W", "M"};

    public static String[] error = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "ñ", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z",
            "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "+", "-", "*", "/", "%", "+", "-", "*", "/", "%", "=", "(", ")", ";", "{", "}", "R", "W", "M"};
    // Patterns
    public static String identifiersP = "<LETRA>(<LETRA>|<DIGITO>)*";
    public static String digitP = "<digito>+";
    public static String operatorsP = "+,-,*,/,++,--,%";
    public static String assignmentP = "=";
    public static String groupersP = "(,),{,},[,]";
    public static String separatorP = ";";
    public static String relationsP = ">,<,>=,<=,==,!=";
    public static String trueFalse = "TRUE,FALSE";

}
