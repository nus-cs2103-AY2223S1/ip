package duke;

import java.io.File;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Duke {

    public final String FILE_NAME;
    private final Storage FO;
    private final TaskList TASK_LIST;

    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");


    /**
     * Returns a Duke instance.
     *
     * @param fileName The name of the file to store the tasks.
     * @param fo The FileOperator to perform file manipulation.
     * @param taskList The TaskList instance to manage all tasks.
     */
    public Duke(String fileName, Storage fo, TaskList taskList) {
        FILE_NAME = fileName;
        this.FO = fo;
        this.TASK_LIST = taskList;
    }

    /**
     * Serves as the entry point for the main Duke application.
     *
     * @param args The user input to the application.
     */
    public static void main(String[] args) {
        Duke duke = new Duke("data.txt", new Storage("data.txt"), new TaskList());
        Ui ui = new Ui(duke.FILE_NAME);
        ui.printWelcomeMessage();

        // Read from file and load into tasks
        File file;

        try {
            file = new File(duke.FILE_NAME);
            if (file.createNewFile()) {
                ui.printCreateNewFile();
            } else {
                duke.FO.loadAllTasksFromFile(duke.TASK_LIST);
                ui.printLoadTasksFromFile();
                ui.printLineBreak();
            }
        } catch (IOException e) {
            ui.printErrorCreatingFile();
        }

        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine().trim();
        while (!input.equals(Commands.BYE)) {
            duke.run(input);
            input = sc.nextLine();
        }

        // cleaning up and shutting down Duke
        ui.printByeMessage();
        sc.close();
    }

    /**
     * Runs the Duke application by processing the user input and executing necessary commands.
     *
     * @param input The input entered by the user.
     */
    public void run(String input) {
        Ui ui = new Ui(FILE_NAME);
        try {
            String[] inputString = input.split(" ", 2);
            Parser parser = new Parser(inputString);
            String command = inputString[0];
            ui.printLineBreak();
            switch (command) {
            case Commands.LIST:
                TASK_LIST.listTasks();
                break;
            case Commands.MARK:
                int taskNumber = parser.parseMark();
                TASK_LIST.markAsDone(taskNumber - 1, FO); // since display is 1-indexed
                break;
            case Commands.UNMARK:
                taskNumber = parser.parseUnmark();
                TASK_LIST.markAsNotDone(taskNumber - 1, FO); // since display is 1-indexed
                break;
            case Commands.TODO:
                Todo task = parser.parseTodo();
                TASK_LIST.add(task, FO);
                break;
            case Commands.DEADLINE:
                Deadline deadline = parser.parseDeadline();
                TASK_LIST.add(deadline, FO);
                break;
            case Commands.EVENT:
                Event event = parser.parseEvent();
                TASK_LIST.add(event, FO);
                break;
            case Commands.DELETE:
                taskNumber = parser.parseDelete();
                TASK_LIST.delete(taskNumber - 1, FO); // since we store tasks 0-indexed in ArrayList
                break;
            case Commands.FIND:
                String keyword = parser.parseFind();
                TASK_LIST.find(keyword);
                break;
            default:
                ui.printCommandNotRecognized();
            }
        } catch (DukeException e) {
            System.out.printf("\t%s\n", e.getMessage());
        } catch (NumberFormatException e) {
            ui.printErrorTaskNumber();
        } catch (java.time.format.DateTimeParseException e) {
            ui.printErrorDatetimeFormat();
        } finally {
            ui.printLineBreak();
        }
    }
}

