package duke.backend;

import java.time.LocalDate;
import java.time.Period;
import java.time.Duration;
import java.time.LocalDateTime;

import java.util.ArrayList;

import duke.Duke;
import duke.DukeException;
import duke.tasktype.Deadline;
import duke.tasktype.Event;
import duke.tasktype.Task;
import duke.tasktype.ToDo;

import javax.swing.*;

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
     * @throws DukeException error message
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
     * @throws DukeException error message
     */
    public void appendDeadlineFromFile(String taskDescription, String date, boolean isCompleted) throws DukeException {
        userTasks.add(new Deadline(taskDescription, date, isCompleted));
    }

    /**
     * Appends a new Event object to the userTasks ArrayList
     * @param taskDescription string of TaskDescription for an Event object to be created
     * @param dateTime represents dateTime for Task to be completed by
     * @throws DukeException error message
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
     * @throws DukeException error message
     */
    public void appendEventFromFile(String taskDescription, String dateTime, boolean isCompleted) throws DukeException {
        userTasks.add(new Event(taskDescription, dateTime, isCompleted));
    }

    public void checkTaskExistsAtIndex(Integer number) throws DukeException {
        if (number >= userTasks.size()) {
            throw new DukeException("Task doesn't exist as the requested index");
        }
    }

    /**
     * Removes a Task from the input index and returns its description
     * @param number index of the task in userTasks ArrayList to be removed
     * @return description of the removed Task
     */
    public String removeTask(String number) throws DukeException {
        int index = Integer.parseInt(number) - 1;
        checkTaskExistsAtIndex(index);

        assert userTasks.size() > 0: "userTasks ArrayList must not be empty";

        Task deletedTask = userTasks.remove(index);
        String taskMessage = deletedTask.returnDescription();
        return taskMessage;
    }

    /**
     * Marks a Task at a specified index as completed
     * @param taskNumber index of the Task to be marked
     * @return the description of the Task after marking it
     */
    public String markSpecificTask(String taskNumber) throws DukeException {
        int index = Integer.parseInt(taskNumber) - 1;
        checkTaskExistsAtIndex(index);

        assert userTasks.size() > 0: "userTasks ArrayList must not be empty";

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
    public String unmarkSpecificTask(String taskNumber) throws DukeException{
        int index = Integer.parseInt(taskNumber) - 1;
        checkTaskExistsAtIndex(index);

        assert userTasks.size() > 0: "userTasks ArrayList must not be empty";

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

    public String listOfTasksForReminder() {
        String content = "";
        for (Task task: userTasks) {
            if (task instanceof Deadline) {
                content += getDeadlineDescriptionIfWithinOneWeek((Deadline) task);
            } else if (task instanceof Event) {
                content += getEventDescriptionIfWithinOneWeek((Event) task);
            }
        }
        return content;
    }

    private String getDeadlineDescriptionIfWithinOneWeek(Deadline task) {
        if (isWithinOneWeek(task.getDate())) {
            return task.returnDescription() + "\n";
        } else {
            return "";
        }
    }

    private String getEventDescriptionIfWithinOneWeek(Event task) {
        if (isWithinOneWeek(task.getDate())) {
            return task.returnDescription() + "\n";
        } else {
            return "";
        }
    }

    private boolean isWithinOneWeek(LocalDateTime d1) {
        LocalDateTime d2 = LocalDateTime.now();
        Duration p = Duration.between(d2, d1);
        return p.toDays() <= 7 && p.toDays() >= 0;
    }

    private boolean isWithinOneWeek(LocalDate d1) {
        LocalDate d2 = LocalDate.now();
        Period p = Period.between(d2, d1);
        return p.getDays() <= 7 && p.getDays() >= 0;
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
