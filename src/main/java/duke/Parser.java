package duke;

import java.io.IOException;

public class Parser {
    private Ui ui;
    private TaskList taskList;
    private Storage storage;

    /**
     * Constructor to create instance of Parser
     * which will make sense of user input
     * Loads the file from data
     *
     */
    public Parser(Ui ui,Storage storage) {
        this.ui = ui;
        this.storage = storage;
        try {
            this.taskList = this.storage.load();
        } catch (IOException error) {
            this.ui.showError(error.getMessage());
        }
    }

    /**
     * Processes the user input and if
     * it is exiting, will return false
     * else return true
     *
     * @return Boolean of the process
     */
    public String process(String cmd) {
        try {
            if (cmd.toLowerCase().equals("bye")) {
                return this.ui.end();
            } else if (cmd.toLowerCase().equals("list")) {
                String log = this.taskList.handleList();
                this.storage.save(this.taskList.getTaskList());
                return ui.printMessage(log);
            } else if (cmd.toLowerCase().startsWith("mark")) {
                String log = this.taskList.handleMark(cmd);
                this.storage.save(this.taskList.getTaskList());
                return ui.printMessage(log);
            } else if (cmd.toLowerCase().startsWith("unmark")) {
                String log = this.taskList.handleUnmark(cmd);
                this.storage.save(this.taskList.getTaskList());
                return ui.printMessage(log);
            } else if (cmd.toLowerCase().startsWith("todo")) {
                String log = this.taskList.handleToDo(cmd);
                this.storage.save(this.taskList.getTaskList());
                return ui.printMessage(log);
            } else if (cmd.toLowerCase().startsWith("deadline")) {
                String log = this.taskList.handleDeadline(cmd);
                this.storage.save(this.taskList.getTaskList());
                return ui.printMessage(log);
            } else if (cmd.toLowerCase().startsWith("event")) {
                String log = this.taskList.handleEvent(cmd);
                this.storage.save(this.taskList.getTaskList());
                return ui.printMessage(log);
            } else if (cmd.toLowerCase().startsWith("delete")) {
                String log = this.taskList.handleDelete(cmd);
                this.storage.save(this.taskList.getTaskList());
                return ui.printMessage(log);
            } else if (cmd.toLowerCase().startsWith("find")){
                String log = this.taskList.handleFind(cmd);
                return ui.printMessage(log);
            } else {
                throw new InvalidInputException(cmd);
            }
        } catch (DukeException e) {
            return ui.showError(e.toString());
        }
    }
}
