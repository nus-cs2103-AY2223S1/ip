package duke.command;

import duke.*;

/**
 * AddCommand stores the Task to be executed.
 * It does it with interaction with the UI and the TaskList.
 */
public class AddCommand extends Command {
    Task task;

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
     * Initialises an AddCommand object.
     * @param fullCommand the input string that the user provide through the Console.
     */
    public AddCommand(String fullCommand) throws DukeException {
        this.task = Parser.commandToTask(fullCommand);
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
