package duke.task;

/**
 * A class to check if the task contains a specified string.
 */
public class Checker {

    /**
     * Returns the boolean value of whether there is a matched task.
     *
     * @param t the task to be checked.
     * @param s the specified string to be checked.
     * @return boolean if the task contains the given string.
     */
    public static boolean contains(Task t, String s) {
        return t.description.contains(s);
    }
}
