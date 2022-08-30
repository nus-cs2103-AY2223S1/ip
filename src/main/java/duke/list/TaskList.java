package duke.list;

import java.util.ArrayList;

import duke.task.Task;

/**
 * Represents a list of tasks.
 *
 * @author WR3nd3
 */
public class TaskList {

    private ArrayList<Task> list = new ArrayList<>();

    /**
     * Returns number of tasks left in the list.
     *
     * @return Integer representing number of tasks left in the list.
     */
    public int tasksLeft() {
        return list.size();
    }

    /**
     * Returns whether there is a task in the given list position.
     *
     * @param position Integer representation of a task in the list.
     * @return boolean representing whether it is a valid position.
     */
    public boolean isValidPosition(int position) {
        return !(position <= 0 || position > tasksLeft());
    }

    /**
     * Adds a task to the list.
     *
     * @param task task.Task to be added to the list.
     */
    public void addTask(Task task) {
        list.add(task);
    }

    /**
     * Returns task in the given rank in the list.
     *
     * @param rank Integer representation of a task in the list.
     * @return task.Task of the given rank.
     */
    public Task retrieveRank(int rank) {
        return list.get(rank - 1);
    }

    /**
     * Marks the task represented by the rank in the list as complete.
     *
     * @param rank Integer indicating the position of the task in the list.
     */
    public void mark(Integer rank) {
        retrieveRank(rank).markAsDone();
    }

    /**
     * Marks the task represented by the rank in the list as incomplete.
     *
     * @param rank Integer indicating the position of the task in the list.
     */
    public void unmark(Integer rank) {
        retrieveRank(rank).markAsNotDone();
    }

    /**
     * Deletes the task represented by the rank in the list from the list.
     *
     * @param rank Integer indicating the position of the task in the list.
     */
    public void delete(Integer rank) {
        list.remove(list.get(rank - 1));
    }

    /**
     * Returns collection of task descriptions as a String array.
     *
     * @return String array of the descriptions of tasks in the list.
     */
    public String[] giveList() {
        String[] list = new String[tasksLeft()];
        for (int i = 0; i < tasksLeft(); i++) {
            Task t = this.list.get(i);
            list[i] = i + 1 + ". " + t;
        }
        return list;
    }

    /**
     * Returns collection of task descriptions of matching tasks as a String array.
     *
     * @param content String of content to match within task descriptions.
     * @return String array of the descriptions of the matching tasks in the list.
     */
    public String[] giveFindList(String content) {
        String[] list = new String[tasksLeft()];
        int count = 0;
        for (int i = 0; i < tasksLeft(); i++) {
            Task t = this.list.get(i);
            if (t.hasContent(content)) {
                list[count] = count + 1 + ". " + t;
                count++;
            }
        }
        return list;
    }


}
