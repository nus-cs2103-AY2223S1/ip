package duke;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import duke.task.Task;

/**
 * Represents a list of tasks.
 */
public class DukeList {
    private final List<Task> listItems;

    public DukeList() {
        this.listItems = new ArrayList<>();
    }

    public DukeList(List<Task> listItems) {
        this.listItems = listItems;
    }

    /**
     * Converts Task list to String representation for saving to file.
     * @return A list of the Tasks in String format to save to file.
     */
    public List<String> getListToDataStr() {
        return listItems.stream().map(this::taskToDataStr).collect(Collectors.toList());
    }

    /**
     * Converts a Task to String for storing in a file.
     * @param t The Task to convert.
     * @return A String representation of the Task for storing in a file.
     */
    private String taskToDataStr(Task t) {
        String taskType = t.getTaskType().toString().toLowerCase();
        String description = t.getDesc();
        String isDone = t.getStatus() ? "1" : "0";
        String otherData = t.getOtherData();

        String taskStr = taskType + Storage.FILE_STR_DIVIDER + isDone + Storage.FILE_STR_DIVIDER + description;

        if (otherData.isEmpty()) {
            return taskStr;
        } else {
            return taskStr + Storage.FILE_STR_DIVIDER + otherData;
        }
    }

    /**
     * Adds a Task to the list.
     * @param item The task to add to the list.
     * @return A String of the message to be displayed.
     */
    public String add(Task item) {
        listItems.add(item);
        return "Got it. I've added this task:\n"
                + item
                + "\nNow you have "
                + listItems.size()
                + (listItems.size() == 1 ? " task" : " tasks")
                + " in the list.";
    }

    /**
     * Marks a task in the list as done.
     * @param index The index of the task to mark as done.
     * @return A String of the message to be displayed.
     * @throws DukeException If index is invalid.
     */
    public String done(int index) throws DukeException {
        int listIndex = index - 1;
        if (listIndex < 0 || listIndex > listItems.size() - 1) {
            throw new DukeException("Invalid task to mark as done.");
        }

        Task t = listItems.get(listIndex);
        t.markAsDone();
        return "Nice! I've marked this task as done:\n" + t;
    }

    /**
     * Marks a task in the list as not done.
     * @param index The index of the task to mark as not done.
     * @return A String of the message to be displayed.
     * @throws DukeException If index is invalid.
     */
    public String undone(int index) throws DukeException {
        int listIndex = index - 1;
        if (listIndex < 0 || listIndex > listItems.size() - 1) {
            throw new DukeException("Invalid task to mark as undone.");
        }

        Task t = listItems.get(listIndex);
        t.markAsUndone();
        return "OK, I've marked this task as not done yet:\n" + t;
    }

    /**
     * Deletes a task in the list.
     * @param index The index of the task to delete.
     * @return A String of the message to be displayed.
     * @throws DukeException If index is invalid.
     */
    public String delete(int index) throws DukeException {
        int listIndex = index - 1;
        if (listIndex < 0 || listIndex > listItems.size() - 1) {
            throw new DukeException("Invalid task to delete.");
        }

        Task t = listItems.get(listIndex);
        listItems.remove(listIndex);
        return "Noted. I've removed this task:\n"
                + t
                + "\nNow you have "
                + listItems.size()
                + (listItems.size() == 1 ? " task" : " tasks")
                + " in the list.";
    }

    /**
     * Searches the task list.
     * @param searchTerm The term to search the task list with.
     * @return A String with the results of the search.
     */
    public String search(String searchTerm) {
        StringBuilder results = new StringBuilder();
        boolean isFound = false;
        for (int i = 0; i < listItems.size(); i++) {
            Task task = listItems.get(i);

            if (task.getDesc().contains(searchTerm)) {
                if (!isFound) {
                    isFound = true;
                    results.append("Here are the matching tasks in your list:");
                }
                results.append("\n").append(i + 1).append(". ").append(task);
            }
        }

        if (!isFound) {
            results.append("Nothing was found.");
        }
        return results.toString();
    }

    /**
     * Returns number of tasks in the list.
     * @return The number of tasks in the list.
     */
    public int getListSize() {
        return listItems.size();
    }

    @Override
    public String toString() {
        StringBuilder listItemsStrBuilder = new StringBuilder();
        listItemsStrBuilder.append("Here are the tasks in your list:\n");
        for (int i = 0; i < listItems.size(); i++) {
            listItemsStrBuilder.append(i + 1).append(". ").append(listItems.get(i));

            if (i != listItems.size() - 1) {
                listItemsStrBuilder.append("\n");
            }
        }
        return listItemsStrBuilder.toString();
    }
}
