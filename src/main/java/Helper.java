import java.lang.NumberFormatException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Arrays;

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

    public static boolean multipleVariable (String str) {
        String[] commands = {"mark", "unmark", "todo", "deadline", "event", "delete"};
        HashSet<String> checkList = new HashSet<String>(Arrays.asList(commands));
        return checkList.contains(str);
    }
}
