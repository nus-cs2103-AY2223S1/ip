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
            ui.showFormattedMessage("Sorry, I don't understand what %s means. :/%n", e.getCommandName());
        }
    }

    /**
     * Runs the Scottie CLI application
     */
    private void run() {
        Cli cli = new Cli(System.in, System.out);
        cli.showStartupMessage();

        while (true) {
            try {
                Instruction instruction = Parser.parse(cli.readLine());
                instruction.execute(this.taskList, cli);
                if (instruction.endsProgram()) {
                    break;
                }
            } catch (InvalidCommandException e) {
                cli.showFormattedMessage("Sorry, I don't understand what %s means. :/%n", e.getCommandName());
            }
        }
    }

    /**
     * The entry point of the Scottie CLI program.
     */
    public static void main(String[] args) {
        new Scottie().run();
    }
}
