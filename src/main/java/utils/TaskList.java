package utils;

import ui.Ui;
import exceptions.EmptyNameException;
import exceptions.InvalidTaskIndexException;
import exceptions.NoTasksException;
import objects.Deadline;
import objects.Event;
import objects.Task;
import objects.Todo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TaskList {
    private static List<Task> tasks = new ArrayList<>();

    public List<Task> getTasks() {
        return tasks;
    }

    /**
     * Creates a new Todo object and adds it to the tasks list.
     * @param inputs array of input strings (e.g. ["todo", "wash dishes"])
     */
    public String addTodo(String[] inputs) throws EmptyNameException {
        if (inputs.length == 1) {
            throw new EmptyNameException("Todo name cannot be empty...");
        }
        StringBuilder todoName = new StringBuilder();
        for (int i = 1; i < inputs.length - 1; i++) {
            todoName.append(inputs[i]).append(" ");
        }
        // Edge case: When inputs.length is 2, the todoName is not captured by the loop above.
        todoName.append(inputs[inputs.length - 1]);

        Todo newTodo = new Todo(todoName.toString().trim());
        assert Objects.equals(newTodo.getName(), todoName.toString().trim());
        tasks.add(newTodo);
        return "Quack! I've added this task:\n" + "  " + newTodo + "\n" + Ui.printNumberOfTasks(tasks);
    }

    /**
     * Creates a new objects.Deadline object and adds it to the tasks list.
     * @param inputs array of input strings
     */
    public String addDeadline(String[] inputs) throws EmptyNameException {
        if (inputs.length == 1) {
            throw new EmptyNameException("Deadline name cannot be empty, quack!");
        }
        StringBuilder deadlineName = new StringBuilder();
        StringBuilder endDateTime = new StringBuilder();
        boolean isReadingDateTime = false;
        for (int i = 1; i < inputs.length - 1; i++) {
            if (inputs[i].equals("/by")) {
                isReadingDateTime = true;
                continue;
            }
            if (!isReadingDateTime) {
                deadlineName.append(inputs[i]).append(" ");
            } else {
                endDateTime.append(inputs[i]).append(" ");
            }
        }
        // Edge case: When name or date is only one string, they are not captured by the loop above.
        if (!isReadingDateTime) {
            deadlineName.append(inputs[inputs.length - 1]);
        } else {
            endDateTime.append(inputs[inputs.length - 1]);
        }

        Deadline newDeadline = new Deadline(
                deadlineName.toString().trim(),
                endDateTime.toString().trim());
        assert Objects.equals(newDeadline.getName(), deadlineName.toString().trim());
        tasks.add(newDeadline);
        return "Quack! I've added this task:\n" + "  "
                + newDeadline + "\n" + Ui.printNumberOfTasks(tasks);
    }

    /**
     * Creates a new objects.Event object and adds it to the tasks list.
     * @param inputs array of input strings
     */
    public String addEvent(String[] inputs) throws EmptyNameException {
        if (inputs.length == 1) {
            throw new EmptyNameException("Event name cannot be empty...");
        }
        StringBuilder eventName = new StringBuilder();
        StringBuilder periodDateTime = new StringBuilder();
        boolean isReadingDateTime = false;
        for (int i = 1; i < inputs.length - 1; i++) {
            if (inputs[i].equals("/at")) {
                isReadingDateTime = true;
                continue;
            }
            if (!isReadingDateTime) {
                eventName.append(inputs[i]).append(" ");
            } else {
                periodDateTime.append(inputs[i]).append(" ");
            }
        }
        // Edge case: When name or date is only one string, they are not captured by the loop above.
        if (!isReadingDateTime) {
            eventName.append(inputs[inputs.length - 1]);
        } else {
            periodDateTime.append(inputs[inputs.length - 1]);
        }

        Event newEvent = new Event(eventName.toString().trim(), periodDateTime.toString().trim());
        assert Objects.equals(newEvent.getName(), eventName.toString().trim());
        tasks.add(newEvent);
        return "Quack! I've added this task:\n" + "  "
                + newEvent + "\n" + Ui.printNumberOfTasks(tasks);
    }

    /**
     * Deletes a task at a specific index.
     *
     * @param taskIndex index of the task to be removed (1-indexed)
     * @throws NoTasksException an exception thrown when the list of Task objects is empty
     * @throws InvalidTaskIndexException an exception thrown when there is no Task object
     * at that particular index (1-indexed)
     */
    public String deleteTask(int taskIndex)
            throws NoTasksException, InvalidTaskIndexException {
        if (tasks.isEmpty()) {
            throw new NoTasksException();
        }
        if (taskIndex > tasks.size() || taskIndex <= 0) {
            throw new InvalidTaskIndexException();
        }
        // taskIndex is 1-indexed
        Task t = tasks.remove(taskIndex - 1);
        return "Quack! I've removed this task:\n" + "  " + t
                + "\n" + Ui.printNumberOfTasks(tasks);
    }

    public String findTasks(String keyword) {
        StringBuilder text = new StringBuilder("Here are the matching tasks in your list, quack:\n");
        boolean isNotFound = true;
        int index = 1;
        for (Task task : tasks) {
            String taskName = task.getName();
            if (taskName.contains(keyword)) {
                text.append(index).append(".").append(task).append("\n");
                isNotFound = false;
                index += 1;
            }
        }
        if (isNotFound) {
            text.append("There are no matching tasks, quack quack quack...");
        }
        return text.toString();
    }

    public String sortByName() {
        tasks.sort((t1, t2) -> t1.getName().compareTo(t2.getName()));
        return "Tasks are sorted successfully, quack!";
    }
}
