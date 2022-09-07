package kirby.time;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import kirby.tasks.Task;

/**
 * HandleTime class contains methods to handle inputs with standard dates.
 */
public class HandleTime {
    private static final int[] INVALID_DATE_ARR = new int[]{-1, -1, -1};
    private static final int VALID_DATE_LENGTH = 3;

    /**
     * Checks if the input given is a standard form of a date.
     *
     * @param inDate Input to be checked if it is a date.
     * @return Boolean true if it is a date or false if it is not in standard form of a date.
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
     * @param input String input from the user.
     * @return Array of the date in the form of [dd, mm, yyyy].
     */
    public static int[] fromStringToDate(String input) {
        assert input != null;
        String[] parts = input.split("-");
        if (parts.length != VALID_DATE_LENGTH) {
            return INVALID_DATE_ARR;
        }
        int date = Integer.parseInt(parts[2]);
        int month = Integer.parseInt(parts[1]);
        int year = Integer.parseInt(parts[0]);
        return new int[]{date, month, year};
    }

    /**
     * Picks out all the tasks with a specified date from a given list of tasks.
     *
     * @param inputTasks List of tasks.
     * @param input Date that the chosen tasks should correspond to.
     * @return List of tasks with the specified date.
     */
    public static ArrayList<Task> getTaskByDate(ArrayList<Task> inputTasks, String input) {
        assert inputTasks != null && input != null;
        ArrayList<Task> datedTaskList = new ArrayList<>();
        int[] inputArray = fromStringToDate(input);
        for (Task task : inputTasks) {
            if (Arrays.equals(inputArray, task.getDate())) {
                datedTaskList.add(task);
            }
        }
        return datedTaskList;
    }

    /**
     * Sorts the list of tasks by chronological order.
     * Tasks with no valid dates are placed above.
     *
     * @param inputTasks List of tasks.
     * @return List of tasks sorted in a chronological order.
     */
    public static ArrayList<Task> sortTaskList(ArrayList<Task> inputTasks) {
        Collections.sort(inputTasks, new SortTaskByDate());
        return inputTasks;
    }
}
