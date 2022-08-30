package duke;

import duke.command.Command;

public class Duke {
    public Ui ui;
    public TaskList taskList;
    public Storage storage;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
    }

    public enum Keyword
    {
        EXIT("bye"), LIST("list"), TODO("todo"), DEADLINE("deadline"), EVENT("event"), DELETE("delete"), MARK("mark"),
        UNMARK("unmark");

        private String keyword;

        private Keyword(String keyword) {
            this.keyword = keyword;
        }

        public String getKeyword() {
            return this.keyword;
        }
    }

    public void run() {
        ui.printGreetingMessage();
        taskList = new TaskList(storage.loadTaskList());
        String userInput;

        while(true) {
            userInput = ui.getUserInput();
            try {
                Command command = Parser.parse(userInput);
                command.execute(taskList, storage, ui);
                if (command.isExit()) {
                    break;
                }
            } catch (DukeException exception) {
                ui.printMessage(exception.toString() + "\n");
            }
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke("data/duke.txt");
        duke.run();
    }
}
