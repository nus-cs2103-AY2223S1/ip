package duke;

import java.io.Serializable;

/**
 * Abstract super class of all Tasks.
 *
 * @author Cui Shen Yi
 * @version CS2103T AY22/23 Semester 1
 */
public abstract class Task implements Serializable {

    protected String task;
    protected boolean isComplete = false;

    /**
     * Constructor to create new duke.Task class.
     *
     * @param task
     */
    protected Task(String task) {
        this.task = task;
    }

    /**
     * Method used get the date and task from a command.
     *
     * @param command  the string containing the task and date entered by user
     * @return a string array, pos 0 refers to task pos 1 refers to date
     */
    private static String[] getTaskAndDate(String command, String split) {
        String task = command.split(split, 2)[0].trim();
        String date = command.split(split, 2)[1].trim();

        return new String[]{ task, date };
    }

    /**
     * Factory method used to create a new duke.Task.
     *
     * @param commandArray the command entered by the user to be parsed by the method
     * @return a duke.Task obj, either a duke.Todo, duke.Deadline or duke.Event
     * @throws DukeException
     */
    public static Task createTask(String[] commandArray) throws DukeException {
        String task;
        String date;
        String command = commandArray[0];
        try {
            switch (Command.valueOf(command)) {
            case todo:
                Task.validateTaskCreation(commandArray, TaskType.TODO);
                task = commandArray[1].trim();
                return new Todo(task);
            case deadline:
                Task.validateTaskCreation(commandArray, TaskType.DEADLINE);
                task = Task.getTaskAndDate(commandArray[1], "/by")[0];
                date = Task.getTaskAndDate(commandArray[1], "/by")[1];
                return new Deadline(task, date);
            case event:
                Task.validateTaskCreation(commandArray, TaskType.EVENT);
                task = Task.getTaskAndDate(commandArray[1], "/at")[0];
                date = Task.getTaskAndDate(commandArray[1], "/at")[1];
                return new Event(task, date);
            default:
                throw new DukeException();
            }
        } catch (IllegalArgumentException e) {
            throw new DukeException();
        }
    }

    /**
     * Method used to mark this task as complete.
     */
    public String markAsDone() {
        this.isComplete = true;
        String output = "\nNice! I've marked this task as done:\n" + this;
        return output;
    }

    /**
     * Method used to mark this task as incomplete
     */
    public String unmark() {
        this.isComplete = false;
        String output = "\nOK, I've marked this task as not done yet:\n" + this;
        return output;
    }

    /**
     * To String method that returns the task in string form to the user
     *
     * @return the task in string format
     */
    @Override
    public String toString() {
        String checkBox = this.isComplete ? "[X] " : "[ ] ";
        return checkBox + this.task;
    }

    /**
     * A method used to validate a userCommand to for a specific task type.
     *
     * @param commandArray the command entered by the user to be parsed by the method
     * @param taskType    the task type either duke.Todo, duke.Deadline or duke.Event
     * @throws DukeException
     */
    private static void validateTaskCreation(
            String[] commandArray,
            TaskType taskType
    )
            throws DukeException {
        // TODO Move this logic to a factory method within the Tasks itself
        if (commandArray.length <= 1 || commandArray[1].length() == 0) {
            throw new DukeException(
                    "☹ OOPS!!! The description of a " + taskType + " cannot be empty."
            );
        }

        if (taskType == TaskType.DEADLINE && commandArray[1].indexOf("/by") < 0) {
            throw new DukeException("☹ OOPS!!! The description of a DEADLINE must contain a '/by'");
        }

        if (taskType == TaskType.EVENT && commandArray[1].indexOf("/at") < 0) {
            throw new DukeException("☹ OOPS!!! The description of a EVENT must contain a '/at'");
        }
    }
}
