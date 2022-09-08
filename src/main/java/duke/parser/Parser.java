package duke.parser;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import duke.commands.HelpCommand;
import duke.exceptions.DukeException;
import duke.tasklist.TaskList;

/**
 * Parser to handle user input.
 */
public class Parser {

    /** Scanner to take in user input. */
    private Scanner in;

    /**
     * Constructor for parser object.
     * @param in Scanner object that reads user input.
     */
    public Parser(Scanner in) {
        this.in = in;
    }

    /**
     * Handles user input and calls methods accordingly, was originally for CLI
     * version, but kept for test cases.
     *
     * @throws DukeException In the event that the command is not recognised.
     */
    public void handleInput() throws DukeException {
        while (in.hasNext()) {
            String[] temp = in.nextLine().trim().split(" ", 2);
            String[] next = new String[2];
            for (int i = 0; i < temp.length; i++) {
                next[i] = temp[i].trim().toLowerCase();
            }
            identifyCommand(next);
        }
    }

    /**
     * Handles user input and calls methods accordingly (GUI).
     *
     * @return String that represents the output from Duke.
     * @throws DukeException In the event that the command is not recognised.
     */
    public String handleGuiInput(String input) throws DukeException {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        PrintStream old = System.out;
        System.setOut(ps);

        String[] temp = input.trim().split(" ", 2);
        String[] next = new String[2];
        for (int i = 0; i < temp.length; i++) {
            next[i] = temp[i].trim().toLowerCase();
        }

        identifyCommand(next);
        System.out.flush();
        System.setOut(old);
        return baos.toString();
    }

    /**
     * Identifies command and calls appropriate functions.
     *
     * @param input User input packaged into a string array.
     */
    public void identifyCommand(String[] input) throws DukeException {
        TaskList tasklist = TaskList.getInstance();
        String command = input[0];

        switch (command) {
        case "bye":
            System.out.println("Bye. Hope to see you again soon!");
            break;
        case "list":
            tasklist.list();
            break;
        case "unmark":
            tasklist.unmark(input[1]);
            break;
        case "mark":
            tasklist.mark(input[1]);
            break;
        case "todo":
        case "deadline":
        case "event":
            tasklist.addTask(input);
            break;
        case "delete":
            tasklist.delete(input[1]);
            break;
        case "find":
            tasklist.findWithFilter(input[1]);
            break;
        case "help":
            HelpCommand help = new HelpCommand();
            help.executeCommand();
            break;
        default:
            throw new DukeException("Invalid command");
        }
    }
}

