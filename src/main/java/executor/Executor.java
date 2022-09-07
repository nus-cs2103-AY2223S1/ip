package executor;

import java.time.LocalDateTime;

import bocil.BocilException;
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
     * @throws BocilException If the user input is not of the accepted format.
     */
    public String addNewTask(String type, String name, LocalDateTime time) throws BocilException {
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
            throw BocilException.bocilInvalidFormatException();
        }
        this.taskList.addTask(task);
        int size = taskList.getSize();
        String header = "NICE! We got a new task to complete:";
        String line = String.format("  %s", task);
        String footer = String.format("Now we have %s task%s in the list, %s marked and %s unmarked",
                size, size > 0 ? "s" : "", taskList.getSizeMarked(), taskList.getSizeUnmarked());
        return String.join("\n", header, line, footer);
    }

    /**
     * Marks a {@link Task} object as done.
     *
     * @param split The user's input split by each space.
     * @return Response of the program.
     * @throws BocilException If the user input is not of the accepted format.
     */
    public String markAsDone(String[] split) throws BocilException {
        Task task;
        String header;
        boolean isDone;
        try {
            if (split.length == 2) {
                int num = Integer.parseInt(split[1]);
                task = this.taskList.getTask(num);
                isDone = task.isDone();
                task.mark();
            } else {
                throw BocilException.bocilInvalidIndexException();
            }
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw BocilException.bocilInvalidIndexException();
        }
        if (!isDone) {
            header = "GG! We've completed this task:";
        } else {
            header = "I've marked this task as completed already:";
        }
        String line = String.format("  %s", task);
        return String.join("\n", header, line);
    }

    /**
     * Unmarks a {@link Task} object as not done.
     *
     * @param split The user's input split by each space.
     * @return Response of the program.
     * @throws BocilException If the user input is not of the accepted format.
     */
    public String unmarkAsDone(String[] split) throws BocilException {
        Task task;
        String header;
        boolean isDone;
        try {
            if (split.length == 2) {
                int num = Integer.parseInt(split[1]);
                task = this.taskList.getTask(num);
                isDone = task.isDone();
                task.unmark();
            } else {
                throw BocilException.bocilInvalidIndexException();
            }
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw BocilException.bocilInvalidIndexException();
        }
        if (!isDone) {
            header = "I've marked this task as not completed already:";
        } else {
            header = "OK, I'll mark this task as not completed for now:";
        }
        String line = String.format("  %s", task);
        return String.join("\n", header, line);
    }

    /**
     * Deletes a {@link Task} object from the {@code taskList}.
     *
     * @param split The user's input split by each space.
     * @return Response of the program.
     * @throws BocilException If the user input is not of the accepted format.
     */
    public String deleteTaskFromList(String[] split) throws BocilException {
        Task task;
        String header;
        try {
            if (split.length == 2) {
                int num = Integer.parseInt(split[1]);
                task = this.taskList.getTask(num);
                taskList.removeTask(num);
            } else {
                throw BocilException.bocilInvalidIndexException();
            }
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw BocilException.bocilInvalidIndexException();
        }
        if (task.isDone()) {
            header = "I will clear this finished task from the list, so we can do new ones:";
        } else {
            header = "NOOO... I will cancel this uncompleted task, if that's what you wish for:";
        }
        String line = String.format("  %s", task);
        return String.join("\n", header, line);
    }

    /**
     * Finds a {@link Task} object from the {@code taskList} by using a string.
     *
     * @param split The user's input split by each space.
     * @return Response of the program.
     * @throws BocilException If the user input is not of the accepted format.
     */
    public String findTaskFromList(String[] split) throws BocilException {
        TaskList matchedTasks;
        try {
            String keyword = split[1];
            matchedTasks = this.taskList.match(keyword);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw BocilException.bocilUnknownCommandException();
        }
        if (matchedTasks.getSize() > 0) {
            String header = "Here are the matching tasks in our list:";
            return String.join("\n", header, matchedTasks.toString());
        } else {
            return "I can't find any matching tasks in our list.";
        }
    }

    /**
     * Creates the bye message.
     *
     * @param input String of user input.
     * @return Response of the program.
     * @throws BocilException If the user input is not of the accepted format.
     */
    public String endProgram(String input) throws BocilException {
        if (input.equals("bye")) {
            return "Bye! See you next time!";
        } else {
            throw BocilException.bocilUnknownCommandException();
        }
    }

    /**
     * Shows the {@link Task} objects inside the {@code taskList}.
     *
     * @param input String of user input.
     * @return Response of the program.
     * @throws BocilException If the user input is not of the accepted format.
     */
    public String showList(String input) throws BocilException {
        if (input.equals("list")) {
            if (taskList.getSize() > 0) {
                String header = "Here are the tasks that we have right now:";
                return String.join("\n", header, taskList.toString());
            } else {
                return "We don't have any tasks in our list right now. Lets add some!";
            }
        } else {
            throw BocilException.bocilUnknownCommandException();
        }
    }
}
