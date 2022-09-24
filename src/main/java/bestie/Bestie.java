package bestie;

import java.util.Timer;
import java.util.TimerTask;

import bestie.command.ReminderCommand;
import bestie.exceptions.BestieException;
import bestie.tasks.ToDoList;
import bestie.utils.Parser;
import bestie.utils.Storage;
import bestie.utils.Ui;

/**
 * Bestie is a friendly CLI Bot that can help with managing tasks.
 */

public class Bestie {
    private Storage storage;
    private ToDoList list;
    private Parser parser;

    /**
     * Constructor for instance of Bestie.
     *
     * @param filePath of data.txt file where tasks are stored.
     */
    public Bestie(String filePath) {
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
        } catch (BestieException exception) {
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
