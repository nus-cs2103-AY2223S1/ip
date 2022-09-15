package duke;

import duke.commands.UserCommand;
import duke.exceptions.DukeException;
import duke.parser.GUIParser;
import duke.storage.FileReader;
import duke.storage.FileSaver;
import duke.tasks.TaskList;

import duke.ui.GUIUi;
import duke.server.ServerCLI;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Duke Class
 */
public class Duke {
    private TaskList tasks;
    private final String Name = "duke";
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
     * Main method for program with CLI. Creates a CLI server to handle input and operates.
     *
     * @param args Arguments of main method.
     * @throws IOException If createDirectories fails to be executed.
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
