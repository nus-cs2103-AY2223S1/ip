package Duke.UserServer;

import Duke.Commands.QuitCommand;
import Duke.Commands.UserCommand;
import Duke.Exceptions.DukeException;
import Duke.Parser.CLIParser;
import Duke.Storage.FileReader;
import Duke.Storage.FileSaver;
import Duke.Tasks.TaskList;

import Duke.UI.CLIUi;

/**
 * ServerCLI
 * provides methods to serve a user's commands
 */
public class ServerCLI {


    private TaskList tasks;

    private final String Name = "Duke";
    private final CLIUi CLIUi = new CLIUi();
    private final CLIParser parser = new CLIParser();


    public void run() {
        CLIUi.showWelcome();
        load();
        serve();
        save();
        CLIUi.showEnding();
    }


    private void load() {
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

    private void save(){
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
