package caca;

import caca.exceptions.InvalidDateException;
import caca.ui.Ui;

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
    private Storage storage;

    private Ui ui;

    private Parser parser;

    /**
     * A TaskList object containing all the tasks in a list.
     */
    private TaskList tasks;

    /**
     * Constructor for CaCa chatbot program.
     *
     * @param dirPath Directory path containing the file with stored tasks.
     * @param filePath Relative file path to store tasks locally.
     * @throws InvalidDateException If date entered by user is not in the specified format.
     */
    public CaCa(String dirPath, String filePath) throws InvalidDateException {
        storage = new Storage(dirPath, filePath);
        tasks = storage.readFile();
        ui = new Ui();
        parser = new Parser(tasks, storage, ui);
    }

    /**
     * Gets the response from CaCa to the user.
     *
     * @param input User input
     * @return CaCa's response to user.
     */
    public String getResponse(String input) {
        return parser.parse(input);
    }

}
