package scottie;

import scottie.instructions.Instruction;
import scottie.instructions.InvalidCommandException;
import scottie.parser.Parser;
import scottie.tasks.TaskList;
import scottie.ui.Cli;

/**
 * Scottie is a task manager application used for recording and
 * retrieving different types of tasks.
 */
public class Scottie {
    /**
     * Runs the Scottie CLI application
     */
    private static void run() {
        TaskList taskList = new TaskList();
        Cli cli = new Cli(System.in, System.out);
        cli.showStartupMessage();

        while (true) {
            try {
                Instruction instruction = Parser.parse(cli.readLine());
                instruction.execute(taskList, cli);
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
        run();
    }
}
