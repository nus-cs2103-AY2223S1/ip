package duke;

import duke.storage.Storage;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.TaskList;
import duke.tasks.Todo;
import duke.UI.Ui;
import duke.parser.Parser;

import java.time.LocalDateTime;

/**
 * Entry point of the Duke application.
 * Initializes the application and starts the interaction with the user.
 */
public class Duke  {
    private Storage storage;
    private TaskList tasks;
    private static Ui ui;

    public Duke() {
        ui = new Ui();
        storage = new Storage("./data/duke.txt");
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showError(e);
            tasks = new TaskList();
        }
    }

    /**
     * for purpose of testing StorageTest
     */
    TaskList getTaskList() {
        return this.tasks;
    }

    /**
     * Shows welcome message in GUI when the chat bot is started
     */
    public static String showWelcomeGUI() {
        return ui.showWelcome() + "\n" + ui.showCommands();
    }

    /**
     * Generates Duke's response based on the input sent by the user
     */
    public String getResponse(String input) {
        String response = "";
        boolean isExit = false;

        // Use assertion to ensure that Duke is properly initialized
        assert ui.showWelcome() == "Hello! I'm Duke" + "\n" + "What can I do for you?"
                :"UI is not initialized!";

        try {
            //we get the commandType to know how to process the command
            String commandType = Parser.getCommandType(input);

            switch (commandType) {
            case "EXIT":
                response += "Bye. Hope to see you again soon!";
                isExit = true;
                break;

            case "PRINT":
                if (tasks.getTasks().size() != 0) {
                    response += tasks.printTasks();
                } else {
                    throw new DukeException("There is currently no tasks!");
                }
                break;

            case "UPDATE":
                int[] arr = Parser.parseUpdateCommand(input);

                //mark/unmark the task depending on the integer in the first entry of our array
                if (arr[0] == 1) {
                    response += tasks.mark(arr[1]);
                } else {
                    response += tasks.unmark(arr[1]);
                }
                break;

            case "DELETE":
                int taskIndex = Parser.getDeleteNum(input);
                response += tasks.delete(taskIndex);
                break;

            case "ADD":
                if (input.startsWith("todo")) {
                    String task = input.substring(5);
                    response += tasks.add(new Todo(task));
                } else {
                    LocalDateTime dateTime = Parser.parseDateTime(input);
                    if (input.startsWith("deadline")) {
                        response += tasks.add(new Deadline(input.substring(9, input.indexOf("/")), dateTime));
                    } else {
                        response += tasks.add(new Event(input.substring(6, input.indexOf("/")), dateTime));
                    }
                }
                break;

            case "FIND":
                response += Parser.parseFindCommand(input, tasks);
                break;

            case "SORT":
                String sortKeyword = Parser.parseSortCommand(input);
                if (sortKeyword.equals("deadlines")) {
                    response += tasks.printSortedDeadlines();
                } else {
                    response += tasks.printSortedEvents();
                }
                break;

            default:
                response += tasks.printUpcomingTasks();
            }

            storage.save(tasks);
            return response;
        } catch (DukeException e) {
            return ui.showError(e);
        } finally {
            if (isExit) {
                System.out.println(response);
                System.exit(0);
            }
        }
    }
}
