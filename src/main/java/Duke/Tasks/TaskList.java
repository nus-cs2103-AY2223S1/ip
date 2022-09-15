package Duke.Tasks;

import java.util.List;
import java.util.ArrayList;

/**
 * Class that contains all operations to handle the task list.
 */
public class TaskList {
    protected List<Task> tasks = new ArrayList<>();

    /**
     * Adds a task to the current task list.
     * @param task Task to be added.
     */
    public void addTask(Task task){
        this.tasks.add(task);
    }

    /**
     * Shows the current task list.
     * @return String contains all the tasks' information.
     */
    public String showTasks(){
        String output = "";
        if (tasks.size() >= 1) {
            Task curTask = tasks.get(0);
            output = output + "1." + curTask.toString();
            for(int i = 1; i < tasks.size(); i++) {
                curTask = tasks.get(i);
                output += "\n" + (i + 1) + "." + curTask.toString();
            }
        }
        return output;
    }

    /**
     * Gets the size of current task list.
     * @return Number of tasks in the current task list.
     */
    public int getTotalTaskNumber() {
        return this.tasks.size();
    }

    /**
     * Marks a task as done.
     * @param index Index of the task needed to mark as done.
     * @return Task that has marked as done.
     * @throws IndexOutOfBoundsException If index is out of bounds.
     */
    public Task markAsDone(int index) throws IndexOutOfBoundsException {
        Task doneTask = this.tasks.get(index);
        doneTask.setIsDone(true);               // Modify the task as done, this is a pointer
        return doneTask;
    }

    /**
     * Finds all tasks that contain the keyword.
     * @param keyword String that tasks could contain.
     * @return TaskList that contains all satisfying tasks.
     */
    public TaskList findTask(String keyword) {
        TaskList foundTasks = new TaskList();
        for(Task t : this.tasks) {
            if (t.searchKeyWord(keyword)) {
                foundTasks.addTask(t);
            }
        }
        return foundTasks;
    }

    /**
     * Deletes a task from the task list.
     * @param index Index of task to be deleted.
     * @return The deleted task.
     * @throws IndexOutOfBoundsException If index is out of bounds.
     */
    public Task deleteTask(int index) throws IndexOutOfBoundsException { return this.tasks.remove(index - 1); }

    /**
     * Save all tasks' information.
     * @return String of all tasks' information in formal.
     */
    public String save() {
        String output = "";
        for (Task task : this.tasks) {
            output += task.save();
        }
        return output;
    }

    /**
     * Sorts all tasks based on the emergency order.
     * @return TaskList containing all previous tasks in a new order.
     */
    public String sortAll() {
        this.tasks.sort((t1, t2) -> {
            if (t1.getIsDone() && !t2.getIsDone()) {
                return 1;
            }
            if (!t1.getIsDone() && t2.getIsDone()) {
                return -1;
            }
            if (t1.getDateTime() == null && t2.getDateTime() != null) {
                return 1;
            }
            if (t1.getDateTime() != null && t2.getDateTime() == null) {
                return -1;
            }
            if (t1.getDateTime() != null && t2.getDateTime() != null) {
                return t1.getDateTime().compareTo(t2.getDateTime());
            }
            return t1.getDiscription().compareTo(t2.getDiscription());

        });

        return this.showTasks();
    }

    /**
     * Sorts all deadlines based on the emergency order.
     * @return TaskList containing all previous deadlines in a new order.
     */
    public String sortDeadline() {
        List<Deadline> deadlines = new ArrayList<>();
        for (Task task : this.tasks) {
            if (task instanceof Deadline) { deadlines.add((Deadline) task); }
        }

        deadlines.sort((d1, d2) -> {
            if (d1.getIsDone() && !d2.getIsDone()) {
                return 1;
            }
            if (!d1.getIsDone() && d2.getIsDone()) {
                return -1;
            }
            return d1.getDateTime().compareTo(d2.getDateTime());
        });
        Deadline curDeadline;
        String output = "";
        for(int i = 0; i < deadlines.size(); i++) {
            curDeadline = deadlines.get(i);
            output += (i + 1) + "." + curDeadline.toString();
            if (i < deadlines.size() - 1) {
                output += "\n";
            }
        }
        return output;
    }

    /**
     * Gets a task based on its index.
     * @param index Index of the task to be gotten.
     * @return Task given its index.
     */
    public Task getTaskByIndex(int index) { return this.tasks.get(index); }

    @Override
    public String toString() { return this.tasks.toString(); }


}
