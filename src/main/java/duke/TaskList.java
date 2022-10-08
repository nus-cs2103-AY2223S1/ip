package duke;

import java.util.ArrayList;
import java.util.List;

import duke.events.Task;
import duke.save.Storage;
import duke.ui.Ui;



/**
 * Helper class that contains the following:
 * 1. Encapsulation of Array
 * 2  Functionality of add/delete
 * 3. Functionality of mark/unmark
 * 4. Functionality of list/finding tasks
 */



public class TaskList {

    //To be given to UI to handle
    private static final String ENDING_MESSAGE = "That's all? Hope to see you again soon :)";
    private static final String LIST_HEADER = "Here are the tasks in your list:";
    private static final String DELETE_HEADER = "Noted. I've removed this task:";
    private static final String DONE_MESSAGE = "Nice! I've marked this task as done:";

    private static final String UNDONE_MESSAGE = "OK, I've marked this task as not done yet:";

    public static final String FIND_HEADER = "Here are the matching tasks in your list:";

    private static final String ADD_HEADER = "Got it. I've added this task:";

    private static final String VIEW_SCHEDULE_HEADER = "Hello, here are the tasks for the date scheduled: ";
    private List<Task> taskList;

    public TaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * searches the taskList based on taskIndex and marks it as done.
     * @param taskIndex Index of task to be marked
     */
    public String markTask(int taskIndex) {
        try {
            String display = "";
            Task currentTask = taskList.get(taskIndex);
            currentTask.setDone();
            display += Ui.displayMessage(DONE_MESSAGE);
            display += Ui.indentTaskDisplay(currentTask);
            Storage.save(taskList);
            return display;
        } catch (IndexOutOfBoundsException e) {
            return e + "\n" + Ui.displayBoundaryWarning(taskList.size(), taskIndex);
        }
    }

    /**
     * Marks tasklist as specified by taskIndex as undone.
     * @param taskIndex
     */

    public String unmarkTask(int taskIndex) {
        try {
            String display = "";
            Task currentTask = taskList.get(taskIndex);
            currentTask.removeDone();
            display += Ui.displayMessage(UNDONE_MESSAGE);
            display += Ui.indentTaskDisplay(currentTask);
            Storage.save(taskList);
            return display;
        } catch (IndexOutOfBoundsException e) {
            return e + "\n" + Ui.displayBoundaryWarning(taskList.size(), taskIndex);
        }
    }

    /**
     * Loops through the taskList
     * And signifies to the UI to display it nicely
     */
    public String list() {
        String display = "";
        display += Ui.displayMessage(LIST_HEADER);
        display += Ui.displayOrderedList(taskList);
        return display;
    }

    /**
     * searches the taskList based on taskIndex
     * and deletes it
     * @param taskIndex index of task to be deleted
     */

    public String deleteTask(int taskIndex) {

        try {
            String display = "";
            Task deletedTask = taskList.get(taskIndex);
            taskList.remove(taskIndex);
            display += Ui.displayMessage(DELETE_HEADER);
            display += Ui.indentTaskDisplay(deletedTask);
            display += Ui.displayTasksLeft(taskList.size());
            Storage.save(taskList);
            return display;
        } catch (IndexOutOfBoundsException e) {
            return e + "\n" + Ui.displayBoundaryWarning(taskList.size(), taskIndex);
        }
    }

    /**
     * Adds a new task to the taskList
     * @param t task to be added
     */

    public String addTask(Task t) {
        String display = "";
        taskList.add(t);
        Storage.save(taskList);
        display += Ui.displayMessage(ADD_HEADER);
        display += Ui.indentTaskDisplay(t);
        display += Ui.displayTasksLeft(taskList.size());
        return display;
    }

    /**
     * Populates a list of tasks that contain the keyword in it's description.
     * And signposts the UI to display it nicely.
     * @param keyword that will be used to match the descriptions in the String.
     */
    public String findTask(String keyword) {
        String display = "";
        //Loop through current tasks to find tasks
        //Containing the keyword in it's description
        List<Task> tasksContainingKeyword = new ArrayList<>();
        for (int i = 0; i < taskList.size(); i++) {
            Task t = taskList.get(i);
            if (t.getDescription().contains(keyword)) {
                tasksContainingKeyword.add(t);
            }
        }
        display += Ui.displayMessage(FIND_HEADER);
        display += Ui.displayOrderedList(tasksContainingKeyword);
        return display;
    }

    /**
     * Based on a given date that is unparsed, check each individual task if
     * it clashes with the date. If it is, add it into a list
     * @param date
     * @return A formatted list of tasks that occur on the same day
     */
    public String viewSchedule(String date) {
        String display = "";
        List<Task> sameDayTasks = new ArrayList<>();
        for (Task t: taskList) {
            if (t.compareDate(date)) {
                sameDayTasks.add(t);
            }
        }

        display += Ui.displayMessage(VIEW_SCHEDULE_HEADER + date);
        display += Ui.displayOrderedList(sameDayTasks);
        return display;
    }


}
