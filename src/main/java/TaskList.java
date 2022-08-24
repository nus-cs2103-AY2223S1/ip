import java.util.ArrayList;

public class TaskList {
//delete errors?, 1 indexed
    private ArrayList<Task> list;

    public TaskList() {
        list = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        list = tasks;
    }

    public int size() {
        return list.size();
    }

    public void add(Task task) {
        list.add(task);
    }

    public Task deleteTask(int index) throws DukeException {
        // add exception handling here
        try {
            return list.remove(index - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Delete task list failed, check index boundary");
        }
    }

    public Task get(int index) throws DukeException {
        try {
            return list.get(index - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Get task list failed, check index boundary");
        }

    }

    public void mark(int index) throws DukeException {
        try {
            list.get(index - 1).finished();
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("mark failed");
        }
    }

    public void unmark(int index) throws DukeException {
        try {
            list.get(index - 1).notFinished();
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("unmark failed");
        }
    }
}
