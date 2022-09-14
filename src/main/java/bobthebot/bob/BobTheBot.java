package bobthebot.bob;

import java.util.ArrayList;
import java.util.Scanner;

import bobthebot.command.GoodbyeCommand;
import bobthebot.command.ReminderCommand;
import bobthebot.exceptions.BobException;
import bobthebot.tasks.Deadline;
import bobthebot.tasks.Task;
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
     * Runs BobTheBot on CLI.
     */
    public void run() throws BobException {
        Ui.sayWelcome();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String command = scanner.nextLine();
            if (command.equals("bye")) {
                assert command == "bye" : "BobTheBot.java: command should be bye";
                ReminderCommand reminderCommand = new ReminderCommand(list);
                GoodbyeCommand goodbyeCommand = new GoodbyeCommand(list);
                reminderCommand.execute();
                goodbyeCommand.execute();
                break;
            }

            try {
                parser.parseCommand(command, list);
            } catch (BobException exception) {
                Ui.printErrorMessage(exception.getMessage());
            }
        }
        scanner.close();
    }

    public static void main(String[] args) throws BobException {
        new BobTheBot("./../../data/data.txt").run();
    }
}
