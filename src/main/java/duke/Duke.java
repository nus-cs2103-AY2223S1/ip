package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TimedTask;
import duke.task.Todo;

import java.util.Scanner;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        try {
            storage = new Storage(filePath);
        } catch (DukeException e) {
            ui.showError(e);
        }
        try {
            tasks = storage.getData();
            ui.showSuccess("Data is loaded successfully.");
        } catch (DukeException e) {
            ui.showError(e);
            tasks = new TaskList();
        }
    }
    public static void main(String[] args) {
        Duke duke = new Duke("data/duke.txt");
        duke.run();
    }

    private void run() {
        ui.showGreeting();
        Scanner sc = new Scanner(System.in);
        scanner: while (sc.hasNextLine()) {
            String[] parsedCommand;
            Task task;
            try {
                parsedCommand = Parser.parse(sc.nextLine());
            } catch (DukeException e) {
                ui.showError(e);
                continue;
            }
            try {
                switch (parsedCommand[0]) {
                case "list":
                    if (parsedCommand.length == 1) {
                        ui.showTasks(tasks);
                    } else {
                        ui.showTasks(tasks, parsedCommand[1]);
                    }
                    break;
                
                case "todo":
                    tasks.add(new Todo(parsedCommand[1]));
                    ui.showSuccess("Got it. I've added this todo:\n  %s\nNow you have %d tasks in the list.", tasks.get(tasks.size()), tasks.size());
                    break;

                case "deadline":
                    tasks.add(new Deadline(parsedCommand[1], parsedCommand[2]));
                    ui.showSuccess("Got it. I've added this deadline:\n  %s\nNow you have %d tasks in the list.", tasks.get(tasks.size()), tasks.size());
                    break;

                case "event":
                    tasks.add(new Event(parsedCommand[1], parsedCommand[2]));
                    ui.showSuccess("Got it. I've added this event:\n  %s\nNow you have %d tasks in the list.", tasks.get(tasks.size()), tasks.size());
                    break;
                
                case "sort":
                    tasks.sort();
                    ui.showTasks(tasks);
                    break;
                
                case "find":
                    ui.showTasks(tasks.filter(parsedCommand[1]));
                    break;

                case "format":
                    TimedTask.setFormat(parsedCommand[1]);
                    ui.showTasks(tasks);
                    break;

                case "mark":
                    task = tasks.mark(Integer.parseInt(parsedCommand[1]));
                    ui.showSuccess("Nice! I've marked this task as done:\n  " + task);
                    break;

                case "unmark":
                    task = tasks.unmark(Integer.parseInt(parsedCommand[1]));
                    ui.showSuccess("OK, I've marked this task as not done yet:\n  " + task);
                    break;
                
                case "delete":
                    task = tasks.delete(Integer.parseInt(parsedCommand[1]));
                    ui.showSuccess("Noted. I've removed this task:\n  %s\nNow you have %d tasks in the list.", task, tasks.size());
                    break;
    
                case "bye":
                    ui.close();
                    break scanner;
                }
            } catch (DukeException e) {
                ui.showError(e);
            }
        }
        sc.close();
        try {
            storage.saveData(tasks);
            ui.showSuccess("Data is saved successfully.");
        } catch (DukeException e) {
            ui.showError(e);
        }
    }
}