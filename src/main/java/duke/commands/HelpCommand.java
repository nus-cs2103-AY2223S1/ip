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
     * @param taskList
     * @param ui User Interface of the to-do-list.
     * @param storage Storage option.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        String[] commands = new String[]{"help: prints the help menu",
            "list: prints out the todo list",
            "bye: closes the program",
            "mark: marks task as done",
            "unmark: marks task as not done",
            "todo: adds a todo task",
            "find: finds all tasks containing given keyword",
            "deadline: adds a task with a deadline",
            "event: adds an event"
        };

        for (int i = 0; i < commands.length; ++i) {
            System.out.printf("\t%s", commands[i]);
        }
    }

    @Override
    public boolean isEnd() {
        return false;
    }
}
