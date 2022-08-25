import java.util.Scanner;

public class Duke {

    private Ui ui;
    private Storage storage;
    private TaskList taskList;

    public Duke() {
        storage = new Storage("data/duke.txt");
        ui = new Ui();
        taskList = new TaskList();
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        boolean hasNextInput = true;

        ui.greet();
        storage.loadTasks(taskList);

        while (hasNextInput) {
            System.out.print("--> ");
            String input = scanner.nextLine();
            int taskIndex;

            try {
                switch (Command.valueOf(input.split(" ")[0])) {
                case bye:
                    hasNextInput = false;
                    scanner.close();
                    storage.saveTasks(taskList);
                    break;
                case list:
                    taskList.displayList();
                    break;
                case mark:
                    taskIndex = Integer.parseInt(input.split(" ")[1]) - 1;
                    taskList.changeTaskStatus(taskIndex, true);
                    break;
                case unmark:
                    taskIndex = Integer.parseInt(input.split(" ")[1]) - 1;
                    taskList.changeTaskStatus(taskIndex, false);
                    break;
                case delete:
                    taskIndex = Integer.parseInt(input.split(" ")[1]) - 1;
                    taskList.removeTask(taskIndex);
                    break;
                default:
                    taskList.addTask(Task.createTask(input));
                    break;
                }
            } catch (NumberFormatException e) {
                ui.showError("Please Enter a valid task number!");
            } catch (IllegalArgumentException e) {
                ui.showError("I'm sorry but I don't know what that means.");
            } catch (DukeException e) {
                ui.showError(e);
            }
        }
        ui.bye();
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }
}
