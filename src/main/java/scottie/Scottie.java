package scottie;

import scottie.instructions.Instruction;
import scottie.instructions.InvalidCommandException;
import scottie.parser.Parser;
import scottie.tasks.TaskList;
import scottie.ui.Ui;

/**
 * Scottie is a task manager application used for recording and
 * retrieving different types of tasks.
 */
public class Scottie {
    /**
     * Runs the Scottie application
     */
    private static void run() {
        TaskList taskList = new TaskList();
        Ui ui = new Ui(System.in, System.out);
        ui.showStartupMessage();

        while (true) {
            try {
                Instruction instruction = Parser.parse(ui.readLine());
                instruction.execute(taskList, ui);
                if (instruction.endsProgram()) {
                    break;
                }
            } catch (InvalidCommandException e) {
                ui.showFormattedMessage("Sorry, I don't understand what %s means. :/%n", e.getCommandName());
            }
        }
    }

    /**
     * The entry point of the Scottie program.
     */
    public static void main(String[] args) {
        run();
    }
}
