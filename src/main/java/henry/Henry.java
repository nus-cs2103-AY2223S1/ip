package henry;

import java.nio.file.Path;

import command.Command;
import command.CommandResult;
import exceptions.HenryException;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.util.Duration;

/**
 * The base class of the Henry application.
 * All functions of Henry pass through this class.
 */
public class Henry {

    private static final String home = System.getProperty("user.home");
    // The text file is created on the user's Desktop
    private static final Path FILE_PATH = java.nio.file.Paths.get(home, "Desktop", "henry.txt");

    private final Ui ui;
    private final Storage storage;
    private final TaskList taskList;
    private final Parser parser;

    /**
     * The constructor for the logical component of Henry.
     * When Henry is instantiated, a new UI, Storage, TaskList
     * and Parser classes are also created.
     */
    public Henry() {
        ui = new Ui();
        storage = new Storage(FILE_PATH.toString());
        taskList = new TaskList(storage.load());
        parser = new Parser();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        if (input.equalsIgnoreCase("bye")) {
            PauseTransition delay = new PauseTransition(Duration.seconds(2));
            delay.setOnFinished(event -> Platform.exit());
            delay.play();
            return "GOODBYE! YOUR TASK LIST HAS BEEN SAVED!";
        }
        Command parsed = parser.parseCommand(input);
        CommandResult result = executeCommand(parsed);
        return result.toString();
    }

    /**
     * Activates the program. The program will take input from
     * the user and perform the adequate actions until the "bye"
     * command is entered.
     *
     * @deprecated As of 30/8/2022: program now runs on JavaFX
     */
    @Deprecated
    public void runProgram() {
        Command command;
        String input;
        do {
            System.out.print("\n> ");
            input = ui.getInput();
            if (input.equals("bye")) {
                close();
                break;
            }
            command = parser.parseCommand(input);
            CommandResult result = executeCommand(command);
            ui.output(result.toString());
        } while (true);
    }

    private CommandResult executeCommand(Command command) {
        try {
            command.setData(taskList);
            CommandResult result = command.execute();
            if (result.getTaskList().isPresent()) {
                storage.appendToFile(result.getTaskList().get().toFileEncodedString());
            }
            return result;
        } catch (HenryException he) {
            throw he;
        } catch (Exception e) {
            ui.output(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    private void close() {
        ui.close();
    }
}
