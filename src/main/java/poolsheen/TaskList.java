package poolsheen;

import java.util.ArrayList;

import poolsheen.task.Task;

/**
 * Represents a collection of all the tasks that Poolsheen remembers.
 */
public class TaskList {
    ArrayList<Task> arl;

    /**
     * Public constructor to initialise an already filled TaskList.
     *
     * @param arl The ArrayList of tasks to fill up the TaskList.
     */
    public TaskList(ArrayList<Task> arl) {
        this.arl = arl;
    }

    /**
     * Public constructor to initialise an empty TaskList.
     */
    public TaskList() {
        this.arl = new ArrayList<>(100);
    }

    public boolean isEmpty() {
        return arl.isEmpty();
    }

    public int getSize() {
        return arl.size();
    }

    public Task get(int index) {
        return arl.get(index);
    }

    public void add(Task t) {
        arl.add(t);
    }

    /**
     * Marks a task as done assuming the user input is correct.
     *
     * @param pos The index position of the task in the list.
     */
    public void mark(int pos) {
        Task selectedTask = this.arl.get(pos - 1);
        selectedTask.markAsDone();
    }

    /**
     * Marks a task as not done assuming the user input is correct.
     *
     * @param pos The index position of the task in the list.
     */
    public void unmark(int pos) {
        Task selectedTask = this.arl.get(pos - 1);
        selectedTask.markAsNotDone();
    }

    /**
     * Removes a task from the list of tasks that Poolsheen remembers.
     *
     * @param pos The index+1 position of the task that is to be deleted.
     */
    public void deleteTask(int pos) {
        Task t = this.arl.get(pos - 1);
        this.arl.remove(pos - 1);
    }

    /**
     * Returns an Object array of all the tasks in the ArrayList.
     *
     * @return An object array.
     */
    public Object[] toArray() {
        return this.arl.toArray();
    }

    /**
     * Returns an ArrayList of tasks that matches the keyword given.
     *
     * @param keyword The keyword that is used to match the description of all tasks.
     * @return An ArrayList of tasks whose descriptions match the keyword.
     */
    public TaskList find(String keyword) {
        ArrayList<Task> finalArl = new ArrayList<>(100);
        for (Task t : this.arl) {
            for (String word : t.description.split(" ")) {
                if (keyword.toUpperCase().equals(word.toUpperCase())) {
                    finalArl.add(t);
                }
            }
        }
        return new TaskList(finalArl);
    }
}
