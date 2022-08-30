package duke;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = storage.load();
    }

    public void run() {
        Ui.printInitialMessage();
        while (true) {
            String command = ui.readCommand();
            switch (Parser.parse(command)) {
            case TODO: {
                Todo todo = new Todo(command.replace("todo", "").trim(), false);
                tasks.add(todo);
                Ui.printAddedMessage(todo, tasks.getSize());
                storage.writeToFile(tasks);
                continue;
            }
            case DEADLINE: {
                String[] splitStr = command.trim().split("/by");
                String date = splitStr[1].replace("by", "").trim();
                Deadline deadline = new Deadline(splitStr[0].replace("deadline", "").trim(),
                        false, date);
                tasks.add(deadline);
                Ui.printAddedMessage(deadline, tasks.getSize());
                storage.writeToFile(tasks);
                continue;
            }
            case EVENT: {
                String[] splitStr = command.trim().split("/at");
                String date = splitStr[1].replace("at", "").trim();
                Event event = new Event(splitStr[0].replace("event", "").trim(), false, date);
                tasks.add(event);
                Ui.printAddedMessage(event, tasks.getSize());
                storage.writeToFile(tasks);
                continue;
            }
            case DELETE: {
                String[] splitStr = command.trim().split("\\s+");
                int deleteItem = Integer.parseInt(splitStr[splitStr.length - 1]) - 1;
                Task deletedTask = tasks.remove(deleteItem);
                Ui.printDeletedMessage(deletedTask, tasks.getSize());
                storage.writeToFile(tasks);
                continue;
            }
            case MARK:
                tasks.markDone(Integer.parseInt(command.replace("mark ", "").trim()));
                storage.writeToFile(tasks);
                continue;
            case UNMARK:
                tasks.unmark(Integer.parseInt(command.replace("mark ", "").trim()));
                storage.writeToFile(tasks);
                continue;
            case QUIT:
                Ui.printGoodbyeMessage();
                return;
            case LIST:
                Ui.printTasks(tasks);
                continue;
            case INVALID:
                Ui.printErrorMessage(new DukeException(
                        "OOPS!!! I'm sorry, but I don't know what that means :-("));
                continue;
            case FIND:
                TaskList filtered = tasks.find(command.replace("find", "").trim());
                Ui.printTasks(filtered);
                continue;
            }
        }
    }

    public static void main(String[] args) {
        new Duke("tasks.txt").run();
    }
}
