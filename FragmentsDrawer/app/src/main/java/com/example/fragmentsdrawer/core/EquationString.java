package com.example.fragmentsdrawer.core;

class EquationString {

    public static String prepareFunctionTo(String inputString) {
        return inputString
                .replaceAll("¬", "~")
                .replaceAll("∧", "&")
                .replaceAll("∨", "|")
                .replaceAll("⇒", "=>")
                .replaceAll("≡", "<=>")
                .replaceAll("/0", "")
                .replaceAll("True", "1")
                .replaceAll("False", "0");
    }

    public static String prepareFunctionFrom(String inputString) {
        return inputString
                .replaceAll("~", "¬")
                .replaceAll("&", "∧")
                .replaceAll("[|]", "∨")
                .replaceAll("<=>", "≡")
                .replaceAll("=>", "⇒")
                .replaceAll("[$]true", "True")
                .replaceAll("[$]false", "False")
                .replaceAll(" ", "");

    }

}
