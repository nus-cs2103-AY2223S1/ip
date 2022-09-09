package duke.tag;

import duke.task.Task;
import duke.ui.Ui;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Encapsulates a hashtag that represents a category of tasks.
 */
public class Tag {
    private static HashMap<Tag, ArrayList<Task>> tagTaskMap = new HashMap<>();
    private static ArrayList<Tag> tags = new ArrayList<>();

    public String name;

    private Tag(String name) {
        this.name = name;
    }

    /**
     * Factory method to create a new instance of a Tag if
     * it has not already been created.
     *
     * @param name Name of the tag.
     * @return An instance of tag with the given name.
     */
    public static Tag of(String name) {
        for (Tag tag : tagTaskMap.keySet()) {
            if (tag.toString().equals(name)) {
                return tag;
            }
        }

        Tag newTag = new Tag(name);
        tagTaskMap.put(newTag, new ArrayList<>());
        return newTag;
    }

    /**
     * Maps the specified task to the specified tag.
     *
     * @param task Task to be added.
     * @param tag Tag to add the task to.
     */
    public static void addTaskToTag(Task task, Tag tag) {
        assert tagTaskMap.containsKey(tag) : "Tag has not been added to the HashMap.";
        ArrayList<Task> tasksUnderTag = tagTaskMap.get(tag);
        tasksUnderTag.add(task);
    }

    /**
     * Deletes the specified task from the specified tag.
     *
     * @param task Task to delete from the tag.
     * @param tag Tag to delete the task from.
     */
    public static void deleteTaskFromTag(Task task, Tag tag) {
        assert tagTaskMap.containsKey(tag) : "Tag has not been added to the HashMap.";
        ArrayList<Task> tasksUnderTag = tagTaskMap.get(tag);
        tasksUnderTag.remove(task);
    }

    /**
     * Prints out a list of all the tags created by the user.
     *
     * @return A string in the form of a list containg all the
     *     tags created by the user.
     */
    public static String printTags() {
        if (tagTaskMap.isEmpty()) {
            return Ui.START + "you have not added any tags. start tagging your tasks now!";
        }

        String tagList = "these are the tags you have added:";
        for (Tag tag : tagTaskMap.keySet()) {
            tagList += "\n  " + tag.toString();
        }
        return tagList;
    }

    public static String tasksUnder(Tag tag) {
        ArrayList<Task> tasks = tagTaskMap.get(tag);
        if (tasks.isEmpty()) {
            return Ui.START + "there are no tasks tagged under " + tag.toString() + "."
                    + "\n start tagging some tasks with this tag now!";
        }

        int x = 1;
        String taskList = Ui.START + "these are the tasks tagged under " + tag.toString() + ":";
        for (Task task : tasks) {
            taskList += "\n" + x + ". " + task.toString();
            x++;
        }
        return taskList;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
