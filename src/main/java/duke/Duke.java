package duke;

import duke.exception.DukeException;
import duke.task.TaskList;
import duke.util.Parser;
import duke.util.StoredTasks;
import duke.util.Ui;
import duke.util.command.Command;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.util.Duration;

public class Duke {

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

    //@author Sen Wei-reused
    // Reused from https://stackoverflow.com/questions/27334455
    // with minor modification

    /**
     * Delays the closing of the GUI for 2 seconds.
     */
    private void executeDelay() {
        PauseTransition delay = new PauseTransition(Duration.seconds(2));
        delay.setOnFinished(event -> Platform.exit());
        delay.play();
    }

    public String getResponse(String input) {
        try {
            Command command = Parser.parseCommand(input);
            if (input.equals("bye")) {
                System.out.println(command.handleCommand(taskList, storedTasks));
                executeDelay();
            }

            return command.handleCommand(taskList, storedTasks);
        } catch (DukeException de) {
            this.storedTasks.save(this.taskList);
            System.out.println(de);
            return de.toString();
        }
    }

}
