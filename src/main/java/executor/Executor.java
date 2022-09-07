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
     * Adds {@link Todo} object into the {@code taskList}.
     *
     * @param name Name of the task.
     * @param tag Tag name if is given by the user.
     * @return Response of the program.
     */
    public String addNewTodo(String name, String... tag) {
        Task task;
        if (tag.length > 0) {
            task = new Todo(name, tag[0]);
        } else {
            task = new Todo(name);
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
     * Adds one of {@link Deadline} or {@link Event} object into the {@code taskList}.
     *
     * @param name Name of the task.
     * @param tag Tag name if is given by the user.
     * @return Response of the program.
     */
    public String addNewDeadlineEvent(String type, String name, LocalDateTime time, String... tag) {
        Task task;
        switch (type) {
        case "deadline":
            if (tag.length > 0) {
                task = new Deadline(name, time, tag[0]);
            } else {
                task = new Deadline(name, time);
            }
            break;
        case "event":
            if (tag.length > 0) {
                task = new Event(name, time, tag[0]);
            } else {
                task = new Event(name, time);
            }
            break;
        default:
            return "";
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
     * Deletes a {@link Task} object from the {@code taskList}.
     *
     * @param split The user's input split by each space.
     * @return Response of the program.
     * @throws BocilException If the user input is not of the accepted format.
     */
    public String deleteTaskFromList(String[] split) throws BocilException {
        Task task;
        String header;
        if (split.length < 2) {
            throw BocilException.bocilInvalidFormatException();
        }
        try {
            int num = Integer.parseInt(split[1]);
            task = this.taskList.getTask(num);
            taskList.removeTask(num);
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
        if (split.length < 2) {
            throw BocilException.bocilInvalidFormatException();
        }
        try {
            int num = Integer.parseInt(split[1]);
            task = this.taskList.getTask(num);
            isDone = task.isDone();
            task.mark();
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw BocilException.bocilInvalidIndexException();
        }
        if (isDone) {
            header = "I've marked this task as completed already:";
        } else {
            header = "GG! We've completed this task:";
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
        if (split.length < 2) {
            throw BocilException.bocilInvalidFormatException();
        }
        try {
            int num = Integer.parseInt(split[1]);
            task = this.taskList.getTask(num);
            isDone = task.isDone();
            task.unmark();
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw BocilException.bocilInvalidIndexException();
        }
        if (isDone) {
            header = "OK, I'll mark this task as not completed for now:";
        } else {
            header = "I've marked this task as not completed already:";
        }
        String line = String.format("  %s", task);
        return String.join("\n", header, line);
    }

    /**
     * Tags a {@link Task} object.
     *
     * @param split The user's input split by each space.
     * @return Response of the program.
     * @throws BocilException If the user input is not of the accepted format.
     */
    public String tagTask(String[] split) throws BocilException {
        Task task;
        String header;
        boolean isTagged;
        if (split.length < 2) {
            throw BocilException.bocilInvalidFormatException();
        }
        try {
            String[] details = split[1].split("\\s+");
            if (details.length < 2) {
                throw BocilException.bocilInvalidFormatException();
            }
            int num = Integer.parseInt(details[0]);
            String tag = details[1];
            task = this.taskList.getTask(num);
            isTagged = task.isTagged();
            task.addTag(tag);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw BocilException.bocilInvalidIndexException();
        }
        if (isTagged) {
            header = "I've updated the tag to this task:";
        } else {
            header = "NICE! We finally got a tag for this task:";
        }
        String line = String.format("  %s", task);
        return String.join("\n", header, line);
    }

    /**
     * Untags a {@link Task} object.
     *
     * @param split The user's input split by each space.
     * @return Response of the program.
     * @throws BocilException If the user input is not of the accepted format.
     */
    public String untagTask(String[] split) throws BocilException {
        Task task;
        String header;
        boolean isTagged;
        if (split.length < 2) {
            throw BocilException.bocilInvalidFormatException();
        }
        try {
            int num = Integer.parseInt(split[1]);
            task = this.taskList.getTask(num);
            isTagged = task.isTagged();
            task.untag();
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw BocilException.bocilInvalidIndexException();
        }
        if (isTagged) {
            header = "OK, I have untagged this task for you:";
        } else {
            header = "The task doesn't have a tag already:";
        }
        String line = String.format("  %s", task);
        return String.join("\n", header, line);
    }

    /**
     * Views all {@link Task} objects with the specified tag.
     *
     * @param split The user's input split by each space.
     * @return Response of the program.
     * @throws BocilException If the user input is not of the accepted format.
     */
    public String viewTag(String[] split) throws BocilException {
        if (split.length < 2) {
            throw BocilException.bocilInvalidFormatException();
        }
        if (split[1].contains("\\s")) {
            throw BocilException.bocilInvalidTagFormatException();
        }
        String tag = split[1];
        TaskList taggedTasks = this.taskList.findTags(tag);
        if (taggedTasks.getSize() > 0) {
            String header = String.format("Here are the tasks with tag '%s' in our list:", tag);
            return String.join("\n", header, taggedTasks.toString());
        } else {
            return String.format("I can't find tasks with tag '%s'", tag);
        }
    }

    /**
     * Finds a {@link Task} object from the {@code taskList} by using a string.
     *
     * @param split The user's input split by each space.
     * @return Response of the program.
     * @throws BocilException If the user input is not of the accepted format.
     */
    public String findTaskFromList(String[] split) throws BocilException {
        if (split.length < 2) {
            throw BocilException.bocilInvalidFormatException();
        }
        String keyword = split[1];
        TaskList matchedTasks = this.taskList.match(keyword);
        if (matchedTasks.getSize() > 0) {
            String header = String.format("Here are the tasks I managed to match with '%s':", keyword);
            return String.join("\n", header, matchedTasks.toString());
        } else {
            return String.format("I can't match any tasks with '%s'", keyword);
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
        if (!input.equals("list")) {
            throw BocilException.bocilUnknownCommandException();
        }
        if (taskList.getSize() > 0) {
            String header = "Here are the tasks that we have right now:";
            return String.join("\n", header, taskList.toString());
        } else {
            return "We don't have any tasks in our list right now. Lets add some!";
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
        if (!input.equals("bye")) {
            throw BocilException.bocilUnknownCommandException();
        }
        return "Bye! See you next time!";
    }
}
