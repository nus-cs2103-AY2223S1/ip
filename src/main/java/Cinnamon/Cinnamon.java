package Cinnamon;
import java.util.Scanner;

import Cinnamon.Exception.DukeException;
import Cinnamon.Handler.Parser;
import Cinnamon.Handler.Ui;
import Cinnamon.Storage.Storage;
import Cinnamon.Tasks.TaskList;
import Cinnamon.GUI.GuiUi;

/**
 * Duke is the main class that will save and run the program
 *
 * @author Fang Yiye
 */
public class Cinnamon {
    private TaskList taskList;
    private static Ui ui;
    private final Storage storage;
    private static Scanner sc;
    private final GuiUi guiUi;


    /**
     * Constructor of Duke to initialise ui, storage and scanner
     */
    public Cinnamon() {
        ui = new Ui();
        guiUi= new GuiUi();
        this.sc = new Scanner(System.in);
        this.taskList = new TaskList();
        storage = new Storage();
        try {
            taskList = new TaskList(storage.loadTasks());
        } catch (DukeException e) {
            ui.loadingError();
            taskList = new TaskList();
        }
    }


    /**
     * main methods that allow the project to run
     *
     * @param args
     * @throws DukeException by the parser if the parser parses an invalid output
     */
    public static void main(String[] args) throws DukeException {
        Cinnamon cinnamon = new Cinnamon();
        Parser parser = new Parser(cinnamon, ui);
        cinnamon.run(parser);
    }


    /**
     * run the program
     *
     * @param parser which takes in the system input
     * @throws DukeException by the parser if the parser parses an invalid output
     */
    public void run(Parser parser) throws DukeException {
        ui.greet();
        while (sc.hasNextLine()) {
            parser.parse(sc.nextLine());
            storage.writeTasks(taskList);
        }
    }





    public String getResponse(String input) throws DukeException {
        String response = guiUi.enterText();
        if (input.equals("list")) {
            response = guiUi.displayTask();
        } else if (input.startsWith("mark")) {
            response = guiUi.displayMarkDone(input);
        } else if (input.startsWith("unmark")) {
            response = guiUi.displayUnmark(input);
        } else if (input.startsWith("todo")) {
            response = guiUi.displayTodo(input);
        } else if (input.startsWith("deadline")) {
            response = guiUi.displayDeadline(input);
        } else if (input.startsWith("event")) {
            response = guiUi.displayEvent(input);
        } else if (input.startsWith("delete")) {
            response = guiUi.displayDelete(input);
        } else if (input.startsWith("find")) {
            response = guiUi.displaySearchName(input);
        } else if (input.startsWith("tag")) {
            response = guiUi.tagTask(input);
        } else if (input.startsWith("print tag")) {
            response = guiUi.printTaskTag(input);
        } else if (input.startsWith("#")) {
            response = guiUi.searchTag(input);
        } else if (input.startsWith("remove tag")) {
            response = guiUi.removeTag(input);
        } else if (input.startsWith("dayFilter")){
            //starts with dateFilter
            response = guiUi.displaySearchDate(input);
        } else {
            response = "no such command, check user guide";
        }
        return response;
    }

    public static String trimUserInput(String userInput) {
        if (userInput.trim().contains(" ")) {
            return userInput.trim().substring(0, userInput.indexOf(" ")).trim();
        }
        return userInput;
    }

    public String displayBye() {
        storage.writeTasks(taskList);
        return "Bye! Hope to see you again soon!";
    }

    public String displayGreet() {
        return "Hello!, I'm Yiye.\nWhat can I do for you? ◠‿◠";
    }

}
