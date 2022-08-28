package task;

import duke.DukeException;

import java.util.ArrayList;

public class TaskList {

    protected ArrayList<Task> taskArray;

    protected String list;

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
        ArrayList<Task> task = new ArrayList<Task>(100);
        this.taskArray = task;
        this.size = 0;
    }



    public String printContent() throws DukeException {
        String out = "";
        try {
            for (int i = 0; i < 2; i++) {
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
    public void add(Task task) {
        this.taskArray.add(task);
        System.out.println("----------------------\n" + "Ok Solid you got this work to do:\n" +
                task.toString() + String.format("\nYou have a total of %d work to do", size + 1)
                + "\n----------------------\n");
        size++;
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
    public void delete(int index) {
        String removed = taskArray.get(index).toString();
        taskArray.remove(index);
        System.out.println("----------------------\n" + "Noted, The following task has been removed\n" +
                removed + String.format("\nYou have a total of %d work to do still", size - 1) +
                "\n----------------------\n");
        size--;
    }

    /**
     * Gets the ArrayList stored in TaskList.
     * @return The ArrayList of Tasks.
     */

    public ArrayList<Task> getTaskList() {
        return this.taskArray;
    }

    /**
     * Gets the Tasks of the ArrayList and converts to String format.
     * @return The string of Tasks in the ArrayList.
     */

    public String getList() {
        String out = "";
        for (int i = 0; i < size; i++) {
            out = out + String.format("%d", i + 1) + "." + taskArray.get(i) + "\n";
        }
        this.list = out;
        return this.list;
    }



}
