package duke;

import duke.task.Task;
import duke.task.TodoTask;
import duke.task.DeadlineTask;
import duke.task.EventTask;

import java.io.IOException;
import java.time.DateTimeException;
import java.util.Scanner;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (Exception e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                Scanner sc = new Scanner(System.in);
                String input = sc.nextLine();
                String parsedInput = Parser.parse(input);
                if (parsedInput.equalsIgnoreCase("bye")) {
                    isExit = true;
                } else if (parsedInput.equalsIgnoreCase("list")) {
                    ui.display(tasks.list());
                } else if (parsedInput.toLowerCase().startsWith("mark")) {
                    int taskNum = Integer.parseInt(parsedInput.substring(4));
                    try {
                        Task task = tasks.mark(taskNum);
                        ui.display(String.format("I've marked this task as complete:%n%s%n", task));
                    } catch (IndexOutOfBoundsException e) {
                        ui.displayError(e);
                    }
                } else if (parsedInput.toLowerCase().startsWith("unmark")) {
                    int taskNum = Integer.parseInt(parsedInput.substring(6));
                    try {
                        Task task = tasks.unmark(taskNum);
                        ui.display(String.format("I've marked this task as incomplete:%n%s%n", task));
                    } catch (IndexOutOfBoundsException e) {
                        ui.displayError(e);
                    }
                } else if (parsedInput.toLowerCase().startsWith("delete")) {
                    int taskNum = Integer.parseInt(parsedInput.substring(6));
                    try {
                        Task task = tasks.delete(taskNum);
                        ui.display(String.format("I've deleted this task:%n%s%n", task));
                    } catch (IndexOutOfBoundsException e) {
                        ui.displayError(e);
                    }
                } else if (parsedInput.toLowerCase().startsWith("dwt")) {
                    String date = parsedInput.substring(3, 11);
                    String time = parsedInput.substring(11, 15);
                    String taskName = parsedInput.substring(15);
                    Task task = new DeadlineTask(taskName, date, time);
                    tasks.add(task);
                    ui.display(String.format("Added new deadline task:%n%s%n", task));
                } else if (parsedInput.toLowerCase().startsWith("dnt")) {
                    String date = parsedInput.substring(3, 11);
                    String taskName = parsedInput.substring(11);
                    Task task = new DeadlineTask(taskName, date);
                    tasks.add(task);
                    ui.display(String.format("Added new deadline task:%n%s%n", task));
                } else if (parsedInput.toLowerCase().startsWith("ewt")) {
                    String date = parsedInput.substring(3, 11);
                    String time = parsedInput.substring(11, 15);
                    String taskName = parsedInput.substring(15);
                    Task task = new EventTask(taskName, date, time);
                    tasks.add(task);
                    ui.display(String.format("Added new event task:%n%s%n", task));
                } else if (parsedInput.toLowerCase().startsWith("ent")) {
                    String date = parsedInput.substring(3, 11);
                    String taskName = parsedInput.substring(11);
                    Task task = new EventTask(taskName, date);
                    tasks.add(task);
                    ui.display(String.format("Added new event task:%n%s%n", task));
                } else if (parsedInput.toLowerCase().startsWith("todo")) {
                    String taskName = parsedInput.substring(4);
                    Task task = new TodoTask(taskName);
                    tasks.add(task);
                    ui.display(String.format("Added new todo task:%n%s%n", task));
                } else {
                    ui.display("Sorry I don't recognise that command :(");
                }
                try {
                    storage.writeToFile(tasks.list());
                } catch (IOException e) {
                    ui.showWritingError();
                }
            } catch (DateTimeException | IllegalArgumentException e) {
               ui.displayError(e);
            }
            ui.showGoodbye();
        }
    }

    public static void main(String[] args) {
        new Duke("data/duke.Duke.txt").run();
    }

}
