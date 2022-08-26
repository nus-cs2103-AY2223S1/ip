package Qoobee;

import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Contains the main class which is primarily run.
 */
public class Qoobee {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    /**
     * Creates a Qoobee object that contains a ui, storage, parser and tasklist.
     */
    public Qoobee() {
        this.ui = new Ui();
        this.storage = new Storage("TaskList.txt");
        try {
            tasks = new TaskList(storage);
            storage.loadFile();
            parser = new Parser(ui, tasks);
        } catch (QoobeeException e) {
            ui.showLoadingError();
            this.tasks = new TaskList(storage);
        }
    }

    /**
     * Starts the bot for the user to interact with.
     */
    public void run() {
        Scanner scanner = new Scanner(System.in);
        ui.greet();
        while (scanner.hasNext()) {
            String input = scanner.nextLine();
            parser.parse(input);
            if (!ui.isOn()) {
                break;
            }
        }
    }

    /**
     * Represents the main function to be run.
     * @param args The array of sequence to be passed into main argument.
     */
    public static void main(String[] args) {
        Qoobee qoobee = new Qoobee();
        qoobee.run();
    }

}
