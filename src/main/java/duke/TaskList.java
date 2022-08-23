package duke;

import java.util.List;

public class TaskList {
    private List<Task> list;
    private Ui ui;
    private static int count = 0;

    public TaskList(List<Task> list) {
        this.list = list;
        this.ui = new Ui();
        count += list.size();
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

    public List<Task> getList() {
        return list;
    }

    public void add(Task task) {
        this.ui.showTaskAdded(task);
        this.list.add(task);
        count++;
        printTaskCount();
    }

    public void delete(int index) {
        Task task = this.list.get(index - 1);
        this.ui.showTaskDeleted(task);
        this.list.remove(index - 1);
        count--;
        printTaskCount();
    }

    public void printTaskCount() {
        this.ui.showTaskCount(count);
    }

    @Override
    public String toString() {
        if (count == 0) {
            return "\tList is empty!";
        } else {
            String result = "\tHere are the tasks in your list:";
            for (int i = 0; i < count; i++) {
                result = result + "\n\t\t" + (i + 1) + "." + this.list.get(i);
            }
            return result;
        }
    }
}
