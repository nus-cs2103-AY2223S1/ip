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
     */
    public CaCa(String dirPath, String filePath) {
        try {
            storage = new Storage(dirPath, filePath);
            tasks = storage.readFile();
            ui = new Ui();
            parser = new Parser(tasks, storage, ui);
        } catch (InvalidDateException e) {
            System.out.println("You have keyed in an invalid date and time!\n"
                    + "Please specify date and time in the format: dd/MM/yyyy HHmm\n"
                    + "E.g. 24/08/2022 2359");
        }
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
