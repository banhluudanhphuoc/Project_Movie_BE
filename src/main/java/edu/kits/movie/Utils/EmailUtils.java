package edu.kits.movie.Utils;

public class EmailUtils {
    public static boolean isEmail(String name){
        return name.matches("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$");
    }
}
