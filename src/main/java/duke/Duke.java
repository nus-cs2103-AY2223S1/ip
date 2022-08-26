package duke;

import java.util.Scanner;
import java.io.IOException;

public class Duke {
    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    private final Parser parser;

    /**
     * Creates a Duke object with the specified file path.
     * @param filePath The file path of the Duke.txt file where the last saved task list will be retrieved from.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        parser = new Parser();
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError(e);
            tasks = new TaskList();
        }
    }

    /**
     * Runs Duke, which starts the Duke chatbot.
     */
    public void run() {
        this.ui.greet();
        Scanner scanner = new Scanner(System.in);
        String input = "";
        while (true) {
            input = scanner.nextLine();
            try {
                if (input.equals("bye")) {
                    break;
                } else if (input.equals("list")) {
                    this.ui.showTaskList(this.tasks);
                } else if (input.startsWith("mark")) {
                    int target = Integer.valueOf(input.split(" ")[1]) - 1;
                    Task toMark = tasks.get(target);
                    toMark.mark();
                    this.ui.showMarked(toMark);
                } else if (input.startsWith("unmark")) {
                    int target = Integer.valueOf(input.split(" ")[1]) - 1;
                    Task toUnmark = tasks.get(target);
                    toUnmark.mark();
                    this.ui.showUnmarked(toUnmark);
                } else if (input.startsWith("todo")) {
                    String[] info = input.split("todo ");
                    if (info.length <= 1) {
                        throw new DukeException("OOPS!! The description of a todo cannot be empty.");
                    }
                    tasks.add(new ToDo(info[1]));
                    ui.showAddedTask(tasks);
                } else if (input.startsWith("deadline")) {
                    String[] info = input.split("deadline ");
                    if (info.length <= 1) {
                        throw new DukeException("OOPS!! The description of a deadline cannot be empty.");
                    }
                    String[] item = info[1].split(" /by ");
                    if (item.length <= 1) {
                        throw new DukeException("OOPS!! A deadline has to be set!");
                    }
                    tasks.add(new Deadline(item[0], item[1]));
                    ui.showAddedTask(tasks);
                } else if (input.startsWith("event")) {
                    String[] info = input.split("event ");
                    if (info.length <= 1) {
                        throw new DukeException("OOPS!! The description of a event cannot be empty.");
                    }
                    String[] item = info[1].split(" /at ");
                    if (item.length <= 1) {
                        throw new DukeException("OOPS!! The timing of the event has to be set!");
                    }
                    tasks.add(new Event(item[0], item[1]));
                    ui.showAddedTask(tasks);
                } else if (input.startsWith("delete")) {
                    String[] info = input.split("delete ");
                    if (info.length <= 1) {
                        throw new DukeException("OOPS!! The item to be deleted has to be specified.");
                    }
                    int target = Integer.valueOf(info[1]) - 1;
                    Task toRemove = tasks.get(target);
                    tasks.remove(target);
                    ui.showRemoved(toRemove);
                } else {
                    throw new DukeException("OOPS!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException error) {
                ui.showError(error);
            }

        }
        ui.showSaving();
        saveFile(tasks);
        ui.showSaved();
        scanner.close();
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Saves the current task list in Duke.txt.
     * @param tasks List of tasks to be saved.
     */
    public void saveFile(TaskList tasks) {
        try {
            this.storage.save(tasks);
        } catch (IOException e) {
            ui.showError(e);
        }
    }


    public static void main(String[] args) {
        new Duke("Duke.txt").run();
    }
}
