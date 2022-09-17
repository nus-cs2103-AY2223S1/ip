package duke;

import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;
import javafx.application.Platform;

public class Ui {
    private Scanner scanner;
    private TaskList items;
    private Storage storage;

    public Ui(String filePath) {
        storage = new Storage(filePath);
    }

    /**
     * Creates an Ui instance.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Gets input from user.
     * @return user input.
     */
    public String getInput() {
        return scanner.nextLine();
    }

    /**
     * Prints welcome.
     */
    public void printWelcome() {
        System.out.println("Hello! I'm Duke \n What can I do for you?");
    }

    /**
     * Prints the message.
     * @param s message.
     */
    public void printMsg(String s) {
        System.out.println(s);
    }

    /**
     * Takes in a command to tell Duke what to do.
     * @param command the input.
     * @return the response from Duke.
     */
    public String getResponse(String command) {
        try {
            items = storage.loadFile();
        } catch (DukeException ex) {
            return ex.getMessage();
        }
        String[] input;
        String output;
        try {
            input = Parser.parseInput(command);
        } catch (DukeException ex) {
            return ex.getMessage();
        }

        switch(input[0]) {
        case "list":
            output = handleList();
            break;
        case "mark":
            assert input.length == 2 : "Unexpected case";
            output = handleMark(input);
            break;
        case "unmark":
            output = handleUnMark(input);
            break;
        case "find":
            output = handleFind(input);
            break;
        case "todo":
            output = handleToDo(input);
            break;
        case "deadline":
            output = handleDeadline(input);
            break;
        case "event":
            output = handleEvent(input);
            break;
        case "delete":
            output = handleDelete(input);
            break;
        case "bye":
            output = handleBye();
            break;
        case "lewis":
            output = handleLewis();
            break;
        case "max":
            output = handleMax();
            break;
        case "toto":
            output = handleToto();
            break;
        case "king":
            output = handleKing();
            break;
        default:
            output = handleInvalid();
        }
        return output;
    }

    private String handleInvalid() {
        return "Pardon me my lord. I'm sorry, but I don't know what that means :-(";
    }

    private String handleKing() {
        return "God save our gracious King!\n"
                + "Long live our noble King!\n"
                + "God save the King!\n"
                + "Send him victorious,\n"
                + "Happy and glorious,\n"
                + "Long to reign over us:\n"
                + "God save the King!";
    }

    private String handleLewis() {
        return "Lapped cars 4 (Norris) - 14 - 31 - 16 - 5 to overtake safety car";
    }

    private String handleMax() {
        return "MAX MAX SUPER MAX";
    }

    private String handleToto() {
        return "No Michael! No Michael, no! That was so not right!";
    }
    private String handleList() {
        return items.displayList();
    }

    private String handleMark(String[] input) {
        String output;
        int num;
        try {
            num = Integer.parseInt(input[1]);
        } catch (NumberFormatException e) {
            return "Input should be a number";
        }
        if (num > items.getSize()) {
            output = "No such task";
        } else {
            try {
                output = items.markTask(num);
                storage.saveFile(items.toStringList());
            } catch (IndexOutOfBoundsException e) {
                output = "No such task";
            }
        }
        return output;
    }

    private String handleUnMark(String[] input) {
        String output;
        int num;
        try {
            num = Integer.parseInt(input[1]);
        } catch (NumberFormatException e) {
            return "Input should be a number";
        }
        if (num > items.getSize()) {
            output = "No such task";
        } else {
            try {
                output = items.unmarkTask(num);
                storage.saveFile(items.toStringList());
            } catch (IndexOutOfBoundsException e) {
                output = "No such task";
            }
        }
        return output;
    }

    private String handleFind(String[] input) {
        return items.findTask(input[1]);
    }

    private String handleToDo(String[] input) {
        String output;
        Task t = new Todo(input[1]);
        assert !t.getDescription().equals("") : "Description cannot be empty";
        if (items.containDuplicate(t)) {
            output = "Task not added. There are duplicates in list";
        } else {
            output = items.addTask(t);
            storage.saveFile(items.toStringList());
        }
        return output;
    }

    private String handleDeadline(String[] input) {
        String output;
        try {
            Task t = new Deadline(input[1], input[2]);
            if (items.containDuplicate(t)) {
                output = "Task not added. There are duplicates in list";
            } else {
                output = items.addTask(t);
                storage.saveFile(items.toStringList());
            }
        } catch (DukeException ex) {
            return ex.getMessage();
        }
        return output;
    }

    private String handleEvent(String[] input) {
        String output;
        Task t = new Event(input[1], input[2]);
        if (items.containDuplicate(t)) {
            output = "Task not added. There are duplicates in list";
        } else {
            output = items.addTask(t);
            storage.saveFile(items.toStringList());
        }
        return output;
    }

    private String handleDelete(String[] input) {
        String output;
        try {
            output = items.deleteTask(Integer.parseInt(input[1]));
            storage.saveFile(items.toStringList());
        } catch (IndexOutOfBoundsException | NumberFormatException ex) {
            output = "Invalid index";
        }
        return output;
    }

    private String handleBye() {
        Timer t = new Timer();
        t.schedule(
                new TimerTask() {
                    @Override
                    public void run() {
                        Platform.exit();
                        t.cancel();
                    }
                },
                3000
        );
        return "Bye my lord, hope to see you again soon!";
    }
}
