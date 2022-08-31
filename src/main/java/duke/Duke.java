package duke;

import java.io.IOException;
import duke.functions.*;

/**
 * The class of the Duke bot.
 * @author lauralee
 */
public class Duke {

    private Ui newUser;
    private TaskList userTaskList;
    private Storage data;

    /**
     * Duke class constructor that initialises a Duke bot.
     * @param filePath The filepath in which this user wants to store their list of tasks in.
     */
    public Duke(String filePath) {
        this.newUser = new Ui();
        this.userTaskList = newUser.getTaskList();
        this.data = new Storage(this.userTaskList, filePath);
    }

    /**
     * Main method which runs the Duke bot.
     * @param args
     */
    public static void main(String[] args) {
        new Duke("duke.txt");
    }

}

