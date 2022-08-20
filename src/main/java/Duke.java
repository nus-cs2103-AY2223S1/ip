import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private final Ui ui;

    private void list(ArrayList<Task> tasks) {
        for (int i = 0; i < tasks.size(); i++) {
            this.ui.showf("%d: %s", i + 1, tasks.get(i));
        }
    }

    private void mark(ArrayList<Task> tasks, String[] split) throws DukeException {
        if (split.length != 2) {
            throw DukeException.noIndex;
        }
        String indexInput = split[1];
        int i = Integer.parseInt(indexInput);
        if (i <= 0 || i > tasks.size()) {
            throw DukeException.invalidIndex;
        }
        // Subtract 1 to account for 0-index data structure.
        Task task = tasks.get(i - 1);
        task.markAsDone();

        this.ui.showMarkResult(task, i);
    }

    private void unmark(ArrayList<Task> tasks, String[] split) throws DukeException {
        if (split.length != 2) {
            throw DukeException.noIndex;
        }
        String indexInput = split[1];
        int i = Integer.parseInt(indexInput);
        if (i <= 0 || i > tasks.size()) {
            throw DukeException.invalidIndex;
        }
        // Subtract 1 to account for 0-index data structure.
        Task task = tasks.get(i - 1);
        task.markAsUndone();

        this.ui.showUnmarkResult(task, i);
    }

    private void delete(ArrayList<Task> tasks, String[] split) throws DukeException {
        if (split.length != 2) {
            throw DukeException.noIndex;
        }
        String indexInput = split[1];
        int i = Integer.parseInt(indexInput);
        if (i <= 0 || i > tasks.size()) {
            throw DukeException.invalidIndex;
        }
        // Subtract 1 to account for 0-index data structure.
        Task task = tasks.get(i - 1);
        tasks.remove(i - 1);
        int numberOfTasks = tasks.size();

        this.ui.showDeleteResult(task, numberOfTasks);
    }

    private void todo(ArrayList<Task> tasks, String[] split) throws DukeException {
        if (split.length != 2) {
            throw Todo.emptyDescription;
        }
        String description = split[1];
        Todo todo = Todo.create(description);
        tasks.add(todo);
        int numberOfTasks = tasks.size();

        this.ui.showTodoResult(todo, numberOfTasks);
    }

    private void deadline(ArrayList<Task> tasks, String[] split) throws DukeException {
        if (split.length != 2) {
            throw Deadline.emptyDescription;
        }
        String descAndDate = split[1];
        Deadline deadline = Deadline.create(descAndDate);
        tasks.add(deadline);
        int numberOfTasks = tasks.size();

        this.ui.showDeadlineResult(deadline, numberOfTasks);
    }

    private void event(ArrayList<Task> tasks, String[] split) throws DukeException {
        if (split.length != 2) {
            throw Event.emptyDescription;
        }
        String descAndDate = split[1];
        Event event = Event.create(descAndDate);
        tasks.add(event);
        int numberOfTasks = tasks.size();

        this.ui.showEventResult(event, numberOfTasks);
    }

    public Duke() {
        this.ui = new Ui();
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.ui.showWelcomeMessage();

        Scanner scanner = new Scanner(System.in);

        Storage storage = new Storage("data", "data/tasks");

        ArrayList<Task> tasks;
        try {
            tasks = storage.load();
        } catch (DukeException e) {
            duke.ui.showErrorMessage(e);
            // Load with empty list instead.
            tasks = new ArrayList<>();
        }

        scanLoop:
        while (scanner.hasNext()) {
            try {
                String input = scanner.nextLine();

                String[] split = input.split(" ", 2);
                String command = split[0];

                // Handle the various commands.
                switch (command) {
                    case "bye":
                        // Stops the application, by breaking out of the scan loop.
                        break scanLoop;
                    case "list":
                        duke.list(tasks);
                        break;
                    case "mark": {
                        duke.mark(tasks, split);
                        storage.save(tasks);
                        break;
                    }
                    case "unmark": {
                        duke.unmark(tasks, split);
                        storage.save(tasks);
                        break;
                    }
                    case "delete": {
                        duke.delete(tasks, split);
                        storage.save(tasks);
                        break;
                    }
                    case "todo": {
                        duke.todo(tasks, split);
                        storage.save(tasks);
                        break;
                    }
                    case "deadline": {
                        duke.deadline(tasks, split);
                        storage.save(tasks);
                        break;
                    }
                    case "event": {
                        duke.event(tasks, split);
                        storage.save(tasks);
                        break;
                    }
                    default:
                        throw DukeException.unknownCommand;
                }
                duke.ui.showLineBreak();
            } catch (DukeException | IOException e) {
                duke.ui.showErrorMessage(e);
            } catch (NumberFormatException e) {
                // Handles case where user inputs an invalid number.
                duke.ui.showErrorMessage("Invalid number!");
            }
        }

        duke.ui.showGoodbyeMessage();

        // Close scanner.
        scanner.close();
    }
}
