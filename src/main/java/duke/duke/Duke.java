package duke.duke;

import duke.command.Command;
import duke.task.TaskList;
import duke.util.Parser;
import duke.util.Storage;


/**
 * Represents the main class of the Duke application
 */
public class Duke {
    public static final String EXIT_MESSAGE = "Bye. Hope to see you again soon! \n\nApplication is now closing.";
    public static final String GREETING_MESSAGE = "Hello! I'm Duke\nWhat can I do for you?";
    public static final String TITLE = "Duke";

    private TaskList taskList;
    private Storage storage;

    /**
     * Represents a Duke object.
     * Initialises Storage and TaskList, data of tasklist is read from provided filepath using method
     * from Storage class
     *
     * @param filePath File path of the data text file
     * @throws DukeException from the loadTaskList method
     */
    public Duke(String filePath) throws DukeException {
        assert !filePath.isEmpty() : "Filepath should not be empty";
        storage = new Storage(filePath);
        taskList = new TaskList(storage.loadTaskList());
    }

    /**
     * Enum that represents the commands keywords Duke can process
     */
    public enum Keyword {
        EXIT("bye"), LIST("list"), TODO("todo"), DEADLINE("deadline"), EVENT("event"), DELETE("delete"), MARK("mark"),
        UNMARK("unmark"), FIND("find"), PRIORITY("priority");

        private String keyword;

        private Keyword(String keyword) {
            this.keyword = keyword;
        }

        public String getKeyword() {
            return this.keyword;
        }
    }

    /**
     * Returns the response from the user after parsing and executing the command.
     *
     * @param userInput Input from the user.
     * @return text response of the executed command.
     */
    public String getResponse(String userInput) {
        try {
            assert !userInput.isEmpty() : "User input should not be empty";
            Command command = Parser.parse(userInput);
            return command.execute(taskList, storage);
        } catch (DukeException exception) {
            return exception.toString();
        }
    }

}
