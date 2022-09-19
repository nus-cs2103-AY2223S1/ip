package duke;

import duke.exceptions.DukeException;
import duke.handlers.DukeCommand;
import duke.models.*;
import javafx.application.Application;

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

    public DukeResponse getResponse(String userInput) {
        try {
            DukeCommand command = parser.parseCommand(userInput);
            return command.run(taskList, parser.parseContent(userInput));
        } catch (DukeException e) {
            assert e.getMessage() == null : "No Duke Exception message.";
            return new DukeResponse(ui.formatError(e.getMessage()));
        }
    }

    public void chat (Scanner sc) {
        ui.greet();

        String userInput = sc.nextLine();
        DukeResponse response = new DukeResponse(null, false);
        while (response.isExit()) {
            try {
                DukeCommand command = parser.parseCommand(userInput);
                response = command.run(taskList, parser.parseContent(userInput));
                ui.showResponse(response.getContent());
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            }
            userInput = sc.nextLine();
        }
        try {
            Storage.saveTaskToDisk(taskList);
        } catch (DukeException e) {
            ui.showError(e.getMessage());
        }
        ui.exit();
    }

    public static void main(String[] args) {
        if (args.length != 0) {
            Scanner sc = new Scanner(System.in);
            new Duke().chat(sc);
        }
        Application.launch(Main.class, args);
    }
}
