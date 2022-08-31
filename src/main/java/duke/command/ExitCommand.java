package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Command to exit the program.
 */
public class ExitCommand extends Command {

    /**
     * Constructor for ExitCommand.
     *
     * @param info Type of command
     */
    public ExitCommand(String info) {
        super(info);
    }

    /**
     * Execute the exit command and prompt the program to terminate
     *
     * @param ui Ui to show Exit operation messages
     * @param taskList TaskList to execute command
     */
    @Override
    public String execute(Ui ui, TaskList taskList) {
        System.out.println(ui.showExitMessage());
        Storage storage = new Storage(ui, "./data/duke.txt");
        storage.writeFile(taskList);
        javafx.application.Platform.exit();
        return ui.showExitMessage();
    }
}
