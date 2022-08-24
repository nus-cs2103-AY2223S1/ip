package henry;

import command.Command;
import command.CommandResult;

import java.nio.file.Path;

public class Henry {

    private static final String home = System.getProperty("user.home");
    private static final Path FILE_PATH = java.nio.file.Paths.get(home, "Desktop", "henry.txt");
    private final Ui ui;
    private final Storage storage;
    private final TaskList taskList;
    private final Parser parser;
    private boolean isActivated;

    public Henry() {
        ui = new Ui();
        storage = new Storage(FILE_PATH.toString());
        taskList = new TaskList(storage.load());
        parser = new Parser();
        isActivated = true;
    }

    public boolean isActivated() {
        return isActivated;
    }

    /**
     * Activates the program. The program will take input from
     * the user and perform the adequate actions until the "bye"
     * command is entered.
     */
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
            ui.output(result.feedback);
        } while (true);
    }

    private CommandResult executeCommand(Command command) {
        try {
            command.setData(taskList);
            CommandResult result = command.execute();
            if (result.getTaskList().isPresent()) {
                storage.appendToFile(result.getTaskList().get().toSimpleString());
            }
            return result;
        } catch (Exception e) {
            ui.output(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    private void close() {
        ui.close();
        isActivated = false;
    }
}
