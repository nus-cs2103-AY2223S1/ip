package duke;

import duke.command.Command;
import duke.exceptions.DukeException;
import duke.exceptions.ImproperFormatException;

import java.io.FileNotFoundException;

public class Duke {

    private Ui ui;
    private TaskList taskList;
    private Storage storage;
    public Duke() {
        this.ui = new Ui();
        this.taskList = new TaskList();
        this.storage = new Storage();
        this.storage.isCreated();
        try {
            this.storage.load(this.taskList);
        } catch (FileNotFoundException e) {
            System.out.println("PLEASE RESTART DUKE");
        } catch (ImproperFormatException e) {
            System.out.println("CORRUPTED DATA");
        }
    }
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(this.ui, this.taskList, this.storage);

        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}