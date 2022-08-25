package Duke;

import Tasks.Task;

import java.util.ArrayList;

/**
 * This class contains the task list and the operations to add / delete tasks in the list
 */
public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    public TaskList(ArrayList<Task> load) {
        this.tasks = load;
    }

    /**
     * Adds the task to taskList
     *
     * @param t
     */
    public void addTask(Task t) {
        tasks.add(t);
    }

    /**
     * Deletes the specified tasks from the taskList
     *
     * @param i
     * @return Deleted Tasks.Task
     */
    public Task deleteTask(int i) {
        Task removed = tasks.get(i - 1);
        tasks.remove(i - 1);
        return removed;
    }

    /**
     * Marks the specified task
     *
     * @param i
     * @return Marked Tasks.Task
     */
    public Task markTask(int i) {
        Task t = tasks.get(i - 1);
        t.setMarked();
        return t;
    }

    /**
     * Unmarks the specified task
     *
     * @param i
     * @return Unmarked Tasks.Task
     */
    public Task unmarkTask(int i) {
        Task t = tasks.get(i - 1);
        t.setUnmarked();
        return t;
    }

    /**
     * Gets the number of tasks in the taskList
     *
     * @return size of taskList
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Prints the tasks in the taskList
     */
    public void printList() {
        try {
            if (tasks.size() == 0) {
                throw new DukeException(Constants.EMPTY_LIST);
            }
            else {
                System.out.println(Constants.LIST);
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println(String.format("%d.%s", i + 1, tasks.get(i).toString()));
                }
            }
        }
        catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }



    /**
     * Puts all the tasks in taskList into a string
     *
     * @return ArrayList containing all the tasks in Strings
     */
    public ArrayList<String> tasksToString() {
        ArrayList<String> content = new ArrayList<String>();
        for (int i = 0; i < tasks.size(); i++) {
            Task t = tasks.get(i);
            String type = t.toString().substring(1,2);
            if (type.equals(Constants.TODO)) {
                content.add(String.format("%s > %s > %s\n", type, t.getStatusIcon(), t.getTaskName()));
            } else {
                content.add(String.format("%s > %s > %s > %s\n", type, t.getStatusIcon(),
                        t.getTaskName(), t.getDateline()));
            }
        }
        return content;
    }
}
