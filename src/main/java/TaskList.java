import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList;

    /**
     * Constructor for TaskList, which stores text entered by user in Duke
     */
    public TaskList() {
        this.taskList = new ArrayList<Task>();
    }

    /**
     * Function to add text to taskList
     * @param task task to be added to taskList
     */
    public void addToTaskList(Task task) {
        this.taskList.add(task);
    }

    /**
     * Function to return size of taskList
     * @return size of taskList
     */
    public int getSize() {
        return this.taskList.size();
    }

    /**
     * Function to get text in taskList at specified index
     * @param index index of task in taskList
     * @return text in taskList at specified index
     */
    public Task getTask(int index) {
        return this.taskList.get(index);
    }

}
