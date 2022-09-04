package Duke.UserServer;

import Duke.Commands.QuitCommand;
import Duke.Commands.UserCommand;
import Duke.Duke;
import Duke.Exceptions.DukeException;
import Duke.Parser.CLIParser;
import Duke.Tasks.TaskList;
import Duke.UI.TextUI;

import javax.xml.stream.events.Comment;

/**
 * ServerCLI
 * provides methods to serve a user's commands
 */
public class ServerCLI {

    private TaskList tasks;
    private TextUI Ui = new TextUI();
    private CLIParser parser = new CLIParser();

    public void run() {
        serve();
    }

    private void serve() {
        UserCommand curCommand = null;
        do {
            System.out.println("AAAA");
            String userInput = Ui.readInput();
            System.out.println(userInput);
            try {
                curCommand = parser.parseCommand(userInput, this.tasks);

             //   executeCommand(curCommand);
                String con = curCommand.execute();
                System.out.println(con);

            } catch (Exception e) {
                System.out.println("serve error");
                System.out.println(e.toString());
            }

        } while (!(curCommand instanceof QuitCommand));
    }

    private String executeCommand(UserCommand curCommand) throws DukeException {
        return curCommand.execute();
    }



}
