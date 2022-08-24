package duke.chatbot;

import duke.chatbot.command.Command;
import duke.chatbot.command.CommandResult;
import duke.chatbot.command.InvalidInputCommand;
import duke.chatbot.data.exception.InvalidInputException;
import duke.chatbot.data.task.TaskList;
import duke.chatbot.parser.Parser;
import duke.chatbot.storage.Storage;
import duke.chatbot.ui.Ui;

import java.io.FileNotFoundException;


public class Duke {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    private void save() {
        storage.save(taskList);
    }

    public void applicationLoop() {
        Command command = null;
        while (!Command.isExit(command)) {
            try {
                command = Parser.parseCommand(ui.getUserInput());
                command.initData(taskList);
                CommandResult result = command.execute();
                ui.showMessage(result);
            } catch (InvalidInputException e) {
                ui.showMessage(new InvalidInputCommand().execute());
            }
        }
        save();
    }

    public void exit() {
        System.exit(0);
    }

    public void run() {
        try {
            storage = Storage.of("duke.txt");
            taskList = storage.getTaskList();
            ui = new Ui();
        } catch (InvalidInputException | FileNotFoundException e) {
            ui.printInitErrorMessage();
            exit();
        }

        ui.greetUser();
        applicationLoop();
        exit();
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
