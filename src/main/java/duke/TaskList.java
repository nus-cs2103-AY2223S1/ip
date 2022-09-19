package duke;

import java.util.LinkedList;

/**
 * The TaskList class encapsulates a list of Tasks as well as relevant operations to manage this list.
 */

class TaskList extends LinkedList {
    private LinkedList<Task> tasks = new LinkedList<>();

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
    public void listAllTasks() {
        this.tasks.forEach(
                task -> { System.out.println(String.format("%d.%s",
                        tasks.indexOf(task) + 1,
                        task.printTask()));
                });
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
    public void deleteTask(int index) {
        Task taskToDelete = this.tasks.get(index - 1);
        this.tasks.remove(taskToDelete);
        Ui.echo(String.format("Task %d [%s] removed.",
                index,
                taskToDelete.getDescription()));
    }
}
