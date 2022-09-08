package duke;

import duke.command.Command;

public class Duke {
    private TaskList taskList;
    private Storage storage;

    public Duke(String filePath) {
        storage = new Storage(filePath);
        taskList = new TaskList(storage.loadTaskList());
    }

    public enum Keyword {
        EXIT("bye"), LIST("list"), TODO("todo"), DEADLINE("deadline"), EVENT("event"), DELETE("delete"), MARK("mark"),
        UNMARK("unmark"), FIND("find");

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
            Command command = Parser.parse(userInput);
            return command.execute(taskList, storage);
        } catch (DukeException exception) {
            return exception.toString();
        }
    }

}
