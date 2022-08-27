package duke.tasks;

import java.util.ArrayList;

import duke.data.Storage;
import duke.exceptions.DukeException;

/**
 * Describes the TaskList class which stores the tasks.
 */
public class TaskList {
    private final ArrayList<Task> taskList;
    private final Storage db;

    /**
     * Constructor of taskList linked to a Storage.
     * @param db the database where taskList stores the tasks.
     */
    public TaskList(Storage db) {
        this.taskList = new ArrayList<>();
        this.db = db;
    }

    public String getRemainingTasks() {
        return String.format("Now you have %d tasks in the list.", taskList.size());
    }

    private void updateDb() {
        db.write(taskList);
    }

    /**
     * Add tasks into the taskList and updates the database.
     * @param task the task to be added.
     * @return the reply when adding a task.
     */
    public String addTask(Task task) {
        taskList.add(task);
        updateDb();
        return "Got it. I've added this task:\n\t" + task + "\n" + getRemainingTasks();
    }

    /**
     * Marks a task as either complete or incomplete.
     * @param value the number of the task to edit.
     * @param isCompleted whether the task is complete or incomplete.
     * @return edited task.
     * @throws DukeException error message.
     */
    public Task markTask(String value, boolean isCompleted) throws DukeException {
        try {
            int taskNumber = Integer.parseInt(value);
            Task task = taskList.get(taskNumber - 1);
            task.mark(isCompleted);
            updateDb();
            return task;
        } catch (NumberFormatException e) {
            throw new DukeException("Please input a number.");
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException(String.format("Please input a valid number! There are %d duke.tasks remaining.",
                    taskList.size()));
        }
    }

    /**
     * Deletes a task from taskList and updates the database.
     * @param value the task number of the task to be deleted.
     * @return the reply when adding a task.
     * @throws DukeException error message
     */
    public String deleteTask(String value) throws DukeException {
        try {
            int taskNumber = Integer.parseInt(value);
            Task task = this.taskList.get(taskNumber - 1);
            this.taskList.remove(taskNumber - 1);
            updateDb();
            return "Noted. I've removed this task:\n\t" + task + "\n" + getRemainingTasks();
        } catch (NumberFormatException e) {
            throw new DukeException("Please input a number.");
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException(String.format("Please input a valid number! There are %d duke.tasks remaining.",
                    taskList.size()));
        }
    }

    /**
     * Adds a task from Database. Used when loading the database into TaskList.
     * @param storedTask task stored in the database.
     * @throws DukeException error message.
     */
    public void addTaskFromDb(String storedTask) throws DukeException {
        String[] args = storedTask.split("\\|");
        String taskType = args[0];
        Task task;
        switch (taskType) {
        case "T":
            task = new ToDo(Boolean.parseBoolean(args[1]), args[2]);
            break;
        case "D":
            task = new Deadline(Boolean.parseBoolean(args[1]), args[2], args[3]);
            break;
        case "E":
            task = new Event(Boolean.parseBoolean(args[1]), args[2], args[3]);
            break;
        default:
            throw new DukeException("Corrupted file");
        }
        taskList.add(task);
    }

    /**
     * The string representation of the TaskList class which shows the tasks that are currently saved.
     * @return string representation.
     */
    public String toString() {
        StringBuilder result = new StringBuilder();
        int counter = 1;
        for (Task task : this.taskList) {
            String numberedTask = counter + "." + task + "\n";
            result.append(numberedTask);
            counter++;
        }
        return result.toString();
    }
}
