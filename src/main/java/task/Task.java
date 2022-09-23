package task;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Represents the information of the Tasks that the user wants to store.
 * Acts as the abstract class that ToDos, Deadlines, and Events extend from.
 */
public abstract class Task {

    protected static final String PRINT_TIME_FORMAT = "EE, dd MMM yyyy, HH:mm";
    protected static final String DEFAULT_TIME_FORMAT = "yyyy-MM-dd HHmm";

    private final String shorthand;
    private final String description;
    private boolean isDone = false;
    private List<String> tags;

    Task(String description, String shorthand) {
        this(description, shorthand, new ArrayList<>());
    }

    Task(String description, String shorthand, List<String> tags) {
        assert description != null: "Description for task cannot be null";
        this.description = description;
        this.shorthand = shorthand;
        assert tags != null: "List of tags cannot be null";
        this.tags = tags;
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
     * Returns a String that represents the Task that should be displayed to
     * the user while using the application.
     *
     * @return The String that represents the Task to be printed.
     */
    public boolean hasKeyword(String keyword) {
        assert keyword != null: "Keyword to search cannot be null";
        String[] words = getUniqueKeywords();
        return Arrays.asList(words).contains(keyword) || tags.contains(keyword);
    }

    /**
     * Adds a tag to the Task. Returns true if the tag was successfully deleted and
     * returns false otherwise.
     *
     * @param tag A tag that is provided by the User.
     * @return A boolean value that indicates if the tag was successfully added.
     */
    public boolean addTag(String tag) {
        assert tag != null: "Tags to be added cannot be null";
        if (tag.split(" ").length > 1) {
            return false;
        }
        if (tag.strip().equals("")) {
            return false;
        }
        return tags.add(tag);
    }

    /**
     * Deletes a tag from the Task. Returns true if the tag was successfully deleted and
     * returns false otherwise.
     *
     * @param tag A tag that is provided by the User.
     * @return A boolean value that indicates if the tag was successfully deleted.
     */
    public boolean deleteTag(String tag) {
        assert tag != null: "Tags to be deleted cannot be null";
        return tags.remove(tag);
    }

    /**
     * Returns a String that represents the Task, and can be interpreted by
     * the Storage class.
     *
     * @return The String that represents the Task in Storage text.
     */
    public String getStorageString() {
        return String.format("%s|%s|%s|%s",
                getShorthand(),
                getStatusIcon(),
                getDescription(),
                getStorageTags());
    }

    /**
     * Returns a String that represents the Task and should be printed to the User.
     *
     * @return The String that represents the Task.
     */
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

    /**
     * Returns a String representation of all the tags tagged to this Task.
     *
     * @return String representation of all tags in this Task.
     */
    protected String getPrintableTags() {
        return tags.stream()
                .map(x-> "#" + x)
                        .reduce((x, y) -> x + "," + y)
                                .orElse("");
    }

    private String getStorageTags() {
        return tags.stream()
                .reduce((x, y) -> x + "," + y)
                        .orElse(" ");
    }
}
