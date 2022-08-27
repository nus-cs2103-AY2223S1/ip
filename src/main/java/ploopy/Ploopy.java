package ploopy;

import java.util.Scanner;

/**
 * Chatbot that can store and edit tasks.
 *
 */
public class Ploopy {

    /** The UI object to interact with the user */
    private UI ui;
    /** The task list to store tasks */
    private TaskList taskList;
    /** The storage file to write to and read from */
    private Storage storage;

    /**
     * Constructor that creates the UI and storage objects
     */
    public Ploopy() {
        ui = new UI();
        try {
            storage = new Storage(ui);
        } catch (PloopyException e) {
            ui.exceptionMessage(e.getMessage());
        }
        taskList = new TaskList(ui, storage);
    }

    /**
     * Greets the user and tries to load data from
     * storage file. Tells user if any error occurred.
     * Asks for input from user.
     *
     */
    public void start() {
        ui.greeting();
        try {
            storage.loadFile(taskList);
        } catch (PloopyException e) {
            ui.exceptionMessage(e.getMessage());
        }
        command();
    }

    /**
     * Prompts user for an input. Parses input and
     * tells user of any invalid inputs or any
     * file storage errors.
     *
     */
    private void command() {
        ui.promptUser();
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        while (!input.equals("bye")) {
            try {
                Parser.parseInput(input, taskList);
            } catch (PloopyException e) {
                ui.exceptionMessage(e.getMessage());
            }
            input = scanner.nextLine();
        }
        ui.bye();
    }

    public static void main(String[] args) {
        new Ploopy().start();
    }
}
