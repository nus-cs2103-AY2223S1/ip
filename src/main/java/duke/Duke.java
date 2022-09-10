package duke;

import java.util.Scanner;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import duke.parser.CommandParser;
import duke.storage.Storage;
import duke.tasks.TaskList;

public class Duke {

    private static final Storage STORAGE = new Storage();
    private static final TaskList TASK_LIST = STORAGE.load();
    private static final CommandParser COMMAND_PARSER = new CommandParser(TASK_LIST);
    private boolean hasTerminated;

    public String getResponse(String command) {
        if (hasTerminated) {
            System.exit(0);
        }
        if (command.equalsIgnoreCase("bye")) {
                STORAGE.save();
                hasTerminated = true;
                return "Bye! Enter any key to exit :)";
        }

        assert !hasTerminated : "hasTerminated should be false";

        return COMMAND_PARSER.handle(command);
    }

    public boolean isHasTerminated() {
        return hasTerminated;
    }
}
