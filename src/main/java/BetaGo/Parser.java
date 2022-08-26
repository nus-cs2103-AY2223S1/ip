package BetaGo;

import BetaGo.Exceptions.EmptyListException;
import BetaGo.Exceptions.InvalidCommandException;

import java.util.Scanner;

public class Parser {
    private TaskList tasks;

    public Parser(TaskList tasks) {
        this.tasks = tasks;
    }
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
                    this.tasks.saveItems();
                } catch (InvalidCommandException e) {
                    Ui.printInvalidMarkerError();
                }
            } else if (inputs[0].equalsIgnoreCase("todo")) {
                try {
                    this.tasks.addTodo(str);
                    this.tasks.saveItems();
                } catch (InvalidCommandException e) {
                    Ui.printInvalidTodoDescriptionError();
                }
            } else if (inputs[0].equalsIgnoreCase("deadline")) {
                try {
                    this.tasks.addDeadline(str);
                    this.tasks.saveItems();
                } catch (InvalidCommandException e) {
                    Ui.printInvalidDeadlineDescriptionError();
                }
            } else if (inputs[0].equalsIgnoreCase("event")) {
                try {
                    this.tasks.addEvent(str);
                    this.tasks.saveItems();
                } catch (InvalidCommandException e) {
                    Ui.printInvalidEventDescriptionError();
                }
            } else if (inputs[0].equalsIgnoreCase("delete")) {
                try {
                    this.tasks.deleteItems(str);
                    this.tasks.saveItems();
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
