package duke;

import duke.command.Parser;
import duke.storage.Storage;
import duke.task.*;
import duke.utilities.DukeException;
import duke.utilities.Ui;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        try {
            storage = new Storage(filePath);
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            this.tasks = new TaskList();
        } catch (IOException e) {
            ui.printIoException(e);
            this.tasks = new TaskList();
        }
    }

    public void run() {
        this.ui.printDukeOpening();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String current = this.ui.nextLine();

        while (!current.equals("bye")) {
            try {
                String information = Parser.parseCommand(current);
                String[] parts = information.split("\\|");

                switch (parts[0]) {
                case "list":
                    this.ui.printTasks(this.tasks);
                    break;
                case "mark":
                    Task marked = this.tasks.changeTaskStatus(Integer.parseInt(parts[1]), true);
                    this.ui.printChangeTaskStatus(marked, true);
                    break;
                case "unmark":
                    Task unmarked = this.tasks.changeTaskStatus(Integer.parseInt(parts[1]), false);
                    this.ui.printChangeTaskStatus(unmarked, false);
                    break;
                case "delete":
                    Task deleted = this.tasks.removeTask(Integer.parseInt(parts[1]));
                    this.ui.printDeleteTask(deleted, this.tasks);
                    break;
                case "todo":
                    Task todo = new Todo(parts[1]);
                    tasks.addTask(todo);
                    this.ui.printAddTask(todo, this.tasks);
                    break;
                case "deadline":
                    Task deadline = new Deadline(
                            parts[1],
                            LocalDateTime.parse(parts[2], dateTimeFormatter)
                    );
                    tasks.addTask(deadline);
                    this.ui.printAddTask(deadline, this.tasks);
                    break;
                case "event":
                    Task event = new Event(
                            parts[1],
                            LocalDateTime.parse(parts[2], dateTimeFormatter),
                            LocalDateTime.parse(parts[3], dateTimeFormatter)
                    );
                    tasks.addTask(event);
                    this.ui.printAddTask(event, this.tasks);
                    break;
                default:
                    throw new DukeException("I'm sorry but I don't know what that means!");
                }
            } catch (DukeException e) {
                this.ui.printDukeException(e);
            } catch (DateTimeParseException e) {
                this.ui.printDateTimeParseException();
            }

            current = this.ui.nextLine();
        }

        try {
            this.storage.writeTasksToStorage(this.tasks);
        } catch (IOException e) {
            this.ui.printIoException(e);
        }

        this.ui.printDukeClosing();
        this.ui.closeScanner();
        System.exit(0);
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}