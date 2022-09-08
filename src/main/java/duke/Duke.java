package duke;

import duke.task.TaskList;
import duke.util.Parser;
import duke.util.SaveTasks;
import duke.util.Ui;
import duke.util.command.Command;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.Region;
import javafx.scene.control.Label;

import java.nio.file.Paths;

public class Duke {

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private static final String LINE = "\n----------------------------------------------------------------\n";
    private Ui ui;
    private SaveTasks savedTasks;
    private TaskList taskList;

    /**
     * Constructor for Duke.
     *
     * @param fileDir  the directory of an existing text database
     * @param filePath the path to an existing text database
     */
    public Duke(String fileDir, String filePath) {
        this.savedTasks = new SaveTasks(fileDir, filePath);
        this.ui = new Ui();
        try {
            this.taskList = new TaskList(this.savedTasks.load());
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
                    System.out.println(LINE + "Bye. Hope to see you again!" + LINE);
                    this.ui.closeInput();
                    this.savedTasks.save(taskList);
                    break;
                }
                Parser.parseCommand(temp, taskList);
            } catch (DukeException err) {
                this.ui.closeInput();
                this.savedTasks.save(taskList);
                System.out.println(err);
                break;
            }

        }
    }

    public String getResponse(String input) {
        try {
            Command command = Parser.parseCommand(input, taskList);
            return command.handleCommand(taskList, savedTasks);
        } catch (DukeException de) {
            this.savedTasks.save(this.taskList);
            System.out.println(de);
            return de.toString();
        }
    }

}
