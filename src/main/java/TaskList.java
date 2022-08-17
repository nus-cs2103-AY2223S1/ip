import java.util.ArrayList;

public class TaskList {
    private ArrayList<String> taskList;

    /**
     * Constructor for TaskList, which stores text entered by user in Duke
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Function to add text to taskList
     * @param text text to be added to taskList
     */
    public void addToTaskList(String text) {
        this.taskList.add(text);
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
     * @param index
     * @return text in taskList at specified index
     */
    public String getText(int index) {
        return this.taskList.get(index);
    }

}
