package tasklist;

import java.util.ArrayList;

import task.Task;
import util.Ui;

/**
 * A list that that handles the storing of tasks
 *
 * @author Bryan Lim Jing Xiang
 */
public class TaskList {
    private final ArrayList<Task> list = new ArrayList<>();

    /**
     * Adds a given task item to the list
     *
     * @param taskItem Task to be added
     * @return The same task that is passed in
     */
    public Task addTask(Task taskItem) {
        if (this.list.contains(taskItem)) {
            return null;
        }
        this.list.add(taskItem);
        return taskItem;
    }

    /**
     * {@inheritDoc}
     *
     * @return A string representation of all tasks within the list
     *     formatted properly to be printed out as output
     */
    @Override
    public String toString() {
        StringBuilder res = new StringBuilder(Ui.formatLine("Here are the tasks in your list:"));
        for (int i = 0; i < this.list.size(); i++) {
            String nextListItem = String.format("%d.%s", i + 1, this.list.get(i));
            res.append(Ui.formatLine(nextListItem));
        }
        return res.toString();
    }

    /**
     * @param index Index of the task to be marked, based on 1-index
     * @return Task that is marked
     */
    public Task markItem(int index) {
        Task taskItem = this.list.get(index - 1);
        taskItem.setIsMarked(true);
        return taskItem;
    }

    /**
     * @param index Index of the task to be unmarked, based on 1-index
     * @return Task that is unmarked
     */
    public Task unmarkItem(int index) {
        Task taskItem = this.list.get(index - 1);
        taskItem.setIsMarked(false);
        return taskItem;
    }

    /**
     * @param index Index of the task to be removed from list, based on 1-index
     * @return Task that is deleted
     */
    public Task deleteItem(int index) {
        Task taskItem = this.list.get(index - 1);
        this.list.remove(index - 1);
        return taskItem;
    }

    /**
     * @param index Index of the task to be retrieved, based on 1-index
     * @return Task that is retrieved
     */
    public Task getItem(int index) {
        return this.list.get(index - 1);
    }

    /**
     * @return Number of tasks within the list currently
     */
    public int getTaskCount() {
        return this.list.size();
    }

    /**
     * @param searchText Text to be searched
     * @return A list of tasks that matches the searchText
     */
    public ArrayList<String> find(String searchText) {
        ArrayList<String> res = new ArrayList<>();

        if (searchText.strip().equals("")) {
            return res;
        }

        for (int i = 0; i < list.size(); i++) {
            Task t = list.get(i);
            if (t.toString().contains(searchText)) {
                String searchedResult = String.format("%d.%s", i + 1, t.toString());
                res.add(searchedResult);
            }
        }
        return res;
    }
}
