package Duke;
import java.util.ArrayList;

/**
* TaskList contains the task list e.g., it has operations to add/delete tasks in the list
*
* @author Linus Chui
*/
public class TaskList {

    /**
     * The ArrayList of Task objects stored in the TaskList instance.
     */
    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Getter method that returns the ArrayList of Task objects currently stored.
     *
     * @return an ArrayList of Task objects that are currently stored in this instance of TaskList.
     */
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Getter method that returns the number of Task objects stored.
     *
     * @return the number of Task objects stored in this instance of TaskList.
     */
    public int getTaskListSize() {
        return this.tasks.size();
    }

    /**
     * Adds a task to the ArrayList of Task objects.
     *
     * @param task the task to be added into the ArrayList of Task objects.
     * @throws DukeException if the task is not an instance of a To-do, Deadline or Event object.
     */
    public void addTask(Task task) throws DukeException {
        if (task instanceof Todo) {
            Todo todo = (Todo) task;
            this.tasks.add(todo);
        } else if (task instanceof Deadline) {
            Deadline deadline = (Deadline) task;
            this.tasks.add(deadline);
        } else if (task instanceof Event) {
            Event event = (Event) task;
            this.tasks.add(event);
        } else {
            throw new DukeException("Invalid task encountered !!");
        }
    }

    /**
     * Builds a string based on the list of tasks contained in the ArrayList
     * of Task objects to be displayed to the user using command "list".
     *
     * @return the details of all tasks previously added by the user which are indexed
     * in chronological order of which the user added to the task list.
     */
    @Override
    public String toString() {
        String list = "";
        for (int i = 0; i < this.tasks.size(); i++) {
            if (i == tasks.size() - 1) {
                list = list + " " + (i + 1) + ": " + tasks.get(i).toString();
                break;
            }
            list = list + " " + (i + 1) + ": " + tasks.get(i).toString() + "\n";
        }
        String listMessage = " Here are the tasks in your list:\n" + list;
        return listMessage;
    }
}
