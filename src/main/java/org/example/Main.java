package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        Pattern p = Pattern.compile("\\d+\\.\\d+");
        Matcher m = p.matcher("string12.34more56.7string890");

        int[] a = {6,8};
        if(m.find()) {
            double v = Integer.parseInt(m.group());
            System.out.println(v);
        }

    }
}