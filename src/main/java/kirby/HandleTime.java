package kirby;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import kirby.tasks.Task;

/**
 * HandleTime class contains methods to handle inputs with standard dates.
 */
public class HandleTime {
    /**
     * Checks if the input given is a standard form of a date.
     *
     * @param inDate the input to be checked if it is a date.
     * @return true if it is a date or false if it is not in standard form of a date.
     */
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

    /**
     * Converts a date from its string form to an array.
     *
     * @param input the string input from the user.
     * @return an array of the date in the form of [dd, mm, yyyy].
     */
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

    /**
     * Picks out all the tasks with a specified date from a given list of tasks.
     *
     * @param arr a list of tasks.
     * @param input the date that the chosen tasks should correspond to.
     * @return a list of tasks with the specified date.
     */
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
