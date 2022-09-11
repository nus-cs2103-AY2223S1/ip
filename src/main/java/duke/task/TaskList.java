package duke.task;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import duke.exception.DukeException;

/**
 * Contains the logic for which a list of tasks works in Duke
 */
public class TaskList {
    private static final String NO_SUCH_INDEX = "No such index in the list, please try again.";
    private static final String NO_TASKS_LEFT = "List is empty, 0 items left !";
    private final List<Task> taskList;

    /**
     * Constructor for a TaskList
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Constructor for a TaskList
     * @param taskList List of tasks
     */
    public TaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    private String handleTaskOutput(Task task, int id) {
        return String.format("%d. %s", id, task.toString());
    }

    /**
     * Get a list of tasks
     * @return a task list
     * @see Task
     */
    public List<Task> getTaskList() {
        return this.taskList;
    }

    private String getItemsLeft() {
        if (taskList.isEmpty()) {
            return NO_TASKS_LEFT;
        } else {
            assert !taskList.isEmpty() : "taskList should not be empty";
            return String.format("Now you have %d tasks in the list", taskList.size());
        }
    }
    /**
     * Returns an instance of TaskList which contains search result from the given query string
     * @param query is the target search term for task description in the list.
     * @return a task list with the filtered result
     */
    public TaskList findMatchingTasks(String query) {
        // Filter from taskList stream and collect into a List of Tasks
        List<Task> result = this.taskList.stream().filter(item -> item.getDescription().contains(query))
                .collect(Collectors.toList());
        TaskList filteredTasks = new TaskList(result);

        return filteredTasks;
    }

    /**
     * Removes a task from list via id and outputs the status.
     * @param id refers to the task number on the list.
     */
    public String removeTaskFromList(int id) {
        try {
            if (id <= 0 || id > taskList.size()) {
                throw new DukeException(NO_SUCH_INDEX);
            }

            Task taskToRemove = this.taskList.get(id - 1);
            assert taskToRemove != null : "taskToRemove cannot be null";

            this.taskList.remove(id - 1);
            String taskRemovedOutput = String.format("Noted. I've removed this task:\n%s\n%s",
                    taskToRemove, getItemsLeft());
            return taskRemovedOutput;
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    /**
     * Adds a task to the list
     * @param task task to be added to list
     * @see Task
     */
    public String addTaskToList(Task task) {
        try {
            assert task != null : "task cannot be null";
            this.taskList.add(task);
            String taskAddedOutput = String.format("Got it. I've added this task:\n%s\n%s",
                    task.toString(), getItemsLeft());
            return taskAddedOutput;
        } catch (Exception e) {
            System.out.println(e);
            return e.getMessage();
        }
    }

    /**
     * Lists all tasks as numbered list.
     */
    public String listTasks() {
        if (taskList.isEmpty()) {
            return NO_TASKS_LEFT;
        }
        String toPrint = "";

        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            String toConcat = handleTaskOutput(task, i + 1);
            toPrint = String.format("%s\n%s", toPrint, toConcat);
        }

        return toPrint.substring(1);
    }

    /**
     * Marks a task as done with regards to given task number.
     * @param id refers to the task number on the list.
     * @see Task
     */
    public String markTaskAsDone(int id) {
        try {
            if (id <= 0 || id > taskList.size()) {
                throw new DukeException(NO_SUCH_INDEX);
            }
            Task targetTask = taskList.get(id - 1);
            assert targetTask != null : "targetTask cannot be null";
            return targetTask.markAsDone(false);
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    /**
     * Marks a task as undone with regards to given task number.
     * @param id refers to the task number on the list.
     * @see Task
     */
    public String markTaskAsUnDone(int id) {
        try {
            if (id <= 0 || id > taskList.size()) {
                throw new DukeException(NO_SUCH_INDEX);
            }
            Task targetTask = taskList.get(id - 1);
            assert targetTask != null : "targetTask cannot be null";
            return targetTask.markAsUnDone();
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    /**
     * Returns last added task output. Recommended for testing purposes.
     * @return output regarding last action.
     */
    public String getAddedTaskOutput() {
        Task lastAddedTask;
        if (!taskList.isEmpty()) {
            lastAddedTask = taskList.get(taskList.size() - 1);
            assert lastAddedTask != null : "lastAddedTask cannot be null";
            return String.format("Got it. I've added this task:\n  %s\n%s\n",
                    lastAddedTask.toString(), getItemsLeft());
        }
        return NO_TASKS_LEFT;
    }

    /**
     * Sorts task list by date of creation or given date.
     * @return sorted tasks
     */
    public static TaskList sortTaskListByDate(List<Task> list) {
        TaskList sortedTaskList = new TaskList(list);
        Collections.sort(sortedTaskList.getTaskList(), new TaskDateComparator());
        return sortedTaskList;
    }

    private boolean withinDate(LocalDateTime start, LocalDateTime end, Task task) {
        if (task.getLocalDateTime().isBefore(start)) {
            return false;
        }
        if (task.getLocalDateTime().isAfter(end)) {
            return false;
        }
        return true;
    }

    /**
     * Finds the tasks of a certain date range that are undone.
     * @return task list within specified date
     */
    public TaskList getTasksWithinDate(LocalDateTime start, LocalDateTime end) {
        List<Task> tasksWithinDate = new ArrayList<>();

        this.getTaskList().forEach(task -> {
            if (withinDate(start, end, task)) {
                tasksWithinDate.add(task);
            }
        });

        return sortTaskListByDate(tasksWithinDate);
    }
}
