import java.lang.NumberFormatException;

public class Helper {
    public static Integer strToInt (String str) {
        Integer result = null;
        try {
            result = Integer.valueOf(str);
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
            System.out.println("String converting to Integer failed. Please check!");
        }
        return result;
    }
}
