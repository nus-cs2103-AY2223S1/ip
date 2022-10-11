package duke.main;

import duke.commandword.CommandWord;
import duke.exception.DukeException;
import duke.task.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    private Duke() {
        // Initialising the field variables
        storage = new Storage();
        ui = new Ui();

        try {
            taskList = new TaskList(storage.loadTaskList());
            ui.successLoadMessage();
        } catch (DukeException de) {
            ui.printErrorMessage(de);
            taskList = new TaskList(new ArrayList<Task>());
        }
    }

    public void run() {
        ui.greetUser();
        boolean isExit = false;
        while (!isExit) {
            // Variables
            Scanner sc = new Scanner(System.in);
            String input = sc.nextLine();

            // If input is invalid, then print error message
            try {
                Parser.parse(input);
            } catch (DukeException de) {
                ui.printErrorMessage(de);
                continue;
            }

            // If input is valid
            CommandWord command = Parser.getCommand();
            String description = Parser.getDescription();

            try {
                switch (command) {
                case BYE: {
                    ui.exitJukebox();
                    isExit = true;
                    break;
                }
                case LIST: {
                    taskList.printList();
                    break;
                }
                case MARK:
                case UNMARK: {
                    taskList.markUnmarkTask(command, description);
                    Task markedTask = taskList.getTask(description);
                    ui.markUnmarkTaskMessage(markedTask, command);
                    break;
                }
                case DELETE: {
                    Task deletedTask = taskList.getTask(description);
                    ui.deleteTaskMessage(deletedTask, taskList);
                    taskList.deleteTask(description);
                    break;
                }
                case FIND: {
                    taskList.findTask(description);
                    break;
                }
                case TODO: {
                    Task newTask = new Todo(description);
                    taskList.addTask(newTask);
                    ui.addTaskMessage(newTask, taskList);
                    break;
                }
                case DEADLINE: {
                    String[] descriptionArr = description.split(" /by ");
                    LocalDateTime dateTime = DateTime.parseDate(descriptionArr[1]);
                    String taskDescription = descriptionArr[0];
                    Task newTask = new Deadline(taskDescription, dateTime);
                    taskList.addTask(newTask);
                    ui.addTaskMessage(newTask, taskList);
                    break;
                }
                case EVENT: {
                    String[] descriptionArr = description.split(" /at ");
                    LocalDateTime dateTime = DateTime.parseDate(descriptionArr[1]);
                    String taskDescription = descriptionArr[0];
                    Task newTask = new Deadline(taskDescription, dateTime);
                    taskList.addTask(newTask);
                    ui.addTaskMessage(newTask, taskList);
                    break;
                }
                }
                storage.saveTaskList(taskList);
            } catch (DukeException de) {
                System.out.println(de.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}

