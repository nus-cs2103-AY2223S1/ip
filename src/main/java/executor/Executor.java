package executor;

import java.time.LocalDateTime;

import duke.DukeException;
import task.Deadline;
import task.Event;
import task.Task;
import task.TaskList;
import task.Todo;

/**
 * Executes commands on the task list and creates the program's response to the user input
 */
public class Executor {

    private final TaskList taskList;

    /**
     * Constructs a {@link Executor} object.
     *
     * @param taskList {@link TaskList} object to modify.
     */
    public Executor(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * Adds one of the {@link Task} object's subclasses into the {@code taskList}.
     *
     * @param type Type of task to add into the {@code taskList}.
     * @param name Name of the task.
     * @param time Time of the task.
     * @return Response of the program.
     * @throws DukeException If the user input is not of the accepted format.
     */
    public String addNewTask(String type, String name, LocalDateTime time) throws DukeException {
        Task task;
        try {
            if (type.equals("todo")) {
                task = new Todo(name);
            } else if (type.equals("deadline")) {
                task = new Deadline(name, time);
            } else {
                task = new Event(name, time);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw DukeException.dukeInvalidFormatException();
        }
        this.taskList.addTask(task);
        String header = "Got it. I've added this task:";
        String line = String.format("  %s", task);
        String footer = String.format("Now you have %s task in the list", taskList.getSize());
        return String.join("\n", header, line, footer);
    }

    /**
     * Marks a {@link Task} object as done.
     *
     * @param split The user's input split by each space.
     * @return Response of the program.
     * @throws DukeException If the user input is not of the accepted format.
     */
    public String markAsDone(String[] split) throws DukeException {
        Task task;
        try {
            if (split.length == 2) {
                int num = Integer.parseInt(split[1]);
                task = this.taskList.getTask(num);
                task.mark();
            } else {
                throw DukeException.dukeInvalidIndexException();
            }
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw DukeException.dukeInvalidIndexException();
        }
        String header = "Nice! I've marked this task as done:";
        String line = String.format("  %s", task);
        return String.join("\n", header, line);
    }

    /**
     * Unmarks a {@link Task} object as not done.
     *
     * @param split The user's input split by each space.
     * @return Response of the program.
     * @throws DukeException If the user input is not of the accepted format.
     */
    public String unmarkAsDone(String[] split) throws DukeException {
        Task task;
        try {
            if (split.length == 2) {
                int num = Integer.parseInt(split[1]);
                task = this.taskList.getTask(num);
                task.unmark();
            } else {
                throw DukeException.dukeInvalidIndexException();
            }
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw DukeException.dukeInvalidIndexException();
        }
        String header = "OK, I've marked this task as not done yet:";
        String line = String.format("  %s", task);
        return String.join("\n", header, line);
    }

    /**
     * Deletes a {@link Task} object from the {@code taskList}.
     *
     * @param split The user's input split by each space.
     * @return Response of the program.
     * @throws DukeException If the user input is not of the accepted format.
     */
    public String deleteTaskFromList(String[] split) throws DukeException {
        Task task;
        try {
            if (split.length == 2) {
                int num = Integer.parseInt(split[1]);
                task = this.taskList.getTask(num);
                taskList.removeTask(num);
            } else {
                throw DukeException.dukeInvalidIndexException();
            }
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw DukeException.dukeInvalidIndexException();
        }
        String header = "Noted. I've removed this task:";
        String line = String.format("  %s", task.toString());
        return String.join("\n", header, line);
    }

    /**
     * Finds a {@link Task} object from the {@code taskList} by using a string.
     *
     * @param split The user's input split by each space.
     * @return Response of the program.
     * @throws DukeException If the user input is not of the accepted format.
     */
    public String findTaskFromList(String[] split) throws DukeException {
        TaskList matchedTasks;
        try {
            String keyword = split[1];
            matchedTasks = this.taskList.match(keyword);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw DukeException.dukeUnknownCommandException();
        }
        if (matchedTasks.getSize() > 0) {
            String header = "Here are the matching tasks in your list";
            return String.join("\n", header, matchedTasks.toString());
        } else {
            return "There are no matching tasks in your list";
        }
    }

    /**
     * Creates the bye message.
     *
     * @param input String of user input.
     * @return Response of the program.
     * @throws DukeException If the user input is not of the accepted format.
     */
    public String endProgram(String input) throws DukeException {
        if (input.equals("bye")) {
            return "Bye. Hope to see you again soon!";
        } else {
            throw DukeException.dukeUnknownCommandException();
        }
    }

    /**
     * Shows the {@link Task} objects inside the {@code taskList}.
     *
     * @param input String of user input.
     * @return Response of the program.
     * @throws DukeException If the user input is not of the accepted format.
     */
    public String showList(String input) throws DukeException {
        if (input.equals("list")) {
            if (taskList.getSize() > 0) {
                String header = "Here are the tasks in your list";
                return String.join("\n", header, taskList.toString());
            } else {
                return "There are no tasks in your list";
            }
        } else {
            throw DukeException.dukeUnknownCommandException();
        }
    }
}
