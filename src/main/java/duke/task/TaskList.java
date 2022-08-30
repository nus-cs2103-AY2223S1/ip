package duke.task;

import java.util.ArrayList;

import duke.helper.Ui;

/**
 * Class to store the list of Tasks
 */
public class TaskList {
    private ArrayList<Task> taskList;

    /**
     * Constructor of the TaskList class given a taskList
     *
     * @param taskList taskList to be loaded
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Constructor of the TaskList class
     */
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

    /**
     * Method to get the task given its index
     *
     * @param index the index of the task to be found
     * @return the task in the index given
     */
    public Task getTask(int index) {
        return taskList.get(index);
    }

    /**
     * Method to get the size of the taskList
     *
     * @return the size of the taskList
     */
    public int getSize() {
        return taskList.size();
    }

    /**
     * Method to clear the entire taskList
     */
    public String clear() {
        taskList.clear();
        return Ui.clear();
    }

    /**
     * Method to print all the tasks currently in the taskList
     */
    public String printTasks() {
        int index = 0;
        Task item;
        String tasks = "";
        while (index < taskList.size()) {
            item = taskList.get(index);
            tasks += (index + 1) + "." + item.toString() + "\n";
            index++;
        }
        return tasks;
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
    public String mark(int index) {
        taskList.get(index).markAsDone();
        return Ui.mark();
    }

    /**
     * Method to unmark the task at the index given
     *
     * @param index the index of the task to be unmarked
     */
    public String unmark(int index) {
        taskList.get(index).markAsUndone();
        return Ui.unmark();
    }

    /**
     * Method to find and print tasks based on keywords
     *
     * @param keywords the string of keywords to be searched
     */
    public String find(String keywords) {
        String[] keywordArr = keywords.split(" ");
        int arrLen = keywordArr.length;
        int pointer;
        TaskList result = new TaskList();

        for (Task task : taskList) {
            pointer = 0;

            while (pointer < arrLen) {
                if (!task.getDescription().contains(keywordArr[pointer])) {
                    break;
                }
                pointer++;
            }

            if (pointer == arrLen) {
                result.add(task);
            }
        }

        if (result.getSize() == 0) {
            return Ui.noTaskFound();
        } else {
            return Ui.taskFound() + "\n"
                    + result.printTasks();
        }
    }
}
