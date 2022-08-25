package duke.task;

import jdk.jshell.execution.LoaderDelegate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public abstract class Task {
    protected String description;
    protected boolean isDone;


    public String getStatusIcon() {
        return (this.isDone ? "X" : " "); // mark done bob task with X
    }

    public void markDone() {
        this.isDone = true;
    }

    public void markUndone() {
        this.isDone = false;
    }

    public abstract String saveStringFormat();

    abstract public String toString();
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
