package duke;

import duke.exceptions.DukeException;
import duke.handlers.DukeCommand;
import duke.models.Parser;
import duke.models.Storage;
import duke.models.TaskList;
import duke.models.Ui;

import java.util.Scanner;

public class Duke {
    public static TaskList taskList;
    public Ui ui = new Ui();
    public Parser parser = new Parser();

    public Duke() {
        try {
            taskList = Storage.loadTasksFromDisk();
        } catch (DukeException e) {
            taskList = new TaskList();
        }
    }

    public void chat (Scanner sc) {
        ui.greet();

        String userInput = sc.nextLine();

        while (!(userInput.equals("Bye") || userInput.equals("bye"))) {
            try {
                DukeCommand command = parser.parseCommand(userInput);
                String result = command.run(taskList, parser.parseContent(userInput));
                ui.showResponse(result);
            } catch (DukeException e) {
                ui.showError(e.errorMessage);
            }
            userInput = sc.nextLine();
        }
        try {
            Storage.saveTaskToDisk(taskList);
        } catch (DukeException e) {
            ui.showError(e.errorMessage);
        }
        ui.exit();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        new Duke().chat(sc);
    }
}
