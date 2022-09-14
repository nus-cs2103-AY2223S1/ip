package duke;

import duke.exception.DukeException;
import duke.task.TaskList;
import duke.util.Parser;
import duke.util.StoredTasks;
import duke.util.Ui;
import duke.util.command.Command;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class Duke {

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private static final String LINE = "\n----------------------------------------------------------------\n";
    private Ui ui;
    private StoredTasks storedTasks;
    private TaskList taskList;

    /**
     * Constructor for Duke.
     *
     * @param fileDir  the directory of an existing text database
     * @param filePath the path to an existing text database
     */
    public Duke(String fileDir, String filePath) {
        this.storedTasks = new StoredTasks(fileDir, filePath);
        this.ui = new Ui();
        try {
            this.taskList = new TaskList(this.storedTasks.load());
        } catch (DukeException e) {
            this.taskList = new TaskList();
        }
    }



//
    public void run() {
        while (true) {
            String temp = this.ui.readInput();
            try {
                if (temp.equals("bye")) {
                    System.out.println(LINE + "Bye bro! Have a nice day!" + LINE);
                    this.ui.closeInput();
                    this.storedTasks.save(taskList);
                    break;
                }
                Parser.parseCommand(temp);
            } catch (DukeException err) {
                this.ui.closeInput();
                this.storedTasks.save(taskList);
                System.out.println(err);
                break;
            }

        }
    }

    public String getResponse(String input) {
        try {
            Command command = Parser.parseCommand(input);
            return command.handleCommand(taskList, storedTasks);
        } catch (DukeException de) {
            this.storedTasks.save(this.taskList);
            System.out.println(de);
            return de.toString();
        }
    }

}
