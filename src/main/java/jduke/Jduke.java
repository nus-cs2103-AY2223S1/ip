package jduke;

import jduke.data.TaskList;
import jduke.data.exception.JdukeException;
import jduke.parser.Parser;
import jduke.storage.Storage;
import jduke.ui.Ui;

public class Jduke {
    public enum Command {
        LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE
    }

    private TaskList list;
    private Storage storage;
    private Ui ui;

    public Jduke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.list = new TaskList(storage.load());
        } catch (JdukeException e) {
            this.ui.showErrorMessage(e.getMessage());
            this.list = new TaskList();
        }
    }

    public void run() {
        this.ui.showGreeting();
        String input = this.ui.getUserCommand();
        while (!input.equals("bye")) {
            try {
                Command mainCmd = Parser.parseMainCommand(input);
                String cmdParams = Parser.parseCommandParams(input);
                switch (mainCmd) {
                case LIST:
                    list.listTasks(cmdParams);
                    break;
                case MARK:
                    list.markTask(cmdParams);
                    break;
                case UNMARK:
                    list.unmarkTask(cmdParams);
                    break;
                case TODO:
                    list.addTodo(cmdParams);
                    break;
                case DEADLINE:
                    list.addDeadline(cmdParams);
                    break;
                case EVENT:
                    list.addEvent(cmdParams);
                    break;
                case DELETE:
                    list.deleteTask(cmdParams);
                    break;
                }
                if (!mainCmd.equals(Command.LIST)) {
                    storage.saveAllTasks(list.getTasksToStore());
                }
            } catch (JdukeException e) {
                this.ui.showErrorMessage(e.getMessage());
            } finally {
                input = this.ui.getUserCommand();
            }
        }
        this.ui.showGoodbye();
    }
    public static void main(String[] args) {
        new Jduke("data/tasks.txt").run();
    }
}
