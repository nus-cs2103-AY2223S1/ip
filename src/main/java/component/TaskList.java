package component;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import mew.MewDateTimeParseException.InputOverFlowException;
import mew.MewDateTimeParseException.InvalidDateTimeFormatException;

/**
 * Public TaskList class that manages addition and deletion to the task list.
 */
public class TaskList {
    private ArrayList<Task> list;

    /**
     * Constructs an empty TaskList object.
     */
    public TaskList() {
        this.list = new ArrayList<>();
    }

    /**
     * Constructs a TaskList object containing tasks given as string.
     * @param tasks Task list
     */
    public TaskList(ArrayList<String> tasks) {
        try {
            this.list = new ArrayList<>();
            for (String task : tasks) {
                Task newTask = Parser.parseFromFile(task);
                this.list.add(newTask);
            }
        } catch (InputOverFlowException | InvalidDateTimeFormatException e) {
            System.out.println(e);
        } catch (DateTimeParseException e) {
            System.out.println(e);
        }
    }

    /**
     * Obtains the task list in the form of ArrayList of Strings.
     * @return ArrayList representation of task list
     */
    public ArrayList<String> extractToStringArray() {
        ArrayList<String> textArray = new ArrayList<>();
        for (Task task : this.list) {
            String entry = task.printText();
            textArray.add(entry);
        }
        return textArray;
    }

    /**
     * Adds Task to the current task list.
     * @param task Task to be added
     * @return Boolean value indicating status of addition to task list
     */
    public boolean addTask(Task task) {
        int size = this.list.size();
        boolean response = this.list.add(task);
        assert (this.list.size() > size) : "Task addition failed";
        return response;
    }

    /**
     * Deletes a Task in the list and returns the deleted Task.
     * @param taskIndex Index of the current task
     * @return Deleted Task object
     */
    public Task deleteTask(int taskIndex) {
        int size = this.list.size();
        Task removedTask = this.list.remove(taskIndex);
        assert (this.list.size() < size) : "Task deletion failed";
        return removedTask;
    }

    /**
     * Marks a Task as done.
     * @param taskIndex Index of the current task
     * @return Marked Task object
     */
    public Task markTask(int taskIndex) {
        String previousStatusIcon = this.list.get(taskIndex).getStatusIcon();
        this.list.get(taskIndex).markAsDone();
        Task currentTask = this.list.get(taskIndex);
        assert (!previousStatusIcon.equals(currentTask.getStatusIcon())) : "Task marking failed";
        return currentTask;
    }

    /**
     * Unmarks a Task as undone.
     * @param taskIndex Index of the current task
     * @return Unmarked Task object
     */
    public Task unmarkTask(int taskIndex) {
        String previousStatusIcon = this.list.get(taskIndex).getStatusIcon();
        this.list.get(taskIndex).unmarkAsDone();
        Task currentTask = this.list.get(taskIndex);
        assert (!previousStatusIcon.equals(currentTask.getStatusIcon())) : "Task unmarking failed";
        return currentTask;
    }

    /**
     * Finds a matching Task according to the keyword given.
     * @param keyword Search keyword
     * @return TaskList object containing the matching tasks
     */
    public TaskList findTasks(String keyword) {
        TaskList foundTasks = new TaskList();
        for (Task task : this.list) {
            if (task.description.contains(keyword)) {
                foundTasks.addTask(task);
            }
        }
        return foundTasks;
    }

    /**
     * Edits the currently available task.
     * @param taskIndex Index of the task to be edited
     * @param newDescription New description of the task
     * @return The edited task
     */
    public Task editTaskDescription(int taskIndex, String newDescription) {
        Task currentTask = this.list.get(taskIndex);
        String previousDescription = currentTask.getDescription();
        currentTask.setDescription(newDescription);
        assert (!currentTask.getDescription().equals(previousDescription)) : "Edit description failed";
        return currentTask;
    }

    /**
     * Returns the number of tasks in the task list.
     * @return Number of tasks
     */
    public int getNumberOfTasks() {
        return this.list.size();
    }

    /**
     * Represents the TaskList object as a String.
     * @return String representation of the TaskList object
     */
    @Override
    public String toString() {
        if (this.list.isEmpty()) {
            return "[No tasks available]";
        }
        String result = "";
        for (int i = 1; i <= this.list.size(); ++i) {
            String index = String.valueOf(i);
            String details = ". " + this.list.get(i - 1);
            result += index + details + "\n";
        }
        return result;
    }
}