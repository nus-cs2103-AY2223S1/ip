import exceptions.DukeException;
import handlers.DukeCommand;
import models.Parser;
import models.Storage;
import models.TaskList;
import models.Ui;

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
