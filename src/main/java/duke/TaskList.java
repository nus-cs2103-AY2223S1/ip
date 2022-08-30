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
    String showList(){
        Ui ui = new Ui();
       return ui.displayListUi(this.list);
    }

    /**
     * Adds task to list.
     * @param task A task to be added into the list.
     * @return A String that will be outputted to screen when added task to list.
     */
    String addToList(Task task) {
        this.list.add(task);
        Ui ui = new Ui();
        return ui.addToListUi(task, this.list);
    }

    /**
     * Deletes task from list.
     * @param s Name of the task to be deleted from the list.
     * @return A string that would be outputted to the screen when deleting task.
     */
    String delete(String s) {
        int i = Integer.parseInt(s.substring(7)) - 1;
        Task task = this.list.remove(i);
        Ui ui = new Ui();
        return ui.deleteUi(task, this.list);
    }

}