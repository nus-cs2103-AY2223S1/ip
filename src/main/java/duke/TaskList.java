package duke;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a list of tasks. A Tasklist object stores information about the tasks in the list.
 */
public class TaskList {
    private List<Task> list;
    private Ui ui;
    private int count;

    /**
     * Creates new TaskList object.
     *
     * @param list List containing all Task objects.
     */
    public TaskList(List<Task> list) {
        this.list = list;
        this.ui = new Ui();
        this.count = list.size();
    }

    /**
     * Marks task at particular index as done.
     *
     * @param index Index of task to be marked.
     * @return String representing task marked.
     */
    public String mark(int index) {
        assert index > 0 : "Index should be positive";
        Task task = this.list.get(index - 1);
        task.setDone(true);
        return this.ui.showTaskMarked(task);
    }

    /**
     * Marks task at particular index as undone.
     *
     * @param index Index of task to be unmarked.
     * @return String representing task unmarked.
     */
    public String unmark(int index) {
        assert index > 0 : "Index should be positive";
        Task task = this.list.get(index - 1);
        task.setDone(false);
        return this.ui.showTaskUnmarked(task);
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

    /**
     * Tags task at particular index based on user input.
     *
     * @param index Index of task to be tagged.
     * @param tag Name of tag.
     * @return String representing tag added to task.
     */
    public String tag(int index, String tag) {
        Task task = this.list.get(index - 1);
        task.setTag(tag);
        return this.ui.showTagAdded(task);
    }

    /**
     * Untags task at particular index.
     *
     * @param index Index of task to be untagged.
     * @return String representing tag of task deleted.
     */
    public String untag(int index) {
        Task task = this.list.get(index - 1);
        task.setTag(null);
        return this.ui.showTagDeleted(task);
    }

    /**
     * Adds a task to the end of the TaskList.
     *
     * @param task Task to be added.
     * @return String representing task added.
     */
    public String add(Task task) {
        this.list.add(task);
        this.count++;
        return this.ui.showTaskAdded(task) + "\n" + this.ui.showTaskCount(this.count);
    }

    /**
     * Deletes task at a particular index of TaskList.
     *
     * @param index Index of task to be deleted.
     * @return String representing task deleted.
     */
    public String delete(int index) {
        assert index > 0 : "Index should be positive";
        Task task = this.get(index - 1);
        this.list.remove(index - 1);
        this.count--;
        return this.ui.showTaskDeleted(task) + "\n" + this.ui.showTaskCount(this.count);
    }

    /**
     * Returns list of all tasks containing a certain string.
     *
     * @param keyword Keyword searched by user.
     * @return TaskList of all tasks with keyword.
     */
    public TaskList findTasks(String keyword) {
        List<Task> list = new ArrayList<Task>();
        for (int i = 0; i < this.count; i++) {
            if (this.get(i).containsKeyword(keyword)) {
                list.add(this.get(i));
            }
        }
        return new TaskList(list);
    }

    /**
     * Returns String representation of TaskList object.
     *
     * @return String representation.
     */
    @Override
    public String toString() {
        assert this.count >= 0 : "Task count should not be negative";
        if (this.count == 0) {
            return "List is empty!";
        }
        String result = "Here are the tasks in your list:";
        for (int i = 0; i < this.count; i++) {
            result = result + "\n\t" + (i + 1) + "." + this.get(i);
        }
        return result;
    }
}
