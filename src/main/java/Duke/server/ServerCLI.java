package duke.server;

import duke.commands.QuitCommand;
import duke.commands.UserCommand;
import duke.exceptions.DukeException;
import duke.parser.CLIParser;
import duke.storage.FileReader;
import duke.storage.FileSaver;
import duke.tasks.TaskList;

import duke.ui.CLIUi;

import java.io.IOException;

/**

 * provides methods to serve a user's commands
 */
public class ServerCLI {


    private TaskList tasks;

    private final String Name = "duke";
    private final CLIUi CLIUi = new CLIUi();
    private final CLIParser parser = new CLIParser();
    public void run() throws IOException {
        CLIUi.showWelcome();
        load();
        serve();
        save();
        CLIUi.showEnding();
    }


    private void load() throws IOException {
        FileReader fileReader = new FileReader(Name);
        TaskList storedTaskList = fileReader.load();
        if (storedTaskList == null) {
            this.tasks = new TaskList();
        } else {
            this.tasks = storedTaskList;
        }
    }

    private void serve() {
        UserCommand curCommand = null;
        do {
            String userInput = CLIUi.readInput();
            try {
                curCommand = parser.parseCommand(userInput, this.tasks);
                String result = executeCommand(curCommand);
                CLIUi.showCommandInfo(result);
            } catch (Exception e) {
                CLIUi.showExceptionInfo(e);
                if (e instanceof DukeException) {
                }
            }
        } while (!(curCommand instanceof QuitCommand));
    }

    private void save() throws IOException {
        FileSaver filesaver = new FileSaver(Name);
        try {
            filesaver.save(tasks);
            CLIUi.showDataSaved();
        } catch (Exception e) {
            CLIUi.showExceptionInfo(e);
        }
    }

    private String executeCommand(UserCommand curCommand) throws DukeException {
        return curCommand.execute();
    }





}
