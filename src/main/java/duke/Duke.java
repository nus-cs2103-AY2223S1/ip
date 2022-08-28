package duke;

import java.util.Scanner;

/**
 * The Duke program implements an application that
 * acts as your to-do list. It can add, delete, mark/unmark
 * and search for tasks.
 *
 * @author Gerald Teo Jin Wei
 * @version 0.1
 * @since 2022-08-28
 */

public class Duke {

    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    /**
     * Constructor for Duke program
     * @param filePath File path to txt file that stores task list data
     */
    public Duke(String filePath) {
        this.storage = new Storage(filePath);
        this.taskList = new TaskList(storage.load());
        this.ui = new Ui();
    }

    /**
     * This method is used to run the Duke program. Duke will
     * respond after each command is entered. Entering command
     * "bye" will exit the program.
     */
    public void run() {
        this.taskList.printList();
        Scanner sc = new Scanner(System.in);
        ui.greet();
        ui.start(sc, this.storage, this.taskList);
    }
    public static void main(String[] args) {
        new Duke("tasks.txt").run();
    }



}
