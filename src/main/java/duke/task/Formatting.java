package duke.task;

import java.util.ArrayList;
import java.util.function.Function;

/**
 * A class to handle the conversion of tasks in array list to string
 * to be placed in the text file.
 */
public class Formatting implements Function<ArrayList<Task>, String> {

    /**
     * Returns a representation of tasks in the array list.
     *
     * @param arr the array list containing the tasks
     * @return string of tasks
     */
    public String apply(ArrayList<Task> arr) {
        String s = "";
        for (int i = 0; i < arr.size(); i++) {
            if (arr.get(i) instanceof ToDo) {
                s += "T | " + checkMark(arr.get(i)) + " | " + arr.get(i).description
                    + "\n";
            } else if (arr.get(i) instanceof Event) {
                s += "E | " + checkMark(arr.get(i)) + " | " + arr.get(i).description
                        + " | " + ((Event) arr.get(i)).date + "\n";
            } else {
                s += "D | " + checkMark(arr.get(i)) + " | " + arr.get(i).description
                        + " | " + ((Deadline) arr.get(i)).date + "\n";
            }
        }
        return s;
    }

    private String checkMark(Task t) {
        return t.isDone ? "1" : "0";
    }
}
