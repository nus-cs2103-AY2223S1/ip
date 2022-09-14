package duke;

import java.util.Scanner;

import duke.Exceptions.NoSuchCommandException;

/**
 * Main class for duke application
 */
public class Duke {
    private TaskList taskList;
    private Parser parser;
    private Ui ui;
    private Storage storage;

    /**
     * Enum list of commands
     */
    public enum Commands {
        LIST,
        BYE,
        TODO,
        MARK,
        UNMARK,
        EVENT,
        DEADLINE,
        DELETE,
        FIND
    }

    /**
     * Constructor for Duke
     * @param parser
     * @param ui
     * @param taskList
     * @param storage
     */
    public Duke(Parser parser, Ui ui, TaskList taskList, Storage storage) {
        this.parser = parser;
        this.ui = ui;
        this.taskList = taskList;
        this.storage = storage;
    }



    public String getResponse(String input) {
        String response = parser.executeInput(this.ui, input, this.storage, this.taskList);
        return response;
    }


}
