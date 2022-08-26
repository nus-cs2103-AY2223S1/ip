package duke;

import java.util.ArrayList;

/**
 * Represents a task list.
 */
public class TaskList {

    private ArrayList<Task> list;

    /**
     * Constructs a task list.
     */
    public TaskList() {
        this.list = new ArrayList<>();
    }

    /**
     * Gets the task list.
     * @return the task list.
     */
    public ArrayList<Task> getList() {
        return this.list;
    }

    /**
     * Adds task into the list.
     * @param task the task to be added.
     */
    public void add(Task task) {
        this.getList().add(task);
    }

    /**
     * Gets the task from the list given its index.
     * @param index the index in the list of a task that needs to be returned.
     * @return
     */
    public Task get(int index) {
        return this.getList().get(index);
    }

    /**
     * Deletes a task from the task list.
     * @param list the task list.
     * @param task the task to be deleted.
     * @param index the index of the to-be-deleted task.
     */
    public void delete(TaskList list, Task task, int index) {
        list.getList().remove(index);
        System.out.println(Ui.DELETE_HEADER + task.toString()
                + "Now you have " + Duke.count + " tasks in the list." + "\n" + Duke.LINE);
    }

    /**
     * Lists out all the task in the task list.
     */
    public void list() {
        System.out.println(Ui.TASK_LIST_HEADER);
        for (int i = 0, j = 1; i < Duke.count; i++, j++) {
            System.out.print(j + ". ");
            list.get(i).list();
        }
        System.out.println(Duke.LINE + "\n");
    }

    /**
     * Marks a task as done.
     * @param list the task list.
     * @param index the index of the task to be marked.
     */
    public void mark(TaskList list, int index) {
        System.out.println(Ui.MARK_HEADER + "[X] " + list.getList().get(index-1).description());
        list.getList().get(index-1).setStatus("[X]");
    }

    /**
     * Marks a task as undone.
     * @param list the task list.
     * @param index the index of the task to be unmarked.
     */
    public void unmark(TaskList list, int index) {
        System.out.println(Ui.UNMARK_HEADER + "[ ] " + list.getList().get(index-1).description());
        list.getList().get(index-1).setStatus("[ ]");
    }

    /**
     * Finds tasks in the task list that matches the keyword.
     * @param list the task list.
     * @param keyword the keyword to be mapped.
     */
    public void find(TaskList list, String keyword) {
        System.out.println(Ui.FIND_HEADER);
        Task task = null;
        int find = 1;
        for (int i = 0; i < Duke.count; i++) {
            task = list.get(i);
            String finding = task.getName();
            if (finding.contains(keyword)) {
                System.out.println(find++ +"." + task.toString());
            }
        }
        System.out.println(Duke.LINE);
    }
}
