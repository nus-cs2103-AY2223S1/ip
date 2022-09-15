package duke.duke;

import duke.util.Parser;
import duke.util.Storage;
import duke.task.TaskList;
import duke.command.Command;

/** Represents the main class of the Duke application */
public class Duke {
    private TaskList taskList;
    private Storage storage;

    public static final String GREETING_MESSAGE = "Hello! I'm Duke\nWhat can I do for you?";
    public static final String TITLE = "Duke";

    public Duke(String filePath) throws DukeException{
        assert !filePath.isEmpty() : "Filepath should not be empty";
        storage = new Storage(filePath);
        taskList = new TaskList(storage.loadTaskList());
    }

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
