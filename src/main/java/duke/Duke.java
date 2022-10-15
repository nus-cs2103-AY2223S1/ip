package duke;

import java.io.FileNotFoundException;
import java.io.IOException;

import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import javafx.application.Platform;

public class Duke {

    private TaskList taskList;
    private String path;

    public Duke(String path) {
        this.path = path;
        this.taskList = new TaskList();
        try {
            Storage.loadData(this.taskList, this.path);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public String getResponse(String input) {
        Parser parser = new Parser(this.taskList);
        String response = "";
        String storageErrorMessage = "";

        try {
            response = parser.parseInput(input);
        } catch (DukeException e) {
            return e.getMessage();
        }

        //Save tasks to file after every valid input.
        try {
            Storage.writeData(this.taskList, this.path);
        } catch (IOException e) {
            storageErrorMessage = System.lineSeparator() 
                    + "Saving of tasks has failed. " + e.getMessage();
        }

        //Check for exit response.
        if (response.equals("Bye. Hope to see you again soon!")) {
            Platform.exit();
        }
        return response + storageErrorMessage;
    }
}

