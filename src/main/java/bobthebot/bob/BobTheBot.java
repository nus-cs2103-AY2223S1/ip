package bobthebot.bob;

import java.util.Timer;
import java.util.TimerTask;

import bobthebot.command.ReminderCommand;
import bobthebot.exceptions.BobException;
import bobthebot.tasks.ToDoList;
import bobthebot.utils.Parser;
import bobthebot.utils.Storage;
import bobthebot.utils.Ui;

/**
 * BobTheBot is a friendly CLI Bot that can help with managing tasks.
 */

public class BobTheBot {
    private Storage storage;
    private ToDoList list;
    private Parser parser;

    /**
     * Constructor for instance of BobTheBot.
     *
     * @param filePath of data.txt file where tasks are stored.
     */
    public BobTheBot(String filePath) {
        this.storage = new Storage(filePath);
        this.list = new ToDoList(this.storage.load(), storage);
        this.parser = new Parser();
    }

    /**
     * Gets Bob's response given a certain command.
     *
     * @param command Command given to Bob.
     * @return String which represents Bob's response.
     */
    public String getResponse(String command) {
        if (command.equals("bye")) {
            quit();

            ReminderCommand reminderCommand = new ReminderCommand(list);
            String response = reminderCommand.execute() + "\n";
            response += Ui.sayGoodbye(list);

            return response;
        }

        String response = null;
        try {
            response = parser.parseCommand(command, list);
        } catch (BobException exception) {
            response = exception.getMessage();
        } finally {
            return response;
        }
    }

    /**
     * Quits the program.
     */
    private void quit() {
        TimerTask exitApp = new TimerTask() {
            @Override
            public void run() {
                System.exit(0);
            }
        };
        new Timer().schedule(exitApp, 2000);
    }
}
