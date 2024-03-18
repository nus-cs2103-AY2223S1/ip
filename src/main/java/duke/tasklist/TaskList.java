package duke.tasklist;

import java.time.LocalDate;
import java.util.ArrayList;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

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

    private static final String NO_TAG = " ";

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
                if (description.contains("|")) {
                    String[] splitBy = description.split("\\| ", 2);
                    taskList.add(new Todo(splitBy[0], splitBy[1]));
                    int numOfTasks = taskList.size();
                    if (isMarked) {
                        taskList.get(numOfTasks - 1).markAsCompleted();
                    } else {
                        taskList.get(numOfTasks - 1).markAsNotCompleted();
                    }
                } else {
                    taskList.add(new Todo(description, " "));
                    int numOfTasks = taskList.size();
                    if (isMarked) {
                        taskList.get(numOfTasks - 1).markAsCompleted();
                    } else {
                        taskList.get(numOfTasks - 1).markAsNotCompleted();
                    }
                }
                break;
            }
            case "D ": {
                String[] splitTag = description.split("\\| ", 2);
                String dateAndOrTag = splitTag[1];
                if (dateAndOrTag.contains("|")) {
                    String[] splitDate = dateAndOrTag.split("\\| ", 2);
                    LocalDate byDate = LocalDate.parse(splitDate[1]);
                    taskList.add(new Deadline(splitTag[0], splitDate[0], byDate));
                    int numOfTasks = taskList.size();
                    if (isMarked) {
                        taskList.get(numOfTasks - 1).markAsCompleted();
                    } else {
                        taskList.get(numOfTasks - 1).markAsNotCompleted();
                    }
                } else {
                    LocalDate byDate = LocalDate.parse(dateAndOrTag);
                    taskList.add(new Deadline(splitTag[0], " ", byDate));
                    int numOfTasks = taskList.size();
                    if (isMarked) {
                        taskList.get(numOfTasks - 1).markAsCompleted();
                    } else {
                        taskList.get(numOfTasks - 1).markAsNotCompleted();
                    }
                }
                break;
            }
            case "E ": {
                String[] splitTag = description.split("\\| ", 2);
                String dateAndOrTag = splitTag[1];
                if (dateAndOrTag.contains("|")) {
                    String[] splitDate = dateAndOrTag.split("\\| ", 2);
                    LocalDate atDate = LocalDate.parse(splitDate[1]);
                    taskList.add(new Event(splitTag[0], splitDate[0], atDate));
                    int numOfTasks = taskList.size();
                    if (isMarked) {
                        taskList.get(numOfTasks - 1).markAsCompleted();
                    } else {
                        taskList.get(numOfTasks - 1).markAsNotCompleted();
                    }
                } else {
                    LocalDate byDate = LocalDate.parse(dateAndOrTag);
                    taskList.add(new Event(splitTag[0], " ", byDate));
                    int numOfTasks = taskList.size();
                    if (isMarked) {
                        taskList.get(numOfTasks - 1).markAsCompleted();
                    } else {
                        taskList.get(numOfTasks - 1).markAsNotCompleted();
                    }
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
        if (s.contains("tag")) {
            String[] splitWord = s.split("/tag ", 2);
            taskList.add(new Todo(splitWord[0], splitWord[1]));
        } else {
            taskList.add(new Todo(s, NO_TAG));
        }
    }

    /**
     * Adds a Deadline task to the list of tasks.
     * @param s Description of the Deadline task.
     */
    public void addDeadline(String s) {
        if (s.contains("tag")) {
            String[] splitDescription = s.split("/tag ", 2);
            String[] splitTag = splitDescription[1].split("/by ", 2);
            String description = splitDescription[0];
            String tag = splitTag[0];
            String by = splitTag[1];
            LocalDate byDate = LocalDate.parse(by);
            taskList.add(new Deadline(description, tag, byDate));
        } else {
            String[] splitDescription = s.split("/by ", 2);
            String description = splitDescription[0];
            String by = splitDescription[1];
            LocalDate byDate = LocalDate.parse(by);
            taskList.add(new Deadline(description, NO_TAG, byDate));
        }
    }

    /**
     * Adds a Event task to the list of tasks.
     * @param s Description of the Event task.
     */
    public void addEvent(String s) {
        if (s.contains("tag")) {
            String[] splitDescription = s.split("/tag ", 2);
            String[] splitTag = splitDescription[1].split("/at ", 2);
            String description = splitDescription[0];
            String tag = splitTag[0];
            String at = splitTag[1];
            LocalDate atDate = LocalDate.parse(at);
            taskList.add(new Event(description, tag, atDate));
        } else {
            String[] splitDescription = s.split("/at ", 2);
            String description = splitDescription[0];
            String at = splitDescription[1];
            LocalDate atDate = LocalDate.parse(at);
            taskList.add(new Event(description, NO_TAG, atDate));
        }
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
     * Converts the status of the task to either 1 or 0 that would be stored in the taskFile in
     * <code>Storage</code>.
     * @param status The status of the <code>Task</code>.
     * @return A representation of the <code>Task</code> status in taskFile.
     */
    public String convertStatus(String status) {
        return status.equals("X") ? "1" : "0";
    }

    /**
     * Returns an appropriate format that will represent a <code>Task</code> in the taskFile.
     * @param type The type of <code>Task</code>
     * @param status Status of the <code>Task</code>.
     * @param description Description of the <code>Task</code>.
     * @param date The date associated with the <code>Task</code>
     * @return A String format suitable for the taskFile.
     */
    public String storeIntoFileFormat(String type, String status, String description, String tag, String date) {
        switch (type) {
        case "T": {
            return tag.equals(" ")
                    ? "T | " + status + " | " + description
                    : "T | " + status + " | " + description + "| " + tag;
        }
        case "D": {
            return tag.equals(" ")
                    ? "D | " + status + " | " + description + "| " + date
                    : "D | " + status + " | " + description + "| " + tag + "| " + date;
        }
        case "E": {
            return tag.equals(" ")
                    ? "E | " + status + " | " + description + "| " + date
                    : "E | " + status + " | " + description + "| " + tag + "| " + date;
        }
        default:
            return "";
        }
    }

    /**
     * Returns the different date format of the Deadline or Event task in the list.
     * @param index The index of the task in the list.
     */
    public String changeDateFormat(int index) {
        if (taskList.get(index) instanceof Deadline) {
            Deadline updatedDeadline = (Deadline) taskList.get(index);
            return updatedDeadline.changeDateFormat();
        } else if (taskList.get(index) instanceof Event) {
            Event updatedEvent = (Event) taskList.get(index);
            return updatedEvent.changeDateFormat();
        } else {
            return "";
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

    /**
     * Returns the tag of the task.
     * @param index The index of the task in the list.
     * @return The tag of the task.
     */
    public String getTag(int index) {
        return taskList.get(index).getTag();
    }
}
