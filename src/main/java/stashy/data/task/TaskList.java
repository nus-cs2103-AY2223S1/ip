package stashy.data.task;

import stashy.data.exception.StashyException;
import stashy.parser.Parser;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList;

    /**
     * Constructor method.
     *
     * @param loadResult The load result from Storage
     */
    public TaskList(ArrayList<String> loadResult) {
        this.taskList = new ArrayList<Task>();
        try {
            for (String rawTaskLine : loadResult) {
                this.taskList.add(Parser.parseTask(rawTaskLine));
            }
        } catch (StashyException se) {
            this.taskList = new ArrayList<Task>();
        }
    }

    /**
     * Overloaded constructor method.
     * Only used when loading error happens.
     */
    public TaskList() {
        this.taskList = new ArrayList<Task>();
    }

    /**
     * Getter method of the task list.
     *
     * @return The task list in form of arraylist of tasks
     */
    public ArrayList<Task> getArrayList() {
        return this.taskList;
    }

    /**
     * Works the same way as ArrayList.add.
     *
     * @param task The task to be added
     */
    public void add(Task task) {
        this.taskList.add(task);
    }

    /**
     * Works the same way as ArrayList.get.
     *
     * @param index The index of the element of interest
     * @return The task referenced by its index
     */
    public Task get(int index) {
        return this.taskList.get(index);
    }

    /**
     * Works the same way as ArrayList.remove.
     *
     * @param index The index of the element of interest
     */
    public void remove(int index) {
        this.taskList.remove(index);
    }

    /**
     * Works the same way as ArrayList.size.
     */
    public int size() {
        return this.taskList.size();
    }

    @Override
    public String toString() {
        String message = "";
        for (int i = 1; i <= this.taskList.size(); i++) {
            message += (i + "." + this.taskList.get(i - 1)) + "\n";
        }
        String trimmedMessage = message.substring(0, Math.max(0, message.length() - 1));
        return trimmedMessage.isEmpty() ? "You have no tasks right now." : trimmedMessage;
    }
}
