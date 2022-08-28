/**
 * This class contains the task list and the operations to add / delete tasks in the list
 */
package Duke;

import Tasks.Task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructor that creates a new task list
     */
    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    /**
     * Constructor that get the updated task list from the loaded file
     *
     * @param load the current tasklist
     */
    public TaskList(ArrayList<Task> load) {
        this.tasks = load;
    }

    /**
     * Adds the task ino the taskList
     *
     * @param t current task that is added into the tasklist
     */
    public void addTask(Task t) {
        tasks.add(t);
    }

    /**
     * Deletes the specified tasks from the taskList
     *
     * @param i index of the current task in the tasklist
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
     * @param i index of the current task in the tasklist
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
     * Returns a list of tasks with the given task name
     *
     * @param taskName string of task
     * @return arraylist of current tasks
     */
    public ArrayList<Task> findTask(String taskName) {
        ArrayList<Task> foundTasks = new ArrayList<Task>();
        for (Task t : tasks) {
            if (t.getTaskName().contains(taskName)) {
                foundTasks.add(t);
            }
        }
        return foundTasks;
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
     * Prints the tasks into the taskList
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
     * Prints the tasks in the given taskList
     */
    public void printList(ArrayList<Task> givenTasks) {
        try {
            if (givenTasks.size() == 0) {
                throw new DukeException(Constants.EMPTY_LIST);
            }
            else {
                System.out.println(Constants.LIST);
                for (int i = 0; i < givenTasks.size(); i++) {
                    System.out.println(String.format("%d.%s", i + 1, givenTasks.get(i).toString()));
                }
            }
        }
        catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Puts all the tasks in taskList into a string for storing into a file
     *
     * @return ArrayList containing all the tasks in Strings
     */
    public ArrayList<String> tasksToString() {
        ArrayList<String> tasksStrings = new ArrayList<String>();
        for (int i = 0; i < tasks.size(); i++) {
            Task t = tasks.get(i);
            String type = t.toString().substring(1,2);
            if (type.equals(Constants.TODO)) {
                tasksStrings.add(String.format("%s > %s > %s\n", type, t.getStatusIcon(), t.getTaskName()));
            } else {
                tasksStrings.add(String.format("%s > %s > %s > %s\n", type, t.getStatusIcon(),
                        t.getTaskName(), t.getDateline()));
            }
        }
        return tasksStrings;
    }
}
