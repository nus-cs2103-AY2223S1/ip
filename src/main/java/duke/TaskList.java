package duke;

import java.util.ArrayList;

/**
 * Class that stores Tasks in ArrayList
 * @author Ashiqur Rahman A0230107Y
 */
public class TaskList {
    protected ArrayList<Task> allTasks;

    /**
     * Constructor for TaskList class
     * @param allTasks All Tasks
     */
    public TaskList(ArrayList<Task> allTasks) {
        this.allTasks = allTasks;
    }

    /**
     * Constructor for TaskList class
     */
    public TaskList() {
        this.allTasks = new ArrayList<Task>();
    }

    /**
     * static method to print tasklist with ordering
     * @param tl Tasklist to be be printed
     * @return String of tasklist
     */
    public static String printTaskList(TaskList tl) {
        String taskRecords = "";
        if (tl.size() == 0) {
            taskRecords = "No items found in TaskList";
        }
        for (int i = 0; i < tl.size(); i++) {
            String taskRecord="";
            if (i == tl.size() - 1) {
                taskRecord = String.format("%d.%s", i + 1, tl.get(i));
            } else {
                taskRecord = String.format("%d.%s\n", i + 1, tl.get(i));
            }
            taskRecords += taskRecord;
        }
        return taskRecords;
    }

    /**
     * Method to return size of taskList
     * @return size of TaskList
     */
    public int size() {
        return this.allTasks.size();
    }

    /**
     * Method to get task
     * @return task of specified index in TaskList
     */
    public Task get(int index) {
        return this.allTasks.get(index);
    }

    /**
     * Method to add task
     */
    public void add(Task t) {
        this.allTasks.add(t);
    }

    /**
     * Method to remove task
     * @return the removed Task
     */
    public Task remove(int index) {
        return this.allTasks.remove(index);
    }

    public TaskList find(String word) {
        ArrayList<Task> newList = new ArrayList<>();
        for (int i = 0; i < this.allTasks.size(); i++) {
            if (this.allTasks.get(i).toString().contains(word)) {
                newList.add(this.allTasks.get(i));
            }
        }
        TaskList newTaskList = new TaskList(newList);
        return newTaskList;
    }
}
