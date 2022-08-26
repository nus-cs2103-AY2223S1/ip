package luffy;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Parser {
    private Ui ui;

    public Parser() {
        this.ui = new Ui();
    }

    public void parse(String s, TaskList tasks) {
        ui.printDivider();
        if (s.equals("list")) {
            ui.printMessage(tasks.toString());
        } else if (s.length() >= 6 && s.substring(0, 4).equals("mark")) {
            try {
                int taskIndex = Integer.parseInt(s.substring(5, 6)) - 1;
                if (taskIndex >= 0 && taskIndex < tasks.getSize()) {
                    tasks.markCompleted(taskIndex);
                } else {
                    ui.printErrorMessage("Task index out of bounds!");
                }
            } catch (StringIndexOutOfBoundsException e) {
                ui.printErrorMessage("Task index cannot be empty!");
            }
        } else if (s.length() >= 8 && s.substring(0, 6).equals("unmark")) {
            try {
                int taskIndex = Integer.parseInt(s.substring(7, 8)) - 1;
                if (taskIndex >= 0 && taskIndex < tasks.getSize()) {
                    tasks.markUncompleted(taskIndex);
                } else {
                    ui.printErrorMessage("");
                }
            } catch (StringIndexOutOfBoundsException e) {
                ui.printErrorMessage("Task index cannot be empty!");
            }
        } else {
            Task newTask;
            if (s.length() >= 4 && s.substring(0, 4).equals("todo")) {
                try {
                    newTask = new Todo(s.substring(5));
                    tasks.add(newTask);
                    ui.printTaskListStatus(tasks);
                } catch(StringIndexOutOfBoundsException e) {
                    ui.printErrorMessage("The description of a todo cannot be empty.");
                }
            } else if (s.length() >= 8 && s.substring(0, 8).equals("deadline")){
                try {
                    String[] splitString = s.split(" /by ");
                    LocalDate deadlineDate = LocalDate.parse(splitString[1]);
                    newTask = new Deadline(splitString[0].substring(9), deadlineDate.toString());
                    tasks.add(newTask);
                    ui.printTaskListStatus(tasks);
                } catch (StringIndexOutOfBoundsException e) {
                    ui.printErrorMessage("The description of a deadline cannot be empty.");
                } catch (ArrayIndexOutOfBoundsException e) {
                    ui.printErrorMessage("The date of a deadline cannot be empty.");
                } catch (DateTimeParseException e) {
                    ui.printErrorMessage("The date of a deadline must be in format yyyy-mm-dd.");
                }
            } else if (s.length() >= 5 && s.substring(0, 5).equals("event")) {
                try {
                    String[] splitString = s.split(" /at ");
                    LocalDate eventDate = LocalDate.parse(splitString[1]);
                    newTask = new Event(splitString[0].substring(6), eventDate.toString());
                    tasks.add(newTask);
                    ui.printTaskListStatus(tasks);
                } catch (StringIndexOutOfBoundsException e) {
                    ui.printErrorMessage("The description of a event cannot be empty.");
                } catch (ArrayIndexOutOfBoundsException e) {
                    ui.printErrorMessage("The period of an event cannot be empty.");
                } catch (DateTimeParseException e) {
                    ui.printErrorMessage("The date of a deadline must be in format yyyy-mm-dd.");
                }
            } else if (s.length() >= 6 && s.substring(0, 6).equals("delete")) {
                try {
                    int taskIndex = Integer.parseInt(s.substring(7, 8)) - 1;
                    if (taskIndex >= 0 && taskIndex < tasks.getSize()) {
                        tasks.delete(taskIndex);
                        ui.printTaskListStatus(tasks);
                    } else {
                        ui.printErrorMessage("Task index " + (taskIndex + 1) + " is not valid!");
                    }
                } catch (StringIndexOutOfBoundsException e) {
                    ui.printErrorMessage("Task index cannot be empty.");
                }
            } else {
                ui.printErrorMessage("I'm sorry, but I don't know what that means :-(");
            }
        }
        ui.printDivider();
    }
}
