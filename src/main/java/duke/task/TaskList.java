package duke.task;

import java.util.ArrayList;

import duke.helper.Ui;

public class TaskList {
    /**
     * Class to store the list of Tasks
     */
    private ArrayList<Task> taskList;

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Method to add a task into the taskList
     *
     * @param task the task to be added
     */
    public void add(Task task) {
        taskList.add(task);
    }

    /**
     * Method to delete a task into the taskList based on its index
     *
     * @param index the index of the task to be deleted
     */
    public void delete(int index) {
        taskList.remove(index);
    }

<<<<<<< HEAD
    /**
     * Method to get the task given its index
     *
     * @param index the index of the task to be found
     * @return the task in the index given
     */
    public Task getTask(int index) { return taskList.get(index); }

    /**
     * Method to get the size of the taskList
     *
     * @return the size of the taskList
     */
    public int getSize() { return taskList.size(); }
=======
    public Task getTask(int index) {
        return taskList.get(index);
    }

    public int getSize() {
        return taskList.size();
    }
>>>>>>> branch-A-CodingStandard

    /**
     * Method to clear the entire taskList
     */
    public void clear() {
        taskList.clear();
        Ui.clear();
    }

    /**
     * Method to print all the tasks currently in the taskList
     */
    public void printTasks() {
        int index = 0;
        Task item;
        while (index < taskList.size()) {
            item = taskList.get(index);
            System.out.println((index + 1) + "." + item.toString());
            index++;
        }
    }

    /**
     * Method to get all the tasks in the format of their respective getInfo()
     *
     * @return a String of all the tasks in the taskList
     */
    public String getTasks() {
        int index = 0;
        Task item;
        String result = "";
        while (index < taskList.size()) {
            item = taskList.get(index);
            result += (item.getInfo() + "\n");
            index++;
        }
        return result;
    }

    /**
     * Method to mark the task at the index given
     *
     * @param index the index of the task to be marked
     */
    public void mark(int index) {
        taskList.get(index).markAsDone();
        Ui.mark();
    }

    /**
     * Method to unmark the task at the index given
     *
     * @param index the index of the task to be unmarked
     */
    public void unmark(int index) {
        taskList.get(index).markAsUndone();
        Ui.unmark();
    }
}
