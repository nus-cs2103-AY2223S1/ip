package duke;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> list;
    private Ui ui;
    private int count;

    public TaskList(List<Task> list) {
        this.list = list;
        this.ui = new Ui();
        this.count = list.size();
    }

    public void mark(int index) {
        Task task = this.list.get(index - 1);
        task.setDone(true);
        this.ui.showTaskMarked(task);
    }

    public void unmark(int index) {
        Task task = this.list.get(index - 1);
        task.setDone(false);
        this.ui.showTaskUnmarked(task);
    }

    public int getCount() {
        return this.count;
    }

    public List<Task> getList() {
        return list;
    }

    public Task get(int index) {
        return this.list.get(index);
    }

    public void add(Task task) {
        this.ui.showTaskAdded(task);
        this.list.add(task);
        this.count++;
        printTaskCount();
    }

    public void delete(int index) {
        Task task = this.get(index - 1);
        this.ui.showTaskDeleted(task);
        this.list.remove(index - 1);
        this.count--;
        printTaskCount();
    }

    public void printTaskCount() {
        this.ui.showTaskCount(this.count);
    }

    public TaskList findTasks(String keyword) {
        List<Task> list = new ArrayList<Task>();
        for (int i = 0; i < this.count; i++) {
            if (this.get(i).containsKeyword(keyword)) {
                list.add(this.get(i));
            }
        }
        return new TaskList(list);
    }

    @Override
    public String toString() {
        if (this.count == 0) {
            return "\tList is empty!";
        } else {
            String result = "\tHere are the tasks in your list:";
            for (int i = 0; i < this.count; i++) {
                result = result + "\n\t\t" + (i + 1) + "." + this.get(i);
            }
            return result;
        }
    }
}
