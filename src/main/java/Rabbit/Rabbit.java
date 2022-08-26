package Rabbit;

import Rabbit.util.Ui;
import Rabbit.util.Parser;
import Rabbit.util.TaskList;
import Rabbit.util.Storage;
import Rabbit.RabbitException.RabbitException;
import Rabbit.RabbitException.ImportDataException;
import Rabbit.RabbitException.InvalidInputException;


/**
 * Rabbit is a short-tempered, annoyed bot that puts in her 30% efforts
 *  to help you solve some simple problems as her part-time jpb.
 *
 * @author Jiang Zhimeng
 */
public class Rabbit {
    private Ui ui;
    private Storage storage;
    private TaskList taskList;

    public Rabbit() {
        this.ui = new Ui();
        this.storage = new Storage();
        this.taskList = new TaskList();
    }

    private void run() {
        try {
            this.storage.importData(this.taskList);

        } catch (ImportDataException e) {
            this.ui.showException(e);
        }

        this.ui.showGreet();

        while (true) {
            String input = this.ui.readCommand();
            if (input.equals("bye")) {
                this.ui.showBye();
                this.ui.endCommand();
                break;
            }
            // the function that the input is calling
            String function = input.substring(0, Parser.parseFunction(input));
            try {
                String content = "";
                switch (function) {
                case "list":
                    this.taskList.list();
                    break;
                case "find ":
                    content = this.taskList.find(input);
                    this.ui.showFind(content);
                    break;
                case "mark ":
                    this.taskList.mark(input);
                    this.storage.exportData(this.taskList);
                    this.ui.showMark();
                    break;
                case "unmark ":
                    this.taskList.unmark(input);
                    this.storage.exportData(this.taskList);
                    this.ui.showUnmark();
                    break;
                case "todo ":
                    content = this.taskList.addToList(TaskList.TaskType.TODO, input);
                    this.storage.exportData(this.taskList);
                    this.ui.showAddToList(content);
                    break;
                case "deadline ":
                    content = this.taskList.addToList(TaskList.TaskType.DEADLINE, input);
                    this.storage.exportData(this.taskList);
                    this.ui.showAddToList(content);
                    break;
                case "event ":
                    content = this.taskList.addToList(TaskList.TaskType.EVENT, input);
                    this.storage.exportData(this.taskList);
                    this.ui.showAddToList(content);
                    break;
                case "delete ":
                    this.taskList.delete(input);
                    this.storage.exportData(this.taskList);
                    this.ui.showDelete();
                    break;
                default:
                    // the user keyed in an invalid input
                    throw new InvalidInputException();
                }

            } catch (RabbitException e) {
                this.ui.showException(e);
            }
        }
    }

    /**
     * Main method of Rabbit.
     *
     * @param args The commandline arguments.
     */
    public static void main(String[] args) {
        Rabbit rabbit = new Rabbit();
        rabbit.run();
    }
}
