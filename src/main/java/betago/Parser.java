package betago;

import betago.exceptions.EmptyListException;
import betago.exceptions.InvalidCommandException;

import java.util.Scanner;

/**
 * Parser class that reads input from user and calls the corresponding methods accordingly.
 */
public class Parser {
    private TaskList tasks;
    private Storage storage;

    /**
     * Constructor for Parser.
     * Initialises TaskList variable.
     */
    public Parser(TaskList tasks, Storage storage) {
        this.tasks = tasks;
        this.storage = storage;
    }

    /**
     * Reads input from the user and calls the corresponding methods.
     */
    public void readCommands() {

        Scanner sc = new Scanner(System.in);
        String str= sc.nextLine();
        String[] inputs = str.split(" ", 2);

        while (!str.equalsIgnoreCase("bye")) {
            if (str.equalsIgnoreCase("list")) {
                try {
                    this.tasks.listItems();
                } catch (EmptyListException e) {
                    Ui.printEmptyList();
                }
            } else if (inputs[0].equalsIgnoreCase("mark") || inputs[0].equalsIgnoreCase("unmark")) {
                try {
                    this.tasks.markUnmarkItems(str);
                    this.storage.saveItems();
                } catch (InvalidCommandException e) {
                    Ui.printInvalidMarkerError();
                }
            } else if (inputs[0].equalsIgnoreCase("todo")) {
                try {
                    this.tasks.addTodo(str);
                    this.storage.saveItems();
                } catch (InvalidCommandException e) {
                    Ui.printInvalidTodoDescriptionError();
                }
            } else if (inputs[0].equalsIgnoreCase("deadline")) {
                try {
                    this.tasks.addDeadline(str);
                    this.storage.saveItems();
                } catch (InvalidCommandException e) {
                    Ui.printInvalidDeadlineDescriptionError();
                }
            } else if (inputs[0].equalsIgnoreCase("event")) {
                try {
                    this.tasks.addEvent(str);
                    this.storage.saveItems();
                } catch (InvalidCommandException e) {
                    Ui.printInvalidEventDescriptionError();
                }
            } else if (inputs[0].equalsIgnoreCase("delete")) {
                try {
                    this.tasks.deleteItems(str);
                    this.storage.saveItems();
                } catch (InvalidCommandException e) {
                    Ui.printInvalidMarkerError();
                }
            } else if (inputs[0].equalsIgnoreCase("find")) {
                try {
                    this.tasks.findTasks(str);
                } catch (InvalidCommandException e) {
                    Ui.printNoFindKeywordError();
                }

            } else {
                Ui.printInvalidCommands();
            }
            str = sc.nextLine();
            inputs = str.split(" ", 2);
        }
    }

}
