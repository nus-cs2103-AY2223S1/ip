package Duke.UserServer;

import Duke.Commands.QuitCommand;
import Duke.Commands.UserCommand;
import Duke.Duke;
import Duke.Exceptions.DukeException;
import Duke.Parser.CLIParser;
import Duke.Storage.FileReader;
import Duke.Tasks.Task;
import Duke.Tasks.TaskList;
import Duke.UI.TextUI;

import javax.xml.stream.events.Comment;
import java.sql.Array;

/**
 * ServerCLI
 * provides methods to serve a user's commands
 */
public class ServerGUI {

    private TaskList tasks;
    private TextUI Ui = new TextUI();
    private CLIParser parser = new CLIParser();

    private FileReader fileReader = new FileReader("Duke");

    public void run() {
        load();
        serve();
       // save();
    }

    private void load() {
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
            String userInput = Ui.readInput();
            try {
                curCommand = parser.parseCommand(userInput, this.tasks);
                String doneInfo = executeCommand(curCommand);
                System.out.println(doneInfo);
            } catch (Exception e) {
                System.out.println("serve error ...");
                System.out.println(e.toString());
            }

        } while (!(curCommand instanceof QuitCommand));
    }


    private String executeCommand(UserCommand curCommand) throws DukeException {
        return curCommand.execute();
    }

    public String getResponse(String input) {
        return input;
    }



}
