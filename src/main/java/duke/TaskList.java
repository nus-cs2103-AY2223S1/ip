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
     */
    void showList(){
        Ui ui = new Ui();
        ui.displayListUi(this.list);
    }

    /**
     * Adds task to list.
     * @param task A task to be added into the list.
     */
    void addToList(Task task) {
        this.list.add(task);
        Ui ui = new Ui();
        ui.addToListUi(task, this.list);
    }

    /**
     * Deletes task from list.
     * @param s Name of the task to be deleted from the list.
     */
     void delete(String s) {
        int i = Integer.parseInt(s.substring(7)) - 1;
        Task task = this.list.remove(i);
         Ui ui = new Ui();
         ui.deleteUi(task, this.list);
    }
}
