package duke.commands;

import duke.data.TaskList;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * Command to print out a helpful list of commands.
 */
public class HelpCommand extends Command {

    /**
     * Executes the command.
     *
     * @param taskList
     * @param ui User Interface of the to-do-list.
     * @param storage Storage option.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {

        StringBuilder ret = new StringBuilder();

        String[] commands = new String[]{"help: prints the help menu",
            "list: prints out the todo list",
            "bye: closes the program",
            "mark: marks task as done",
            "unmark: marks task as not done",
            "find: finds all tasks containing given keyword",
            "todo: adds a todo task",
            "deadline: adds a task with a deadline (use /by)",
            "event: adds an event (use /at)"
        };

        for (int i = 0; i < commands.length; ++i) {
            System.out.printf("\t%s", commands[i]);
            ret.append(String.format("\t%s\n", commands[i]));
        }

        return ret.toString();
    }

    @Override
    public boolean isEnd() {
        return false;
    }
}
