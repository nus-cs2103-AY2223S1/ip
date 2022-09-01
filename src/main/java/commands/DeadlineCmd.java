package commands;

import drivers.Storage;
import drivers.TaskList;
import drivers.Ui;
import exceptions.DENoArgException;
import exceptions.DENoTimingException;
import exceptions.DETimingOverflowException;
import exceptions.TumuException;
import tasks.Deadline;

/**
 * Class to be executed when a deadline command is issued
 * by the user.
 */
public class DeadlineCmd extends Command {
    private final String body;

    /**
     * Constructor for the DeadlineCmd class.
     * @param body The rest of the instruction issued by the user after command.
     */
    public DeadlineCmd(String body) {
        this.body = body;
    }

    /**
     * Executes the command and gives the appropriate
     * feedback to the user.
     * @param tasks TaskList containing all the tasks currently available.
     * @param ui Specifies how the program interacts with the user.
     * @param storage Stores and retrieves data from a local .txt file.
     * @throws TumuException Parent exception for the program.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws TumuException {
        //Check for "/by", if not available then prompt user to add timing.
        if (!body.contains("/by")) {
            throw new DENoTimingException("by");
        } else {
            //Parse the string. Make sure there is no multiple "/by" statements.
            String[] parse = body.split("/by");
            if (parse.length > 2) {
                throw new DETimingOverflowException();
            } else if (parse.length < 2 || parse[0].isBlank() || parse[1].isBlank()) {
                throw new DENoArgException();
            } else {
                return addTaskType(new Deadline(parse[0].trim(),
                        parse[1].replaceAll("\\s+", "")), storage, tasks, ui);
            }
        }
    }
}
