package caca;

import java.io.IOException;
import java.util.Scanner;

import caca.exceptions.InvalidDateException;

/**
 * CaCa is a personal assistant chatbot that helps users manage and track things.
 * It greets user, reads and stores user input,
 * allows user to add and delete tasks, update task status as done or undone,
 * displays all tasks with status and exits when user inputs bye.
 *
 * @author Carrie Zheng Jiarui
 * @version CS2103T AY22/23 Semester 1, iP
 */
public class CaCa {

    // Structure below is adapted from A-MoreOOP in
    // https://nus-cs2103-ay2223s1.github.io/website/schedule/week3/project.html
    private final Storage storage;

    private final Ui ui;

    /**
     * A TaskList object containing all the tasks in a list.
     */
    private TaskList tasks;

    /**
     * Constructor for CaCa chatbot program.
     *
     * @param dirPath Directory path containing the file with stored tasks.
     * @param filePath Relative file path to store tasks locally.
     */
    public CaCa(String dirPath, String filePath) {
        ui = new Ui();
        storage = new Storage(dirPath, filePath);
        try {
            tasks = storage.readFile();
        } catch (InvalidDateException e) {
            System.out.println("You have keyed in an invalid date and time!\n"
                    + "Please specify date and time in the format: dd/MM/yyyy HHmm\n"
                    + "E.g. 24/08/2022 2359");
        }
    }

    /**
     * Runs CaCa chatbot program.
     *
     * @throws IOException If there exists failed or interrupted I/O operations.
     */
    public void run() throws IOException {
        Parser parser = new Parser(tasks, storage);
        // Greets user.
        ui.greet();

        // Load file that was previously saved to local hard disk.
        storage.loadFile();

        Scanner sc = new Scanner(System.in);

        while (true) {
            // Reads user input.
            String input = sc.nextLine();
            boolean isRunning = parser.hasStopped(input);
            if (!isRunning) {
                break;
            }
        }
    }

    /**
     * The main chatbot program.
     *
     * @param args Command line arguments.
     * @throws IOException If there exists failed or interrupted I/O operations.
     */
    public static void main(String[] args) throws IOException {
        new CaCa("data/", "data/caca.txt").run();
    }

}
