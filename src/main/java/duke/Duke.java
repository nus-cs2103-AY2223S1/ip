package duke;

import java.io.File;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Duke {

    public final String FILE_NAME;
    private Storage fo;
    private TaskList taskList;

    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");



    public Duke(String fileName, Storage fo, TaskList taskList) {
        FILE_NAME = fileName;
        this.fo = fo;
        this.taskList = taskList;
    }

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
                duke.fo.loadAllTasksFromFile(duke.taskList);
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

    public void run(String input) {
        Ui ui = new Ui(FILE_NAME);
        try {
            String[] inputString = input.split(" ", 2);
            Parser parser = new Parser(inputString);
            String command = inputString[0];
            ui.printLineBreak();
            switch (command) {
            case Commands.LIST:
                taskList.listTasks();
                break;
            case Commands.MARK:
                int taskNumber = parser.parseMark();
                taskList.markAsDone(taskNumber - 1, fo); // since display is 1-indexed
                break;
            case Commands.UNMARK:
                taskNumber = parser.parseUnmark();
                taskList.markAsNotDone(taskNumber - 1, fo); // since display is 1-indexed
                break;
            case Commands.TODO:
                Todo task = parser.parseTodo();
                taskList.add(task, fo);
                break;
            case Commands.DEADLINE:
                Deadline deadline = parser.parseDeadline();
                taskList.add(deadline, fo);
                break;
            case Commands.EVENT:
                Event event = parser.parseEvent();
                taskList.add(event, fo);
                break;
            case Commands.DELETE:
                taskNumber = parser.parseDelete();
                taskList.delete(taskNumber - 1, fo); // since we store tasks 0-indexed in ArrayList
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

