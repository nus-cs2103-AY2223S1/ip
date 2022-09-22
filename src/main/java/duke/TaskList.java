package duke;

import java.util.LinkedList;

/**
 * The TaskList class encapsulates a list of Tasks as well as relevant operations to manage this list.
 */

class TaskList extends LinkedList {
    private LinkedList<Task> tasks = new LinkedList<>();

    public String printMatchingTasks(String termTofind) {
        String str = "The following matching tasks are found:\n";

        for (int i = 0; i < this.tasks.size(); i++) {
            if (this.tasks.get(i).getDescription().contains(termTofind)) {
                str = str.concat(String.format("%d.%s\n",
                        i + 1,
                        this.tasks.get(i).printTask()));
            }
        }

        return str;
    }

    /**
     * Retrieves the size of the TaskList.
     *
     * @return size of the TaskList.
     */
    @Override
    public int size() {
        return tasks.size();
    }

    /**
     * Adds a Task to the TaskList.
     *
     * @param task a Task to be added.
     * @return true if the operation is successful.
     */
    public boolean add(Task task) {
        return this.tasks.add(task);
    }

    /**
     * Prints all the Tasks in the TaskList.
     */
    public String listAllTasks() {
        String str = "";

        for (int i = 0; i < this.tasks.size(); i++) {
            str = str.concat(String.format("%d.%s\n",
                    i + 1,
                    tasks.get(i).printTask()));
        }

        return str;
    }

    @Override
    public Task getLast() {
        return this.tasks.getLast();
    }

    @Override
    public Task get(int index) {
        return this.tasks.get(index);
    }

    /**
     * Deletes a Task of a given index from the TaskList.
     *
     * @param index the index of Task to delete.
     */
    public String deleteTask(int index) {
        String str = "";

        Task taskToDelete = this.tasks.get(index - 1);
        this.tasks.remove(taskToDelete);
        str = String.format("Task %d [%s] removed.",
                index,
                taskToDelete.getDescription());

        return str;
    }
}
