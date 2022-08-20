import java.time.LocalDate;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.printWelcomeMessage();
        String input = ui.collectUserInput();
        Parser parser = new Parser();
        String response = parser.checkResponse(input);
        while (!response.equals("bye")) {
            try {
                switch (response) {
                    case "list":
                        ui.printList(tasks);
                        break;
                    case "mark": {
                        int taskNumber = parser.getTaskNumber();
                        tasks.markAsDone(taskNumber);
                        break;
                    }
                    case "unmark": {
                        int taskNumber = parser.getTaskNumber();
                        tasks.markNotDone(taskNumber);
                        break;
                    }
                    case "delete": {
                        int taskNumber = parser.getTaskNumber();
                        ui.printDeleteMessage(tasks, taskNumber);
                        tasks.deleteTask(taskNumber);
                        break;
                    }
                    case "deadline": {
                        String task = parser.getDeadlineDescription();
                        LocalDate d1 = parser.getDeadlineTime();
                        tasks.addTask(new Deadline(task, d1));
                        ui.printAddMessage(tasks);
                        break;
                    }
                    case "event": {
                        String task = parser.getEventDescription();
                        String d1 = parser.getEventTime();
                        tasks.addTask(new Event(task, d1));
                        ui.printAddMessage(tasks);
                        break;
                    }
                    case "todo": {
                        String task = parser.getTodoDescription();
                        tasks.addTask(new Todo(task));
                        ui.printAddMessage(tasks);
                        break;
                    }
                    default:
                        throw new InvalidCommandException();
                }
            } catch (InvalidCommandException | EmptyTodoListException e) {
                System.out.println(e.getMessage());
            }
            storage.saveTasks(tasks);
            input = ui.collectUserInput();
            response = parser.checkResponse(input);
        }
        ui.printGoodbyeMessage();
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}
