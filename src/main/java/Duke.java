import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class is the main logic unit for Duke
 */
public class Duke {
    /* Handles task list logic. */
    private TaskList taskList;
    /* Handles file storage logic. */
    private Storage storage;

    /**
     * Constructor for the Duke Chat bot.
     */
    public Duke() {
        taskList = new TaskList();
        ArrayList<Task> tasks = taskList.getTasks();
        Storage.Load(tasks);
    }

    /**
     * Handles the logic for the task manager.
     */
    public void run() {
        UI.Greet();
        Scanner sc = new Scanner(System.in);
        String in = sc.nextLine();

        while (true) {
            // Splits the input to retrieve possible commands.
            Parser.parseInput(in);
            Command command = Parser.getUserCommand();
            String userInstructions = Parser.getUserInstructions();

            // Break out of loop
            if (command == Command.BYE) {
                break;
            }

            // List out current tasks in the list
            if (command == Command.LIST) {
                taskList.printTaskList();
            }

            if (command == Command.MARK || command == Command.UNMARK) {
                taskList.taskMarker(command, userInstructions);
            }

            if (command == Command.DELETE) {
                try {
                    taskList.deleteTask(userInstructions);
                } catch (IndexOutOfBoundsException e) {
                    UI.printDeleteErrorMessage();
                }
            }

            if (command == Command.TODO || command == Command.DEADLINE || command == Command.EVENT) {
                try {
                    taskList.addTask(command, userInstructions);
                } catch (DukeException e) {
                    UI.printDukeExceptionMessage(e);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            in = sc.nextLine();
        }
        UI.Goodbye();
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
