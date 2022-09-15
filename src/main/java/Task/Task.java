package Task;

import java.util.Arrays;

/**
 * Represents the information of the Tasks that the user wants to store.
 * Acts as the abstract class that ToDos, Deadlines, and Events extend from.
 */
    public abstract class Task {

    protected static String PRINT_TIME_FORMAT = "EE, dd MMM yyyy, HH:mm";
    protected static String DEFAULT_TIME_FORMAT = "yyyy-MM-dd HHmm";

    private final String description;
    private boolean isDone = false;
    private final String shorthand;

    Task(String description, String shorthand) {
        this.description = description;
        this.shorthand = shorthand;
    }

    /**
     * Marks the Task as complete.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the Task as incomplete.
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    /**
     * Returns a String that represents the Task, and can be interpreted by
     * the Storage class.
     *
     * @return The String that represents the Task in Storage text.
     */
    public String getStorageString() {
        return String.format("%s|%s|%s",
                getShorthand(),
                getStatusIcon(),
                getDescription());
    }

    /**
     * Returns a String that represents the Task that should be displayed to
     * the user while using the application.
     *
     * @return The String that represents the Task to be printed.
     */
    public boolean hasKeyword(String keyword) {
        String[] words = getUniqueKeywords();
        return Arrays.asList(words).contains(keyword);
    }

    @Override
    public String toString() {
        return String.format("[%s][%s] %s",
                getShorthand(),
                getStatusIcon(),
                getDescription());
    }

    private String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    private String getDescription() {
        return this.description;
    }

    private String getShorthand() {
        return this.shorthand;
    }

    private String[] getUniqueKeywords() {
        return Arrays.stream(description.split(" ")).distinct().toArray(String[]::new);
    }
}