package duke.task;

import java.util.ArrayList;

import duke.helper.Ui;

public class TaskList {
    /**
     * Class to store the list of Tasks
     */
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

    /**
     * Method to find and print tasks based on keywords
     *
     * @param keywords the string of keywords to be searched
     */
    public void find(String keywords) {
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
            Ui.noTaskFound();
        } else {
            Ui.taskFound();
            result.printTasks();
        }
    }
}
