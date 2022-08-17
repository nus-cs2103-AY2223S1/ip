import java.util.ArrayList;

public class TaskRecords {
    private ArrayList<Task> lst;

    TaskRecords() {
        this.lst = new ArrayList<Task>();
    }

    void addProcess(Task task) {
        if (!this.lst.contains(task)) {
            this.lst.add(task);
        } else {
            this.lst.set(lst.indexOf(task),task);
        }
    }

    Task get(int idx) {
        return this.lst.get(idx);
    }

    ArrayList<Task> getList() {
        return this.lst;
    }
}
