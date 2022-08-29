import duke.Command;
import duke.Parser;
import duke.Storage;
import duke.Task;
import duke.TaskList;
import duke.Ui;
import java.util.Scanner;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Duke {
    private Storage storage;
    private Ui ui;
    private TaskList tasks;

    public Duke(Path filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (Exception exception) {
            ui.showLoadingError();
        }
        tasks = new TaskList();
    }

    public void run() {
        ui.showOpeningStatement();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String input = scanner.nextLine();
            if (Parser.parse(input).equals(Command.EXIT)) {
                break;
            } else if (Parser.parse(input).equals(Command.LIST)) {
                ui.showTaskList(tasks);
            } else if (Parser.parse(input).equals(Command.MARK)) {
                int taskIndex = Parser.getTaskIndex(input);
                Task markedTask = tasks.markTask(taskIndex);
                try {
                    ui.showMarkDone(markedTask);
                } catch (StringIndexOutOfBoundsException | NumberFormatException e) {
                    ui.askWhichTask();
                } catch (Exception e) {
                    ui.printException(e);
                } finally {
                    storage.saveData(tasks);
                }
            } else if (Parser.parse(input).equals(Command.UNMARK)) {
                int taskIndex = Parser.getTaskIndex(input);
                Task unmarkedTask = tasks.unmarkTask(taskIndex);
                try {
                    ui.showUnmarkDone(unmarkedTask);
                } catch (StringIndexOutOfBoundsException | NumberFormatException e) {
                    ui.askWhichTask();
                } catch (Exception e) {
                    ui.printException(e);
                } finally {
                    storage.saveData(tasks);
                }
            } else if (Parser.parse(input).equals(Command.DELETE)) {
                try {
                    int taskIndex = Parser.getTaskIndex(input);
                    Task deletedTask = tasks.deleteTask(taskIndex);
                    ui.showDeleteTaskDone(deletedTask, tasks.getNumberOfTasks());
                    storage.saveData(tasks);
                } catch (StringIndexOutOfBoundsException e) {
                    ui.askWhichTask();
                } catch (Exception e) {
                    ui.printException(e);
                }
            } else if (Parser.parse(input).equals(Command.CREATE_EVENT)) {
                Task newTask = Parser.parseTask(input, "E");
                tasks.addTask(newTask);
                ui.showAddTaskDone(newTask, tasks.getNumberOfTasks());
                storage.saveData(tasks);
            } else if (Parser.parse(input).equals(Command.CREATE_DEADLINE)) {
                Task newTask = Parser.parseTask(input, "D");
                tasks.addTask(newTask);
                ui.showAddTaskDone(newTask, tasks.getNumberOfTasks());
                storage.saveData(tasks);
            } else if (Parser.parse(input).equals(Command.CREATE_TODO)) {
                Task newTask = Parser.parseTask(input, "T");
                tasks.addTask(newTask);
                ui.showAddTaskDone(newTask, tasks.getNumberOfTasks());
                storage.saveData(tasks);
            } else if (Parser.parse(input).equals(Command.FIND)) {
                String keyword = Parser.getKeyword(input);
                TaskList foundTasks = tasks.findTasks(keyword);
                ui.ShowFoundTasks(foundTasks);
            } else if (Parser.parse(input).equals(Command.UNKNOWN)) {
                ui.showUnknownCommand();
            }
        }
        ui.showExitStatement();
        scanner.close();
    }


    public static void main(String[] args) {
        String home = System.getProperty("user.home");
        Path path = Paths.get(home, "data", "duke");
        new Duke(path).run();
    }
}
