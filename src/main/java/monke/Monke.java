package monke;

import monkeExceptions.MonkeException;
import tasks.TaskList;
import util.*;

/**
 * This class encapsulates the Chat Bot.
 */
public class Monke {
    public static Monke instance;
    private Storage storage;
    private final Ui ui;
    private final FileParser fileParser;
    private final Executor executor;
    private TaskList taskList;

    /**
     * Private Constructor.
     */
    private Monke() {
        this.ui = new Ui();
        this.executor = new Executor();
        this.taskList = new TaskList();
        this.fileParser = new FileParser();

        try {
            this.storage = Storage.getInstance();
        } catch(MonkeException e) {
            executor.excException(e.getMessage());
        }
    }

    public static Monke getInstance() {
        if (Monke.instance == null) {
            Monke.instance = new Monke();
        }
        Monke.instance.begin();
        return Monke.instance;
    }


    /**
     * Entry creates an alan.Alan singleton
     *
     * @param args args.
     */
    public static void main(String[] args) {
        if (Monke.instance == null) {
            Monke.instance = new Monke();
        }
        Monke.instance.begin();
    }

    private void begin() {
        try {
            this.taskList = new TaskList(fileParser.parseFile(storage.read()));
        } catch (MonkeException e) {
            executor.excException(e.getMessage());
        }
    }

    public String getResponse(String input) {
        String response;
        System.out.println("How may I be of service?");

        String command = input.split(" ", 2)[0];

        try {
            command = keyword.Keywords.getInstance().getCommand(command);
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
                    response = executor.excHelp(input);
                    break;
                case "addkw":
                    response = executor.excAkw(input);
                    break;
                case "delkw":
                    response = executor.excRkw(input);
                    break;
                default:
                    response = ui.invalid();
                    break;
            }
        } catch (MonkeException exception) {
            response = executor.excException(exception.getMessage());
        }
        // At this point response must not be null
        assert response != null : "No response was generated for user input";
        return response;
    }

    // Prints a greeting (Deprecated with GUI)
//    private void greet() {
//        final String logo = " $$$$$$\\  $$\\        $$$$$$\\  $$\\   $$\\\n"
//                + "$$  __$$\\ $$ |      $$  __$$\\ $$$\\  $$ |\n"
//                + "$$ /  $$ |$$ |      $$ /  $$ |$$$$\\ $$ |\n"
//                + "$$$$$$$$ |$$ |      $$$$$$$$ |$$ $$\\$$ |\n"
//                + "$$  __$$ |$$ |      $$  __$$ |$$ \\$$$$ |\n"
//                + "$$ |  $$ |$$ |      $$ |  $$ |$$ |\\$$$ |\n"
//                + "$$ |  $$ |$$$$$$$$\\ $$ |  $$ |$$ | \\$$ |\n"
//                + "\\__|  \\__|\\________|\\__|  \\__|\\__|  \\__|\n";
//
//        System.out.println(getTimeGreeting() +
//                "!\nMy name is\n\n" + logo);
//    }

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




