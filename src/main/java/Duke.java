import java.time.LocalDateTime;

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
            ui.showError(e);
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();
        tasks.printTasks();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.printLine();
                String commandType = Parser.getCommandType(fullCommand);
                if (commandType.equals("EXIT")) {
                    System.out.println("Bye. Hope to see you again soon!");
                    isExit = true;
                } else if (commandType.equals("PRINT")) {
                    tasks.printTasks();
                } else if (commandType.equals("UPDATE")) {
                    int[] arr = Parser.parseUpdateCommand(fullCommand);
                    if (arr[0] == 1) {
                        tasks.mark(arr[1]);
                    } else {
                        tasks.unmark(arr[1]);
                    }
                } else if (commandType.equals("DELETE")) {
                    int taskIndex = Parser.getDeleteNum(fullCommand);
                    tasks.delete(taskIndex);
                } else if (commandType.equals("ADD")){
                    if (fullCommand.startsWith("todo")) {
                        String task = fullCommand.substring(5);
                        tasks.add(new Todo(task));
                    } else {
                        LocalDateTime dateTime = Parser.parseDateTime(fullCommand);
                        if (fullCommand.startsWith("deadline")) {
                            tasks.add(new Deadline(fullCommand.substring(9, fullCommand.indexOf("/")), dateTime));
                        } else {
                            tasks.add(new Event(fullCommand.substring(6, fullCommand.indexOf("/")), dateTime));
                        }
                    }
                } else {
                    Parser.printUpcomingTasks(fullCommand, tasks);
                }
                storage.save(tasks);
            } catch (DukeException e){
                ui.showError(e);
            } finally {
                ui.printLine();
            }
        }
    }
    public static void main(String[] args)  {
        new Duke("./src/main/data/duke.txt").run();
    }
}