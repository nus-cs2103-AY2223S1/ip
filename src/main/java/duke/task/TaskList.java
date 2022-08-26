package duke.task;

import duke.exceptions.InvalidInputException;

import java.util.ArrayList;

import static duke.common.Messages.*;


/**
 * Represents the task list and methods to manage the task list.
 */
public class TaskList {

    private ArrayList<Task> tasks = new ArrayList<>();
    private int taskCount = 0;

    /**
     * Constructor for a new task list.
     *
     * @param tasks list of tasks if preloaded
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
        this.taskCount = tasks.size();
    }

    private Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Creates a new Todo Task and adds it to the task list.
     *
     * @param description description of the Todo task
     */
    public void addTodoTask(String description) {
        Task task = new TodoTask(description);
        tasks.add(task);
        System.out.println(MESSAGE_TASK_ADDED);
        System.out.println(MESSAGE_OTHER_ACTIONS);
        taskCount++;
    }

    /**
     * Creates a new Event Task and adds it to the task list.
     *
     * @param description description of the event task
     * @param eventDate date and/or time of the event
     */
    public void addEventTask(String description, String eventDate) {
        Task task = new EventTask(description, eventDate);
        tasks.add(task);
        System.out.println(MESSAGE_TASK_ADDED);
        System.out.println(MESSAGE_OTHER_ACTIONS);
        taskCount++;
    }

    /**
     * Creates a new Deadline Task and adds it to the task list.
     *
     * @param description description of the task
     * @param deadline deadline for this task
     */
    public void addDeadlineTask(String description, String deadline) {
        Task task = new DeadlineTask(description, deadline);
        tasks.add(task);
        System.out.println(MESSAGE_TASK_ADDED);
        System.out.println(MESSAGE_OTHER_ACTIONS);
        taskCount++;
    }

    /**
     * Deletes a task from the task list.
     *
     * @param taskNumber index of the task in the list
     */
    public void deleteTask(int taskNumber) throws InvalidInputException {
        Task chosenTask = getTask(checkIndex(taskNumber));
        tasks.remove(chosenTask);
        System.out.println(this);
        System.out.printf((MESSAGE_TASK_REMOVED), taskNumber);
        System.out.println(MESSAGE_OTHER_ACTIONS);
        taskCount--;
    }

    /**
     * Marks a task of the task list as done.
     *
     * @param taskNumber visible index of the task
     */
    public void markTask(int taskNumber) throws InvalidInputException {
        Task chosenTask = getTask(checkIndex(taskNumber));
        if (chosenTask.isDone) {
            throw new InvalidInputException("Task already marked!");
        }
        chosenTask.markDone();
        System.out.println(this);
        System.out.printf(MESSAGE_TASK_MARKED, taskNumber);
    }

    public void findTask(String taskSubstring) {
        StringBuilder sb = new StringBuilder();
        for (Task task : tasks) {
            if (task.toString().contains(taskSubstring)) {
                sb.append(task).append("\n");
            }
        }

        if (sb.toString().isEmpty()) {
            System.out.println(MESSAGE_NO_TASK_FOUND);
        } else {
            System.out.println(sb);
        }
    }


    private int checkIndex(int taskNumber) throws InvalidInputException {
        int actualTaskIndex = taskNumber - 1;
        if (actualTaskIndex >= tasks.size() || actualTaskIndex < 0) {
            throw new InvalidInputException(MESSAGE_INVALID_TASK_NUMBER);
        }
        return actualTaskIndex;
    }

    /**
     * Converts the list of tasks to a string to be saved into the storage file.
     */
    public String encodeToString() {
        int i = 1;
        System.out.println(MESSAGE_TASK_LIST);
        StringBuilder sb = new StringBuilder();
        for (Task task : tasks) {
            sb.append(task.encodeToString() + "\n");
            i++;
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        int i = 1;
        StringBuilder sb = new StringBuilder();
        for (Task task : tasks) {
            sb.append(i + ": " + task.toString() + "\n");
            i++;
        }
        return sb.toString();
    }

}
