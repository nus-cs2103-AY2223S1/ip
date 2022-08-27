package duke.util;

import duke.Duke;
import duke.exception.DukeCommandFormatException;
import duke.exception.DukeDateTimeFormatException;
import duke.exception.DukeIndexOutOfBoundException;
import duke.exception.DukeMissingTaskDateTimeException;
import duke.exception.DukeMissingTaskTitleException;
import duke.task.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * A wrapper class that wraps a list of Task and facilitates some fundamental operations, such as add, find, delete and
 * modify.
 */
public class TaskList {

    private static final String TAB = Duke.TAB;
    private static final String EMPTY_LIST_MESSAGE = "The list is empty.";
    private static final String NOTHING_FOUND_MESSAGE = "Sorry, nothing found in the list.";


    private final List<Task> tasks;

    /**
     * The standard constructor.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Gives the current state of each Task saved in the list.
     *
     * @return a readable string that represents the internal state in the list.
     */
    public String getListInfo() {
        int len = tasks.size();
        if (len == 0) {
            return EMPTY_LIST_MESSAGE;
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < len; i++) {
            stringBuilder.append(i + 1).append(". ").append(tasks.get(i));
            if (i < len - 1) {
                stringBuilder.append('\n' + TAB);
            }
        }
        return stringBuilder.toString();
    }

    /**
     * Adds a new task to the list and returns a string as a follow-up of the operation.
     *
     * @param newTask The Task to be added into the list.
     * @return A string as a response of the operation.
     */
    public String addNewTask(Task newTask) throws DukeCommandFormatException, DukeMissingTaskTitleException, DukeMissingTaskDateTimeException, DukeDateTimeFormatException {
        tasks.add(newTask);
        return "Added: " + newTask.toString();
    }

    /**
     * Mark the index-th Task as done and returns a string as a follow-up of the operation.
     *
     * @param index The index indicating which Task to mark.
     * @return A string as a response of the operation.
     */
    public String markTaskDone(int index) throws DukeIndexOutOfBoundException {
        if (index < 0 || index >= tasks.size()) {
            throw new DukeIndexOutOfBoundException("");
        } else {
            Task targetTask = tasks.get(index);
            targetTask.markDone();
            return targetTask.toString();
        }
    }

    /**
     * Mark the index-th Task as undone and returns a string as a follow-up of the operation.
     *
     * @param index The index indicating which Task to mark.
     * @return A string as a response of the operation.
     */
    public String markTaskUndone(int index) throws DukeIndexOutOfBoundException {
        if (index < 0 || index >= tasks.size()) {
            throw new DukeIndexOutOfBoundException("");
        } else {
            Task targetTask = tasks.get(index);
            targetTask.markUndone();
            return targetTask.toString();
        }
    }

    /**
     * Remove the index-th Task from the list and returns a string as a follow-up of the operation.
     *
     * @param index The index indicating which Task to delete.
     * @return A string as a response of the operation.
     */
    public String deleteTask(int index) throws DukeIndexOutOfBoundException {
        if (index < 0 || index >= tasks.size()) {
            throw new DukeIndexOutOfBoundException("");
        } else {
            Task removedTask = tasks.remove(index);
            return removedTask.toString();
        }
    }

    /**
     * Returns a structured string that is convenient to be saved in a .txt file.
     *
     * @return A formatted string representing the internal state of the list.
     */
    public String getFileStream() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            stringBuilder.append(tasks.get(i).getFileRepresentation()).append('\n');
        }
        return stringBuilder.toString();
    }

    /**
     * Finds every Task in the list whose title contains the keyword.
     *
     * @param keyword The keyword to look up.
     * @return A string carrying all matched results.
     */
    public String find(String keyword) {
        int len = tasks.size();

        if (len == 0) {
            return '\n' + TAB + EMPTY_LIST_MESSAGE;
        }

        StringBuilder stringBuilder = new StringBuilder();
        int displayIndex = 1;

        for (int i = 0; i < len; i++) {
            Task curr = tasks.get(i);
            if (curr.contains(keyword)) {
                stringBuilder.append('\n' + TAB).append(displayIndex++).append(". ").append(curr);
            }
        }

        if (displayIndex == 1) {
            stringBuilder.append(NOTHING_FOUND_MESSAGE);
        }

        return stringBuilder.toString();
    }

    /**
     * Returns true if and only if there is one Task in the list.
     *
     * @return Whether there is only one Task in the list.
     */
    public boolean hasOnlyOneTask() {
        return tasks.size() == 1;
    }

    /**
     * Returns the size of the list.
     *
     * @return The number of Task in the list.
     */
    public int size() {
        return tasks.size();
    }
}
