package ekud.task;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import ekud.exception.EkudException;

public class TaskList {
    private final List<Task> taskList;

    /**
     * Constructor that instantiates an instance of taskList.
     * 
     * @param taskList List of tasks.
     */
    public TaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Constructor that instantiates an instance of taskList with an empty list of
     * tasks.
     */
    public TaskList() {
        this.taskList = new ArrayList<Task>();
    }

    /**
     * Gets the task list.
     * 
     * @return List of tasks.
     */
    public List<Task> getTaskList() {
        return this.taskList;
    }

    /**
     * Deletes task based on command given.
     * 
     * @param message Command in the form of delete [index].
     * @return Message to be printed to the user.
     * @throws EkudException Error message.
     */
    public String deleteTask(String message) throws EkudException {
        if (message.matches("^delete \\d+$")) {
            int idx = Integer.parseInt(message.substring("delete ".length()));
            if (idx > this.taskList.size() || idx < 1) {
                throw new EkudException("Invalid index. Type 'list' to see available tasks and their indexes.");
            }
            Task task = this.taskList.remove(idx - 1);
            return (String.format("Noted. I've removed this task:\n%s\nNow you have %d tasks in the list.",
                    task.toString(),
                    taskList.size()));
        } else {
            throw new EkudException(("Invalid syntax. Use delete <index>"));
        }
    }

    /**
     * Marks task as done based on command given.
     * 
     * @param message Command in the form of mark [index].
     * @return Message to be printed to the user.
     * @throws EkudException Error message.
     */
    public String markAsDone(String message) throws EkudException {
        if (message.matches("^mark \\d+$")) {
            int idx = Integer.parseInt(message.substring("mark ".length()));
            if (idx > this.taskList.size() || idx < 1) {
                throw new EkudException("Invalid index. Type 'list' to see available tasks and their indexes.");
            }
            Task task = this.taskList.get(idx - 1);
            boolean result = task.markAsDone();
            if (result) {
                return (String.format("Nice! I've marked this task as done:\n%s", task.toString()));
            } else {
                throw new EkudException("That task is already marked as done!");
            }
        } else {
            throw new EkudException("Invalid syntax. Use mark <index>");
        }
    }

    /**
     * Marks task as undone based on command given.
     * 
     * @param message Command in the form of mark [index].
     * @return Message to be printed to the user.
     * @throws EkudException Error message.
     */
    public String markAsUndone(String message) throws EkudException {
        if (message.matches("^unmark \\d+$")) {
            int idx = Integer.parseInt(message.substring("unmark ".length()));
            if (idx > this.taskList.size() || idx < 1) {
                throw new EkudException("Invalid index. Type 'list' to see available tasks and their indexes.");
            }
            Task task = this.taskList.get(idx - 1);
            boolean result = task.markAsUndone();
            if (result) {
                return (String.format("OK, I've marked this task as not done yet:\n%s", task.toString()));
            } else {
                throw new EkudException("This task is already marked as undone!");
            }
        } else {
            throw new EkudException("Invalid syntax. Use unmark <index>");
        }
    }

    /**
     * Gets string representation of all tasks.
     * 
     * @return String representation of all tasks.
     */
    public String printTasks() {
        return this.printTasks(this.taskList);
    }

    /**
     * Gets string representation of all tasks of a specified task list.
     * 
     * @param taskList Task list to get string representation of.
     * @return String representation.
     */
    private String printTasks(List<Task> taskList) {
        StringBuilder builder = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < taskList.size(); i++) {
            builder.append(String.format("%d.%s\n", i + 1, taskList.get(i).toString()));
        }
        return (builder.toString());
    }

    /**
     * Adds task to task list.
     * 
     * @param description Description of task.
     * @param type Type of task.
     * @return Message to be printed to the user.
     * @throws EkudException Error message.
     */
    public String addTask(String description, TaskType type) throws EkudException {
        if (this.taskList.size() == 100) {
            throw new EkudException("Sorry, you've reached the maximum limit of 100 tasks.");
        }
        Task task = parseSpecialTask(description, type);
        this.taskList.add(task);
        return (String.format("Got it. I've added this task: %s", task.toString()));
    }

    private Task parseSpecialTask(String description, TaskType type) throws EkudException {
        Optional<Task> task = Optional.empty();
        String[] parts = description.split(" ");
        switch (type) {
        case TODO:
            if (parts.length < 2) {
                throw new EkudException("Description of a TODO cannot be empty.");
            }
            task = Optional.of(new ToDo(String.join(" ", Arrays.copyOfRange(parts, 1, parts.length))));
            break;
        case DEADLINE:
            // Fallthrough
        case EVENT:
            int idxOfSeparator = 0;
            for (int i = 0; i < parts.length; i++) {
                if (type == TaskType.EVENT && parts[i].equals("/at")) {
                    idxOfSeparator = i;
                } else if (type == TaskType.DEADLINE && parts[i].equals("/by")) {
                    idxOfSeparator = i;
                }
            }
            if (idxOfSeparator == 0) {
                String separator = type == TaskType.EVENT ? "/at" : "/by";
                throw new EkudException(
                        String.format("Invalid syntax. Use %s <task> %s <date/time>", type.toString(), separator));
            } else if (idxOfSeparator == 1) {
                throw new EkudException(String.format("Description of an %s cannot be empty.", type.toString()));
            } else if (idxOfSeparator == parts.length - 1) {
                throw new EkudException("Date/Time cannot be empty.");
            }
            String taskDesc = String.join(" ", Arrays.copyOfRange(parts, 1, idxOfSeparator));
            String taskDueDate = String.join(" ", Arrays.copyOfRange(parts, idxOfSeparator + 1, parts.length));
            if (type == TaskType.EVENT) {
                task = Optional.of(new Event(taskDesc, taskDueDate));
            } else {
                task = Optional.of(new Deadline(taskDesc, taskDueDate));
            }

            break;
        default:
            throw new EkudException("Invalid task type.");
        }
        if (task.isEmpty()) {
            throw new EkudException("Unexpected error.");
        } else {
            return task.get();
        }
    }

    public String searchTasks(String searchPhrase) {
        List<Task> matchingTasks = new ArrayList<Task>();
        for (int i = 0; i < this.taskList.size(); i++) {
            if (this.taskList.get(i).getDescription().contains(searchPhrase)) {
                matchingTasks.add(this.taskList.get(i));
            }
        }
        return this.printTasks(matchingTasks);
    }

}
