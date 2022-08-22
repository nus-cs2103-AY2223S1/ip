import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList;

    private static final String outOfBoundsMessage = "The entered task index is out of bounds";

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public int getSize() {
        return this.taskList.size();
    }

    public Task add(Task task) {
        this.taskList.add(task);
        return task;
    }

    public Task delete(int idx) throws DukeException {
        try {
            return this.taskList.remove(idx - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(outOfBoundsMessage);
        }
    }

    public Task mark(int idx) throws DukeException {
        try {
            Task task = this.taskList.get(idx - 1);
            task.mark();
            return task;
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(outOfBoundsMessage);
        }
    }

    public Task unmark(int idx) throws DukeException {
        try {
            Task task = this.taskList.get(idx - 1);
            task.unmark();
            return task;
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(outOfBoundsMessage);
        }
    }

    public Task get(int idx) throws DukeException {
        try {
            return this.taskList.get(idx);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(outOfBoundsMessage);
        }
    }
}
