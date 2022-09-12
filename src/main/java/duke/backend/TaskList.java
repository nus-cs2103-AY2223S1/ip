package duke.backend;

import java.util.ArrayList;

import duke.DukeException;
import duke.tasktype.Deadline;
import duke.tasktype.Event;
import duke.tasktype.Task;
import duke.tasktype.ToDo;

/**
 * TaskList is the data structure for storing all types of Tasks entered into Duke.
 */
public class TaskList {
    private ArrayList<Task> userTasks;

    /**
     * Constructor for TaskList class
     * Uses Arraylist to store Tasks
     */
    public TaskList() {
        userTasks = new ArrayList<Task>();
    }

    /**
     * Returns the length of the current userTasks ArrayList
     * @return number of Tasks stored in ArrayList
     */
    public int getSize() {
        return userTasks.size();
    }

    /**
     * Returns the description of the last task in the userTasks ArrayList
     * @return the description of last task
     */
    public String getLastTask() {
        return userTasks.get(userTasks.size() - 1).returnDescription();
    }

    /**
     * Appends a new _ToDo object to the userTasks ArrayList
     * @param taskDescription string of TaskDescription for a _ToDo object to be created
     */
    public void appendToDo(String taskDescription) {
        userTasks.add(new ToDo(taskDescription));
    }

    /**
     * Appends a new _ToDo object to the userTasks ArrayList
     * @param taskDescription string of TaskDescription for a _ToDo object to be created
     * @param isCompleted boolean representing completion status of _ToDo object
     */
    public void appendToDoFromFile(String taskDescription, boolean isCompleted) {
        userTasks.add(new ToDo(taskDescription, isCompleted));
    }

    /**
     * Appends a new DeadLine object to the userTasks ArrayList
     * @param taskDescription string of TaskDescription for a DeadLine object to be created
     * @param date represents date Task is to be completed by
     * @throws DukeException
     */
    public void appendDeadline(String taskDescription, String date) throws DukeException {
        date = date.replace("by ","");
        userTasks.add(new Deadline(taskDescription, date));
    }

    /**
     * Appends a new DeadLine object to the userTasks ArrayList
     * @param taskDescription string of TaskDescription for a DeadLine object to be created
     * @param date represents date Task is to be completed by
     * @param isCompleted boolean representing completion status of DeadLine object
     * @throws DukeException
     */
    public void appendDeadlineFromFile(String taskDescription, String date, boolean isCompleted) throws DukeException {
        userTasks.add(new Deadline(taskDescription, date, isCompleted));
    }

    /**
     * Appends a new Event object to the userTasks ArrayList
     * @param taskDescription string of TaskDescription for an Event object to be created
     * @param dateTime represents dateTime for Task to be completed by
     * @throws DukeException
     */
    public void appendEvent(String taskDescription, String dateTime) throws DukeException {
        dateTime = dateTime.replace("at ","");
        userTasks.add(new Event(taskDescription, dateTime));
    }

    /**
     * Appends a new Event object to the userTasks ArrayList
     * @param taskDescription string of TaskDescription for an Event object to be created
     * @param dateTime represents dateTime for Task to be completed by
     * @param isCompleted boolean representing completion status of Event object
     * @throws DukeException
     */
    public void appendEventFromFile(String taskDescription, String dateTime, boolean isCompleted) throws DukeException {
        userTasks.add(new Event(taskDescription, dateTime, isCompleted));
    }

    /**
     * Removes a Task from the input index and returns its description
     * @param number index of the task in userTasks ArrayList to be removed
     * @return description of the removed Task
     */
    public String removeTask(String number) {
        int index = Integer.parseInt(number) - 1;
        Task deletedTask = userTasks.remove(index);
        String taskMessage = deletedTask.returnDescription();
        return taskMessage;
    }

    /**
     * Marks a Task at a specified index as completed
     * @param taskNumber index of the Task to be marked
     * @return the description of the Task after marking it
     */
    public String markSpecificTask(String taskNumber) {
        int index = Integer.parseInt(taskNumber) - 1;
        Task currentTask = userTasks.get(index);
        currentTask.markTask();
        String content = currentTask.returnDescription();
        return content;
    }

    /**
     * Marks a Task at a specified index as incomplete
     * @param taskNumber index of the Task to be unmarked
     * @return the description of the Task after unmarking it
     */
    public String unmarkSpecificTask(String taskNumber) {
        int index = Integer.parseInt(taskNumber) - 1;
        Task currentTask = userTasks.get(index);
        currentTask.unmarkTask();
        String content = currentTask.returnDescription();
        return content;
    }

    /**
     * The description of all current Tasks in the userTasks ArrayList
     * @return String concatenated with all the current tasks' description
     */
    public String listOfTaskForDisplay() {
        String content = "";
        int index = userTasks.size();
        for (int i = 0; i < index; i++) {
            content += "  " + String.valueOf(i + 1) + ". " + userTasks.get(i).returnDescription();
            if(i != index - 1) {
                content += "\n";
            }
        }
        return content;
    }

    /**
     * Returns the description of all Tasks that contain the input substring
     * @param substring the phrase that Tasks have to contain in order to be matched
     * @return description of Tasks that have been matched to input substring
     */
    public String listOfMatchedTasks(String substring) {
        String content = "";
        int index = userTasks.size();
        for (int i = 0; i < index; i++) {
            if (userTasks.get(i).getTaskDescription().contains(substring)) {
                content += userTasks.get(i).returnDescription();
                content += "\n";
            }
        }
        return content;
    }

    /**
     * Returns the description of all Tasks in the userTasks ArrayList in a format suitable for saving
     * @return the description of all Tasks in the userTasks ArrayList
     */
    public String listOfTasksForSaving() {
        String content = "";
        for (Task task: userTasks) {
            content += task.toWriteFile() + System.lineSeparator();
        }
        return content;
    }

}
