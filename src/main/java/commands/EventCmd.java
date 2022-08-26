package commands;

import drivers.Storage;
import drivers.TaskList;
import drivers.UI;
import exceptions.DENoArgException;
import exceptions.DENoTimingException;
import exceptions.DETimingOverflowException;
import exceptions.TumuException;
import tasks.Event;

/**
 * Class to be executed when an event command is issued
 * by the user.
 */
public class EventCmd extends Command {
    private final String body;

    /**
     * Constructor for the EventCmd class.
     * @param body The rest of the instruction issued by the user after command.
     */
    public EventCmd(String body) {
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
    public void execute(TaskList tasks, UI ui, Storage storage) throws TumuException {
        //Check for "/at", if not available then prompt user to add timing.
        if (!body.contains("/at")) {
            throw new DENoTimingException("at");
        } else {
            //Parse the string. Make sure there is no multiple "/at" statements.
            String[] parse = body.replaceAll("\\s+", "").split("/at");
            if (parse.length > 2) {
                throw new DETimingOverflowException();
            } else if (parse.length < 2 || parse[0].isBlank() || parse[1].isBlank()) {
                throw new DENoArgException();
            } else {
                addTaskType(new Event(parse[0], parse[1]), tasks, ui);
            }
        }

        saveUserTasks(storage, tasks);
    }
}
