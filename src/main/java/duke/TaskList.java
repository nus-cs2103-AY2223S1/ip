package duke;

import java.util.List;
import java.util.ArrayList;

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
    private List<Task> taskList;

    public TaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * searches the taskList based on taskIndex
     * and marks it as done
     * @param taskIndex
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
            return e + "\n" + Ui.outOfBoundsMessage(taskList.size(), taskIndex);
        }
    }

    /**
     * searches the taskList based on taskIndex
     * and marks it as undone
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
            return e + "\n" + Ui.outOfBoundsMessage(taskList.size(), taskIndex);
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
     * @param taskIndex
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
            return e + "\n" + Ui.outOfBoundsMessage(taskList.size(), taskIndex);
        }
    }

    /**
     * Adds a new task to the taskList
     * @param t of type Task
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
     * Populates a list of tasks that contain the keyword in it's description
     * And signposts the UI to display it nicely
     * @param keyword
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


}
