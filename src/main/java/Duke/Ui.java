package Duke;

import java.util.ArrayList;
import java.util.Scanner;

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

        if (input[0].equals("list")) {
            // when user request list
            output = items.displayList();
        } else {
            switch(input[0]) {
                case "mark":
                    // when user wants to mark as done
                    int num = Integer.parseInt(input[1]);
                    if (num > items.getSize()) {
                        return "No such task";
                    } else {
                        output = items.markTask(num);
                    }
                    break;

                case "unmark":
                    // when user wants to mark as not done
                    int numb = Integer.parseInt(input[1]);
                    if (numb > items.getSize()) {
                        return "No such task";
                    } else {
                        output = items.unmarkTask(numb);
                    }
                    break;

                case "find":
                    output = items.findTask(input[1]);
                    break;

                case "todo":
                    // when user wants to add todo task
                    Task t1 = new Todo(input[1]);
                    output = items.addTask(t1);
                    break;

                case "deadline":
                    // when user wants to add deadline task
                    try {
                        Task t2 = new Deadline(input[1], input[2]);
                        output = items.addTask(t2);
                    } catch (DukeException ex) {
                        return ex.getMessage();
                    }
                    break;

                case "event":
                    // when user wants to add event task
                    Task t3 = new Event(input[1], input[2]);
                    output = items.addTask(t3);
                    break;

                case "delete":
                    // when user wants to delete task
                    try {
                        output = items.deleteTask(Integer.parseInt(input[1]));
                    } catch (IndexOutOfBoundsException ex) {
                        return "Invalid index";
                    }
                    break;

                case "bye":
                    return "Bye. Hope to see you again soon!";

                default:
                    return "No such command";
            }
        }
        storage.saveFile(items.toStringList());
        return output;
    }
}