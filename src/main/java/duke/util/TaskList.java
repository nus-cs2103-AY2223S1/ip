package duke.util;

//import util
import java.util.ArrayList;

import duke.exception.TaskDuplicatedException;
import duke.exception.TaskMarkException;
import duke.exception.TaskNotFoundException;
import duke.exception.TaskUnmarkException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * Represents a list of Tasks and provides methods to modify the list.
 */
public class TaskList {
    private static final String TOTAL_TASK_MESSAGE = "Total Task: ";
    private static final String UNMARK_TASK_MESSAGE = "Unmarked Task: ";

    private static final int START_SQUARE_BRACKET_INDEX = 3;
    private static final int END_SQUARE_BRACKET_INDEX = 6;
    private static final int START_DESCRIPTION_INDEX = 10;
    private static final int IS_DONE_INDEX = 7;

    private ArrayList<Task> tasks;

    /**
     * Constructs empty ArrayList that stores Task.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Constructs a ArrayList that stores tasks embedded in text.
     *
     * @param text Contain a number of task.
     */
    public TaskList(String text) {
        this();
        assert text != "" : "You should not be reading from an empty file.";
        addTaskFromFile(text);
    }

    /**
     * Reads text from file and parse into Task object.
     *
     * @param text text read from file.
     */
    private void addTaskFromFile(String text) {
        String[] texts = text.split("\n");
        String taskType;
        String description;
        String information;
        String date;
        boolean isDone;
        for (int i = 0; i < texts.length; i++) {
            taskType = texts[i].substring(START_SQUARE_BRACKET_INDEX, END_SQUARE_BRACKET_INDEX);
            String[] descriptions;
            switch (taskType) {
            case "[T]":
                description = texts[i].substring(START_DESCRIPTION_INDEX);
                tasks.add(new Todo(description, texts[i].charAt(7) == 'X'));
                break;
            case "[D]":
                description = texts[i].substring(START_DESCRIPTION_INDEX);
                descriptions = filterDescription(description, "by");
                information = descriptions[0];
                date = descriptions[1];
                isDone = texts[i].charAt(IS_DONE_INDEX) == 'X';
                tasks.add(new Deadline(information, date, isDone));
                break;
            case "[E]":
                description = texts[i].substring(START_DESCRIPTION_INDEX);
                descriptions = filterDescription(description, "at");
                information = descriptions[0];
                date = descriptions[1];
                isDone = texts[i].charAt(IS_DONE_INDEX) == 'X';
                tasks.add(new Event(information, date, isDone));
                break;
            default:
                break;
            }
        }
    }

    private String[] filterDescription(String description, String keyword) {
        String[] descriptions = description.split(keyword);
        descriptions[0] = descriptions[0].substring(0, description.indexOf("(") - 1);
        descriptions[1] = descriptions[1].substring(2, descriptions[1].length() - 1);
        return descriptions;
    }

    /**
     * Adds task to tasks.
     *
     * @param task To be added to tasks.
     */
    public void addTask(Task task) throws TaskDuplicatedException {
        if (isDuplicate(task)) {
            throw new TaskDuplicatedException();
        }
        tasks.add(task);
    }

    private boolean isDuplicate(Task task) {
        for (int i = 0; i < tasks.size(); i++) {
            Task taskInList = tasks.get(i);
            if (taskInList.equals(task)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if index is out of bound.
     *
     * @param index index to be checked.
     * @return true if index is in bound else false.
     */
    private boolean isIndexOutOfBound(int index) {
        return (index < 0 || index > tasks.size() - 1);
    }

    /**
     * Marks task specified by the index as done.
     *
     * @param index index of task to be mark as done.
     * @return task that is marked.
     * @throws TaskMarkException when Task is already marked.
     * @throws TaskNotFoundException when the given index is out of bound.
     */
    public Task markTask(int index) throws TaskMarkException, TaskNotFoundException {
        if (isIndexOutOfBound(index)) {
            throw new TaskNotFoundException(index + 1);
        }
        Task task = tasks.get(index);
        task.mark();
        return task;
    }

    /**
     * Marks task specified by the index as not done.
     *
     * @param index index of task to be mark as not done.
     * @return task that is unmarked.
     * @throws TaskUnmarkException when Task is already not done.
     * @throws TaskNotFoundException when the given index is out of bound.
     */
    public Task unmarkTask(int index) throws TaskUnmarkException, TaskNotFoundException {
        if (isIndexOutOfBound(index)) {
            throw new TaskNotFoundException(index + 1);
        }
        Task task = tasks.get(index);
        task.unmark();
        return task;
    }

    /**
     * Deletes task specified by the index.
     *
     * @param index index of task to be deleted
     * @return task that is removed.
     * @throws TaskNotFoundException when the given index is out of bound.
     */
    public Task deleteTask(int index) throws TaskNotFoundException {
        if (isIndexOutOfBound(index)) {
            throw new TaskNotFoundException(index + 1);
        }
        return tasks.remove(index);
    }

    /**
     * Checks if tasks is empty.
     *
     * @return true if tasks is empty else false.
     */
    public boolean isEmpty() {
        return tasks.size() == 0;
    }

    /**
     * Displays the status of the tasks.
     * Includes total number of task and unmarked task.
     *
     * @return String Representation of the status.
     */
    public String getStatus() {
        String totalTaskMessage = TOTAL_TASK_MESSAGE + tasks.size();
        String unmarkTaskMessage = UNMARK_TASK_MESSAGE + getNoOfUnmarkTask();
        return totalTaskMessage + "\n" + unmarkTaskMessage;
    }

    /**
     * Retrieves the number of unmark task in tasks.
     *
     * @return the number of unmark task.
     */
    private int getNoOfUnmarkTask() {
        Task task;
        int count = 0;
        for (int i = 0; i < tasks.size(); i++) {
            task = tasks.get(i);
            if (!task.isDone()) {
                count++;
            }
        }
        return count;
    }

    /**
     * Returns a list of task that contains description in it.
     *
     * @param description filter out task with description in it.
     * @return TaskList containing all filtered out task with description in it.
     */
    public TaskList findTask(String description) throws TaskDuplicatedException {
        TaskList filteredTasks = new TaskList();
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            if (task.doesContain(description)) {
                filteredTasks.addTask(task);
            }
        }
        return filteredTasks;
    }

    /**
     * Returns a string representation of tasks.
     *
     * @return tasks in string representation.
     */
    @Override
    public String toString() {
        int size = tasks.size();
        String text = String.format("%d. %s", 1, tasks.get(0));
        for (int i = 1; i < size; i++) {
            text += String.format("\n%d. %s", i + 1, tasks.get(i));
        }
        return text;
    }
}
