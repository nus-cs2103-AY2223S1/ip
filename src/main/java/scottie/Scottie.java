package scottie;

import java.nio.file.Path;
import java.nio.file.Paths;

import scottie.instructions.Instruction;
import scottie.instructions.InvalidCommandException;
import scottie.parser.Parser;
import scottie.storage.FileStorage;
import scottie.tasks.AutoSavingTaskList;
import scottie.tasks.TaskList;
import scottie.ui.Cli;
import scottie.ui.Ui;

/**
 * Scottie is a task manager application used for recording and
 * retrieving different types of tasks.
 */
public class Scottie {
    private static final Path TASKS_DATA_FILE_PATH = Paths.get("data", "tasks.txt");

    private static final String INVALID_COMMAND_MESSAGE = "Um... sorry I don't know what \"%s\" means...";

    private final TaskList taskList = new AutoSavingTaskList(new FileStorage(TASKS_DATA_FILE_PATH));

    /**
     * Sends an input to Scottie to process and respond using the given Ui.
     *
     * @param input The input from the user to process.
     * @param ui The Ui for Scottie to use to respond to the user.
     */
    public void sendInput(String input, Ui ui) {
        try {
            Instruction instruction = Parser.parse(input);
            instruction.execute(this.taskList, ui);
        } catch (InvalidCommandException e) {
            ui.showFormattedError(INVALID_COMMAND_MESSAGE, e.getCommandName());
        }
    }

    /**
     * Runs the Scottie CLI application
     */
    void run() {
        Cli cli = new Cli(System.in, System.out);
        cli.showStartupMessage();

        while (true) {
            try {
                Instruction instruction = Parser.parse(cli.readLine());
                instruction.execute(this.taskList, cli);
                if (cli.isProgramEnded()) {
                    break;
                }
            } catch (InvalidCommandException e) {
                cli.showFormattedError(INVALID_COMMAND_MESSAGE, e.getCommandName());
            }
        }
    }

    /**
     * Starts the Scottie CLI program.
     */
    public static void main(String[] args) {
        new Scottie().run();
    }
}
