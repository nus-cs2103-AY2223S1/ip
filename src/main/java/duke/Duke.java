package duke;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Duke {

    private TaskList taskList;
    private String path;

    public Duke(String path) {
        this.path = path;
        this.taskList = new TaskList();
        try {
            Storage.loadData(this.taskList, this.path);
        } catch (FileNotFoundException e) {
        }
    }

    public String getResponse(String input) {
        String response = "";
        try {
            response = Parser.feedDuke(input, this.taskList);
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

