import exceptions.EmptyNameException;
import exceptions.InvalidTaskIndexException;
import exceptions.NoTasksException;
import objects.Deadline;
import objects.Event;
import objects.Task;
import objects.Todo;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private static List<Task> tasks = new ArrayList<>();

    public List<Task> getTasks() {
        return tasks;
    }

    /**
     * Creates a new objects.Todo object and adds it to the tasks list.
     * @param inputs array of input strings (e.g. ["todo", "wash dishes"]
     */
    public void addTodo(String[] inputs) throws EmptyNameException {
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
        tasks.add(newTodo);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + newTodo);
        Ui.printNumberOfTasks(tasks);
    }

    /**
     * Creates a new objects.Deadline object and adds it to the tasks list.
     * @param inputs array of input strings
     */
    public void addDeadline(String[] inputs) throws EmptyNameException {
        if (inputs.length == 1) {
            throw new EmptyNameException("Deadline name cannot be empty...");
        }
        StringBuilder deadlineName = new StringBuilder();
        StringBuilder endDateTime = new StringBuilder();
        boolean readDateTime = false;
        for (int i = 1; i < inputs.length - 1; i++) {
            if (inputs[i].equals("/by")) {
                readDateTime = true;
                continue;
            }
            if (!readDateTime) {
                deadlineName.append(inputs[i]).append(" ");
            } else {
                endDateTime.append(inputs[i]).append(" ");
            }
        }
        // Edge case: When name or date is only one string, they are not captured by the loop above.
        if (!readDateTime) {
            deadlineName.append(inputs[inputs.length - 1]);
        } else {
            endDateTime.append(inputs[inputs.length - 1]);
        }

        Deadline newDeadline = new Deadline(
                deadlineName.toString().trim(),
                endDateTime.toString().trim());
        tasks.add(newDeadline);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + newDeadline);
        Ui.printNumberOfTasks(tasks);
    }

    /**
     * Creates a new objects.Event object and adds it to the tasks list.
     * @param inputs array of input strings
     */
    public void addEvent(String[] inputs) throws EmptyNameException {
        if (inputs.length == 1) {
            throw new EmptyNameException("Event name cannot be empty...");
        }
        StringBuilder eventName = new StringBuilder();
        StringBuilder periodDateTime = new StringBuilder();
        boolean readDateTime = false;
        for (int i = 1; i < inputs.length - 1; i++) {
            if (inputs[i].equals("/at")) {
                readDateTime = true;
                continue;
            }
            if (!readDateTime) {
                eventName.append(inputs[i]).append(" ");
            } else {
                periodDateTime.append(inputs[i]).append(" ");
            }
        }
        // Edge case: When name or date is only one string, they are not captured by the loop above.
        if (!readDateTime) {
            eventName.append(inputs[inputs.length - 1]);
        } else {
            periodDateTime.append(inputs[inputs.length - 1]);
        }

        Event newEvent = new Event(eventName.toString().trim(), periodDateTime.toString().trim());
        tasks.add(newEvent);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + newEvent);
        Ui.printNumberOfTasks(tasks);
    }

    /**
     * Deletes a task at a specific index.
     *
     * @param taskIndex index of the task to be removed (1-indexed)
     * @throws NoTasksException an exception thrown when the list of Task objects is empty
     * @throws InvalidTaskIndexException an exception thrown when there is no Task object
     * at that particular index (1-indexed)
     */
    public void deleteTask(int taskIndex)
            throws NoTasksException, InvalidTaskIndexException {
        if (tasks.isEmpty()) {
            throw new NoTasksException();
        }
        if (taskIndex > tasks.size() || taskIndex <= 0) {
            throw new InvalidTaskIndexException();
        }
        // taskIndex is 1-indexed
        Task t = tasks.remove(taskIndex - 1);
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + t);
        Ui.printNumberOfTasks(tasks);
    }
}
