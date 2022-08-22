package duke;

import java.util.Scanner;

import duke.command.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Config;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class Duke {
    private Ui ui;
    private Parser parser;
    private Storage storage = null;
    private TaskList taskList = null;

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

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.listenCommand();
    }

    private void listenCommand() {
        this.ui.printWelcomeMessage();
        Scanner sc = new Scanner(System.in);

        while(this.parser.getIsListening()) {
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

}
