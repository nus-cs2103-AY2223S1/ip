package duke.command;

import duke.DukeException;
import duke.Parser;
import duke.Storage;
import duke.Task;
import duke.TaskList;
import duke.Ui;

/**
 * AddCommand stores the Task to be executed.
 */
public class AddCommand extends Command {
    private Task task;

    /**
     * Initialises an AddCommand object.
     * @param fullCommand the input string that the user provide through the Console.
     */
    public AddCommand(String fullCommand) throws DukeException {
        this.task = Parser.commandToTask(fullCommand);
    }
    private static String getFirstWord(String input) {
        return input.split(" ")[0].toLowerCase();
    }

    private static boolean isTodo(String input) {
        return getFirstWord(input).equals("todo");
    }

    private static boolean isEvent(String input) {

        return getFirstWord(input).equals("event");
    }

    private static boolean isDeadline(String input) {
        return getFirstWord(input).equals("deadline");
    }


    /**
     * @param tasks TaskList of all Duke tasks.
     * @param ui Ui object for decorative purposes.
     * @param storage Storage of app information.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        if (task == null) {
            return "task in addcomand is null";
        }
        tasks.add(task);
        return "duke.Task added: " + task;
    }

    /**
     * @return boolean false.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * @param o Object we're comparing with.
     * @return whether compared object is equal.
     */
    @Override
    public boolean equals(Object o) {
        return o instanceof AddCommand;
    }
}
