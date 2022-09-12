package task;

import duke.DukeException;

import java.util.ArrayList;

public class TaskList {

    protected ArrayList<Task> taskArray;

    protected int size;

    /**
     * Creates a TaskList.
     * @param arr The ArrayList of stored task to be added.
     */

    public TaskList(ArrayList<Task> arr) {
        this.taskArray = arr;
        this.size = arr.size();
    }

    /**
     * Creates an empty TaskList.
     */

    public TaskList() {
        this.taskArray = new ArrayList<Task>(100);
        this.size = 0;
    }



    public String printContent() throws DukeException {
        String out = "";
        try {
            for (int i = 0; i < taskListSize(); i++) {
                out = out + taskArray.get(i).toString() + "\n";
            }
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("");
        }
        return out;
    }


    /**
     * Gets the number of Tasks.
     * @return The number of Tasks.
     */
    public int taskListSize() {
        return size;
    }


    /**
     * Adds the Task to the ArrayList.
     * @param task The task to be added.
     */
    public String add(Task task) {
        this.taskArray.add(task);
        String out = "----------------------\n" + "Ok Solid you got this work to do:\n" +
                task.toString() + String.format("\nYou have a total of %d work to do", size + 1)
                + "\n----------------------\n";
        size++;
        return out;
    }

    /**
     * Adds the Tasks saved in the txt file.
     * @param task The Task to be added.
     */

    public void addStart(Task task) {
        this.taskArray.add(task);
        size++;
    }

    /**
     * Deletes the Task from the ArrayList.
     * @param index The index of the Task in the ArrayList to be removed.
     */
    public String delete(int index) {
        String removed = taskArray.get(index).toString();
        taskArray.remove(index);
        String output = "----------------------\n" + "Noted, The following task has been removed\n" +
                removed + String.format("\nYou have a total of %d work to do still", size - 1) +
                "\n----------------------\n";
        size--;
        return output;
    }

    /**
     * Gets the ArrayList stored in TaskList.
     * @return The ArrayList of Tasks.
     */

    public ArrayList<Task> getTaskList() {
        return this.taskArray;
    }


}
