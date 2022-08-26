package duke;

import java.io.FileNotFoundException;
import java.io.IOException;

import duke.command.Command;
import duke.parser.Parser;
import duke.storage.FileManager;
import duke.storage.TaskRecords;
import duke.ui.BotUI;

/**
 * Represents the main class of the duke bot program.
 * A <code>Duke</code> object consists of BotUI and TaskRecords.
 */
public class Duke {

    private final BotUI ui;
    private final TaskRecords taskList;

    /**
     * Constructs Duke object.
     * The taskList is assigned to the previous duke chatBot data if the file is found.
     * TaskRecords will be initialised to an empty taskList if file is not found.
     */
    Duke() {
        this.ui = new BotUI();
        TaskRecords temp;
        try {
            temp = FileManager.read();
        } catch (FileNotFoundException ex) {
            temp = new TaskRecords();
        }
        this.taskList = temp;
    }

    /**
     * Runs the duke chatBot program.
     */
    void runBot() {
        ui.sayHello();
        boolean exitDuke = false;
        while (!exitDuke) {
            try {
                Command c = Parser.parse(ui.readCommand());
                c.execute(taskList, ui);
                FileManager.write(this.taskList);
                exitDuke = c.isExit();
            } catch (DukeException de) {
                System.out.print(de.getMessage());
            } catch (IOException ex) {
                System.out.println("Error while Saving File!");
            }
        }
    }
    /**
     * Main method of the program.
     */
    public static void main(String[] args) {
        new Duke().runBot();
    }
}
