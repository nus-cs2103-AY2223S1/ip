package duke.task;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Task encapsulates a task containing a description and whether it is done.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Prints X as status icon if task has been completed.
     *
     * @return X if task has been done.
     */
    public String getStatusIcon() {
        return (this.isDone ? "X" : " "); // mark done bob task with X
    }

    /**
     * Marks task as completed.
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Unmarks the task as not completed.
     */
    public void markUndone() {
        this.isDone = false;
    }

    /**
     * Turns task into String format to be saved.
     *
     * @return String representation of task.
     */
    public abstract String saveStringFormat();

    /**
     * String representation of Task.
     *
     * @return The string representation of a task.
     */
    abstract public String toString();

    /**
     * Reconstructs a Task from a given String format
     *
     * @param taskString The string format representing the task.
     * @return The Task after conversion
     */
    public static Task loadStringToTask(String taskString) {
        //Note that | has a special meaning in regex, thus use //| (must take note of spacing as well
        String[] splitTaskString = taskString.split(" \\| ", 4);
        char type = splitTaskString[0].charAt(0);
        boolean isDone = splitTaskString[1].equals("1");
        String description = splitTaskString[2];
        LocalDate dateTime = splitTaskString.length == 4 ? LocalDate.parse(splitTaskString[3],
                DateTimeFormatter.ofPattern("MMM d yyyy")) : null;

        switch (type) {
        case 'D':
            return new Deadline(description, isDone, dateTime);
        case 'E':
            return new Event(description, isDone, dateTime);
        default:
            return new ToDo(description, isDone);
        }
    }

    /**
     * Checks if the task contains a certain word in its description.
     *
     * @param wordToFind The keyword to search for.
     * @return A boolean indicating if the word is found in the task description.
     */
    public boolean containsWord(String wordToFind) {
        return this.description.contains(wordToFind);
    }
}
