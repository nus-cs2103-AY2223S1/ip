import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> lst;

    public TaskList() {
        this.lst = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> lst) {
        this.lst = lst;
    }

    public ArrayList<Task> getTasks() {
        return lst;
    }

    public Task getTask(int i) {
        return lst.get(i);
    }

    public int getSize() {
        return lst.size();
    }

    public void addTask(Task task) {
        lst.add(task);
    }

    public void markTask(int index) throws DukeException {
        int size = lst.size();
        if (index >= size || index < 0) {
            throw new DukeException("OOPS! The index of the task does not exists.");
        } else {
            Task taskToBeMarked = lst.get(index);
            taskToBeMarked.markAsDone();
        }
    }

    public void unmarkTask(int index) throws DukeException {
        int size = lst.size();
        if (index >= size || index < 0) {
            throw new DukeException("OOPS! The index of the task does not exists.");
        } else {
            Task taskToBeUnmarked = lst.get(index);
            taskToBeUnmarked.markAsUnDone();
        }
    }

    /**
     * Deletes the task specified by the index.
     *
     * @param index An int representing the index of task to be deleted.
     * @throws DukeException
     */
    public Task deleteTask(int index) throws DukeException {
        int size = lst.size();
        if (index >= size || index < 0) {
            throw new DukeException("OOPS! The index of the task does not exists.");
        } else {
            Task taskToBeDeleted = lst.get(index);
            lst.remove(index);
            return taskToBeDeleted;
        }
    }
}