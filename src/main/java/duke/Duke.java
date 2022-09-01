package duke;

import duke.exception.DukeException;
import duke.util.Parser;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Main code for Duke.
 */
public class Duke {
    private final Storage storage;
    private TaskList list;
    private final Parser p;
    private boolean isExiting;

    public Duke() {
        storage = new Storage("data" + File.separator + "taskList.txt");
        try {
            list = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            list = new TaskList();
        }
        p = new Parser(list);
        isExiting = false;
    }

    public String getResponse(String userInput) {
        String response;
        try {
            response = p.parseInput(userInput, false);
        } catch (DukeException e) {
            return Ui.showErrorOccurred(e);
        }
        return response;
    }

    public void exit() {
        storage.saveToFile(list);
    }

    public static void main(String[] args) {

    }
}
