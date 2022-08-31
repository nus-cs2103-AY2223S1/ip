package iana.tasks;

import iana.exception.IanaException;
import iana.tasks.Task;

/**
 * Task class to represent the tasks to be added into task list.
 */
public class Task {
    protected final String task;
    protected final String taskType;
    private boolean isCompleted;

    /**
     * Factory method to create a new task.
     * @param taskString user input of task description.
     * @param isCompleted true if task has been completed.
     * @return the task if it is correctly entered into program.
     * @throws IanaException if user input contains format error.
     */
    public static Task of(String taskString, boolean isCompleted) throws IanaException {
        String[] textArr = taskString.split(" ", 2);
        String startText = textArr[0];

        String[] taskArray;
        try {
            switch(TaskType.valueOf(startText)) {
                case todo:
                taskArray = check(textArr, "todo");
                Todo newTodo = new Todo(taskArray[0], isCompleted);
                return newTodo;
                    
                case event:
                taskArray = check(textArr, "event");
                Event newEvent = new Event(taskArray[0], taskArray[1], isCompleted);
                return newEvent;

                case deadline:
                taskArray = check(textArr, "deadline");
                Deadline newDeadline = new Deadline(taskArray[0], taskArray[1], isCompleted);
                return newDeadline;

                default:
                throw new IanaException("Invalid tasks!");
            } 
        } catch (IllegalArgumentException e) {
            throw new IanaException("Invalid task!! >:C");
        }
    }

    /**
     * Verifies whether task to be added is in the correct format.
     * @param textArray array of strings containing task description.
     * @param taskType type of task to be created.
     * @return array of string containing only relevant task description.
     * @throws IanaException if task input is in the wrong format.
     */
    private static String[] check(String[] textArray, String taskType) throws IanaException {
        if (textArray.length <= 1) {
            throw new IanaException(String.format("Oops! Your %s cannot be empty! :-(", taskType));
        }

        String[] taskArray = {};

        switch(TaskType.valueOf(taskType)) {
            case event:
            taskArray = textArray[1].split("/at ", 2);
            if (taskArray.length <= 1) {
                throw new IanaException("Add event again with the format EVENT <event> /at <event time> !! :-)");
            }
            break;
            
            case deadline:
            taskArray = textArray[1].split("/by ", 2);
            if (taskArray.length <= 1) {
                throw new IanaException("Use the format <deadline> /by <deadline time> to create a deadline!! :D");
            }
            break;
            
            case todo:
            String[] temp = {textArray[1]};
            taskArray = temp;
            break;

            default:
            throw new IanaException("Sorry, this is an invalid task type!! D-:");
        }

        return taskArray;
    }

    /**
     * Task to be created. 
     * @param task full user input of task description.
     * @param taskType type of task to be created.
     * @param isCompleted whether task is completed.
     */
    protected Task(String task, String taskType, boolean isCompleted) {
        this.task = task;
        this.taskType = taskType;
        this.isCompleted = isCompleted;
    }
    
    /**
     * Change whether task is completed.
     * @param isCompleted true if task is completed.
     */
    public void toggleComplete(boolean isCompleted) {
        this.isCompleted = isCompleted;
    }

    /**
     * Returns string representation of task to be stored in storage.
     * @return string representation.
     */
    public boolean containsKeyword(String keyword) {
        return this.task.contains(keyword);
    }
 
    public String toFileData() {
        return String.format("%d | %s", this.isCompleted ? 1 : 0, this.task);
    }

    /**
     * Returns string representation of task.
     */
    @Override
    public String toString() {
        return String.format("%s %s", this.isCompleted ? "[X]" : "[ ]", this.task);
    }
}
