package duke;
import java.util.ArrayList;

/**
 * Represents a list of tasks where it can show the task of the list, add tasks to list,and delete tasks from list.
 */
public class TaskList {
    ArrayList<Task> list;

    /**
     * Creates a TaskList object.
     * @param list ArrayList of tasks.
     */
    TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    /**
     * Displays the tasks in the list.
     * @return A string that would be outputted to the screen when showing list of tasks.
     */
    String showList() {
        Ui ui = new Ui();
        return ui.displayListUi(this.list);
    }

    /**
     * Adds task to list.
     * @param task A task to be added into the list.
     * @return A String that will be outputted to screen when added task to list.
     */
    String addToList(Task task) {
        assert task != null;
        addTaskToList(task,this.list);
        return getOutputShownForAddTask(task, this.list);
    }

    void addTaskToList(Task task, ArrayList<Task> list) {
        list.add(task);
    }

    String getOutputShownForAddTask(Task task, ArrayList<Task> list) {
        Ui ui = new Ui();
        return ui.addToListUi(task,list);
    }

    /**
     * Deletes task from list.
     * @param fullCommand Name of the task to be deleted from the list.
     * @return A string that would be outputted to the screen when deleting task.
     */

    String delete(String fullCommand) {
        Task taskToBeDeleted = deleteTaskFromList(this.list, fullCommand);
        return getOutputShownForDeleteTask(taskToBeDeleted,this.list);
    }

    Task deleteTaskFromList(ArrayList<Task> list, String fullCommand) {
        int taskNumber = getTaskNumberOfTaskToBeDeleted(fullCommand);
        return list.remove(taskNumber);
    }

    int getTaskNumberOfTaskToBeDeleted(String fullCommand) {
        return Integer.parseInt(fullCommand.substring(7)) - 1;
    }

    String getOutputShownForDeleteTask(Task task, ArrayList<Task> list) {
        Ui ui = new Ui();
        return ui.deleteUi(task,list);
    }
}