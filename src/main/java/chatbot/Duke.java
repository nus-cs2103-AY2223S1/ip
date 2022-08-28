package chatbot;

import java.io.IOException;

import chatbot.commands.Command;
import chatbot.exceptions.DukeException;
import chatbot.parser.Parser;
import chatbot.storage.Storage;
import chatbot.tasks.*;
import chatbot.ui.UI;

/**
 * The Duke chatbot named Zlimez functions as a task manager
 * that helps users keep track of future tasks, their date
 * and completion status.
 *
 * @author James Chiu
 */
public class Duke {
    private boolean isActive = true;
    private UI ui;
    private Storage storage;
    private TaskList taskList;
    private Command currentCommand;

    /**
     * The Constructor initializes the duke chatbot instance
     * equipped with UI, Storage and Task list components.
     */
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

    /**
     * The method is called immediately after the chatbot instance is created
     * to kickstart the interaction with the user.
     * If a task list is generated during a previous session, its data will be
     * read by the current chatbot.
     *
     * @throws IOException
     */
    public void start() throws IOException {
        ui.greet();
        try {
            storage.initData(taskList);
        } catch (DukeException e) {
            ui.reprimand(e);
        }
    }


    /**
     * The method is responsible for responding to user inputs during the chatbot session.
     *
     * @throws DukeException Exceptions that can be raised when interacting
     * with the chatbot.
     */
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

    /**
     * Ends the current chatbot session and demands the data in the current
     * task list to be saved.
     *
     * @throws IOException
     */
    public void exit() throws IOException {
        storage.saveData(taskList);
    }













}
