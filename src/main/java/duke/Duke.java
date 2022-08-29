package duke;

import java.util.Scanner;

import duke.command.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Config;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;
import javafx.util.Pair;

/**
 * Duke is a personal assistant chat-bot.
 *
 * @author Farrel Dwireswara Salim
 */
public class Duke {
    private final Ui ui;
    private final Parser parser;
    private Storage storage = null;
    private TaskList taskList = null;

    /**
     * Constructs a new Duke instance.
     */
    public Duke() {
        this.ui = new Ui();
        this.parser = new Parser();

        try {
            this.storage = new Storage(Config.DIRECTORY, Config.NAME);
            this.taskList = this.storage.loadTasksInStorage();
        } catch (DukeException error) {
            this.ui.printError(error);
        }
    }

    /**
     * Runs the duke application.
     *
     * @param args the arguments.
     */
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.listenCommand();
    }

    private void listenCommand() {
        this.ui.printWelcomeMessage();
        Scanner sc = new Scanner(System.in);

        while (this.parser.getIsListening()) {
            try {
                String currentText = sc.nextLine();
                Command command = this.parser.parseText(currentText);
                command.execute(this.ui, this.storage, this.taskList);
            } catch (DukeException error) {
                this.ui.printError(error);
            }
        }

        sc.close();
    }

    public Pair<Boolean, String> getResponse(String input) {
        try {
            Command command = this.parser.parseText(input);
            return command.execute(this.ui, this.storage, this.taskList);
        } catch (DukeException error) {
            this.ui.printError(error);
            return new Pair<>(true, error.getMessage());
        }
    }

}
