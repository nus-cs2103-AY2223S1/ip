package scottie;

import scottie.instructions.Instruction;
import scottie.parser.InvalidCommandException;
import scottie.parser.Parser;
import scottie.tasks.Storage;
import scottie.tasks.TaskList;
import scottie.ui.Ui;

public class Scottie {
    public static void main(String[] args) {
        TaskList taskList = new TaskList(new Storage());
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
                ui.showFormattedMessage("Sorry, I don't understand what %s means. :/%n", e.getCommandString());
            }
        }
    }
}
