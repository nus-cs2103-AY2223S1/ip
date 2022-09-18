package duke;

import java.io.IOException;

import exceptions.UnknownCommandException;

/**
 * Duke is the main class of this program. It is a todolist where users can enter 3 types of commands.
 * The commands that are available is todo, event, deadline, list, bye, mark, unmark and delete.
 */
public class Duke {
    private final Ui ui;
    private final Storage storage;
    private final TaskList taskList;

    /**
     * Constructor for Duke.
     * Load tasks into tasks list if there is storage
     */
    public Duke() {
        this.taskList = new TaskList();
        this.storage = new Storage("data/duke.txt", taskList);
        TagList tagList = new TagList();
        try {
            storage.loadFile();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        this.ui = new Ui(taskList, storage, tagList);
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.start();
    }


    public void start() {
        ui.run();
    }


    public String getReponse(String nextCommand) {
        try {
            return ui.getResponse(nextCommand);
        } catch (UnknownCommandException e) {
            return e.getMessage();
        }
    }


}
