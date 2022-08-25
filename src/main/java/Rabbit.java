import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.io.File;
import java.nio.file.Files;
import java.io.FileWriter;
import java.time.DateTimeException;
import java.time.LocalDateTime;

/**
 * Rabbit is a short-tempered, annoyed bot that puts in her 30% efforts
 *  to help you solve some simple problems as her part-time jpb.
 *
 * @author Jiang Zhimeng
 */
public class Rabbit {

    private Ui ui;
    private Storage storage;
    private TaskList taskList;

    public Rabbit() {
        this.ui = new Ui();
        this.storage = new Storage();
        this.taskList = new TaskList();
    }

    private void run() {
        try {
            this.storage.importData(this.taskList);

        } catch (ImportDataException e) {
            this.ui.showException(e);
        }

        this.ui.showGreet();

        while (true) {
            String input = this.ui.readCommand();
            if (input.equals("bye")) {
                this.ui.showBye();
                this.ui.endCommand();
                break;
            }
            // the function that the input is calling
            String function = input.substring(0, Parser.parseFunction(input));
            try {
                String content = "";
                switch (function) {
                case "list":
                    this.taskList.list();
                    break;
                case "mark ":
                    this.taskList.mark(input);
                    this.storage.exportData(this.taskList);
                    this.ui.showMark();
                    break;
                case "unmark ":
                    this.taskList.unmark(input);
                    this.storage.exportData(this.taskList);
                    this.ui.showUnmark();
                    break;
                case "todo ":
                    content = this.taskList.addToList(TaskList.TaskType.TODO, input);
                    this.storage.exportData(this.taskList);
                    this.ui.showAddToList(content);
                    break;
                case "deadline ":
                    content = this.taskList.addToList(TaskList.TaskType.DEADLINE, input);
                    this.storage.exportData(this.taskList);
                    this.ui.showAddToList(content);
                    break;
                case "event ":
                    content = this.taskList.addToList(TaskList.TaskType.EVENT, input);
                    this.storage.exportData(this.taskList);
                    this.ui.showAddToList(content);
                    break;
                case "delete ":
                    this.taskList.delete(input);
                    this.storage.exportData(this.taskList);
                    this.ui.showDelete();
                    break;
                default:
                    // the user keyed in an invalid input
                    throw new InvalidInputException();
                }

            } catch (RabbitException e) {
                this.ui.showException(e);
            }
        }
    }

    /**
     * Main method of Rabbit.
     *
     * @param args The commandline arguments.
     */
    public static void main(String[] args) {
        Rabbit rabbit = new Rabbit();
        rabbit.run();
        /*
        // if directory ./date does not exist, create it
        if (!Files.exists(path.getParent())) {
            try {
                Files.createDirectories(path.getParent());
            } catch (IOException e) {
                System.out.println("Error when creating directory ./data.");
                return;
            }
        }

        // try import data.txt from ./data
        try {
            File data = new File(path.toString());
            Scanner s = new Scanner(data); // create a Scanner using the File as the source
            while (s.hasNext()) {
                String input = s.nextLine();
                importData(input);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File data.txt is not found.");
            System.out.println("Create a new data.txt.");
            // if data.txt is not found, create a new data.txt file under ./data
            try {
                Files.createFile(path);
            } catch (IOException ioException) {
                System.out.println("Error when creating data.txt.");
                return;
            }
        } catch (RabbitException e) {
            System.out.println(e);
        }

        System.out.println(greet);
        Scanner sc = new Scanner(System.in);

        while (true) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                System.out.println(bye);
                sc.close();
                break;
            }
            // the function that the input is calling
            String function = input.substring(0, scanFunction(input));
            try {
                switch (function) {
                case "list":
                    list();
                    break;
                case "mark ":
                    mark(input);
                    break;
                case "unmark ":
                    unmark(input);
                    break;
                case "todo ":
                    addToList(TaskType.TODO, input);
                    break;
                case "deadline ":
                    addToList(TaskType.DEADLINE, input);
                    break;
                case "event ":
                    addToList(TaskType.EVENT, input);
                    break;
                case "delete ":
                    delete(input);
                    break;
                default:
                    // the user keyed in an invalid input
                    throw new InvalidInputException();
                }
                exportData();
            } catch (RabbitException e) {
                System.out.println(e);
            }
        }

         */
    }
}
