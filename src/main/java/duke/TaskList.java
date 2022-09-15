package duke;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a list of tasks given to Duke chatbot.
 *
 * @author Conrad
 */
public class TaskList {

    private List<Task> userTasks;

    public TaskList(List<Task> userTasks) {
        this.userTasks = userTasks;
    }

    public TaskList() {
        this.userTasks = new ArrayList<>();
    }

    /**
     * Adds the given <code>Task</code> to the list.
     *
     * @param tasks Variable number of <code>Task</code>s to be added to the list.
     * @throws DukeException If duplicate tasks are added.
     */
    public void addTasks(Task ...tasks) throws DukeException {
        for (Task t : tasks) {
            long numberOfDuplicateTasks = this.userTasks.stream().filter(currentTask
                    -> currentTask.getTaskDescription().equals(t.getTaskDescription())).count();
            if (numberOfDuplicateTasks > 0) {
                throw new DukeException("This task is already in the list.");
            }
            this.userTasks.add(t);
        }
    }

    /**
     * Deletes the task with the given task number from the list.
     *
     * @param taskNumber Array index of the task number to be deleted.
     * @throws DukeException If the task number does not exist.
     */
    public void deleteTask(int taskNumber) throws DukeException {
        try {
            assert taskNumber < this.userTasks.size() : "Task number exceeds the number of tasks";
            this.userTasks.remove(taskNumber);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("    " + "No such task exists.\n");
        }
    }

    /**
     * Marks the task with the given task number as done.
     *
     * @param taskNumber Array index of the task number to be deleted.
     * @throws DukeException If the task number does not exist.
     */
    public void markTask(int taskNumber) throws DukeException {
        try {
            assert taskNumber < this.userTasks.size() : "Task number exceeds the number of tasks";
            Task userTask = this.userTasks.get(taskNumber);
            if (userTask.isCompleted()) {
                throw new DukeException("    " + "This task is already marked as done.\n");
            } else {
                userTask.setCompleted();
            }
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("    " + "No such task exists.\n");
        }
    }

    /**
     * Marks the task with the given task number as not done.
     *
     * @param taskNumber Array index of the task number to be deleted.
     * @throws DukeException If the task number does not exist.
     */
    public void unmarkTask(int taskNumber) throws DukeException {
        try {
            assert taskNumber < this.userTasks.size() : "Task number exceeds the number of tasks";
            Task userTask = this.userTasks.get(taskNumber);
            if (!userTask.isCompleted()) {
                throw new DukeException("    "
                        + "This task is already marked as not done yet.\n");
            } else {
                userTask.setUncompleted();
            }
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("    " + "No such task exists.\n");
        }
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return A number representing the number of tasks in the list.
     */
    public int length() {
        return this.userTasks.size();
    }

    /**
     * Returns a representation of the current list to be stored locally.
     *
     * @return A string representing the task list in storage.
     */
    public String getStorageRepresentation() {
        String storageRepresentation = "";
        for (Task userTask : this.userTasks) {
            storageRepresentation += userTask.getTextRepresentation();
        }
        return storageRepresentation;
    }

    /**
     * Returns a string representation of a task list.
     *
     * @return The string representation of a task list.
     */
    @Override
    public String toString() {
        String output = "";
        for (int i = 0; i < this.userTasks.size(); i++) {
            Task userTask = this.userTasks.get(i);
            output += "        " + (i + 1) + ". " + userTask + "\n";
        }
        return output;
    }

    public String getMatchingTasksRepresentation(String searchInput) {
        String output = "";
        for (int i = 0; i < this.userTasks.size(); i++) {
            Task userTask = this.userTasks.get(i);
            if (userTask.matchesString(searchInput)) {
                output += "        " + (i + 1) + ". " + userTask + "\n";
            }

        }
        return output;
    }

}
