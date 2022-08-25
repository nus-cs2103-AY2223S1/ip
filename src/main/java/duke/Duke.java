package duke;

import duke.data.Storage;
import duke.data.TaskList;
import duke.task.DeadlineTask;
import duke.task.EventTask;
import duke.task.TodoTask;
import duke.util.DukeException;
import duke.util.Parser;
import duke.util.Ui;

import java.util.Scanner;

/**
 * Takes in input and chats with user.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    /**
     * Constructor for Duke Class.
     * @param filePath file path of data.
     */
    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.tasks = new TaskList(storage.loadData());
        this.parser = new Parser();
    }

    public static void main(String[] args) {
        new Duke("src/main/java/duke/data/data.txt").run();
    }

    /**
     * Echos the input and stores it in the list
     * Exit when user types "bye"
     **/
    public void run() {
        Scanner input = new Scanner(System.in);
        ui.showWelcome();
        while (input.hasNext()) {
            storage.saveData(tasks);
            String action = input.nextLine();
            switch (parser.parseCommand(action)) {
            case "bye":
                ui.exit();
                return;
            case "list":
                ui.getList(tasks.getList());
                break;
            case "markTask":
                markTask(action);
                break;
            case "unMarkTask":
                unMarkTask(action);
                break;
            case "deleteTask":
                deleteTask(action);
                break;
            case "addToList":
                addToList(action);
                break;
            default:
                ui.cannotUnderstandError();
                break;
            }
        }
    }

    /**
     * Deletes specified task
     * @param action takes in action from input
     */
    public void deleteTask(String action) {
        try {
            String i = action.substring(6).replaceAll(" ", "");
            int index = Integer.parseInt(i) - 1;
            String removedTask = tasks.getTask(index);
            tasks.removeTask(index);
            ui.removeTask(removedTask, tasks.getSize());
        } catch (Exception e) {
            ui.noSuchTaskError();
        }
    }

    /**
     * determines which type of task and input into list accordingly
     * @param action takes in action from input
     */
    public void addToList(String action) {
        try {
            switch (parser.parseTaskType(action)) {
            case "todoTask":
                tasks.addTask(new TodoTask(action.substring(4).strip()));
                break;
            case "eventTask":
                int i = action.indexOf('/');
                String event = action.substring(i + 3).strip();
                tasks.addTask(new EventTask(action.substring(5, i).strip(), event));
                break;
            case "deadlineTask":
                int j = action.indexOf('/');
                String by = action.substring(j + 3).strip();
                tasks.addTask(new DeadlineTask(action.substring(8, j).strip(), by));
                break;
            default:
                throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
            ui.addedTask(tasks.getTask(tasks.getSize() - 1), tasks.getSize());
        } catch (DukeException e) {
            ui.cannotUnderstandError();
        }
    }

    /**
     * edits the task to check if mark or unmarked
     * @param action action of task
     * @param mark status if mark or unmark
     */
    public void editTask(String action, boolean mark) {
        try {
            String i = mark ? action.substring(4): action.substring(6);
            i = i.replaceAll(" ", "");
            int index = Integer.parseInt(i) - 1;
            tasks.markTaskStatus(index, mark);
            ui.markedTask(mark, tasks.getTask(index));
        } catch (Exception e) {
            ui.noSuchTaskError();
        }
    }

    /**
     * marks the task to done
     * @param action task action
     */
    public void markTask(String action) {
        editTask(action, true);
    }

    /**
     * set the task to undone
     * @param action task action
     */
    public void unMarkTask(String action) {
        editTask(action, false);
    }
}
