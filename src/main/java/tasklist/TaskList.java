package tasklist;

import java.util.ArrayList;

import task.Task;
import util.Ui;


public class TaskList {
    private final ArrayList<Task> list = new ArrayList<>();

    public Task addTask(Task taskItem) {
        this.list.add(taskItem);
        return taskItem;
    }

    @Override
    public String toString() {
        String res = Ui.formatLine("Here are the tasks in your list:");
        for (int i = 0; i < this.list.size(); i++) {
            String nextListItem = String.format("%d.%s", i + 1, this.list.get(i));
            res += Ui.formatLine(nextListItem);
        }
        return res;
    }

    public Task markItem(int index) {
        Task taskItem = this.list.get(index - 1);
        taskItem.setIsMarked(true);
        return taskItem;
    }

    public Task unmarkItem(int index) {
        Task taskItem = this.list.get(index - 1);
        taskItem.setIsMarked(false);
        return taskItem;
    }

    public Task deleteItem(int index) {
        Task taskItem = this.list.get(index - 1);
        this.list.remove(index - 1);
        return taskItem;
    }

    public Task getItem(int index) {
        return this.list.get(index - 1);
    }

    public int getTaskCount() {
        return this.list.size();
    }
}
