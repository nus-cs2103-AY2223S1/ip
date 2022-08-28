import java.util.Scanner;
import java.io.IOException;

public class Duke {
    private static final String saveDirectoryPath = "../data";
    private static final String saveFilePath = "../data/duke.txt";
    private static TaskList tasklist = new TaskList();
    private static Storage storage = new Storage(saveDirectoryPath, saveFilePath);
    private static Ui ui = new Ui();

    private static void run(Scanner scanner) {
        ui.showGreeting();

        try {
            storage.checkExistsOrCreateNewFile(tasklist);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        while(scanner.hasNext()) {
            String inputString = scanner.nextLine();
            String[] inputs = Parser.splitBySpace(inputString);
            String input = inputs[0];

            try {
                if (input.equals("bye")) {
                    ui.showBye();
                    break;
                } else if (input.equals("list")) {
                    ui.showList(tasklist);
                } else if (input.equals("mark")) {
                    ui.showMark(tasklist, inputs[1]);
                    storage.writeToFile(tasklist.listOfTasksForSaving());
                } else if (input.equals("unmark")) {
                    ui.showUnmark(tasklist, inputs[1]);
                    storage.writeToFile(tasklist.listOfTasksForSaving());
                } else if (input.equals("todo")) {
                    if (inputs.length <= 1) {
                        throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                    }
                    tasklist.appendToDo(inputString);
                    ui.showAddedTask(tasklist);
                    storage.writeToFile(tasklist.listOfTasksForSaving());
                } else if (input.equals("deadline")) {
                    if (inputs.length <= 1) {
                        throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
                    }
                    String[] deadlineDescription = Parser.splitBySlash(inputString);
                    tasklist.appendDeadline(deadlineDescription[0], deadlineDescription[1]);
                    ui.showAddedTask(tasklist);
                    storage.writeToFile(tasklist.listOfTasksForSaving());
                } else if (input.equals("event")) {
                    if (inputs.length <= 1) {
                        throw new DukeException("☹ OOPS!!! The description of a event cannot be empty.");
                    }
                    String[] eventDescription = Parser.splitBySlash(inputString);
                    tasklist.appendEvent(eventDescription[0], eventDescription[1]);
                    ui.showAddedTask(tasklist);
                    storage.writeToFile(tasklist.listOfTasksForSaving());
                } else if (input.equals("delete")) {
                    String taskMessage = tasklist.removeTask(inputs[1]);
                    ui.showDeletedTask(taskMessage, tasklist);
                    storage.writeToFile(tasklist.listOfTasksForSaving());
                } else {
                    throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } catch (IOException e) {
                ui.showError(e.getMessage());
            }
        }
    }
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        Scanner scanner = new Scanner(System.in);
        run(scanner);
    }
}
