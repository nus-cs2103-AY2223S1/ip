package scottie;

import scottie.instructions.Instruction;
import scottie.instructions.InvalidCommandException;
import scottie.parser.Parser;
import scottie.tasks.TaskList;
import scottie.ui.Cli;
import scottie.ui.Ui;

/**
 * Scottie is a task manager application used for recording and
 * retrieving different types of tasks.
 */
public class Scottie {
    private final TaskList taskList = new TaskList();

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
            ui.showFormattedMessage("Sorry, I don't understand what %s means. :/", e.getCommandName());
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
                cli.showFormattedMessage("Sorry, I don't understand what %s means. :/", e.getCommandName());
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
