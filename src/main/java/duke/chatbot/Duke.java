package duke.chatbot;

import java.io.FileNotFoundException;

import duke.chatbot.command.Command;
import duke.chatbot.command.CommandResult;
import duke.chatbot.command.InvalidInputCommand;
import duke.chatbot.data.exception.InvalidInputException;
import duke.chatbot.data.task.TaskList;
import duke.chatbot.parser.Parser;
import duke.chatbot.storage.Storage;
import duke.chatbot.ui.Ui;

/**
 * The main application class.
 * @author Jordan Quah Shao Xuan
 */
public class Duke {
    /** The storage which handles saving and loading of files containing list of tasks */
    private Storage storage;

    /** The runtime instance of the list of tasks */
    private TaskList taskList;

    /** The input and output handler */
    private Ui ui;

    /**
     * Saves into storage the current state of the task list.
     */
    private void save() {
        storage.save(taskList);
    }

    /**
     * Takes and parses user input. Loops until an error occurs
     * or and exit command is given by the user.
     */
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

    /**
     * Exits the application.
     */
    public void exit() {
        System.exit(0);
    }

    /**
     * Initialises the storage, task list and ui before starting the
     * application loop.
     */
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

    public static void main(String ... args) {
        new Duke().run();
    }
}
