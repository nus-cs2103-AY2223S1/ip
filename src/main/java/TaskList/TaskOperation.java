package TaskList;

import Ui.Constants;

import java.util.ArrayList;

public class TaskOperation {

    /**
     * Add text that user typed to the word list
     *
     * @param task     text the user typed
     * @param workList
     */
    public static void add(Task task, ArrayList<Task> workList) {
        System.out.println(Constants.ARROW + "Added task: " + task.toString());
        workList.add(task);
        System.out.println("Now you have " + workList.size() + " task(s) on your list.");
    }

    /**
     * Delete a task
     *
     * @param task     text the user typed
     * @param workList
     */
    public static void delete(Task task, ArrayList<Task> workList) {
        System.out.println(Constants.ARROW + "Deleted task: " + task.toString());
        workList.remove(task);
        System.out.println("Now you have " + workList.size() + " task(s) on your list.");
    }

    /**
     * Print all item in the word list
     */
    public static void listItems(ArrayList<Task> workList) {
        System.out.println(Constants.LISTING_MESSAGE);
        for (int i = 0; i < workList.size(); i++) {
            System.out.println((i+1) + ") " + workList.get(i).toString());
        }
    }
}
