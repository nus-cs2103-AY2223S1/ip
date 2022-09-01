package alan;

import alanExceptions.AlanException;
import tasks.TaskList;
import util.Executor;
import util.FileParser;
import util.Storage;
import util.Ui;

import java.util.Scanner;

/**
 * This class encapsulates the Chat Bot.
 */
public class Alan {
    public static Alan instance;
    private Storage alanIO;
    private final Ui ui;
    private final FileParser fileParser;
    private final Executor executor;
    private TaskList taskList;

    /**
     * Private Constructor.
     */
    private Alan() {
        this.ui = new Ui();
        this.executor = new Executor();
        this.taskList = new TaskList();
        this.fileParser = new FileParser();

        try {
            this.alanIO = new Storage();
        } catch(AlanException e) {
            executor.excException(e.getMessage());
        }
    }

    public static Alan getInstance() {
        if (Alan.instance == null) {
            Alan.instance = new Alan();
        }
        Alan.instance.begin();
        return Alan.instance;
    }


    /**
     * Entry creates an alan.Alan singleton
     *
     * @param args args.
     */
    public static void main(String[] args) {
        if (Alan.instance == null) {
            Alan.instance = new Alan();
        }
        Alan.instance.begin();
    }

    private void begin() {
        try {
            this.taskList = new TaskList(fileParser.parseFile(alanIO.read()));
        } catch (AlanException e) {
            executor.excException(e.getMessage());
        }
    }

    public String getResponse(String input) {
        String response;
        System.out.println("How may I be of service?");

        String command = input.split(" ", 2)[0];

        try {
            switch (command) {
                case "bye":
                    response = executor.excBye();
                    break;
                case "list":
                    response = executor.excList(taskList);
                    break;
                case "event":
                    response = executor.excEvent(taskList, input);
                    break;
                case "deadline":
                    response = executor.excDeadline(taskList, input);
                    break;
                case "todo":
                    response = executor.excTodo(taskList, input);
                    break;
                case "find":
                    response = executor.excFind(taskList, input);
                    break;
                case "mark":
                    response = executor.excMark(taskList, input);
                    break;
                case "unmark":
                    response = executor.excUnmark(taskList, input);
                    break;
                case "delete":
                    response = executor.excDelete(taskList, input);
                    break;
                case "help":
                    // TODO: 18/8/22
                    response = "Sorry i cant help you just yet ):";
                    break;
                default:
                    response = ui.invalid();
                    break;
            }
        } catch (AlanException exception) {
            response = executor.excException(exception.getMessage());
        }

        return response;
    }

    // Prints a greeting
    private void greet() {
        final String logo = " $$$$$$\\  $$\\        $$$$$$\\  $$\\   $$\\\n"
                + "$$  __$$\\ $$ |      $$  __$$\\ $$$\\  $$ |\n"
                + "$$ /  $$ |$$ |      $$ /  $$ |$$$$\\ $$ |\n"
                + "$$$$$$$$ |$$ |      $$$$$$$$ |$$ $$\\$$ |\n"
                + "$$  __$$ |$$ |      $$  __$$ |$$ \\$$$$ |\n"
                + "$$ |  $$ |$$ |      $$ |  $$ |$$ |\\$$$ |\n"
                + "$$ |  $$ |$$$$$$$$\\ $$ |  $$ |$$ | \\$$ |\n"
                + "\\__|  \\__|\\________|\\__|  \\__|\\__|  \\__|\n";

        System.out.println(getTimeGreeting() +
                "!\nMy name is\n\n" + logo);
    }

    // Checks hour of day and returns appropriate greeting
    private String getTimeGreeting() {
        int hour = java.time.LocalTime.now().getHour();
        String greeting = hour < 12
                ? "Morning"
                : hour < 18
                ? "Afternoon"
                : "Evening";
        return "\nGood " + greeting;
    }
}




