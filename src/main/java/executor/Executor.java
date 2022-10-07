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
     * Adds a new {@link Todo} object into the task list.
     *
     * @param name Name of task to be added.
     * @param tag Tag of task to be added if exists.
     * @return Response of the program.
     */
    public String addNewTodo(String name, String... tag) {
        Task task;
        if (tag.length > 0) {
            task = new Todo(name, tag[0]);
        } else {
            task = new Todo(name);
        }
        return this.addNewTask(task);
    }

    /**
     * Adds a new {@link Deadline} or {@link Event} object into the task list.
     *
     * @param type Type of task to be added.
     * @param name Name of task to be added.
     * @param time Time of task to be added.
     * @param tag Tag of task to be added if exists.
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
        return this.addNewTask(task);
    }

    /**
     * Adds a new {@link Task} object into the task list.
     *
     * @param task Task to be added.
     * @return Response of the program.
     */
    public String addNewTask(Task task) {
        this.taskList.addTask(task);
        int size = taskList.getSize();
        String header = "NICE! We got a new task to complete:";
        String line = String.format("  %s", task);
        String footer = String.format("Now we have %s task%s in the list, %s marked and %s unmarked",
                size, size > 0 ? "s" : "", taskList.getSizeMarked(), taskList.getSizeUnmarked());
        return String.join("\n", header, line, footer);
    }

    /**
     * Deletes a {@link Task} object from the task list.
     *
     * @param num Task number to delete.
     * @return Response of the program.
     * @throws BocilException If the specified index is out of range.
     */
    public String deleteTaskFromList(int num) throws BocilException {
        Task task;
        String header;
        try {
            task = this.taskList.getTask(num);
            taskList.removeTask(num);
        } catch (IndexOutOfBoundsException e) {
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
     * @param num Task number to mark.
     * @return Response of the program.
     * @throws BocilException If the specified index is out of range.
     */
    public String markAsDone(int num) throws BocilException {
        Task task;
        String header;
        boolean isDone;
        try {
            task = this.taskList.getTask(num);
            isDone = task.isDone();
            task.mark();
        } catch (IndexOutOfBoundsException e) {
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
     * @param num Task number to unmark.
     * @return Response of the program.
     * @throws BocilException If the specified index is out of range.
     */
    public String unmarkAsDone(int num) throws BocilException {
        Task task;
        String header;
        boolean isDone;
        try {
            task = this.taskList.getTask(num);
            isDone = task.isDone();
            task.unmark();
        } catch (IndexOutOfBoundsException e) {
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
     * @param num Task number to tag.
     * @param tag Tag name to be given to task.
     * @return Response of the program.
     * @throws BocilException If the specified index is out of range.
     */
    public String tagTask(int num, String tag) throws BocilException {
        Task task;
        String header;
        boolean isTagged;
        try {
            task = this.taskList.getTask(num);
            isTagged = task.isTagged();
            task.addTag(tag);
        } catch (IndexOutOfBoundsException e) {
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
     * @param num Task number to untag.
     * @return Response of the program.
     * @throws BocilException If the specified index is out of range.
     */
    public String untagTask(int num) throws BocilException {
        Task task;
        String header;
        boolean isTagged;
        try {
            task = this.taskList.getTask(num);
            isTagged = task.isTagged();
            task.untag();
        } catch (IndexOutOfBoundsException e) {
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
     * @param tag Tag to find.
     * @return Response of the program.
     */
    public String viewTag(String tag) {
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
     * @param keyword Keyword to match with.
     * @return Response of the program.
     */
    public String findTaskFromList(String keyword) {
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
     * @return Response of the program.
     */
    public String showList() {
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
     * @return Response of the program.
     */
    public String endProgram() {
        return "Bye! See you next time!";
    }
}
