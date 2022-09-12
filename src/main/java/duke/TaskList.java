package duke;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents a TaskList. A <code>TaskList</code> is an <code>ArrayList</code> that contains <code>Tasks</code>.
 */
public class TaskList {

    private ArrayList<Task> taskList;

    /**
     * Initialises TaskList object. Creates a new ArrayList.
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Returns size of TaskList.
     * @return size of TaskList.
     */
    public int size() {
        return taskList.size();
    }

    /**
     * Add task into TaskList.
     * @param task Task to be added into TaskList.
     */
    public void add(Task task) {
        taskList.add(task);
    }

    /**
     * Delete task from TaskList.
     * @param i index (from Ui) of task to mark as done
     * @return Deletes task of index i - 1 from TaskList.
     */
    public Task delete(int i) {
        Task toDelete = taskList.remove(i - 1);
        return toDelete;
    }

    /**
     * Mark task from TaskList as done.
     * @param i index (from Ui) of task to mark as done
     * @return Marks task of index i - 1 as done.
     */
    public Task mark(int i) {
        Task toMark = taskList.get(i - 1);
        toMark.markAsDone();
        return toMark;
    }

    /**
     * Mark task from TaskList as undone.
     * @param i index (from Ui) of task to mark as undone
     * @return Marks task of index i - 1 as undone.
     */
    public Task unmark(int i) {
        Task toUnmark = taskList.get(i - 1);
        toUnmark.markAsUndone();
        return toUnmark;
    }

    /**
     * Returns task from TaskList.
     * @param i index of task in TaskList.
     * @return Task i of TaskList.
     */
    public Task get(int i) {
        return taskList.get(i);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (taskList.size() == 0) {
            String str = ("There are currently no tasks in the list.");
            sb.append(str);
        } else {
            String str = ("Here are the tasks in your list: \n");
            sb.append(str);
            for (int i = 0; i < this.size(); i++) {
                str = ("\t" + (i+1) + ".\t " + this.get(i).toString());
                sb.append(str);
                if (i != this.size() - 1) {
                    sb.append("\n");
                }
            }
        }
        return sb.toString();

    }

    public List<Task> find(String toFind) {
        return taskList.stream().filter(t -> t.toString().contains(toFind)).collect(Collectors.toList());
    }
}
