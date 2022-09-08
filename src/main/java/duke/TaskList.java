package duke;

import duke.Task;

import java.util.ArrayList;

public class TaskList {

    int count = 0;
    private ArrayList<Task> arr = new ArrayList<>();


    //constructor
    public TaskList() {

    }

    /**
     * to add items into TaskList
     *
     * @param Task s
     */
    public void add(Task s) {
        arr.add(s);
    }

    /**
     * Getter function to get the element in arr
     *
     * @param index to select which element to get
     * @return object of type Task the element itself
     */
    public Task get(int index) {
        return arr.get(index);
    }

    /**
     * To remove elements from the array
     *
     * @param index to select which element to remove
     * @return object of type Task the element removed
     */
    public Task remove(int index) {
        return arr.remove(index);
    }


    /**
     * To get the arr for processing
     *
     * @return object of type ArrayList<Task>
     */
    public ArrayList<Task> getArr() {
        return arr;
    }

    /**
     * To obtain the current length of the array
     *
     * @return integer value showing length of array
     */
    public int length() {
        return arr.size();
    }
}
