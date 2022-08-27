import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> list;

    public TaskList() {
        this.list = new ArrayList<Task>();
    }

    public int size() {
        return this.list.size();
    }

    public Task get(int index) {
        return this.list.get(index);
    }

    public void addTaskWithoutOutput(Task task) {
        list.add(task);
    }

    public void addTask(Task task, Ui ui) {
        list.add(task);
        ui.addResponse(task, this);
    }

    public void deleteTask(int index, Ui ui) {
        ui.deleteResponse(this, index);
        list.remove(index);
    }

    public void mark (int index, Ui ui) throws IllegalIndexException {
        //  error checking
        if (index < 0 || list.get(index) == null) {
            throw new IllegalIndexException("Index invalid!");
        } else {
            list.get(index).setDone();
            ui.markResponse(this, index);
        }
    }

    public void unmark(int index, Ui ui) throws IllegalIndexException {
        //  error checking
        if (index < 0 || list.get(index) == null) {
            throw new IllegalIndexException("Index invalid!");
        } else {
            list.get(index).setNotDone();
            ui.unmarkResponse(this, index);
        }
    }
}
