package kirby;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import kirby.tasks.Task;

/**
 * The kirby.HandleTime class contains methods to deal with time and dates.
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
        if (parts.length != 3) {
            return new int[]{-1, -1, -1};
        }
        int date = Integer.parseInt(parts[2]);
        int month = Integer.parseInt(parts[1]);
        int year = Integer.parseInt(parts[0]);
        return new int[]{date, month, year};
    }

    public static ArrayList<Task> getTaskByDate(ArrayList<Task> arr, String input) {
        ArrayList<Task> result = new ArrayList<>();
        int[] inputArray = fromStringToDate(input);
        for (Task task : arr) {
            if (Arrays.equals(inputArray, task.getDate())) {
                result.add(task);
            }
        }
        return result;
    }
}
