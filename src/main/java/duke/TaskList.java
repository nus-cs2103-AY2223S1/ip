package duke;

import java.util.ArrayList;

/**
 * Contains the task list
 *
 * @author Sean Lam
 */
public class TaskList {
    protected ArrayList<Task> itemList;

    /**
     * Constructor for TaskList.
     *
     * @param itemList Takes in task list loaded from storage or empty task list if no previous history
     */
    public TaskList(ArrayList<Task> itemList) {
        this.itemList = itemList;
    }

    /**
     * Constructor for TaskList.
     */
    public TaskList() {
        this.itemList = new ArrayList<>();
    }

    /**
     * Adds a task to the list without printing a notification.
     *
     *  @param toAdd Task to add
     */
    public void silentAdd(Task toAdd) {
        itemList.add(toAdd);
    }

    /**
     * Checks if task list is empty.
     *
     * @return Is task list empty
     */
    public boolean isEmpty() {
        return itemList.size() == 0;
    }

    /**
     * Adds a task to the list.
     *
     * @param toAdd Task to be added
     */
    public void addTask(Task toAdd) {
        itemList.add(toAdd);
        System.out.println("Got it. I've added this task:");
        System.out.println("\t" + toAdd);
        System.out.println("Now you have " + itemList.size() + " tasks in the list.");
    }

    /**
     * Deletes a specified task from the list.
     *
     * @param stringDex Index of task to be deleted
     * @throws DukeException In the case that the task is not found
     */
    public void deleteTask(String stringDex) throws DukeException {
        int index = Integer.parseInt(stringDex);
        if (index > itemList.size()) {
            throw new DukeException("Item to be deleted not found");
        } else {
            System.out.println("Noted. I've removed this task:");
            System.out.println("\t" + itemList.get(index - 1));
            itemList.remove(index - 1);
            System.out.println("Now you have " + itemList.size() + " tasks in the list.");

        }
    }

    /**
     * String representation of the TaskList.
     *
     * @return string
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < itemList.size(); i++) {
            int index = i + 1;
            String add = index + "." + itemList.get(i) + "\n";
            sb.append(add);
        }
        return sb.toString();
    }

    /**
     * Marks the task as done.
     *
     * @param index Index of task to be marked
     */
    public void markTask(int index) {
        Task marked = itemList.get(index);
        marked.setStatusIcon(true);
        marked.updateStatus();
    }

    /**
     * Marks the task as not done.
     *
     * @param index Index of task to be marked
     */
    public void unmarkTask(int index) {
        Task marked = itemList.get(index);
        marked.setStatusIcon(false);
        marked.updateStatus();
    }

    /**
     * Returns the task list.
     *
     * @return Tasks
     */
    public ArrayList<Task> getItemList() {
        return itemList;
    }

    /**
     * Matches input description with task descriptions in the list.
     *
     * @param keyword Description to match
     */
    public void findTask(String keyword) {
        TaskList matchingTasks = new TaskList();

        for (int i = 0; i < itemList.size(); i++) {
            if (itemList.get(i).getDescription().contains(keyword)) {
                matchingTasks.silentAdd(itemList.get(i));
            }
        }

        if (matchingTasks.isEmpty()) {
            System.out.println("No such task found. Please try another phrase");
        } else {
            System.out.println("Here are the matching tasks in your list:");
            System.out.println(matchingTasks);
        }
    }
}
