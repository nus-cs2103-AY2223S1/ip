package Duke;

import Duke.Commands.UserCommand;
import Duke.Exceptions.DukeException;
import Duke.Parser.GUIParser;
import Duke.Storage.FileReader;
import Duke.Storage.FileSaver;
import Duke.Tasks.TaskList;

import Duke.UI.GUIUi;
import Duke.UserServer.ServerCLI;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Duke Class
 */
public class Duke {
    private TaskList tasks;
    private final String Name = "Duke";
    private GUIUi GUIUi = new GUIUi();
    private GUIParser parser = new GUIParser();

    /**
     * Public constructor of Duke class.
     * Creates a new directory for data storing.
     * @throws IOException Exception may occur during this session.
     */
    public Duke() throws IOException {
        Files.createDirectories(Paths.get("data"));
    };

    /**
     * Handles the input message and get the response from Duke.
     * @param input Input String from user.
     * @return String to user.
     * @throws IOException Exception may occur during this session.
     */
    public String getResponse(String input) throws IOException {
        load();
        String result = serve(input);
        String dataResult = save();
        return result;
    }
    private void load() {
        try {
            FileReader fileReader = new FileReader(Name);
            TaskList storedTaskList = fileReader.load();
            if (storedTaskList == null) {
                this.tasks = new TaskList();
            } else {
                this.tasks = storedTaskList;
            }
        } catch (IOException e) {
            this.tasks = new TaskList();
        }
    }
    private String serve(String input) {
        try {
            String finalInput = input.trim();
            UserCommand curCommand = parser.parseCommand(finalInput, this.tasks);
            return executeCommand(curCommand);
        } catch (Exception e) {
            return e.toString();
        }
    }
    private String executeCommand(UserCommand curCommand) throws DukeException {
        return curCommand.execute();
    }
    private String save() throws IOException {
        FileSaver fileSaver = new FileSaver(Name);
        try {
            fileSaver.save(tasks);
        } catch (Exception e) {
            return e.toString();
        }
        return "Data Saved!";
    }

    /**
     * Gets help information.
     * @return Help information in detail.
     */
    public String getHelp() {
        return GUIUi.showHelp();
    }

    /**
     * Main class.
     * Sets a CLI server to handle input.
     * @param args
     * @throws IOException Exception may occur during this session.
     */

    public static void main(String[] args) throws IOException {
        ServerCLI serverCLI = new ServerCLI();
        try {
            serverCLI.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
