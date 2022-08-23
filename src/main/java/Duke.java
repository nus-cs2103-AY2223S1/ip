import java.util.List;

public class Duke {
    public static void main(String[] args) {
        Storage storage = new Storage("./tasks.txt");
        List<Task> tasks = storage.readTasks();
        Ui ui = new Ui();

        ui.showWelcome();
        while (true) {
            String command = ui.readCommand();
            if (command.equals("list")) {
                ui.showTaskList(tasks);
            } else if (command.matches("^mark \\d+$")) {
                Task task = tasks.get(Integer.parseInt(command.substring(5)) - 1);
                task.markDone();
                ui.showMarkAsDone(task);
            } else if (command.matches("^unmark \\d+$")) {
                Task task = tasks.get(Integer.parseInt(command.substring(7)) - 1);
                task.markNotDone();
                ui.showMarkAsNotDone(task);
            } else if (command.matches("^delete \\d+$")) {
                Task task = tasks.remove(Integer.parseInt(command.substring(7)) - 1);
                ui.showTaskRemoved(task, tasks);
            } else if (command.equals("bye")) {
                break;
            } else {
                Task task;
                try {
                    if (command.startsWith("todo")) {
                        if (command.length() < 6) {
                            throw new DukeException("The description of a todo cannot be empty.");
                        }
                        task = new Todo(command.substring(5));
                    } else if (command.startsWith("deadline")) {
                        if (command.length() < 10) {
                            throw new DukeException(
                                    "The description of a deadline cannot be empty.");
                        } else if (!command.contains("/by")) {
                            throw new DukeException("A deadline must contain a /by");
                        }
                        String[] split = command.substring(9).split(" /by ");
                        task = new Deadline(split[0], split[1]);
                    } else if (command.startsWith("event")) {
                        if (command.length() < 7) {
                            throw new DukeException("The description of an event cannot be empty.");
                        } else if (!command.contains("/at")) {
                            throw new DukeException("An event must contain an /at");
                        }
                        String[] split = command.substring(6).split(" /at ");
                        task = new Event(split[0], split[1]);
                    } else {
                        throw new DukeException("I'm sorry, but I don't know what that means :-(");
                    }
                    tasks.add(task);
                    ui.showTaskAdded(task, tasks);
                } catch (DukeException err) {
                    ui.showDukeException(err);
                }
            }
            storage.writeTasks(tasks);
        }

        ui.showBye();
    }
}
