package duke.command;

import duke.Storage;
import duke.Ui;
import duke.exception.DukeException;
import duke.exception.EmptyDeadlineException;
import duke.exception.NoDeadlineException;
import duke.task.Deadline;
import duke.task.TaskList;

/**
 * A class to represent the deadline command
 */
public class DeadlineCommand extends Command {
    public static final String COMMAND = "deadline";
    private String desc;
    private String deadline;

    /**
     * Constructs a new DeadlineCommand instance.
     *
     * @param description the description given by the user.
     * @throws DukeException If description is in incorrect format.
     */
    public DeadlineCommand(String description) throws DukeException {
        //some regex to parse the strings correctly
        //0th index: task, 1st index: deadline
        String[] splitted = description.split("\\s/by\\s", 2);
        String desc = splitted[0].trim();

        //No Description Given
        if (desc.equals("") || desc.startsWith("/by")) {
            throw new EmptyDeadlineException();
        }

        //No Deadline Given
        if (splitted.length == 1) {
            throw new NoDeadlineException();
        }

        String deadline = splitted[1].trim();
        this.desc = desc;
        this.deadline = deadline;
    }

    /**
     * Adds a new deadline instance to the list of tasks
     *     and returns a response message.
     *
     * @param taskList the current list of tasks.
     * @return the response message.
     * @throws DukeException If there is a problem executing the command.
     */
    @Override
    public String executeAndGetResponse(TaskList taskList) throws DukeException {
        Deadline task = new Deadline(desc, deadline);
        taskList.add(task);
        Storage.saveTasks(taskList);
        return Ui.getTaskAddedMessage(task, taskList.getSize());
    }
}
