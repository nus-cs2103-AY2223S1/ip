package duke;

import java.io.FileNotFoundException;
import java.io.IOException;

import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasklist.TaskList;

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
        Parser parser = new Parser(this.path, this.taskList);
        String response = "";
        try {
            response = parser.parseInput(input);
        } catch (DukeException e) {
            return e.getMessage();
        }
        if (response.equals("Bye. Hope to see you again soon!")) {
            try {
                Storage.writeData(this.taskList, this.path);
            } catch (IOException e) {
                response += "Unfortunately, saving of data has failed.";
            }
        }
        return response;
    }
}

