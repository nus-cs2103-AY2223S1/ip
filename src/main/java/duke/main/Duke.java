package duke.main;

import duke.exception.DukeException;
import duke.task.Task;
import duke.task.Event;
import duke.task.ToDo;
import duke.task.Deadline;

import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.ArrayList;


/**
 * Main class of Duke.
 */
public class Duke {

    /* Storage object handling saving and reading from save file */
    private Storage storage;
    /* List of tasks */
    private TaskList tasks;
    /* Ui object handling output to user */
    private Ui ui;

    /**
     * Constructor for Duke class.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage();
        try {
            // Greets User
            ui.greetUser();

            tasks = new TaskList(storage.readTaskListFromFile());
        } catch (DukeException e) {
            ui.printErrorMessage(e);
            tasks = new TaskList(new ArrayList<Task>());
        }
    }

    /**
     * Executes the Duke chatbot logic.
     */
    public void run() {
        // Helper Fields
        Scanner sc = new Scanner(System.in);
        boolean isExit = false;



        while (!isExit) {

            // Retrieve Input and Parse
            String input = sc.nextLine();
            try {
                Parser.parse(input);
            } catch (DukeException de) {
                ui.printErrorMessage(de);
                continue;
            }

            // Valid Input
            Keyword command = Parser.getCommand();
            String argument = Parser.getArgument();
            try {
                switch(command) {
                case TODO: {
                    Task task = new ToDo(argument);
                    tasks.addTask(task);
                    ui.displayTaskAddedMessage(task, tasks.size());
                    break;
                }
                case DEADLINE: {
                        String[] taskTokens = argument.split(" /by ");
                        String taskName = taskTokens[0];
                        String deadline = taskTokens[1];
                        LocalDateTime deadlineDate = DateTimeFormatUtils.parseDate(deadline);
                        Task task = new Deadline(taskName, deadlineDate);
                        tasks.addTask(task);
                        ui.displayTaskAddedMessage(task, tasks.size());
                        break;
                }
                case EVENT: {
                        String[] taskTokens = argument.split(" /at ");
                        String taskName = taskTokens[0];
                        String duration = taskTokens[1];
                        LocalDateTime[] eventDuration = DateTimeFormatUtils.parseDuration(duration);
                        Task task = new Event(taskName, eventDuration[0], eventDuration[1]);
                        tasks.addTask(task);
                        ui.displayTaskAddedMessage(task, tasks.size());
                        break;
                }
                case LIST: {
                    tasks.displayTaskList();
                    break;
                }
                case FIND: {
                    tasks.searchTaskList(argument);
                    break;
                }
                case DELETE: {
                    Task deletedTask = tasks.getTask(argument);
                    tasks.deleteTask(argument);
                    ui.displayTaskDeletedMessage(deletedTask, tasks.size());
                    break;
                }
                case MARK:
                case UNMARK: {
                    tasks.markUnmarkTask(argument, command);
                    Task task = tasks.getTask(argument);
                    ui.displayTaskMarkUnmarkMessage(task, command);
                    break;
                }
                case BYE: {
                    ui.sayGoodbye();
                    isExit = true;
                    break;
                }
                }
                // Update Save File
                storage.saveTaskListToFile(tasks);

            } catch(DukeException de) {
                ui.printErrorMessage(de);
            }
        }
    }

    public static void main(String[] args) {
        new Duke().run();
    }

}


