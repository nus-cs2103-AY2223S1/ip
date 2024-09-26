package duke.task;

import java.util.HashSet;

import duke.exception.DukeException;

/**
 * Task encapsulates a task with a description and a completion status.
 */
public class Task {
    protected static final String STORAGE_DELIMITER = " | ";
    private static final String STORAGE_TAG_DELIMITER = " |";
    private static final String DONE_STATUS = "X";
    private static final String NOT_DONE_STATUS = " ";
    private static final String DONE_STORAGE = "1";
    private static final String NOT_DONE_STORAGE = "0";
    private String description;
    private boolean isDone;
    private HashSet<String> tags;

    /**
     * Constructor for Task.
     *
     * @param description Description of the Task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.tags = new HashSet<>();
    }

    /**
     * Returns the String representation of completion status.
     *
     * @return String representation of the completion status.
     */
    public String getStatusIcon() {
        return (isDone ? DONE_STATUS : NOT_DONE_STATUS);
    }

    /**
     * Returns the String representation of the Task.
     *
     * @return String representation of the Task.
     */
    @Override
    public String toString() {
        return ("[" + getStatusIcon() + "] " + description);
    }

    /**
     * Marks the Task as completed.
     */
    public void markDone() {
        isDone = true;
    }

    /**
     * Marks the Task as uncompleted.
     */
    public void markUndone() {
        isDone = false;
    }

    /**
     * Returns the String representation to be stored.
     *
     * @return storage String representation of the Task.
     */
    public String toStorageString() {
        String statusStorage = isDone ? DONE_STORAGE : NOT_DONE_STORAGE;
        return statusStorage + Task.STORAGE_DELIMITER + description;
    }

    /**
     * Creates a Task object from a storage String.
     *
     * @param string The Storage String representing the Task.
     * @return The Task represented by the Storage String.
     */
    public static Task fromStorageString(String string) {
        String[] taskSubstrings = string.split(" \\| ");
        TaskType taskType = TaskType.parse(taskSubstrings[0]);
        Task task;
        try {
            switch (taskType) {
            case DEADLINE:
                task = new Deadline(taskSubstrings[2], taskSubstrings[3]);
                if (taskSubstrings.length == 5) {
                    String[] storageTags = taskSubstrings[4].split(" ");
                    fromStorageTags(task, storageTags);
                }
                break;
            case EVENT:
                task = new Event(taskSubstrings[2], taskSubstrings[3]);
                if (taskSubstrings.length == 5) {
                    String[] storageTags = taskSubstrings[4].split(" ");
                    fromStorageTags(task, storageTags);
                }
                break;
            case TODO:
                task = new ToDo(taskSubstrings[2]);
                if (taskSubstrings.length == 4) {
                    String[] storageTags = taskSubstrings[3].split(" ");
                    fromStorageTags(task, storageTags);
                }
                break;
            default:
                throw new RuntimeException(String.format("Invalid task type %s.", taskType));
            }
        } catch (DukeException e) {
            throw new RuntimeException(e.getMessage());
        }
        if (taskSubstrings[1].equals(Task.DONE_STORAGE)) {
            task.isDone = true;
        }
        return task;
    }

    /**
     * Adds tags from storage tags to Task object.
     *
     * @param task The Task to be tagged.
     * @param storageTags The String array containing the Tags.
     */
    public static void fromStorageTags(Task task, String[] storageTags) {
        for (String storageTag : storageTags) {
            task.addTag(storageTag.substring(1));
        }
    }

    /**
     * Checks whether the Task description matches the given keyword.
     *
     * @param keyword The keyword to match.
     * @return Boolean indicating whether the description contains the keyword.
     */
    public boolean hasKeyword(String keyword) {
        return description.contains(keyword);
    }

    /**
     * Checks whether the Task tags matches the given tag.
     *
     * @param searchTag The tag to match.
     * @return Boolean indicating whether the tags contain the tag.
     */
    public boolean hasTag(String searchTag) {
        return tags.contains(searchTag);
    }

    /**
     * Adds a tag to the Task.
     */
    public void addTag(String newTag) {
        tags.add(newTag);
    }

    /**
     * Removes a tag from the Task.
     */
    public void removeTag(String currentTag) {
        tags.remove(currentTag);
    }

    /**
     * Returns the String representation of the tags.
     *
     * @return String representation of the tags.
     */
    public String getTagsString() {
        if (tags.isEmpty()) {
            return "";
        }
        return " #" + String.join(" #", tags);
    }

    /**
     * Returns the storage String representation of the tags.
     *
     * @return storage String representation of the tags.
     */
    public String getTagsStorageString() {
        String tagsString = getTagsString();
        return tagsString == "" ? "" : STORAGE_TAG_DELIMITER + tagsString;
    }

}
