package duke.task;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Represents a task list.
 */
public class TaskList {
    private static final String MESSAGE_AFTER_ADD = "I shall add this task.";
    private static final String MESSAGE_AFTER_DELETE = "Noted. I've removed this task:";
    private List<Task> taskList;

    /**
     * Initialises the task list.
     *
     * @param taskList
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Initialises the task list.
     */
    public TaskList() {
        this(new ArrayList<>());
    }

    /**
     * Method to add a task to the task list.
     *
     * @param task
     * @return message
     */
    public String add(Task task) {
        taskList.add(task);
        return String.format(MESSAGE_AFTER_ADD + "\n" + task.toString() + "\nNow you have " + taskList.size()
                + " tasks in the list.");
    }

    /**
     * Method to get a task from the task list.
     *
     * @param index
     */
    public Task get(int index) {
        return taskList.get(index);
    }

    /**
     * Method to remove a task from the task list.
     *
     * @param index
     * @return message
     */
    public String remove(int index) {
        Task task = taskList.get(index);
        taskList.remove(index);
        return String.format(MESSAGE_AFTER_DELETE + "\n" + task.toString() + "\nNow you have " + taskList.size()
                + " tasks in the list.");
    }

    /**
     * Method to check if the task list is empty.
     *
     * @return isEmpty
     */
    public boolean isEmpty() {
        return taskList.isEmpty();
    }

    /**
     * Method to return the size of the task list.
     *
     * @return size
     */
    public int size() {
        return taskList.size();
    }

    /**
     * Filters and returns a new TaskList object based on a search term.
     *
     * @return taskList the filtered TaskList
     */
    public TaskList findTask(String description) {
        ArrayList<Task> list = new ArrayList<>();

        for (Task task : taskList) {
            if (task.getDescription().contains(description)) {
                list.add(task);
            }
        }

        return new TaskList(list);
    }

    /**
     * Sort deadlines and return a new TaskList with the sorted deadlines.
     *
     * @return taskList sorted TaskList
     */
    public TaskList sortDeadlines() {
        List<Task> list = this.taskList.stream().filter(task -> task instanceof Deadline).map(task -> (Deadline) task)
                .sorted((o1, o2) -> o1.getDateTime().compareTo(o2.getDateTime())).collect(Collectors.toList());
        ArrayList<Task> arrayList = new ArrayList<>(list);
        return new TaskList(arrayList);
    }

    /**
     * Returns a string representation to a TaskList object with a message.
     *
     * @param message
     * @return str
     */
    public String printListWithMessage(String message) {
        if (taskList.size() == 0) {
            return "There are no tasks in the task list";
        }
        String[] strArray = IntStream.range(0, taskList.size())
                .mapToObj(i -> String.format("%d.%s", i + 1, taskList.get(i).toString())).toArray(String[]::new);
        StringBuilder sb = new StringBuilder(message + "\n");
        for (String str : strArray) {
            sb.append(str + "\n");
        }
        return sb.toString();
    }

    /**
     * Returns a string representation to a TaskList object.
     *
     * @return str
     */
    @Override
    public String toString() {
        return this.printListWithMessage("Here are the tasks in your list:");
    }
}
