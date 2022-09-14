package ploopy;

import javafx.application.Application;
import ploopy.task.TaskList;
import ploopy.ui.Main;
import ploopy.ui.TextUI;

/**
 * Chatbot that can store and edit tasks.
 *
 */
public class Ploopy {

    /** The task list to store tasks */
    private TaskList taskList;
    /** The storage file to write to and read from */
    private Storage storage;


    /**
     * Greets the user and tries to load data from
     * storage file. Tells user if any error occurred.
     * Asks for input from user.
     *
     */
    public String start() {
        String introString = TextUI.greeting() + "\n";
        try {
            storage = new Storage();
            introString += TextUI.createFilesMessage() + "\n";
            taskList = new TaskList(storage);
            introString += TextUI.addingFilesMessage() + "\n";
            storage.loadFile(taskList);
            return introString;
        } catch (PloopyException e) {
            return introString + TextUI.exceptionMessage(e.getMessage());
        }
    }

    /**
     * Parses user's input and
     * tells user of any invalid inputs or any
     * file storage errors.
     *
     */
    public String getResponse(String input) {
        try {
            return Parser.parseInput(input, taskList);
        } catch (PloopyException e) {
            return TextUI.exceptionMessage(e.getMessage());
        }
    }

    public static void main(String[] args) {
        Application.launch(Main.class, args);

    }
}
