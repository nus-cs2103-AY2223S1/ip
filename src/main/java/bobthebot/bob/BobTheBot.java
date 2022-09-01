package bobthebot.bob;

import java.util.Scanner;

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
     * Runs BobTheBot.
     */
    public void run() {
        Ui.sayWelcome();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String command = scanner.nextLine();
            if (command.toLowerCase().equals("bye")) {
                Ui.sayGoodbye(list);
                break;
            }
            parser.parseCommand(command, list);
        }

        scanner.close();
    }

    /*
    public static void main(String[] args) {
        new BobTheBot("./../../data/data.txt").run();
    }
    */
}
