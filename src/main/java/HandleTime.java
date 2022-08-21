import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
/**
 * The HandleTime class contains methods to deal with time and dates.
 * @author Sheryl-Lynn Tan (G11)
 */
public class HandleTime {
    public static boolean isValidDate(String inDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(inDate.trim());
        } catch (ParseException pe) {
            return false;
        }
        return true;
    }

    public static int[] fromStringToDate(String input) {
        String[] parts = input.split("-");
        int date = Integer.parseInt(parts[2]);
        int month = Integer.parseInt(parts[1]);
        int year = Integer.parseInt(parts[0]);
        return new int[]{date, month, year};
    }

    public static ArrayList<Task> getDate(ArrayList<Task> arr, String input) {
        int[] toCompare = fromStringToDate(input);
        ArrayList<Task> result = new ArrayList<>();

        for (Task task : arr) {
            if (Arrays.equals(toCompare, task.getDate())) {
                result.add(task);
            }
        }
        return result;
    }
}
