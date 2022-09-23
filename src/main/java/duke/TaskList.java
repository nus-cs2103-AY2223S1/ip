package duke;

import java.util.ArrayList;

/*
Contains list of tasks and operations to edit them
*/
public class TaskList {
    ArrayList<Task> taskList;

    public TaskList(ArrayList<Task> al) {
        this.taskList = al;
    }

    public TaskList() {
        this.taskList = new ArrayList<Task>();
    }

    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    /**
     * Returns the size of the existing task list.
     *
     * @return The size of task list.
     */
    public int getTaskListSize() {
        return this.taskList.size();
    }

    /**
     * Marks a task as done if it is present.
     *
     * @param task Name of the task to be marked as done.
     * @return The success or unsuccessful message to be shown.
     */
    public boolean markDone(String task) {
        for (int i = 0; i < this.taskList.size(); i++) {
            if (this.taskList.get(i).getTask().equals(task)) {
                this.taskList.get(i).markDone();
                return true;
            }
        }
        return false;
    }

    /**
     * Marks a task as undone if it is present.
     *
     * @param task Name of the task to be marked as undone.
     * @return The success or unsuccessful message to be shown.
     */
    public boolean unmarkDone(String task) {
        for (int i = 0; i < this.taskList.size(); i++) {
            if (this.taskList.get(i).getTask().equals(task)) {
                this.taskList.get(i).unmarkDone();
                return true;
            }
        }
        return false;
    }

    /**
     * Deletes a task if it is present.
     *
     * @param userInput Index number of the task to be removed from list.
     * @throws DukeException If task does not exist.
     */
    public Task deleteTask(String userInput) throws DukeException {
        int index;
        try {
            index = Integer.parseInt(userInput.trim());
        } catch (NumberFormatException e) {
            throw new DukeException("please enter the index of the task to delete!");
        }
        if (index <= 0 || index > this.taskList.size()) {
            throw new DukeException("sowwie this item is not found. enter a valid index number from list please!");
        }
        Task taskRemoved = this.taskList.get(index - 1);
        this.taskList.remove(index - 1);
        return taskRemoved;
    }

    /**
     * Adds a task to the task list.
     *
     * @param task Complete user input.
     */
    public void addTask(Task task) {
        this.taskList.add(task);
    }

    /**
     * Finds tasks that contain the given keyword.
     *
     * @param keyword Keyword used to search for tasks.
     * @return The tasks that contain given keyword.
     */
    public ArrayList<Task> find(String keyword) {
        ArrayList<Task> foundTasks = new ArrayList<>();
        for (int i = 0; i < taskList.size(); i++) {
            if (taskList.get(i).getTask().contains(keyword)) {
                foundTasks.add(taskList.get(i));
            }
        }
        return foundTasks;
    }

    /**
     * Updates the date of a specific task.
     *
     * @param taskName Name of the task to be updated.
     * @param newDate Date to be changed into.
     * @return The updated task.
     */
    public Task updateDate(String taskName, String newDate) throws DukeException {
        for (int i = 0; i < taskList.size(); i++) {
            if (taskList.get(i).getTask().contains(taskName)) {
                taskList.get(i).updateDate(newDate);
                return taskList.get(i);
            }
        }
        return null;
    }

}
