package duke.tasklist;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.util.ArrayList;
import java.time.LocalDate;

/**
 * Represents a list of tasks that the user has added. A <code>TaskList</code>
 * contains tasks or a task that the user has added. It also handles the operations
 * to add, delete, among other operations.
 */
public class TaskList {
    /**
     * Represents the list of tasks. A <code>ArrayList</code> contains items of type
     * <code>Task</code> which represents a task added by the user.
     */
    private static ArrayList<Task> taskList;

    /**
     * Initialises a <code>TaskList</code> object that contains the user's tasks.
     * It takes in tasks so that it can process tasks that were stored on the user's
     * hard disk so that it can remember tasks that the user had added previously.
     * @param tasks An <code>ArrayList</code> containing the tasks or task.
     */
    public TaskList(ArrayList<String> tasks) {
        taskList = new ArrayList<>();
        for (String s : tasks) {
            String[] splitCommand = s.split("\\| ", 3);
            String taskType = splitCommand[0];
            boolean isMarked = splitCommand[1].equals("1 ");
            String description = splitCommand[2];
            switch (taskType) {
            case "T ": {
                taskList.add(new Todo(description));
                int numOfTasks = taskList.size();
                if (isMarked) {
                    taskList.get(numOfTasks - 1).markAsCompleted();
                } else {
                    taskList.get(numOfTasks - 1).markAsNotCompleted();
                }
                break;
            }
            case "D ": {
                String[] splitBy = description.split("\\| ", 2);
                LocalDate byDate = LocalDate.parse(splitBy[1]);
                taskList.add(new Deadline(splitBy[0], byDate));
                int numOfTasks = taskList.size();
                if (isMarked) {
                    taskList.get(numOfTasks - 1).markAsCompleted();
                } else {
                    taskList.get(numOfTasks - 1).markAsNotCompleted();
                }
                break;
            }
            case "E ": {
                String[] splitAt = description.split("\\| ", 2);
                LocalDate atDate = LocalDate.parse(splitAt[1]);
                taskList.add(new Event(splitAt[0], atDate));
                int numOfTasks = taskList.size();
                if (isMarked) {
                    taskList.get(numOfTasks - 1).markAsCompleted();
                } else {
                    taskList.get(numOfTasks - 1).markAsNotCompleted();
                }
                break;
            }
            default:
                break;
            }
        }
    }

    /**
     * Adds a Todo task to the list of tasks.
     * @param s Description of the todo task.
     */
    public void addTodo(String s) {
        taskList.add(new Todo(s));
    }

    /**
     * Adds a Deadline task to the list of tasks.
     * @param s Description of the Deadline task.
     */
    public void addDeadline(String s) {
        String[] splitWord = s.split("/by ", 2);
        String description = splitWord[0];
        String by = splitWord[1];
        LocalDate byDate = LocalDate.parse(by);
        taskList.add(new Deadline(description, byDate));
    }

    /**
     * Adds a Event task to the list of tasks.
     * @param s Description of the Event task.
     */
    public void addEvent(String s) {
        String[] splitWord = s.split("/at ", 2);
        String description = splitWord[0];
        String at = splitWord[1];
        LocalDate atDate = LocalDate.parse(at);
        taskList.add(new Event(description, atDate));
    }

    /**
     * Returns the description of the task along with its status.
     * @param index The index of the task in the list.
     * @return A description and status of the task.
     */
    public String readTask(int index) {
        return taskList.get(index).toString();
    }

    /**
     * Returns the status of the task.
     * @param index The index of the task in the list.
     * @return The status of the task.
     */
    public String readStatus(int index) {
        return taskList.get(index).getStatus();
    }

    /**
     * Sets the status of the task to complete.
     * @param index The index of the task in the list.
     */
    public void setCompleted(int index) {
        taskList.get(index).markAsCompleted();
    }

    /**
     * Sets the status of the task to not complete.
     * @param index The index of the task in the list.
     */
    public void setNotCompleted(int index) {
        taskList.get(index).markAsNotCompleted();
    }

    /**
     * Returns the number of tasks in the list.
     * @return The number of tasks in the list.
     */
    public int getNumOfTasks() {
        return taskList.size();
    }

    /**
     * Deletes a task from the list.
     * @param index The index of the task in the list.
     */
    public void deleteTask(int index) {
        taskList.remove(index);
    }

    /**
     * Changes the date format of the Deadline or Event task in the list.
     * @param index The index of the task in the list.
     */
    public void changeDateFormat(int index) {
        if (taskList.get(index) instanceof Deadline) {
            ((Deadline) taskList.get(index)).changeDateFormat();
        } else if (taskList.get(index) instanceof Event) {
            ((Event) taskList.get(index)).changeDateFormat();
        }
    }

    /**
     * Returns the description of the task.
     * @param index The index of the task in the list.
     * @return The description of the task.
     */
    public String getDescription(int index) {
        return taskList.get(index).getDescription();
    }

    /**
     * Returns the date of the Deadline or Event task. If called on a Todo task,
     * it will return a failed message.
     * @param index The index of the task in the list.
     * @return The date of the task.
     */
    public String getDate(int index) {
        Task curr = taskList.get(index);
        if (curr instanceof Deadline) {
            return ((Deadline) curr).getBy();
        } else if (curr instanceof Event) {
            return ((Event) curr).getAt();
        } else {
            return "There is no date for this task.";
        }
    }
}
