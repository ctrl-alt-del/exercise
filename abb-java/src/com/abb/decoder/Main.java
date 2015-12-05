package com.abb.decoder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    /**
     * Given a string, find all its possible variants that includes at least one capitalized letter
     *
     * Ex: given "abc", its completed list of variants are "Abc", "ABc", "ABC", "AbC", "aBc", "aBC", "abC
     */
    public static void main(String[] args) {
        String str = "abc";
        for (String result : unveilCode(str.toCharArray(), 0)) {
            System.out.println(result);
        }
    }

    public static List<String> unveilCode(char[] chars, int n) {
        int l = chars.length;
        char lastChar = chars[l - 1];
        if (Character.toUpperCase(lastChar) == lastChar) {
            return Collections.emptyList();
        }

        char tempChar;
        List<String> results = new ArrayList<>();
        for (int i = n; i < l; i++) {
            tempChar = chars[i];

            chars[i] = Character.toUpperCase(chars[i]);
            results.add(new String(chars));
            results.addAll(unveilCode(chars, i + 1));

            chars[i] = tempChar;
        }
        return  results;
    }
}
