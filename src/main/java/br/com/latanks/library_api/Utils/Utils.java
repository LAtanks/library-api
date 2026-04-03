package br.com.latanks.library_api.Utils;

public class Utils {
    public static boolean hasAnyNumber(String str){
        for (char c : str.toCharArray()) {
            if (Character.isDigit(c)) {
                return true;
            }
        }
        return false;
    }
}
