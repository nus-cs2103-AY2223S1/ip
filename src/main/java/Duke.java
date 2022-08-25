import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class Duke {

    public final String FILE_NAME;
    private Storage fo;
    private TaskList taskList;

    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    private static final String WELCOME_MESSAGE = "\t-------------------------------\n"
            + "\tHello, I'm Duke!\n" + "\tWhat can I do for you?\n" + "\t-------------------------------";

    private static final String BYE_MESSAGE = "\t-------------------------------\n" +
            "\tBye! Hope to see you again\n" + "\t-------------------------------\n";

    public Duke(String fileName, Storage fo, TaskList taskList) {
        FILE_NAME = fileName;
        this.fo = fo;
        this.taskList = taskList;
    }

    public static void main(String[] args) {
        Duke duke = new Duke("data.txt", new Storage("data.txt"), new TaskList());

        System.out.println(WELCOME_MESSAGE);

        // Read from file and load into tasks
        File file;
        try {
            file = new File(duke.FILE_NAME);
            if (file.createNewFile()) {
                System.out.println(String.format("\tCreated new file %s to store tasks!", duke.FILE_NAME));
            } else { // load tasks from file to Task.tasks
                duke.fo.loadAllTasksFromFile(duke.taskList);
                System.out.println(String.format("\tLoaded tasks from %s", duke.FILE_NAME));
                System.out.println("\t-------------------------------");
            }
        } catch (IOException e) {
            System.out.println(String.format("\tError opening/creating %s!!!", duke.FILE_NAME));
        }

        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine().trim();
        while (!input.equals(Commands.BYE)) {
            duke.run(input);
            input = sc.nextLine();
        }

        // cleaning up and shutting down Duke
        System.out.println(BYE_MESSAGE);
        sc.close();
    }

    public void run(String input) {
        try {
            String[] inputString = input.split(" ", 2);
            Parser parser = new Parser(inputString);
            String command = inputString[0];
            System.out.println("\t-------------------------------");
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
                System.out.println("\tOops! I've no idea what you're talking about!");
            }
            System.out.println("\t-------------------------------");
        } catch (DukeException e) {
            System.out.printf("\t%s\n", e.getMessage());
            System.out.println("\t-------------------------------");
        } catch (NumberFormatException e) {
            System.out.println("\tPlease make sure you enter a task number correctly!");
            System.out.println("\t-------------------------------");
        } catch (java.time.format.DateTimeParseException e) {
            System.out.println("\tPlease ensure your datetime format is in YYYY-MM-DD HH:MM");
            System.out.println("\t-------------------------------");
        }
    }
}

