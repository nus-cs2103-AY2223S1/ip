package chatbot;

import java.io.IOException;

import chatbot.commands.Command;
import chatbot.exceptions.DukeException;
import chatbot.parser.Parser;
import chatbot.storage.Storage;
import chatbot.tasks.*;
import chatbot.ui.UI;

public class Duke {
    private UI ui;
    private Storage storage;
    private TaskList taskList;
    private Command currentCommand;

    public Duke() {
        ui = new UI();
        storage = new Storage();
        taskList = new TaskList();
    }

    public static void main(String[] args) throws IOException {
        Duke bot = new Duke();
        bot.start();
        bot.respond();
        bot.exit();
    }

    public void start() throws IOException {
        ui.greet();
        try {
            storage.initData(taskList);
        } catch (DukeException e) {
            ui.reprimand(e);
        }
    }

    public void respond() {
        do {
            String input = ui.getUserInput();
            try {
                currentCommand = Parser.parse(input);
                currentCommand.execute(taskList, ui);
            } catch (DukeException e) {
                ui.reprimand(e);
            }
        } while (!currentCommand.isExit());
    }

    public void exit() throws IOException {
        storage.saveData(taskList);
    }
}
