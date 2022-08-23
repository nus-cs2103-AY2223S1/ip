import java.io.IOException;
import java.util.Scanner;

public class Duke {
    private Ui ui;
    private TaskList tasks;
    private Storage storage;
    public static String filePath = "data/list.txt";

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage();
        tasks = new TaskList();
        storage.loadStorage(filePath, tasks);

    }

    public static void main(String[] args) {
        new Duke(Duke.filePath).run();

    }

    public void run() {
        ui.showWelcome();
        Parser parser = new Parser();


        boolean isExit = false;
        while (!isExit) {
            String fullInput = ui.readCommand();
            if (fullInput.equals("list")) {
                tasks.printList();
            } else {

                try {
 /*                   if (command.length >= 1) {
                        switch (command[0]) {
                            case "mark":
                                ui.mark(command[1],this.tasks);
                                break;
                            case "unmark":
                                ui.unmark(command[1], this.tasks);
                                break;
                            case "deadline":
                                ui.proccessDeadline(command, this.tasks);
                                break;
                            case "todo":
                                ui.proccessTodo(command, this.tasks);
                                break;
                            case "event":
                                ui.proccessEvent(command, this.tasks);
                                break;
                            case "delete":
                                ui.proccessRemove(command, this.tasks);
                                break;
                            default:
                                throw new UnknownCommand();
                        }
                    }*/
                Command command = parser.parse(fullInput);
                command.execute(tasks, ui, storage);
                isExit = command.isExit();
                } catch (DukeException e) {
                    System.out.println(e);
                }
            }

        }
        storage.writeToTaskList(filePath, tasks);
        System.out.println("Bye. Hope to see you again soon!");
    }
}
